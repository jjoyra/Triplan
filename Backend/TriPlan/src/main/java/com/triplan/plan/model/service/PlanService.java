package com.triplan.plan.model.service;

import com.triplan.plan.model.CourseListDto;
import com.triplan.plan.model.PlanDto;

import java.util.List;
import java.util.Map;

public interface PlanService {
    // 1. 플랜 생성
    // 플랜
    void registPlan(PlanDto planDto) throws Exception;

    // 2. 플랜 수정

    // 3. 전체 플랜 목록 조회

    // 4. 사용자 플랜 목록 조회
    List<PlanDto> getUserPlanList(String memberId) throws Exception;

    // 5. 플랜 상세보기
    // 플랜 조회
    PlanDto getPlanDetail(int planId) throws Exception;

    List<CourseListDto> getCourseList(int planId) throws Exception;

    // 6. 플랜 삭제
    void deletePlan(int planId) throws Exception;
}
