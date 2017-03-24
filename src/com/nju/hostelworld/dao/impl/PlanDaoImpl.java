package com.nju.hostelworld.dao.impl;

import com.nju.hostelworld.dao.BaseDao;
import com.nju.hostelworld.dao.PlanDao;
import com.nju.hostelworld.model.Plan;
import com.nju.hostelworld.model.Reservation;
import com.nju.hostelworld.model.Trade;
import com.nju.hostelworld.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongyibo on 2017/1/9.
 */

@Repository
public class PlanDaoImpl implements PlanDao {


    @Autowired
    private BaseDao baseDao;

    @Override
    public void savePlan(Plan plan) {
        this.baseDao.save(plan);
    }

    @Override
    public List<Integer> getAllRoomNumber(String hid) {
        String hql = "select r.roomNumber from Room r, Plan p where r.rid = p.room.rid and p.hostel.hid = ?";
        List<Integer> list = this.baseDao.getAllList(hql, hid);
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<Plan> getAllPlan(String hid) {
        String hql = "from Plan p left outer join fetch p.room where p.hostel.hid = ? order by p.room.roomNumber asc";
        List<Plan> plans = this.baseDao.getAllList(hql, hid);
        if (plans == null) {
            plans = new ArrayList<>();
        }
        return plans;
    }

    @Override
    public List<Integer> getAllReservedPlan(String hid) {
        String hql = "select p.pid from Plan p, Reservation r where p.pid = r.plan.pid and p.hostel.hid = ?";
        List<Integer> list = this.baseDao.getAllList(hql, hid);
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<Integer> getAllTradePlan(String hid) {
        String hql = "select p.pid from Plan p, Trade t where p.pid = t.plan.pid and p.hostel.hid = ?";
        List<Integer> list = this.baseDao.getAllList(hql, hid);
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    @Override
    public void deletePlan(int pid) {
        this.baseDao.deleteById(Plan.class, pid);
    }

    @Override
    public List<Reservation> getAllReservationNotUse(String hid, String vid) {
        String hql = "from Reservation re left outer join fetch re.plan where re.plan.hostel.hid = ? and re.vip.vid = ? and re.isUse = ?";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql).setString(0, hid).setString(1, vid).setString(2, "false");
        List<Reservation> reservations = query.list();
        if (reservations == null) {
            reservations = new ArrayList<>();
        }
        return reservations;
    }

    @Override
    public Plan getPlan(int pid) {
        String hql = "from Plan p left outer join fetch p.room where p.pid = ?";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql).setInteger(0, pid);
        return (Plan) query.uniqueResult();
    }

    @Override
    public void saveVIPTrade(Trade trade) {
//        System.err.println(trade);
        this.baseDao.save(trade);
    }

    @Override
    public void saveNormalTrade(Trade trade) {
//        System.err.println(trade);
        User user = trade.getUser();
        this.baseDao.save(user);
        this.baseDao.save(trade);
    }

    @Override
    public List<Reservation> getAllReservationForPlan(int pid) {
        String hql = "from Reservation re where re.plan.pid = ? and re.isUse = ?";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql).setInteger(0, pid).setString(1, "true");
        List<Reservation> reservations = query.list();
        if (reservations == null) {
            reservations = new ArrayList<>();
        }
        return reservations;
    }

    @Override
    public List<Reservation> getAllReservationForPlanFuture(int pid) {
        String hql = "from Reservation re where re.plan.pid = ?";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql).setInteger(0, pid);
        List<Reservation> reservations = query.list();
        if (reservations == null) {
            reservations = new ArrayList<>();
        }
        return reservations;
    }

    @Override
    public List<Reservation> getAllReservationNotUse(int pid) {
        String hql = "from Reservation re where re.plan.pid = ? and re.isUse = ?";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql).setInteger(0, pid).setString(1, "false");
        List<Reservation> reservations = query.list();
        if (reservations == null) {
            reservations = new ArrayList<>();
        }
        return reservations;
    }

    @Override
    public List<Trade> getAllTradeForPlan(int pid) {
        String hql = "from Trade t where t.plan.pid = ?";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql).setInteger(0, pid);
        List<Trade> trades = query.list();
        if (trades == null) {
            trades = new ArrayList<>();
        }
        return trades;
    }

    @Override
    public List<Trade> getAllTrade(String hid) {
        String hql = "from Trade t left outer join fetch t.user left outer join fetch t.plan where t.plan.hostel.hid = ? " +
                "order by t.tid desc";
        List<Trade> trades = this.baseDao.getAllList(hql, hid);
        if (trades == null) {
            trades = new ArrayList<>();
        }
        return trades;
    }

    @Override
    public Reservation getReservation(int reid) {
        String hql = "from Reservation re left outer join fetch re.plan where re.reid = ?";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql).setInteger(0, reid);
        return (Reservation) query.uniqueResult();
    }

    @Override
    public void updateReservation(Reservation reservation) {
        this.baseDao.update(reservation);
    }

    @Override
    public void updateTradeState(int tid, String state) {
        String hql = "update Trade t set t.state = ? where t.tid = ?";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql).setString(0, state).setInteger(1, tid);
        query.executeUpdate();
    }

    @Override
    public List<Reservation> getAllVIPReservation(String vid) {
        String hql = "from Reservation re where re.vip.vid = ?";
        List<Reservation> reservations = this.baseDao.getAllList(hql, vid);
        if (reservations == null) {
            reservations = new ArrayList<>();
        }
        return reservations;
    }

    @Override
    public Plan getPlanWithHotel(int pid) {
        String hql = "from Plan p left outer join fetch p.hostel left outer join fetch p.room where p.pid = ?";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql).setInteger(0, pid);
        return (Plan) query.uniqueResult();
    }

    @Override
    public void deleteReservation(int reid) {
        this.baseDao.deleteById(Reservation.class, reid);
    }

    @Override
    public void saveReservation(Reservation reservation) {
        this.baseDao.save(reservation);
    }

    @Override
    public List<Trade> getAllUnPaidTrade(String hid) {
        String hql = "from Trade t where t.way = ? and t.plan.hostel.hid = ?";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql).setString(0, "会员卡").setString(1, hid);
        List<Trade> trades = query.list();
        if (trades == null) {
            return new ArrayList<>();
        }
        return trades;
    }

    @Override
    public Trade getTrade(int tid) {
        return (Trade) this.baseDao.get(Trade.class, tid);
    }

    @Override
    public void updateTrade(Trade trade) {
        this.baseDao.update(trade);
    }

    @Override
    public List<Trade> getAllTradeForVIP(String vid) {
        String hql = "select distinct t from Trade t, VIP v where t.user.uid = v.user.uid and v.vid = ?";
        List<Trade> trades = this.baseDao.getAllList(hql, vid);
        if (trades == null) {
            return new ArrayList<>();
        }
        return trades;

    }

    @Override
    public List<Reservation> getAllReservationForHostel(String hid) {
        String hql = "from Reservation r where r.plan.hostel.hid = ?";
        List<Reservation> reservations = this.baseDao.getAllList(hql, hid);
        if (reservations == null) {
            return new ArrayList<>();
        }
        return reservations;
    }

    @Override
    public List<Trade> getAllPaidTrade(String hid) {
        String hql = "from Trade t where t.way <> ? and t.plan.hostel.hid = ?";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql).setString(0, "会员卡").setString(1, hid);
        List<Trade> trades = query.list();
        if (trades == null) {
            return new ArrayList<>();
        }
        return trades;
    }

    @Override
    public List<Trade> getAllTradeCheckIn(String hid) {
        String hql = "from Trade t where t.plan.hostel.hid = ? and t.state = ?";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql).setString(0, hid).setString(1, "入店");
        List<Trade> trades = query.list();
        if (trades == null){
            return new ArrayList<>();
        }
        return trades;
    }
}
