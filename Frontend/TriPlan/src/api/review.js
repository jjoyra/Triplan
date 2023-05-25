import { apiInstance } from "./http";

const api = apiInstance();

async function registReview(params, success, fail) {
  // 1 - 1. 리뷰 생성 map.get("reviewDto")
  // 1-2. 코스별 코멘트 생성(하나씩 수정) map.get("planId")
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.post(`/review`, params).then(success).catch(fail);
}

async function getReviewList(param, success, fail) {
  await api.get(`/review`, { params: param }).then(success).catch(fail);
}

async function getReviewDetail(reviewId, success, fail) {
  // 3-1. 리뷰 상세 보기
  // 3-2. 리뷰 코스 가져오기
  await api.get(`/review/${reviewId}`).then(success).catch(fail);
}

async function deleteReview(reviewId, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.delete(`/review/${reviewId}`).then(success).catch(fail);
}

async function modifyReview(params, success, fail) {
  // 5-1. 리뷰 수정 ReviewDto reviewDto
  // 5-2. 코스 코멘트 수정(하나씩 수정) CourseListDto courseDto
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.put(`/review`, params).then(success).catch(fail);
}

async function getTotalReviewCount(param, success, fail) {
  await api.get(`/review/count`, { params: param }).then(success).catch(fail);
}

async function updateHit(reviewId, success, fail) {
  await api.put(`/review/hit/${reviewId}`).then(success).catch(fail);
}

async function updateRecommendCnt(reviewId, success, fail) {
  await api.put(`/review/recommend/${reviewId}`).then(success).catch(fail);
}

export { registReview, getReviewList, getReviewDetail, deleteReview, modifyReview, getTotalReviewCount, updateHit, updateRecommendCnt };
