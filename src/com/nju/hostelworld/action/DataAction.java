package com.nju.hostelworld.action;

import com.nju.hostelworld.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dongyibo on 2017/1/17.
 */

@Controller
public class DataAction extends BaseAction {

    @Autowired
    private PlanService planService;

    private ArrayList<String> list1;
    private ArrayList<String> list2;

    /**
     * 返回每月客栈收入
     * @return
     */
    public String getIncomePerMonth() {
        this.list1 = new ArrayList<>();
        this.list2 = new ArrayList<>();
        String hid = String.format("%07d", Integer.parseInt(this.request.getParameter("hid")));
        HashMap<String, Double> map = (HashMap<String, Double>) this.planService.getTradeDetailByMonth(hid);
        for (Map.Entry<String, Double> entry : map.entrySet()) {
//            System.out.println("key= " + entry.getKey() + " and value= "
//                    + entry.getValue());
            this.list1.add(entry.getKey());
            this.list2.add(entry.getValue()+"");
        }

        return "ajax";
    }



    public ArrayList<String> getList1() {
        return list1;
    }

    public ArrayList<String> getList2() {
        return list2;
    }
}
