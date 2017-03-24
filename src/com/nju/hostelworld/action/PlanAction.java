package com.nju.hostelworld.action;

import com.nju.hostelworld.model.Hostel;
import com.nju.hostelworld.model.Plan;
import com.nju.hostelworld.model.Room;
import com.nju.hostelworld.model.VO.ReservedPlan;
import com.nju.hostelworld.service.PlanService;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by dongyibo on 2017/1/9.
 */

@Controller
public class PlanAction extends BaseAction implements ModelDriven<Plan>{

    @Autowired
    private PlanService planService;

    private Plan plan;

    /**
     * 发布计划
     * @return
     */
    public String release(){
        String roomNo = this.request.getParameter("roomNumber");
        Room room = new Room(Integer.parseInt(roomNo));
        this.plan.setRoom(room);
        if (this.planService.releasePlan(this.plan)){
            //获取所有计划
            Hostel hostel = this.plan.getHostel();
            List<Plan> plans = this.planService.getAllPlan(hostel.getHid());
//            this.session.put("plans", plans);
            List<ReservedPlan> reservedPlans = this.planService.getAllPlanWithReserved(hostel.getHid(), plans);
            this.session.put("plans", reservedPlans);
        }
        else{
//            System.err.println("fail");
            this.request.setAttribute("fail", "repeat");
            return "fail";
        }
        return "release";
    }

    /**
     * 删除计划
     * @return
     */
    public String delete(){
        String pid = this.request.getParameter("pid");
        String hid = String.format("%07d",Integer.parseInt(this.request.getParameter("hid")));
        this.planService.deletePlan(Integer.parseInt(pid));
        //获取所有计划
        List<Plan> plans = this.planService.getAllPlan(hid);
        List<ReservedPlan> reservedPlans = this.planService.getAllPlanWithReserved(hid, plans);
        this.session.put("plans", reservedPlans);
        return "delete";
    }

    @Override
    public Plan getModel() {
        this.plan = new Plan();
        return this.plan;
    }
}
