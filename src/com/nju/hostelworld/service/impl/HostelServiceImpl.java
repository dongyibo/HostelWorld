package com.nju.hostelworld.service.impl;

import com.nju.hostelworld.dao.HostelDao;
import com.nju.hostelworld.dao.PlanDao;
import com.nju.hostelworld.model.Hostel;
import com.nju.hostelworld.model.HostelCache;
import com.nju.hostelworld.model.Trade;
import com.nju.hostelworld.service.HostelService;
import com.nju.hostelworld.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dongyibo on 2017/1/9.
 */

@Service
public class HostelServiceImpl implements HostelService {


    @Autowired
    private HostelDao hostelDao;

    @Autowired
    private PlanDao planDao;

    @Override
    public Hostel getHostel(String hid) {
        return this.hostelDao.getHostel(hid);
    }

    @Override
    public List<Hostel> getAllNormalHostel() {
        return this.hostelDao.getAllNormalHostel();
    }

    @Override
    public void updatePhone(String hid, String phone) {
        HostelCache hostelCache = this.hostelDao.getHostelCache(hid);
        if (hostelCache != null) {
            this.hostelDao.deleteHostelCache(hostelCache);
        }
        // 插入
        hostelCache = new HostelCache();
        Hostel hostel = new Hostel();
        hostel.setHid(hid);
        hostelCache.setHostel(hostel);
        hostelCache.setPhone(phone);
        this.hostelDao.saveHostelCache(hostelCache);
//        this.hostelDao.updatePhone(hid, phone);
    }

    @Override
    public boolean updatePassword(String hid, String oldPWD, String newPWD) {
        Hostel hostel = this.hostelDao.getHostel(hid);
        if (!MD5.getMd5(oldPWD).equals(hostel.getPassword())) {
            return false;
        }
        hostel.setPassword(MD5.getMd5(newPWD));
        this.hostelDao.updateHostel(hostel);
        return true;

    }

    @Override
    public List<Hostel> getAllHostelWithoutApproval() {
        return this.hostelDao.getAllHostelWithoutApproval();
    }

    @Override
    public void approveHostel(String hid) {
        Hostel hostel = this.hostelDao.getHostel(hid);
        hostel.setState("正常");
        this.hostelDao.updateHostel(hostel);
    }

    @Override
    public List<HostelCache> getAllHostelCaches() {
        return this.hostelDao.getAllHostelCaches();
    }

    @Override
    public void approveHostelChange(String hid, String phone) {
        // 删除 缓存记录
        HostelCache hostelCache = this.hostelDao.getHostelCache(hid);
        this.hostelDao.deleteHostelCache(hostelCache);
        // 更新
        this.hostelDao.updatePhone(hid, phone);
    }

    @Override
    public List<Hostel> getAllVIPTradeHostel() {
        return this.hostelDao.getAllVIPTradeHostel();
    }

    @Override
    public void allPayForHostel(String hid) {
        List<Trade> trades = this.planDao.getAllUnPaidTrade(hid);
        double income = 0;
        for (Trade trade : trades) {
            double fee = trade.getFee();
            income += fee;
            trade.setWay("会员卡已支付");
            this.planDao.updateTrade(trade);
        }
        this.hostelDao.addHostelIcome(hid, income);
    }

    @Override
    public List<Hostel> getAllHostelWithApproval() {
        return this.hostelDao.getAllHostelWithApproval();
    }

    @Override
    public Double getTotalIncome() {
        List<Hostel> hostels = this.hostelDao.getAllHostelWithApproval();
        double income = 0;
        for (Hostel hostel : hostels) {
            income += hostel.getIncome();
        }
        return income;
    }


}
