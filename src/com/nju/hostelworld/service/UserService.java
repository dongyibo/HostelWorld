package com.nju.hostelworld.service;

import com.nju.hostelworld.model.Manager;
import com.nju.hostelworld.model.VIP;
import com.nju.hostelworld.model.VIPCard;
import com.nju.hostelworld.util.Result;

import java.util.List;

/**
 * Created by dongyibo on 2017/1/4.
 */
public interface UserService {

    /**
     * 获取会员信息
     * @param vid
     * @return
     */
    public VIP getVIP(String vid);

    /**
     * 修改会员电话号码
     * @param vid
     * @param phone
     */
    public void updatePhoneOfVIP(String vid, String phone);

    /**
     * 会员更新密码
     * @param vid
     * @param oldPWD
     * @param newPWD
     * @return
     */
    public boolean updatePassword(String vid, String oldPWD, String newPWD);

    /**
     * 获取会员的会员卡
     * @param vip
     * @return
     */
    public VIPCard getVIPCard(VIP vip);

    /**
     * 积分兑换现金
     * @param vcid
     * @return
     */
    public int convertPoint(int vcid);

    /**
     * 充值
     * @param vcid
     * @param money
     * @return
     */
    public double recharge(int vcid, double money);

    /**
     * 获取经理实体
     * @return
     */
    public Manager getManager();

    /**
     * 获取所有的vip
     * @return
     */
    public List<VIP> getAllVIP();

    /**
     * 续费
     * @param vcid
     * @return
     */
    public Result renewal(int vcid);

    /**
     * 废弃会员资格
     * @param vid
     */
    public void abandon(String vid);
}
