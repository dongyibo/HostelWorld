package com.nju.hostelworld.service.impl;

import com.nju.hostelworld.dao.CardDao;
import com.nju.hostelworld.dao.UserDao;
import com.nju.hostelworld.model.BankCard;
import com.nju.hostelworld.model.Manager;
import com.nju.hostelworld.model.VIP;
import com.nju.hostelworld.model.VIPCard;
import com.nju.hostelworld.service.UserService;
import com.nju.hostelworld.util.CardBusiness;
import com.nju.hostelworld.util.MD5;
import com.nju.hostelworld.util.Result;
import com.nju.hostelworld.util.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by dongyibo on 2017/1/4.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private CardDao cardDao;

    @Override
    public VIP getVIP(String vid) {
        return this.userDao.getVIP(vid);
    }

    @Override
    public void updatePhoneOfVIP(String vid, String phone) {
        this.userDao.updatePhoneOfVIP(vid, phone);
    }

    @Override
    public boolean updatePassword(String vid, String oldPWD, String newPWD) {
        VIP vip = this.userDao.getVIP(vid);
        //旧密码相符则更新
        if (vip.getPassword().equals(MD5.getMd5(oldPWD))) {
            vip.setPassword(MD5.getMd5(newPWD));
            this.userDao.updateVIP(vip);
            return true;
        }
        return false;
    }

    @Override
    public VIPCard getVIPCard(VIP vip) {
        return this.cardDao.getVIPCard(vip.getVid());
    }

    @Override
    public int convertPoint(int vcid) {
        VIPCard vipCard = this.cardDao.getVIPCardById(vcid);
        int point = vipCard.getPoint();
        int k = point / CardBusiness.POINT_LINE;
        if (k == 0) {
            return k;
        }
        double money = k * CardBusiness.POINT_MONEY;
        //增加卡余额
        this.cardDao.addDespoit(vcid, money);
        point = point - k * CardBusiness.POINT_LINE;
        //保存现在的卡积分
        this.cardDao.savePoint(vcid, point);

        return k;
    }

    @Override
    public double recharge(int vcid, double money) {
        VIPCard vipCard = this.cardDao.getVIPCardById(vcid);
        String vid = vipCard.getVip().getVid();
        BankCard bankCard = this.cardDao.getBankCard(vid);
        //获取存款
        double deposit = bankCard.getDeposit();
        // 钱够
        if (money <= deposit) {
            // 银行卡扣钱
            deposit -= money;
            bankCard.setDeposit(deposit);
            this.cardDao.updateBankCard(bankCard);
            // 会员卡加钱
            double newDeposit = vipCard.getDeposit() + money;
            vipCard.setDeposit(newDeposit);
            this.cardDao.updateVIPCard(vipCard);
            return newDeposit;
        }
        // 银行卡钱不够
        else {
            return CardBusiness.RECHARGE_FAIL;
        }
    }

    @Override
    public Manager getManager() {
        return this.userDao.getManager();
    }

    @Override
    public List<VIP> getAllVIP() {
        return this.userDao.getAllVIP();
    }

    @Override
    public Result renewal(int vcid) {
        VIPCard vipCard = this.cardDao.getVIPCardById(vcid);
        VIP vip = this.userDao.getVIP(vipCard.getVip().getVid());
        BankCard bankCard = this.cardDao.getBankCard(vip.getVid());
        String state = vip.getState();
        Date deadline = vip.getDeadline();
        if (bankCard.getDeposit() >= CardBusiness.RENEWAL_MONEY) {
            //银行卡扣钱
            bankCard.setDeposit(bankCard.getDeposit() - CardBusiness.RENEWAL_MONEY);
            this.cardDao.updateBankCard(bankCard);
            //总经理收入增加
            this.userDao.addManagerIncome();
            // 会员到期时间再延长一年
            vip.setDeadline(Time.getDateAfterOneYear(deadline));
            //如果到期则更改状态
            if (state.equals("暂停")){
                vip.setState("正常");
            }
            this.userDao.updateVIP(vip);
            return Result.True;
        }
        return Result.InsufficientBalance;
    }

    @Override
    public void abandon(String vid) {
        VIP vip = this.userDao.getVIP(vid);
        vip.setState("停止");
        this.userDao.updateVIP(vip);
    }
}
