package com.triplan.plan.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.triplan.plan.model.CourseListDto;
import com.triplan.plan.model.ReviewDto;
import com.triplan.plan.model.mapper.ReviewMapper;
import com.triplan.util.SizeConstant;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	private ReviewMapper reviewMapper;
	
	public ReviewServiceImpl(ReviewMapper reviewMapper) {
		super();
		this.reviewMapper = reviewMapper;
	}

	@Override
	public void registReview(ReviewDto reviewDto) throws SQLException {
		reviewMapper.registReview(reviewDto);
	}

	@Override
	public void registCourseComment(CourseListDto courseDto) throws SQLException {
		reviewMapper.registCourseComment(courseDto);
	}

	@Override
	public List<ReviewDto> getReviewList(Map<String, Object> map) throws SQLException {
		Map<String, Object> param = new HashMap<String, Object>();
		String sortkey = (String) map.get("sortkey");
		param.put("sortkey", sortkey == null || sortkey.length() == 0 ? "create_date" : sortkey);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int pgNo = Integer.parseInt(((String) map.get("pgno") == "" || map.get("pgno") == null) ? "1" : (String) map.get("pgno"));
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);
		return reviewMapper.getReviewList(param);
	}

	@Override
	public ReviewDto getReviewDetail(int reviewId) throws SQLException {
		reviewMapper.updateHit(reviewId);
		return reviewMapper.getReviewDetail(reviewId);
	}

	@Override
	public void deleteReview(int reviewId) throws SQLException {
		reviewMapper.deleteReview(reviewId);
	}

	@Override
	public void modifyReview(ReviewDto reviewDto) throws SQLException {
		reviewMapper.modifyReview(reviewDto);
	}

	@Override
	public void modifyCourseComment(CourseListDto courseDto) throws SQLException {
		reviewMapper.modifyCourseComment(courseDto);
	}

	@Override
	public int getTotalReviewCount(Map<String, Object> map) throws SQLException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		return reviewMapper.getTotalReviewCount(param);
	}

	@Override
	public void updateHit(int reviewId) throws SQLException {
		reviewMapper.updateHit(reviewId);
	}

	@Override
	public void updateRecommendCnt(int reviewId) throws SQLException {
		reviewMapper.updateRecommendCnt(reviewId);
	}

}
