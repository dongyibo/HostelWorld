package com.nju.hostelworld.service;

import com.nju.hostelworld.model.Hostel;
import com.nju.hostelworld.model.HostelCache;

import java.util.List;

/**
 * Created by dongyibo on 2017/1/9.
 */
public interface HostelService {

    /**
     * 获取指定的客栈实体
     * @param hid
     * @return
     */
    public Hostel getHostel(String hid);

    /**
     * 获取所有已审批通过的客栈
     * @return
     */
    public List<Hostel> getAllNormalHostel();

    /**
     * 更新客栈的电话号码
     * @param hid
     * @param phone
     */
    public void updatePhone(String hid, String phone);

    /**
     * 客栈修改密码
     * @param hid
     * @param oldPWD
     * @param newPWD
     * @return
     */
    public boolean updatePassword(String hid, String oldPWD, String newPWD);

    /**
     * 获取所有未审批的宾馆
     * @return
     */
    public List<Hostel> getAllHostelWithoutApproval();

    /**
     * 审批客栈
     * @param hid
     */
    public void approveHostel(String hid);

    /**
     * 取出所有的修改申请
     * @return
     */
    public List<HostelCache> getAllHostelCaches();

    /**
     * 审批客栈修改
     * @param hid
     * @param phone
     */
    public void approveHostelChange(String hid, String phone);

    /**
     * 获取所有含会员卡
     * @return
     */
    public List<Hostel> getAllVIPTradeHostel();

    /**
     * 为某客栈一次性全部结算
     * @param hid
     */
    public void allPayForHostel(String hid);

    /**
     * 获取所有已审批的客栈
     * @return
     */
    public List<Hostel> getAllHostelWithApproval();

    /**
     * 获取收入总额
     * @return
     */
    public Double getTotalIncome();
}
