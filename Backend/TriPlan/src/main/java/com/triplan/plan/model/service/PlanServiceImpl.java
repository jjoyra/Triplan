package com.triplan.plan.model.service;

import com.triplan.plan.model.CourseListDto;
import com.triplan.plan.model.PlanDto;
import com.triplan.plan.model.mapper.PlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanMapper planMapper;

    @Override
    public void registPlan(PlanDto planDto) throws Exception {
        planMapper.registPlan(planDto);
        System.out.println(planDto.getPlanId());

        Map<String, Object> map = new HashMap<>();
        map.put("owner", planDto.getMembers().get("owner"));
        map.put("planId",planDto.getPlanId());
        map.put("courseList", planDto.getCourseList());

        planMapper.registMemberPlan(map);

        planMapper.registCourseList(map);
    }

    @Override
    public List<PlanDto> getUserPlanList(String memberId) throws Exception {
        return planMapper.getUserPlanList(memberId);
    }

    @Override
    public List<CourseListDto> getCourseList(int planId) throws Exception {
        return planMapper.getCourseList(planId);
    }

    @Override
    public PlanDto getPlanDetail(int planId) throws Exception {
        PlanDto planDto = planMapper.getPlan(planId);
        planDto.setCourseList(planMapper.getCourseList(planId));

        Map<String, Object> map = new HashMap<>();
        map.put("owner", planMapper.getPlanOwner(planId));
        map.put("member", planMapper.getPlanMembers(planId));

        planDto.setMembers(map);
        return planDto;
    }

    @Override
    public void deletePlan(int planId) throws Exception {
        planMapper.deletePlanMember(planId);
        planMapper.deletePlanCourse(planId);
        planMapper.deletePlan(planId);
    }
}
