package com.nju.hostelworld.model.VO;

import java.util.Date;

/**
 * Created by dongyibo on 2017/1/11.
 */
public class RecordDate {

    private Date startDate;

    private Date endDate;

    public RecordDate(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public RecordDate() {
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "RecordDate{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
