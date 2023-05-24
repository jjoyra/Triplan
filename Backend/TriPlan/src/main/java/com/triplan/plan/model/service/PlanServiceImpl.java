package com.triplan.plan.model.service;

import com.triplan.plan.model.CourseListDto;
import com.triplan.plan.model.PlanDto;
import com.triplan.plan.model.mapper.PlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanMapper planMapper;

    @Override
    public void registPlan(PlanDto planDto) throws Exception {
        planMapper.registPlan(planDto);
    }

    @Override
    public void registMemberPlan(Map<String, Object> members) throws Exception {
        planMapper.registMemberPlan(members);
    }

    @Override
    public void registCourseList(List<CourseListDto> courseList) throws Exception {
        planMapper.registCourseList(courseList);
    }

    @Override
    public List<PlanDto> getUserPlanList(String memberId) throws Exception {
        return planMapper.getUserPlanList(memberId);
    }

    @Override
    public PlanDto getPlan(int planId) throws Exception {
        return planMapper.getPlan(planId);
    }

    @Override
    public List<CourseListDto> getCourseList(int planId) throws Exception {
        return planMapper.getCourseList(planId);
    }

    @Override
    public String getPlanOwner(int planId) throws Exception {
        return planMapper.getPlanOwner(planId);
    }

    @Override
    public List<String> getPlanMembers(int planId) throws Exception {
        return planMapper.getPlanMembers(planId);
    }

    @Override
    public void deletePlanMember(int planId) throws Exception {
        planMapper.deletePlanMember(planId);
    }

    @Override
    public void deletePlanCourse(int planId) throws Exception {
        planMapper.deletePlanCourse(planId);
    }

    @Override
    public void deletePlan(int planId) throws Exception {
        planMapper.deletePlan(planId);
    }
}
