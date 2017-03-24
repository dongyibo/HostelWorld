package com.nju.hostelworld.action;

import com.nju.hostelworld.model.*;
import com.nju.hostelworld.service.HostelService;
import com.nju.hostelworld.service.PlanService;
import com.nju.hostelworld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongyibo on 2017/1/16.
 */
@Controller
public class ManagerAction extends BaseAction {

    @Autowired
    private HostelService hostelService;

    @Autowired
    private PlanService planService;

    @Autowired
    private UserService userService;

    private ArrayList<String> hostelName;

    private ArrayList<String> hostelIncome;

    /**
     * 显示所有审批的客栈
     *
     * @return
     */
    public String showApproval() {
        List<Hostel> hostels = this.hostelService.getAllHostelWithoutApproval();
        this.session.put("hostelListsWithoutApproval", hostels);

        List<HostelCache> hostelCaches = this.hostelService.getAllHostelCaches();
        this.session.put("hostelCaches", hostelCaches);
        return "showApproval";
    }

    /**
     * 审批客栈注册
     *
     * @return
     */
    public String approve() {
        String hid = String.format("%07d", Integer.parseInt(this.request.getParameter("hid")));
        this.hostelService.approveHostel(hid);
        return "approve";
    }

    /**
     * 审批修改
     *
     * @return
     */
    public String approveChange() {
        String hid = String.format("%07d", Integer.parseInt(this.request.getParameter("hid")));
        String phone = this.request.getParameter("phone");
        this.hostelService.approveHostelChange(hid, phone);
        return "approveChange";
    }

    /**
     * 显示未结账客栈
     *
     * @return
     */
    public String showAccount() {
        List<Hostel> hostels = this.hostelService.getAllVIPTradeHostel();
        this.session.put("accountHostels", hostels);
        this.session.remove("unPaidTrades");
        return "showAccount";
    }

    /**
     * 选择客栈结算
     *
     * @return
     */
    public String settle() {
        String hid = String.format("%07d", Integer.parseInt(this.request.getParameter("hid")));
        List<Trade> trades = this.planService.getAllUnPaidTrade(hid);
        this.planService.addPlanForTrade(trades);
        this.session.put("unPaidTrades", trades);
        return "settle";
    }

    /**
     * 支付单次销售
     *
     * @return
     */
    public String pay() {
        String hid = String.format("%07d", Integer.parseInt(this.request.getParameter("hid")));
        int tid = Integer.parseInt(this.request.getParameter("tid"));
        this.planService.payTradeForVIPWay(tid, hid);

        List<Trade> trades = this.planService.getAllUnPaidTrade(hid);
        this.planService.addPlanForTrade(trades);
        this.session.put("unPaidTrades", trades);
        return "pay";
    }

    /**
     * 为某客栈一次性全部结算
     *
     * @return
     */
    public String allPay() {
        String hid = String.format("%07d", Integer.parseInt(this.request.getParameter("hid")));
        this.hostelService.allPayForHostel(hid);
        return "allPay";
    }

    /**
     * 展示信息
     *
     * @return
     */
    public String showHostel() {
        List<Hostel> hostels = this.hostelService.getAllHostelWithApproval();
        this.request.setAttribute("hostelData", hostels);
        return "showHostel";
    }

    /**
     * 查看客栈入住情况
     *
     * @return
     */
    public String viewHostelData() {
        String hid = String.format("%07d", Integer.parseInt(this.request.getParameter("hid")));
        List<Trade> trades = this.planService.getAllTradeCheckIn(hid);
        //装载计划
        this.planService.addPlanForTrade(trades);
        //装载用户
        this.planService.addUserForTrade(trades);
        this.request.setAttribute("hostelTrades", trades);
        return "viewHostelData";
    }

    /**
     * 显示所有VIP
     *
     * @return
     */
    public String showVIP() {
        List<VIP> vips = this.userService.getAllVIP();
        this.request.setAttribute("vips", vips);
        return "showVIP";
    }

    /**
     * 显示会员信息细节
     *
     * @return
     */
    public String viewVIPData() {
        String vid = String.format("%07d", Integer.parseInt(this.request.getParameter("vid")));
        List<Reservation> reservations = this.planService.getAllVIPReservation(vid);
        this.planService.addPlanForVIPReservation(reservations);
        this.request.setAttribute("vipReservations", reservations);

        List<Trade> trades = this.planService.getAllTradeForVIP(vid);
        this.planService.addPlanForVIPTrade(trades);
        this.request.setAttribute("vipTrades", trades);

        return "viewVIPData";
    }

    /**
     * 注销
     * @return
     */
    public String logout(){
        this.session.clear();
        return "logout";
    }

    /**
     * 显示财务信息
     *
     * @return
     */
    public String showFinance() {
        Double income = this.hostelService.getTotalIncome();
        this.request.setAttribute("finance", income);
        return "showFinance";
    }

    public String getIncome() {
        this.hostelName = new ArrayList<>();
        this.hostelIncome = new ArrayList<>();
        List<Hostel> hostels = this.hostelService.getAllHostelWithApproval();
        for (Hostel hostel:hostels){
            this.hostelName.add(hostel.getHname());
            this.hostelIncome.add(hostel.getIncome()+"");
        }
        return "ajax";
    }

    public ArrayList<String> getHostelName() {
        return hostelName;
    }

    public ArrayList<String> getHostelIncome() {
        return hostelIncome;
    }

}
