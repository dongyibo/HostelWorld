package com.nju.hostelworld.action;

import com.nju.hostelworld.model.*;
import com.nju.hostelworld.service.LoginService;
import com.nju.hostelworld.service.PlanService;
import com.nju.hostelworld.service.UserService;
import com.nju.hostelworld.util.Result;
import com.nju.hostelworld.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by dongyibo on 2017/1/10.
 */
@Controller
public class RegisterAction extends BaseAction {

    @Autowired
    private LoginService loginService;

    @Autowired
    private PlanService planService;

    @Autowired
    private UserService userService;

    private InputStream inputStream;

    /**
     * 转向登记页面，显示计划选项
     *
     * @return
     */
    public String show() {
        Hostel hostel = (Hostel) this.session.get("hostel");
        String hid = hostel.getHid();
        List<Plan> plans = this.planService.getAllPlanCanOrder(hid);
//        System.err.println(plans);
        this.session.put("showPlans", plans);
        // 取出客栈所有的登记
        List<Trade> trades = this.planService.getAllTrade(hid);
        //装载计划
        this.planService.addPlanForTrade(trades);
        this.session.put("trades", trades);
        return "show";
    }

    /**
     * 验证vip
     *
     * @return
     */
    public String validateVIP() {
        String vid = this.request.getParameter("vid");
        String pwd = this.request.getParameter("password");
        String hid = this.request.getParameter("hid");
        Role role = this.loginService.checkUserLogin(vid, pwd);
        //验证失败
        if (role != Role.VIP) {
            request.setAttribute("fail", "validateFail");
            return "fail";
        }
        //验证成功，取出会员信息
        VIP vip = this.userService.getVIP(vid);
        this.request.setAttribute("loginVIP", vip);
        //取出该vip的预约
        List<Reservation> reservations = this.planService.getAllReservationNotUse(hid, vid);
        //装载计划
        this.planService.addPlanForReservation(reservations);
        this.request.setAttribute("reservation", reservations);
        List<Plan> plans = this.planService.getAllPlanCanOrder(hid);
        this.session.put("showPlans", plans);
        return "vip";
    }

    /**
     * 用户登记
     *
     * @return
     */
    public String register() {
        //获取记录
        String hid = String.format("%07d", Integer.parseInt(this.request.getParameter("hid")));
        String isVIP = this.request.getParameter("isVIP");
        String uid = this.request.getParameter("uid");
        String name = this.request.getParameter("name");
        String IdCardNo = this.request.getParameter("IdCardNo");
        String gender = this.request.getParameter("gender");
        String pid = this.request.getParameter("roomNo");
        String days = this.request.getParameter("days");
        String addition = this.request.getParameter("addition");
        User user = new User();
        String way = "";
        if (isVIP.equals("1")) {
            user.setUid(Integer.parseInt(uid));
            user.setIsVIP("true");
            way = this.request.getParameter("way");
        } else {
            user.setUname(name);
            user.setGender(gender);
            user.setIdCardNumber(IdCardNo);
            user.setIsVIP("false");
            way = "非会员卡";
        }
        Plan plan = new Plan();
        plan.setPid(Integer.parseInt(pid));
        Hostel hostel = new Hostel();
        hostel.setHid(hid);
        plan.setHostel(hostel);
        Trade trade = new Trade(way, addition, "入店", Integer.parseInt(days), user, plan);
//        System.out.println(hid);
        //登记
        Result result = this.planService.registerTrade(trade);
        if (result != Result.True) {
            this.request.setAttribute("regFail", result.toString());
            return "regFail";
        }
        return "register";
    }

    /**
     * 为会员的订单生成消费单
     * @return
     */
    public String registerForReservation(){
        String reid = this.request.getParameter("reid");
        //生成订单
        Result result = this.planService.registerTradeForReservation(Integer.parseInt(reid));
        System.err.println(result);
        if (result != Result.True){
            this.request.setAttribute("regFail", result.toString());
            return "regFail";
        }
        return "vipRe";
    }

    /**
     * 更新入住状态
     * @return
     */
    public String updateState(){
        int tid = Integer.parseInt(this.request.getParameter("tid"));
        String state = this.request.getParameter("state");
        this.planService.updateTradeState(tid, state);
//        System.err.println(state);
        try {
            this.inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "ajax";
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
