package com.nju.hostelworld.util;

/**
 * Created by dongyibo on 2017/1/13.
 */
public class CardBusiness {

    private CardBusiness() {
    }

    /**
     * 每次消费给予的积分
     */
    public static final int POINT_INCREMENT = 100;

    /**
     * 积分兑换的阈值
     */
    public static final int POINT_LINE = 500;

    /**
     * 积分每次兑换的金钱
     */
    public static final int POINT_MONEY = 40;

    /**
     * 续费的缴费金额
     */
    public static final int RENEWAL_MONEY = 1000;

    /**
     * 充值失败
     */
    public static final int RECHARGE_FAIL = -1;

    private static double discounts[] = {0.9, 0.85, 0.8, 0.75};

    private static double upperLine[] = {1000, 4000, 10000};

    /**
     * 根据会员等级返回折扣
     *
     * @param grade
     * @return
     */
    public static double getDiscount(String grade) {
        return discounts[Integer.parseInt(grade)];
    }

    /**
     * 判断会员是否能够升级
     *
     * @param grade
     * @param consumption
     * @return
     */
    public static boolean isUpgrade(String grade, double consumption) {
        int g = Integer.parseInt(grade);
        //已到顶级则不升级
        if (g == 3) {
            return false;
        }
        if (consumption > upperLine[g]) {
            return true;
        }
        return false;
    }
}
