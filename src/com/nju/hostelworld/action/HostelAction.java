package com.nju.hostelworld.action;

import com.nju.hostelworld.model.Hostel;
import com.nju.hostelworld.model.Plan;
import com.nju.hostelworld.model.Reservation;
import com.nju.hostelworld.model.Trade;
import com.nju.hostelworld.model.VO.ReservedPlan;
import com.nju.hostelworld.service.HostelService;
import com.nju.hostelworld.service.LoginService;
import com.nju.hostelworld.service.PlanService;
import com.nju.hostelworld.util.Role;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by dongyibo on 2017/1/8.
 */
@Controller
public class HostelAction extends BaseAction implements ModelDriven<Hostel> {

    @Autowired
    private LoginService loginService;

    @Autowired
    private HostelService hostelService;

    @Autowired
    private PlanService planService;

    private InputStream inputStream;

    private Hostel hostel;


    /**
     * 客栈登录
     *
     * @return
     */
    public String login() {
        Role role = this.loginService.checkHostelLogin(this.hostel.getHid(), this.hostel.getPassword());
        //登录失败
        if (role == Role.Fail) {
            this.request.setAttribute("login", "fail");
        }
        //登录成功
        else {
            this.hostel = this.hostelService.getHostel(this.hostel.getHid());
            this.session.put("hostel", this.hostel);
            //读取所有计划
            List<Plan> plans = this.planService.getAllPlan(this.hostel.getHid());
//            this.session.put("plans", plans);
            List<ReservedPlan> reservedPlans = this.planService.getAllPlanWithReserved(this.hostel.getHid(), plans);
            this.session.put("plans", reservedPlans);
            this.session.put("loginFlag", "hostel");
        }
        return role.toString();
    }

    /**
     * 客栈注册
     *
     * @return
     */
    public String reg() {
        String hid = this.loginService.hostelReg(this.hostel);
        this.session.put("hid", hid);
        return "reg";
    }

    /**
     * 修改客栈电话
     *
     * @return
     */
    public String updatePhone() {
        String hid = String.format("%07d", Integer.parseInt(this.request.getParameter("hid")));
        String phone = this.request.getParameter("phone");
        this.hostelService.updatePhone(hid, phone);
        try {
            this.inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "ajax";
    }

    /**
     * 显示客栈信息
     *
     * @return
     */
    public String showData() {
        return "showData";
    }

    /**
     * 客栈修改密码
     *
     * @return
     */
    public String updatePassword() {
        String hid = this.hostel.getHid();
        String oldPWD = this.request.getParameter("oldPWD");
        String newPWD = this.request.getParameter("newPWD");
        boolean success = this.hostelService.updatePassword(hid, oldPWD, newPWD);
        if (!success) {
            this.request.setAttribute("fail", "different");
        } else {
            this.hostel = this.hostelService.getHostel(hid);
            session.put("hostel", this.hostel);
        }
        return "password";
    }

    public String showAllData() {
        Hostel hostel = (Hostel) this.session.get("hostel");
        List<Reservation> reservations = this.planService.getAllReservationForHostel(hostel.getHid());
        //装载计划
        this.planService.addPlanForReservation(reservations);
        //装载用户
        this.planService.addUserForReservation(reservations);
        this.session.put("allVIPReservations", reservations);

        this.session.remove("allTrades");

        return "showAllData";
    }

    /**
     * 获取月收入明细
     * @return
     */
    public String monthDetail(){
        String hid = String.format("%07d", Integer.parseInt(this.request.getParameter("hid")));
        String time = this.request.getParameter("time");
//        System.out.println(hid+ " "+time);
        List<Trade> trades = this.planService.getAllTradePerMonth(hid, time);
        //装载计划
        this.planService.addPlanForTrade(trades);
        //装载用户
        this.planService.addUserForTrade(trades);
        this.session.put("allTrades",trades);
        return "monthDetail";
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
    public Hostel getModel() {
        this.hostel = new Hostel();
        return this.hostel;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

}
