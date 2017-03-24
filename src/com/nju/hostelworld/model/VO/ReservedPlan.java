package com.nju.hostelworld.model.VO;

import com.nju.hostelworld.model.Plan;

/**
 * Created by dongyibo on 2017/1/10.
 */
public class ReservedPlan {

    private Plan plan;

    private boolean isReserved;

    public ReservedPlan() {
    }

    public ReservedPlan(Plan plan, boolean isReserved) {
        this.plan = plan;
        this.isReserved = isReserved;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }
}
