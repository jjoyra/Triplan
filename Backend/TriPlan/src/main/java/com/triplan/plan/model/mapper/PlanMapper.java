package com.triplan.plan.model.mapper;

import com.triplan.plan.model.CourseListDto;
import com.triplan.plan.model.PlanDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PlanMapper {

    // 1. 플랜 생성
    // 플랜
    void registPlan(PlanDto planDto) throws Exception;

    // 플랜 멤버 추가
    void registMemberPlan(Map<String, Object> members) throws Exception;

    // 코스 리스트 추가
    void registCourseList(Map<String, Object> courseList) throws Exception;

    // 2. 플랜 수정
//    void modifyPlan(PlanDto planDto) throws Exception;

    // 3. 전체 플랜 목록 조회
//    List<PlanDto> getPlanList() throws Exception;

    // 4. 사용자 플랜 목록 조회
    List<PlanDto> getUserPlanList(String memberId) throws Exception;

    // 5. 플랜 상세보기
    // 플랜 조회
    PlanDto getPlan(int planId) throws Exception;

    // 플랜 코스 조회
    List<CourseListDto> getCourseList(int planId) throws Exception;

    // 플랜 멤버 조회(플랜 작성자)
    String getPlanOwner(int planId) throws Exception;

    // 플랜 멤버 조회(플랜 멤버)
    List<String> getPlanMembers(int planId) throws Exception;

    // 6. 플랜 삭제
    // 멤버 삭제
    void deletePlanMember(int planId) throws Exception;

    // 코스 삭제
    void deletePlanCourse(int planId) throws Exception;

    // 플랜 삭제
    void deletePlan(int planId) throws Exception;


}
