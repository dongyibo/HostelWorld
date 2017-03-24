package com.nju.hostelworld.dao;

import com.nju.hostelworld.model.Hostel;
import com.nju.hostelworld.model.HostelCache;

import java.util.List;

/**
 * Created by dongyibo on 2017/1/8.
 */
public interface HostelDao {

    /**
     * 检查客栈登录
     * @param id
     * @param password
     * @return
     */
    public boolean checkHostelLogin(String id, String password);

    /**
     * 为注册的客栈分配id
     * @return
     */
    public String getNewHid();

    /**
     * 客栈注册
     * @param hostel
     */
    public void hostelReg(Hostel hostel);

    /**
     * 获取客栈实体
     * @param id
     * @return
     */
    public Hostel getHostel(String hid);

    /**
     * 获取所有审批通过的客栈
     * @return
     */
    public List<Hostel> getAllNormalHostel();

    /**
     * 增加客栈收入
     * @param hid
     * @param fee
     */
    public void addHostelIcome(String hid, double fee);

    /**
     * 更新客栈电话
     * @param hid
     * @param phone
     */
    public void updatePhone(String hid, String phone);

    /**
     * 更新客栈
     * @param hostel
     */
    public void updateHostel(Hostel hostel);

    /**
     * 获取所有未审批的宾馆
     * @return
     */
    public List<Hostel> getAllHostelWithoutApproval();

    /**
     * 获取所有已审批的客栈
     * @return
     */
    public List<Hostel> getAllHostelWithApproval();

    /**
     * 获取客栈信息修改缓存
     * @param hid
     * @return
     */
    public HostelCache getHostelCache(String hid);

    /**
     * 删除客栈信息缓存
     * @param hostelCache
     */
    public void deleteHostelCache(HostelCache hostelCache);

    /**
     * 插入客栈信息缓存
     * @param hostelCache
     */
    public void saveHostelCache(HostelCache hostelCache);

    /**
     * 获取所有客栈信息缓存
     * @return
     */
    public List<HostelCache> getAllHostelCaches();

    /**
     * 获取所有含有未支付的会员卡消费的客栈
     * @return
     */
    public List<Hostel> getAllVIPTradeHostel();

}
