package com.nju.hostelworld.service.impl;

import com.nju.hostelworld.dao.CardDao;
import com.nju.hostelworld.dao.HostelDao;
import com.nju.hostelworld.dao.UserDao;
import com.nju.hostelworld.model.Hostel;
import com.nju.hostelworld.model.VIP;
import com.nju.hostelworld.service.LoginService;
import com.nju.hostelworld.util.MD5;
import com.nju.hostelworld.util.Role;
import com.nju.hostelworld.util.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by dongyibo on 2017/1/8.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private HostelDao hostelDao;

    @Autowired
    private CardDao cardDao;

    @Override
    public Role checkUserLogin(String id, String password) {
        //判断是否是总经理
        if (id.equals("0000000")) {
            if (MD5.getMd5(password).equals(this.userDao.getManagerPWD())) {
                return Role.Manager;
            } else {
                return Role.Fail;
            }
        }
        //判断是否为会员用户
        if (this.userDao.checkUserLogin(id, password)) {
            //满足条件
            if (this.satifyVIP(id)) {
                return Role.VIP;
            }
        }
        return Role.Fail;
    }


    /**
     * 满足登录条件
     *
     * @param vid
     * @return
     */
    private boolean satifyVIP(String vid) {
        VIP vip = this.userDao.getVIP(vid);
        //获取到期时间
        Date deadline = vip.getDeadline();
        Date today = new Date();
        //没到期,可以登录
        if (today.before(deadline)) {
            return true;
        }
        //到期
        else {
            //判断是否到期满一年
            if (today.before(Time.getDateAfterOneYear(deadline))) {
                //如果未满一年,会员资格为 暂停
                if (vip.getState().equals("正常")){
                    vip.setState("暂停");
                    this.userDao.updateVIP(vip);
                }
                return true;
            }
            //满一年
            else {
                vip.setState("停止");
                this.userDao.updateVIP(vip);
                return false;
            }
        }
    }

    @Override
    public Role checkHostelLogin(String id, String password) {
        if (this.hostelDao.checkHostelLogin(id, password)) {
            return Role.Hostel;
        }
        return Role.Fail;
    }

    @Override
    public String userReg(VIP vip) {
        vip.setState("正常");
        vip.setDeadline(Time.getDateAfterOneYear());
        vip.setConsumption(0);
        String vid = this.userDao.getNewVid();
        vip.setVid(vid);
        //注册用户
        this.userDao.userReg(vip);
        //注册卡
        this.cardDao.cardReg(vip);
        //增加经理收入
        this.userDao.addManagerIncome();
        return vid;
    }

    @Override
    public String hostelReg(Hostel hostel) {
        String hid = this.hostelDao.getNewHid();
        hostel.setHid(hid);
        String password = hostel.getPassword();
        hostel.setPassword(MD5.getMd5(password));
        hostel.setState("待审批");
        hostel.setIncome(0);
        this.hostelDao.hostelReg(hostel);
        return hid;
    }


}
