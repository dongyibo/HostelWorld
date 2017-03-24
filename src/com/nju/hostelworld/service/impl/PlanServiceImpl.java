package com.nju.hostelworld.service.impl;

import com.nju.hostelworld.dao.CardDao;
import com.nju.hostelworld.dao.HostelDao;
import com.nju.hostelworld.dao.PlanDao;
import com.nju.hostelworld.dao.UserDao;
import com.nju.hostelworld.model.*;
import com.nju.hostelworld.model.VO.RecordDate;
import com.nju.hostelworld.model.VO.ReservedPlan;
import com.nju.hostelworld.service.PlanService;
import com.nju.hostelworld.util.CardBusiness;
import com.nju.hostelworld.util.Result;
import com.nju.hostelworld.util.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by dongyibo on 2017/1/9.
 */
@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanDao planDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private HostelDao hostelDao;

    @Autowired
    private CardDao cardDao;

    @Override
    public boolean releasePlan(Plan plan) {
        Hostel hostel = plan.getHostel();
        Room room = plan.getRoom();
        List<Integer> list = this.planDao.getAllRoomNumber(hostel.getHid());
        if (list != null) {
            for (Integer i : list) {
                if (i == room.getRoomNumber()) {
                    return false;
                }
            }
        }
        //保存
        this.planDao.savePlan(plan);
        return true;
    }

    @Override
    public List<Plan> getAllPlan(String hid) {
        List<Plan> plans = this.planDao.getAllPlan(hid);
        return plans;
    }

    @Override
    public List<ReservedPlan> getAllPlanWithReserved(String hid, List<Plan> plans) {
        List<ReservedPlan> reservedPlans = new ArrayList<>();
        for (Plan plan : plans) {
            ReservedPlan reservedPlan = new ReservedPlan(plan, false);
            reservedPlans.add(reservedPlan);
        }

        List<Integer> list1 = this.planDao.getAllReservedPlan(hid);
        List<Integer> list2 = this.planDao.getAllTradePlan(hid);
        //求并集
        list2.removeAll(list1);
        list1.addAll(list2);

        for (ReservedPlan p : reservedPlans) {
            Plan plan = p.getPlan();
            for (Integer i : list1) {
                if (i == plan.getPid()) {
                    p.setReserved(true);
                    break;
                }
            }
        }
        return reservedPlans;
    }

    @Override
    public void deletePlan(int pid) {
        this.planDao.deletePlan(pid);
    }

    @Override
    public List<Reservation> getAllReservationNotUse(String hid, String vid) {
        return this.planDao.getAllReservationNotUse(hid, vid);
    }

    @Override
    public void addPlanForReservation(List<Reservation> reservations) {
        for (Reservation reservation : reservations) {
            Plan plan = this.planDao.getPlan(reservation.getPlan().getPid());
            reservation.setPlan(plan);
        }
    }

    @Override
    public Result registerTrade(Trade trade) {
        int days = trade.getDays();
        Plan plan = this.planDao.getPlan(trade.getPlan().getPid());
        double price = plan.getPrice();
        double fee = price * days;
        trade.setFee(fee);
        trade.setDate(new Date());
        //检查与预定的冲突
        List<Reservation> reservations = this.planDao.getAllReservationForPlanFuture(plan.getPid());
        int i = 0;
        for (i = 0; i < reservations.size(); i++) {
            Reservation reservation = reservations.get(i);
            Date date1 = reservation.getReserveTime();
            RecordDate recordDate1 = new RecordDate(date1, Time.getDateAfterDay(date1, reservation.getDays()));
            Date date2 = trade.getDate();
            RecordDate recordDate2 = new RecordDate(date2, Time.getDateAfterDay(date2, trade.getDays()));
            if (Time.isTimeConflict(recordDate1, recordDate2)) {
                return Result.Conflict;
            }
        }
        //无时间冲突，记录
        User user = trade.getUser();

        //非会员
        if (user.getIsVIP().equals("false")) {
            // 增加客栈的收入
            this.hostelDao.addHostelIcome(plan.getHostel().getHid(), fee);
            // 保存销售记录
            this.planDao.saveNormalTrade(trade);
        }
        //会员
        else {
            //现金支付
            if (trade.getWay().equals("非会员卡")) {
                //打九五折
                fee = fee * 0.95;
                trade.setFee(fee);
                //增加客栈的收入
                this.hostelDao.addHostelIcome(plan.getHostel().getHid(), fee);
                //保存销售记录
                this.planDao.saveVIPTrade(trade);
            }
            //会员卡支付
            else {
                return this.payAsVIPCard(user, trade);
            }

        }
        return Result.True;
    }

    @Override
    public List<Plan> getAllPlanCanOrder(String hid) {
        List<Plan> plans = this.planDao.getAllPlan(hid);
        List<Plan> showPlans = new ArrayList<>();
        for (Plan plan : plans) {
            // 生效的预定
            List<Reservation> reservations = this.planDao.getAllReservationForPlan(plan.getPid());
            List<Trade> trades = this.planDao.getAllTradeForPlan(plan.getPid());

            if (reservations.size() == 0 && trades.size() == 0) {
                showPlans.add(plan);
                continue;
            }

            int i = 0;
            for (i = 0; i < reservations.size(); i++) {
                Date date = reservations.get(i).getReserveTime();
                RecordDate recordDate1 = new RecordDate(date, Time.getDateAfterDay(date, reservations.get(i).getDays()));
                if (Time.isTimeConflict(recordDate1, new Date())) {
                    break;
                }
            }
            if (i != reservations.size()) {
                continue;
            }

            int j = 0;
            for (j = 0; j < trades.size(); j++) {
                Date date = trades.get(j).getDate();
                RecordDate recordDate2 = new RecordDate(date, Time.getDateAfterDay(date, trades.get(j).getDays()));
                if (Time.isTimeConflict(recordDate2, new Date())) {
                    break;
                }
            }
            if (j == trades.size()) {
                showPlans.add(plan);
            }
        }
        return showPlans;
    }

    @Override
    public List<Trade> getAllTrade(String hid) {
        return this.planDao.getAllTrade(hid);
    }

    @Override
    public void addPlanForTrade(List<Trade> trades) {
        for (Trade trade : trades) {
            Plan plan = this.planDao.getPlan(trade.getPlan().getPid());
            trade.setPlan(plan);
        }
    }

    @Override
    public Result registerTradeForReservation(int reid) {
        Reservation reservation = this.planDao.getReservation(reid);
        // 先判断时间
        Result result = Time.isTimeCorrect(reservation.getReserveTime());
        if (Result.True != result) {
            return result;
        }

        VIP vip = this.userDao.getVIP(reservation.getVip().getVid());
//        int uid = vip.getUser().getUid();
        User user = vip.getUser();

        int days = reservation.getDays();
        double fee = days * reservation.getPlan().getPrice();
        Trade trade = new Trade();
        trade.setFee(fee);
        trade.setWay("会员卡");
        trade.setAddition("");
        trade.setState("入店");
        trade.setDays(days);
        trade.setDate(reservation.getReserveTime());
        trade.setUser(user);
        trade.setPlan(reservation.getPlan());
        //会员卡支付
        result = this.payAsVIPCard(user, trade);
        if (result == Result.True) {
            //保存销售记录
            //this.planDao.saveVIPTrade(trade);
            //更新预订单
            reservation.setIsUse("true");
            this.planDao.updateReservation(reservation);
        }

        return result;
    }

    @Override
    public void updateTradeState(int tid, String state) {
        String s = "";
        if (state.equals("in")) {
            s = "入店";
        } else {
            s = "离店";
        }
        this.planDao.updateTradeState(tid, s);
    }

    @Override
    public List<Reservation> getAllVIPReservation(String vid) {
        return this.planDao.getAllVIPReservation(vid);
    }

    @Override
    public void addPlanForVIPReservation(List<Reservation> reservations) {
        for (Reservation reservation : reservations) {
            Plan plan = this.planDao.getPlanWithHotel(reservation.getPlan().getPid());
            reservation.setPlan(plan);
        }
    }

    @Override
    public void cancelReservation(int reid) {
        this.planDao.deleteReservation(reid);
    }

    @Override
    public Result registerReservation(Reservation reservation) {
        int pid = reservation.getPlan().getPid();
        Date date = reservation.getReserveTime();
        int days = reservation.getDays();

        List<Reservation> reservations = this.planDao.getAllReservationNotUse(pid);
        List<Trade> trades = this.planDao.getAllTradeForPlan(pid);

        for (Trade trade : trades) {
            Date tradeDate = trade.getDate();
            RecordDate recordDate = new RecordDate(tradeDate, Time.getDateAfterDay(tradeDate, trade.getDays()));
            if (Time.isTimeConflict(recordDate, date)) {
                return Result.Conflict;
            }
        }

        Date today = new Date();
        for (Reservation re : reservations) {
            Date reservedDate = re.getReserveTime();
            //无用的预约略过
            if (Time.isBefore(reservedDate, today)) {
                continue;
            } else {
                //判断冲突
                RecordDate recordDate1 = new RecordDate(date, Time.getDateAfterDay(date, days));
                RecordDate recordDate2 = new RecordDate(reservedDate, Time.getDateAfterDay(reservedDate, re.getDays()));
                if (Time.isTimeIntervalConflict(recordDate1, recordDate2)) {
                    return Result.Conflict;
                }
            }
        }
        //记录会员预定
        this.planDao.saveReservation(reservation);
        return Result.True;
    }

    @Override
    public List<Trade> getAllUnPaidTrade(String hid) {
        return this.planDao.getAllUnPaidTrade(hid);
    }

    @Override
    public void payTradeForVIPWay(int tid, String hid) {
        Trade trade = this.planDao.getTrade(tid);
        double fee = trade.getFee();
        trade.setWay("会员卡已支付");
        this.planDao.updateTrade(trade);
        // 支付
        this.hostelDao.addHostelIcome(hid, fee);
    }

    @Override
    public List<Trade> getAllTradeForVIP(String vid) {
        return this.planDao.getAllTradeForVIP(vid);
    }

    @Override
    public void addPlanForVIPTrade(List<Trade> trades) {
        for (Trade trade : trades) {
            Plan plan = this.planDao.getPlanWithHotel(trade.getPlan().getPid());
            trade.setPlan(plan);
        }
    }

    @Override
    public List<Reservation> getAllReservationForHostel(String hid) {
        return this.planDao.getAllReservationForHostel(hid);
    }

    @Override
    public void addUserForReservation(List<Reservation> reservations) {
        for (Reservation reservation : reservations) {
            VIP vip = this.userDao.getVIP(reservation.getVip().getVid());
            reservation.setVip(vip);
        }
    }

    @Override
    public Map<String, Double> getTradeDetailByMonth(String hid) {
        //获取已付款的交易
        List<Trade> trades = this.planDao.getAllPaidTrade(hid);
        Map<String, Double> map = new HashMap<>();
        for (Trade trade : trades) {
            String year_month = Time.getYearMonth(trade.getDate());
            if (map.containsKey(year_month)) {
                double income = map.get(year_month);
                income += trade.getFee();
                map.put(year_month, income);

            }
            else{
                map.put(year_month, trade.getFee());
            }
        }
        return map;
    }

    @Override
    public List<Trade> getAllTradePerMonth(String hid, String time) {
        List<Trade> trades = this.planDao.getAllPaidTrade(hid);
        List<Trade> showTrades = new ArrayList<>();
        for (Trade trade:trades){
            if (Time.getYearMonth(trade.getDate()).equals(time)){
                showTrades.add(trade);
            }
        }
        return showTrades;
    }

    @Override
    public void addUserForTrade(List<Trade> trades) {
        for(Trade trade:trades){
            User user = this.userDao.getUser(trade.getUser().getUid());
            trade.setUser(user);
        }
    }

    @Override
    public List<Trade> getAllTradeCheckIn(String hid) {
        return this.planDao.getAllTradeCheckIn(hid);
    }


    /**
     * 会员卡支付
     *
     * @return
     */
    private Result payAsVIPCard(User user, Trade trade) {
        double fee = trade.getFee();

        //取出会员（包含会员卡）实体
        VIP vip = this.userDao.getVIPByUid(user.getUid());
        VIPCard vipCard = this.cardDao.getVIPCard(vip.getVid());
        //查看会员级别
        String grade = vipCard.getGrade();
        //查看剩余金额
        double deposit = vipCard.getDeposit();
        //计算消费
        fee = fee * CardBusiness.getDiscount(grade);
        //根据余额，够则扣钱，并增加会员的消费，不够则返回
        if (deposit >= fee) {
            System.err.println("here  "+deposit);

            // 会员卡扣钱
            vipCard.setDeposit(deposit - fee);
            //增加积分
            vipCard.setPoint(vipCard.getPoint() + CardBusiness.POINT_INCREMENT);
            //增加会员消费
            double consumption = vip.getConsumption() + fee;
            vip.setConsumption(consumption);
            // 根据现在的消费判断会员是否要升级
            if (CardBusiness.isUpgrade(grade, consumption)) {
                vipCard.setGrade((Integer.parseInt(grade) + 1) + "");
            }
            //保存卡
            this.cardDao.updateVIPCard(vipCard);
            // 这里不增加客栈收入，交由总经理处理
            trade.setFee(fee);
            //保存销售记录
            this.planDao.saveVIPTrade(trade);
        } else {
            System.err.println("here");
            //余额不足
            return Result.InsufficientBalance;
        }

        return Result.True;
    }

}
