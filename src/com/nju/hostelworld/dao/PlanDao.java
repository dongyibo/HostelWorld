package com.nju.hostelworld.dao;

import com.nju.hostelworld.model.Plan;
import com.nju.hostelworld.model.Reservation;
import com.nju.hostelworld.model.Trade;

import java.util.List;

/**
 * Created by dongyibo on 2017/1/9.
 */
public interface PlanDao {

    /**
     * 保存计划
     * @param plan
     */
    public void savePlan(Plan plan);

    /**
     * 获取所有的房间号
     * @return
     */
    public List<Integer> getAllRoomNumber(String hid);

    /**
     * 获取所有的计划
     * @param hid
     * @return
     */
    public List<Plan> getAllPlan(String hid);

    /**
     * 获取所有预定的计划的id
     * @param hid
     * @return
     */
    public List<Integer> getAllReservedPlan(String hid);

    /**
     * 获取所有已销售的计划的id
     * @param hid
     * @return
     */
    List<Integer> getAllTradePlan(String hid);

    /**
     * 删除计划
     * @param pid
     */
    public void deletePlan(int pid);

    /**
     * 获取某会员所有预定
     * @param hid
     * @param vid
     * @return
     */
    public List<Reservation> getAllReservationNotUse(String hid, String vid);

    /**
     * 获取单个计划
     * @param pid
     * @return
     */
    public Plan getPlan(int pid);

    /**
     * 记录会员消费
     * @param trade
     */
    public void saveVIPTrade(Trade trade);

    /**
     * 记录普通用户消费
     * @param trade
     */
    public void saveNormalTrade(Trade trade);

    /**
     * 获取计划的所有预定
     * @param pid
     * @return
     */
    public List<Reservation> getAllReservationForPlan(int pid);

    /**
     * 获取计划的所有预定
     * @param pid
     * @return
     */
    public List<Reservation> getAllReservationForPlanFuture(int pid);

    /**
     * 取出未生效的预定
     */
    public List<Reservation> getAllReservationNotUse(int pid);

    /**
     * 获取计划的所有的销售
     * @param pid
     * @return
     */
    public List<Trade> getAllTradeForPlan(int pid);

    /**
     * 获取所有的销售
     * @param hid
     * @return
     */
    public List<Trade> getAllTrade(String hid);

    /**
     * 获取订单
     * @param reid
     * @return
     */
    public Reservation getReservation(int reid);

    /**
     * 更新预定单
     * @param reservation
     */
    public void updateReservation(Reservation reservation);

    /**
     * 更新销售状态
     * @param tid
     * @param state
     */
    public void updateTradeState(int tid, String state);

    /**
     * 获取会员全部的预定
     * @param vid
     * @return
     */
    public List<Reservation> getAllVIPReservation(String vid);

    /**
     * 获取带有客栈信息的计划
     * @param pid
     * @return
     */
    public Plan getPlanWithHotel(int pid);

    /**
     * 删除预定
     * @param reid
     */
    public void deleteReservation(int reid);

    /**
     * 保存会员预定记录
     * @param reservation
     */
    public void saveReservation(Reservation reservation);

    /**
     * 获取客栈内全部未支付交易
     * @param hid
     * @return
     */
    public List<Trade> getAllUnPaidTrade(String hid);

    /**
     * 获取销售
     * @param tid
     * @return
     */
    public Trade getTrade(int tid);

    /**
     * 更新销售状态
     * @param trade
     */
    public void updateTrade(Trade trade);

    /**
     * 获取所有的会员消费
     * @param vid
     * @return
     */
    public List<Trade> getAllTradeForVIP(String vid);

    /**
     * 获取客栈所有的预定
     * @param hid
     * @return
     */
    public List<Reservation> getAllReservationForHostel(String hid);

    /**
     * 获取客栈已付款的交易
     * @param hid
     * @return
     */
    public List<Trade> getAllPaidTrade(String hid);

    /**
     * 获取所有入住的交易
     * @param hid
     * @return
     */
    public List<Trade> getAllTradeCheckIn(String hid);
}
