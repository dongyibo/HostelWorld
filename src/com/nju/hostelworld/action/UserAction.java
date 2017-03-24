package com.nju.hostelworld.action;

import com.nju.hostelworld.model.*;
import com.nju.hostelworld.service.HostelService;
import com.nju.hostelworld.service.LoginService;
import com.nju.hostelworld.service.PlanService;
import com.nju.hostelworld.service.UserService;
import com.nju.hostelworld.util.MD5;
import com.nju.hostelworld.util.Result;
import com.nju.hostelworld.util.Role;
import com.nju.hostelworld.util.Time;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * Created by dongyibo on 2017/1/4.
 */

@Controller
public class UserAction extends BaseAction implements ModelDriven<VIP> {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @Autowired
    private PlanService planService;

    @Autowired
    private HostelService hostelService;

    private VIP vip;

    private InputStream inputStream;


    /**
     * 用户登录
     *
     * @return
     */
    public String login() {
        Role role = this.loginService.checkUserLogin(this.vip.getVid(), this.vip.getPassword());
        //登录失败
        if (role == Role.Fail) {
            this.request.setAttribute("login", "fail");
        }
        //成功
        else {
            if (role == Role.VIP) {
                //获取会员实体
                this.vip = this.userService.getVIP(this.vip.getVid());
                this.session.put("vip", this.vip);
                this.session.put("loginFlag", "vip");
            } else if (role == Role.Manager) {
                Manager manager = this.userService.getManager();
                this.session.put("manager", manager);
                //取出所有未审批的宾馆
                List<Hostel> hostels = this.hostelService.getAllHostelWithoutApproval();
                this.session.put("hostelListsWithoutApproval", hostels);
                // 取出所有修改申请列表
                List<HostelCache> hostelCaches = this.hostelService.getAllHostelCaches();
                this.session.put("hostelCaches", hostelCaches);
                this.session.put("loginFlag", "manager");
            }
//            this.session.put("loginFlag", "loginFlag");
        }
        return role.toString();
    }

    /**
     * 用户注册
     *
     * @return
     */
    public String reg() {
        String name = this.request.getParameter("name");
        String password = this.request.getParameter("password");
        String phone = this.request.getParameter("phone");
        String idCardNo = this.request.getParameter("idCardNo");
        String gender = this.request.getParameter("gender");

        User user = new User(name, gender, idCardNo);
        user.setIsVIP("true");
        VIP vip = new VIP(MD5.getMd5(password), phone, user);
        String vid = this.loginService.userReg(vip);
        //获取实体
        this.vip = this.userService.getVIP(vid);
        //存入session
        this.session.put("vip", this.vip);
        this.session.put("loginFlag", "vip");
        return "reg";
    }

    /**
     * 用户修改手机号码
     *
     * @return
     */
    public String updatePhone() {
        String vid = String.format("%07d", Integer.parseInt(this.request.getParameter("vid")));
        String phone = this.request.getParameter("phone");
        this.userService.updatePhoneOfVIP(vid, phone);
        this.vip = this.userService.getVIP(vid);
        this.session.put("vip", this.vip);
        try {
            this.inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "ajax";
    }

    /**
     * 会员修改密码
     *
     * @return
     */
    public String updatePassword() {
        String vid = this.vip.getVid();
        String oldPWD = this.request.getParameter("oldPWD");
        String newPWD = this.request.getParameter("newPWD");
        boolean success = this.userService.updatePassword(vid, oldPWD, newPWD);
        if (!success) {
            this.request.setAttribute("fail", "different");
        } else {
            this.vip = this.userService.getVIP(vid);
            session.put("vip", this.vip);
        }
        return "password";
    }

    /**
     * 显示会员的预定
     *
     * @return
     */
    public String showReservation() {
        VIP vip = (VIP) this.session.get("vip");
        List<Reservation> reservations = this.planService.getAllVIPReservation(vip.getVid());
        //装载会员计划
        this.planService.addPlanForVIPReservation(reservations);
        this.session.put("VIPReservations", reservations);
        //显示所有的以审批的hostel
        List<Hostel> hostels = this.hostelService.getAllNormalHostel();
        this.session.put("hostels", hostels);
        //把客栈对应的session删除
        this.session.remove("hostelPlans");

        return "showReservation";
    }

    /**
     * 会员取消预定
     *
     * @return
     */
    public String cancelReservation() {
        int reid = Integer.parseInt(this.request.getParameter("reid"));
        this.planService.cancelReservation(reid);
        return "cancelReservation";
    }

    /**
     * 显示客栈对应所有计划
     *
     * @return
     */
    public String showPlan() {
        String hid = String.format("%07d", Integer.parseInt(this.request.getParameter("hid")));
        List<Plan> plans = this.planService.getAllPlan(hid);
        this.session.put("hostelPlans", plans);
        return "showPlan";
    }

    /**
     * 会员预定
     *
     * @return
     */
    public String book() {
        int pid = Integer.parseInt(this.request.getParameter("pid"));
        Plan plan = new Plan();
        plan.setPid(pid);
        int year = Integer.parseInt(this.request.getParameter("year"));
        int month = Integer.parseInt(this.request.getParameter("month"));
        int day = Integer.parseInt(this.request.getParameter("day"));
        int days = Integer.parseInt(this.request.getParameter("days"));
        Date date = Time.date(year, month, day);
        VIP vip = (VIP) this.session.get("vip");
        //判断会员状态
        if (!vip.getState().equals("正常")){
            this.request.setAttribute("fail", "frozen");
            return "frozen";
        }
        Reservation reservation = new Reservation();
        reservation.setReserveTime(date);
        reservation.setDays(days);
        reservation.setPlan(plan);
        reservation.setVip(vip);
        reservation.setIsUse("false");
//        System.out.println(date+" "+days+ " "+pid+" "+ vip.getVid());
        //记录 预订
        Result result = this.planService.registerReservation(reservation);
//        System.out.println(result);
        if (result == Result.Conflict) {
            this.request.setAttribute("fail", "conflict");
            return "conflict";
        }
        return "book";
    }

    /**
     * 展示会员所有的预定信息
     * @return
     */
    public String showAllData(){
        this.vip = (VIP) this.session.get("vip");
        //得到所有预订
        List<Reservation> reservations = this.planService.getAllVIPReservation(this.vip.getVid());
        this.planService.addPlanForVIPReservation(reservations);
        this.session.put("allReservations", reservations);
        //得到所有消费
        List<Trade> trades = this.planService.getAllTradeForVIP(this.vip.getVid());
        this.planService.addPlanForVIPTrade(trades);
        this.session.put("allTrades", trades);

        return "showAllData";
    }

    /**
     * 注销
     * @return
     */
    public String logout(){
        this.session.clear();
        return "logout";
    }


    @Override
    public VIP getModel() {
        this.vip = new VIP();
        return this.vip;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
