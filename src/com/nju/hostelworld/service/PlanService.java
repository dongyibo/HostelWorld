package com.nju.hostelworld.service;

import com.nju.hostelworld.model.Plan;
import com.nju.hostelworld.model.Reservation;
import com.nju.hostelworld.model.Trade;
import com.nju.hostelworld.model.VO.ReservedPlan;
import com.nju.hostelworld.util.Result;

import java.util.List;
import java.util.Map;

/**
 * Created by dongyibo on 2017/1/9.
 */
public interface PlanService {

    /**
     * 发布计划
     *
     * @param plan
     */
    public boolean releasePlan(Plan plan);

    /**
     * 获取所有的计划
     *
     * @param hid
     * @return
     */
    public List<Plan> getAllPlan(String hid);

    /**
     * 获取所有计划且包含预定属性
     *
     * @param hid
     * @return
     */
    public List<ReservedPlan> getAllPlanWithReserved(String hid, List<Plan> plans);

    /**
     * 删除计划
     *
     * @param pid
     */
    public void deletePlan(int pid);

    /**
     * 获取用户所有未生效的预定
     *
     * @param hid
     * @param vid
     * @return
     */
    public List<Reservation> getAllReservationNotUse(String hid, String vid);

    /**
     * 装备预定的计划
     *
     * @param reservations
     */
    public void addPlanForReservation(List<Reservation> reservations);

    /**
     * 登记入店
     *
     * @param trade
     * @return
     */
    public Result registerTrade(Trade trade);

    /**
     * 获取所有可以销售的计划
     *
     * @param hid
     * @return
     */
    public List<Plan> getAllPlanCanOrder(String hid);

    /**
     * 获取所有销售
     *
     * @param hid
     * @return
     */
    public List<Trade> getAllTrade(String hid);

    /**
     * 为销售装载计划
     *
     * @param trades
     */
    public void addPlanForTrade(List<Trade> trades);

    /**
     * 为预定生成订单
     *
     * @param reid
     * @return
     */
    public Result registerTradeForReservation(int reid);

    /**
     * 更新销售状态
     *
     * @param tid
     * @param state
     */
    public void updateTradeState(int tid, String state);

    /**
     * 获取会员所有的预定
     *
     * @param vid
     * @return
     */
    public List<Reservation> getAllVIPReservation(String vid);

    /**
     * 为会员预定装载计划
     *
     * @param reservations
     */
    public void addPlanForVIPReservation(List<Reservation> reservations);

    /**
     * 取消预定
     *
     * @param reid
     */
    public void cancelReservation(int reid);

    /**
     * 记录会员预订
     *
     * @param reservation
     * @return
     */
    public Result registerReservation(Reservation reservation);

    /**
     * 获取客栈里所有未支付的交易
     *
     * @param hid
     * @return
     */
    public List<Trade> getAllUnPaidTrade(String hid);

    /**
     * 为单次支付卡支付
     * @param tid
     * @param hid
     */
    public void payTradeForVIPWay(int tid, String hid);

    /**
     * 获取所有的会员消费
     * @param vid
     * @return
     */
    public List<Trade> getAllTradeForVIP(String vid);

    /**
     * 为会员消费装载计划
     * @param trades
     */
    public void addPlanForVIPTrade(List<Trade> trades);

    /**
     * 获取客栈所有的预订
     * @param hid
     * @return
     */
    public List<Reservation> getAllReservationForHostel(String hid);

    /**
     * 为预订装载用户
     * @param reservations
     */
    public void addUserForReservation(List<Reservation> reservations);

    /**
     * 获取客栈按月的收入
     * @param hid
     * @return
     */
    public Map<String, Double> getTradeDetailByMonth(String hid);

    /**
     * 得到每月的消费明细
     * @param hid
     * @param time
     * @return
     */
    public List<Trade> getAllTradePerMonth(String hid, String time);

    /**
     * 为消费装载用户
     * @param trades
     */
    public void addUserForTrade(List<Trade> trades);

    /**
     * 获取所有入住的交易
     * @param hid
     * @return
     */
    public List<Trade> getAllTradeCheckIn(String hid);
}
