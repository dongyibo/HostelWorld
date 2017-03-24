package com.nju.hostelworld.dao.impl;

import com.nju.hostelworld.dao.BaseDao;
import com.nju.hostelworld.dao.HostelDao;
import com.nju.hostelworld.model.Hostel;
import com.nju.hostelworld.model.HostelCache;
import com.nju.hostelworld.util.MD5;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongyibo on 2017/1/8.
 */

@Repository
public class HostelDaoImpl implements HostelDao {

    @Autowired
    private BaseDao baseDao;

    @Override
    public boolean checkHostelLogin(String id, String password) {
        Hostel hostel = (Hostel) this.baseDao.get(Hostel.class, id);
        if (hostel == null) {
            return false;
        } else if (hostel.getPassword() != null && MD5.getMd5(password).equals(hostel.getPassword())) {
            //审批 通过
            if (hostel.getState().equals("正常")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getNewHid() {
        String hql = "select max(hid) from Hostel";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql);
        String hid = (String) query.uniqueResult();
        if (hid == null) {
            hid = "0000001";
        } else {
            int id = Integer.parseInt(hid);
            id++;
            hid = String.format("%07d", id);
        }
        return hid;
    }

    @Override
    public void hostelReg(Hostel hostel) {
        this.baseDao.save(hostel);
    }

    @Override
    public Hostel getHostel(String hid) {
        Hostel hostel = (Hostel) this.baseDao.get(Hostel.class, hid);
        return hostel;
    }

    @Override
    public List<Hostel> getAllNormalHostel() {
        String hql = "from Hostel h where h.state = ?";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql).setString(0, "正常");
        List<Hostel> hostels = query.list();
        if (hostels == null) {
            hostels = new ArrayList<>();
        }
        return hostels;
    }

    @Override
    public void addHostelIcome(String hid, double fee) {
        String hql = "update Hostel h set h.income = h.income + ? where h.hid = ?";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql).setDouble(0, fee).setString(1, hid);
        query.executeUpdate();
    }

    @Override
    public void updatePhone(String hid, String phone) {
        String hql = "update Hostel h set h.phone = ? where h.hid = ?";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql).setString(0, phone).setString(1, hid);
        query.executeUpdate();
    }

    @Override
    public void updateHostel(Hostel hostel) {
        this.baseDao.update(hostel);
    }

    @Override
    public List<Hostel> getAllHostelWithoutApproval() {
        String hql = "from Hostel h where h.state = ?";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql).setString(0, "待审批");
        List<Hostel> hostels = query.list();
        if (hostels == null) {
            hostels = new ArrayList<>();
        }
        return hostels;
    }

    @Override
    public List<Hostel> getAllHostelWithApproval() {
        String hql = "from Hostel h where h.state = ?";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql).setString(0, "正常");
        List<Hostel> hostels = query.list();
        if (hostels == null) {
            hostels = new ArrayList<>();
        }
        return hostels;
    }

    @Override
    public HostelCache getHostelCache(String hid) {
        String hql = "from HostelCache hc where hc.hostel.hid = ?";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql).setString(0, hid);
        return (HostelCache) query.uniqueResult();
    }

    @Override
    public void deleteHostelCache(HostelCache hostelCache) {
        this.baseDao.deleteByModel(hostelCache);
    }

    @Override
    public void saveHostelCache(HostelCache hostelCache) {
        this.baseDao.save(hostelCache);
    }

    @Override
    public List<HostelCache> getAllHostelCaches() {
        String hql = "from HostelCache hc left outer join fetch hc.hostel";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql);
        List<HostelCache> hostelCaches = query.list();
        if (hostelCaches == null){
            return new ArrayList<>();
        }
        return hostelCaches;
    }

    @Override
    public List<Hostel> getAllVIPTradeHostel() {
        String hql = "select distinct h from Hostel h, Trade t where h.hid = t.plan.hostel.hid and t.way = ?";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql).setString(0, "会员卡");
        List<Hostel> hostels = query.list();
        if (hostels==null){
            return new ArrayList<>();
        }
        return hostels;
    }

}
