package com.triplan.plan.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.triplan.plan.model.CourseListDto;
import com.triplan.plan.model.ReviewDto;

@Mapper
public interface ReviewMapper {
	
	// 1. 리뷰 생성
	void registReview(ReviewDto reviewDto) throws SQLException;
	
	// 2. 리뷰 목록 불러오기
	List<ReviewDto> getReviewList() throws SQLException;
	
	// 3-1. 리뷰 상세 보기
	ReviewDto getReviewDetail(int reviewId) throws SQLException;
	
	// 3-2. 리뷰 코스 가져오기
	// PlanMapper 플랜 코스 가져오기 참고
	
	// 4. 리뷰 삭제
	void deleteReview(int reviewId) throws SQLException;
	
	// 5. 리뷰 수정
	void modifyReview(ReviewDto reviewDto, CourseListDto courseListDto) throws SQLException;
	
	// 6. 리뷰 목록 총 개수
	int getTotalReviewCount(Map<String, Object> map) throws SQLException;
	
	// 7. 리뷰 조회수 증가
	void updateHit(int reviewId) throws SQLException;
	
	// 8. 리뷰 추천수 증가
	void updateRecommendCnt(int reviewId) throws SQLException;
	
}
