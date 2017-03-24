package com.nju.hostelworld.dao;

import com.nju.hostelworld.model.Manager;
import com.nju.hostelworld.model.User;
import com.nju.hostelworld.model.VIP;

import java.util.List;

/**
 * Created by dongyibo on 2017/1/4.
 */
public interface UserDao {

    /**
     * 获取总经理密码
      * @return
     */
    public String getManagerPWD();

    /**
     * 验证VIP用户登录
     * @param id
     * @param password
     * @return
     */
    public boolean checkUserLogin(String id, String password);

    /**
     * 为注册的会员分配id
     * @return
     */
    public String getNewVid();

    /**
     * 会员注册
     * @param vip
     */
    public void userReg(VIP vip);

    /**
     * 获取会员信息
     * @param vid
     * @return
     */
    public VIP getVIP(String vid);

    /**
     * 更新会员号码
     * @param vid
     * @param phone
     */
    public void updatePhoneOfVIP(String vid, String phone);

    /**
     * 更新会员
     */
    public void updateVIP(VIP vip);

    /**
     * 根据用户id取出会员实体
     * @param uid
     * @return
     */
    public VIP getVIPByUid(int uid);

    /**
     * 获取经理实体
     * @return
     */
    public Manager getManager();

    /**
     * 获取用户
     * @param uid
     * @return
     */
    public User getUser(int uid);

    /**
     * 获取所有的会员
     * @return
     */
    public List<VIP> getAllVIP();

    /**
     * 增加经理收入1000
     */
    public void addManagerIncome();
}
