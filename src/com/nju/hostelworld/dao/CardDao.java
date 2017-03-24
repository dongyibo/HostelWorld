package com.nju.hostelworld.dao;

import com.nju.hostelworld.model.BankCard;
import com.nju.hostelworld.model.VIP;
import com.nju.hostelworld.model.VIPCard;

/**
 * Created by dongyibo on 2017/1/9.
 */
public interface CardDao {

    /**
     * 卡类服务注册
     * @param vip
     */
    public void cardReg(VIP vip);

    /**
     * 根据会员id获取会员卡
     * @param vid
     * @return
     */
    public VIPCard getVIPCard(String vid);

    /**
     * 更新会员卡
     * @param vipCard
     */
    public void updateVIPCard(VIPCard vipCard);

    /**
     * 会员卡充值钱
     * @param vcid
     * @param money
     */
    public void addDespoit(int vcid, double money);

    /**
     * 保存会员卡积分
     * @param vcid
     * @param point
     */
    public void savePoint(int vcid, int point);

    /**
     * 根据id获取会员卡
     * @param vcid
     * @return
     */
    public VIPCard getVIPCardById(int vcid);

    /**
     * 获取银行卡
     * @param vid
     * @return
     */
    public BankCard getBankCard(String vid);

    /**
     * 保存银行卡
     * @param bankCard
     */
    public void updateBankCard(BankCard bankCard);

}
