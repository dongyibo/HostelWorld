package com.nju.hostelworld.dao.impl;

import com.nju.hostelworld.dao.BaseDao;
import com.nju.hostelworld.dao.CardDao;
import com.nju.hostelworld.model.BankCard;
import com.nju.hostelworld.model.VIP;
import com.nju.hostelworld.model.VIPCard;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by dongyibo on 2017/1/9.
 */

@Repository
public class CardDaoImpl implements CardDao {

    @Autowired
    private BaseDao baseDao;

    @Override
    public void cardReg(VIP vip) {
        BankCard bankCard = new BankCard(2000, vip);
        VIPCard vipCard = new VIPCard(0, "0", 0, vip);
        this.baseDao.save(bankCard);
        this.baseDao.save(vipCard);
    }

    @Override
    public VIPCard getVIPCard(String vid) {
        String hql = "from VIPCard c where c.vip.vid = ?";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql).setString(0, vid);
        return (VIPCard) query.uniqueResult();
    }

    @Override
    public void updateVIPCard(VIPCard vipCard) {
        this.baseDao.update(vipCard);
    }

    @Override
    public void addDespoit(int vcid, double money) {
        String hql = "update VIPCard vc set vc.deposit = vc.deposit + ? where vc.vcid = ?";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql).setDouble(0, money).setInteger(1, vcid);
        query.executeUpdate();
    }

    @Override
    public void savePoint(int vcid, int point) {
        String hql = "update VIPCard vc set vc.point = ? where vc.vcid = ?";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql).setInteger(0, point).setInteger(1, vcid);
        query.executeUpdate();
    }

    @Override
    public VIPCard getVIPCardById(int vcid) {
        return (VIPCard) this.baseDao.get(VIPCard.class, vcid);
    }

    @Override
    public BankCard getBankCard(String vid) {
        String hql = "from BankCard bc where bc.vip.vid = ?";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql).setString(0 ,vid);
        return (BankCard) query.uniqueResult();
    }

    @Override
    public void updateBankCard(BankCard bankCard) {
        this.baseDao.save(bankCard);
    }
}
