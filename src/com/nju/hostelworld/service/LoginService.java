package com.nju.hostelworld.service;

import com.nju.hostelworld.model.Hostel;
import com.nju.hostelworld.model.VIP;
import com.nju.hostelworld.util.Role;

/**
 * Created by dongyibo on 2017/1/8.
 */
public interface LoginService {

    /**
     * 检查用户登录
     * @param id
     * @param password
     * @return
     */
    public Role checkUserLogin(String id, String password);

    /**
     * 检查客栈登录
     * @param id
     * @param password
     * @return
     */
    public Role checkHostelLogin(String id, String password);

    /**
     * 会员注册
     * @param vip
     */
    public String userReg(VIP vip);

    /**
     * 客栈注册
     * @param hostel
     */
    public String hostelReg(Hostel hostel);
}
