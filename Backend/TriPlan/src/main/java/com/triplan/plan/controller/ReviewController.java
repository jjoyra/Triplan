package com.triplan.plan.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.triplan.member.model.service.JwtServiceImpl;
import com.triplan.plan.model.CourseListDto;
import com.triplan.plan.model.ReviewDto;
import com.triplan.plan.model.service.PlanService;
import com.triplan.plan.model.service.ReviewService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin("*")
public class ReviewController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private ReviewService reviewService;
	private PlanService planService;
	private JwtServiceImpl jwtService;

	public ReviewController(ReviewService reviewService, PlanService planService) {
		super();
		this.reviewService = reviewService;
		this.planService = planService;
	}
	
	// 1-1. 리뷰 생성, 1-2. 코스별 코멘트 생성(하나씩 수정)
//	int plan_id, String content_id, String comment, int order
	@PostMapping("/review")
	public ResponseEntity<?> registReview(@RequestBody Map<String, Object> map, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			System.out.println("사용 가능한 토큰..");
		
			try {
				// 리뷰 생성
				ReviewDto reviewDto = (ReviewDto) map.get("reviewDto");
				System.out.println("review 등록 " + reviewDto);
				reviewService.registReview(reviewDto);
				
				// 코스별 코멘트 생성
				int planId = (int) map.get("planId");
				List<CourseListDto> courseList = planService.getCourseList(planId);
				
				for (CourseListDto course: courseList) {
					System.out.println("comment 등록 " + course);
					reviewService.registCourseComment(course);
				}

				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (SQLException e) {
				e.printStackTrace();
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			System.out.println("사용 불가능한 토큰..");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	// 2. 리뷰 목록 불러오기
	@GetMapping("/review")
	public ResponseEntity<?> getReviewList(@RequestParam Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;

		try {
			List<ReviewDto> reviews = reviewService.getReviewList(map);
			resultMap.put("reviews", reviews);
			resultMap.put("pgno", map.get("pgno"));
			resultMap.put("sortkey", map.get("sortkey"));
			resultMap.put("word", map.get("word"));
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (SQLException e) {
			e.printStackTrace();
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	// 3-1. 리뷰 상세 보기, 3-2. 리뷰 코스 가져오기
	@GetMapping("/review/{reviewId}")
	public ResponseEntity<?> getReviewDetail(@PathVariable("reviewId") int reviewId, @RequestParam Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		
		try {
			System.out.println("reviewId " + reviewId);
			ReviewDto review = reviewService.getReviewDetail(reviewId);
			
			List<CourseListDto> courseList = planService.getCourseList(review.getPlanId());
			
			resultMap.put("review", review);
			resultMap.put("courseList", courseList);
			resultMap.put("pgno", map.get("pgno"));
			resultMap.put("sortkey", map.get("sortkey"));
			resultMap.put("word", map.get("word"));
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (SQLException e) {
			e.printStackTrace();
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	// 4. 리뷰 삭제
	@DeleteMapping("/review/{reviewId}")
	public ResponseEntity<?> deleteReview(@PathVariable("reviewId") int reviewId, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			System.out.println("사용 가능한 토큰!");
			
			try {
				reviewService.deleteReview(reviewId);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (SQLException e) {
				e.printStackTrace();
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			System.out.println("사용 불가능한 토큰..");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	// 5-1. 리뷰 수정, 5-2. 코스 코멘트 수정(하나씩 수정)
	@PutMapping("/review")
	public ResponseEntity<?> modifyReview(@RequestBody Map<String, Object> map, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			System.out.println("사용 가능한 토큰..");
		
			try {
				// 리뷰 수정
				ReviewDto review = (ReviewDto) map.get("review");
				reviewService.modifyReview(review);
				
				// 코스 코멘트 수정
				List<CourseListDto> courseList = (List<CourseListDto>) map.get("courseList");
				for (CourseListDto course: courseList) {
					reviewService.modifyCourseComment(course);
				}
				
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (SQLException e) {
				e.printStackTrace();
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			System.out.println("사용 불가능한 토큰..");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	// 6. 리뷰 목록 총 개수
	@GetMapping("/review/count")
	public ResponseEntity<?> getTotalReviewCount(@RequestParam Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		
		try {
			int cnt = reviewService.getTotalReviewCount(map);
			resultMap.put("cnt", cnt);
			resultMap.put("word", map.get("word"));
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	// 7. 리뷰 조회수 증가
	@PutMapping("/review/hit/{reviewId}")
	public ResponseEntity<?> updateHit(@PathVariable("reviewId") int reviewId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		
		try {
			reviewService.updateHit(reviewId);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	// 8. 리뷰 추천수 증가
	@PutMapping("/review/recommend/{reviewId}")
	public ResponseEntity<?> updateRecommendCnt(@PathVariable("reviewId") int reviewId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		
		try {
			reviewService.updateRecommendCnt(reviewId);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
}
