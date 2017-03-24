package com.nju.hostelworld.dao.impl;

import com.nju.hostelworld.dao.BaseDao;
import com.nju.hostelworld.dao.UserDao;
import com.nju.hostelworld.model.Manager;
import com.nju.hostelworld.model.User;
import com.nju.hostelworld.model.VIP;
import com.nju.hostelworld.util.MD5;
import org.hibernate.Interceptor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongyibo on 2017/1/4.
 */

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private BaseDao baseDao;


    @Override
    public String getManagerPWD() {
        Manager manager = (Manager) this.baseDao.get(Manager.class, "0000000");
        return manager.getPassword();
    }

    @Override
    public boolean checkUserLogin(String id, String password) {
        VIP vip = (VIP) this.baseDao.get(VIP.class, id);
        if (vip == null) {
            return false;
        }
        //账号密码匹配
        else if (vip.getPassword() != null && MD5.getMd5(password).equals(vip.getPassword())) {
            //会员账号而没有被停止
            if(!vip.getState().equals("停止")){
                return true;
            }
        }
        return false;
    }

    @Override
    public String getNewVid() {
        String hql = "select max(vid) from VIP";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql);
        String vid = (String) query.uniqueResult();
        if(vid == null){
            vid = "0000001";
        }
        else{
            int id = Integer.parseInt(vid);
            id++;
            vid = String.format("%07d",id);
        }
        return vid;
    }

    @Override
    public void userReg(VIP vip) {
        this.baseDao.save(vip);
    }

    @Override
    public VIP getVIP(String vid) {
        String hql = "from VIP v left outer join fetch v.user where v.vid = ?";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql).setString(0, vid);
        return (VIP) query.uniqueResult();
    }

    @Override
    public void updatePhoneOfVIP(String vid, String phone) {
//        System.out.println(vid+"   "+phone);
        String hql = "update VIP v set v.phone = ? where v.vid = ?";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql).setString(0, phone).setString(1, vid);
        query.executeUpdate();
//        System.out.println("end...............");
    }

    @Override
    public void updateVIP(VIP vip) {
        this.baseDao.update(vip);
    }

    @Override
    public VIP getVIPByUid(int uid) {
        String hql = "from VIP v where v.user.uid = ?";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql).setInteger(0, uid);
        return (VIP) query.uniqueResult();
    }

    @Override
    public Manager getManager() {
        String hql = "from Manager m left outer join fetch m.user";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql);
        return (Manager) query.uniqueResult();
    }

    @Override
    public User getUser(int uid) {
        return (User) this.baseDao.get(User.class, uid);
    }

    @Override
    public List<VIP> getAllVIP() {
        String hql = "from VIP v left outer join fetch v.user where v.state = ?";
        List<VIP> vips = this.baseDao.getAllList(hql,"正常");
        if (vips == null){
            return new ArrayList<>();
        }
        return vips;
    }

    @Override
    public void addManagerIncome() {
        String hql = "update Manager m set m.income = m.income + ?";
        Session session = this.baseDao.getSession();
        Query query = session.createQuery(hql).setDouble(0, 1000);
        query.executeUpdate();
    }


}
