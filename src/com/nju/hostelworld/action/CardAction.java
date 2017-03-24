package com.nju.hostelworld.action;

import com.nju.hostelworld.model.VIP;
import com.nju.hostelworld.model.VIPCard;
import com.nju.hostelworld.service.UserService;
import com.nju.hostelworld.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by dongyibo on 2017/1/14.
 */
@Controller
public class CardAction extends BaseAction {

    @Autowired
    private UserService userService;

    private InputStream inputStream;

    /**
     * 显示会员卡界面
     *
     * @return
     */
    public String showCard() {
        VIP vip = (VIP) this.session.get("vip");
        vip = this.userService.getVIP(vip.getVid());
        this.session.put("vip", vip);
        VIPCard vipCard = this.userService.getVIPCard(vip);
//        System.out.println(vipCard  );
        this.session.put("vipCard", vipCard);
        return "showCard";
    }

    /**
     * 积分兑换
     * @return
     */
    public String convert() {
        int vcid = Integer.parseInt(this.request.getParameter("vcid"));
        int k = this.userService.convertPoint(vcid);
        try {
            this.inputStream = new ByteArrayInputStream((k + "").getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "ajax";
    }

    /**
     * 充值
     * @return
     */
    public String recharge(){
        int vcid = Integer.parseInt(this.request.getParameter("vcid"));
        double money = Double.parseDouble(this.request.getParameter("money"));
        double deposit = this.userService.recharge(vcid, money);
        try {
            this.inputStream = new ByteArrayInputStream((deposit + "").getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "ajax";
    }

    /**
     * 续费一年
     * @return
     */
    public String renewal(){
        int vcid = Integer.parseInt(this.request.getParameter("vcid"));
        Result result = this.userService.renewal(vcid);
        if (result == Result.InsufficientBalance){
            this.request.setAttribute("renewalFail", result.toString());
            return "renewalFail";
        }
        return "renewal";
    }

    /**
     * 废弃会员资格
     * @return
     */
    public String abandon(){
        String vid = String.format("%07d", Integer.parseInt(this.request.getParameter("vid")));
        this.userService.abandon(vid);
        this.session.clear();
        return "abandon";
    }

    public InputStream getInputStream() {
        return inputStream;
    }


}
