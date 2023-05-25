package com.triplan.plan.model;

import java.time.LocalDateTime;

public class ReviewDto {
    private int reviewId;
    private String memberId;
    private int peopleCnt;
    private String title;
    private String content;
    private int recommendCnt;
    private int hit;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int planId;
    private int rating;
    private int companion;
    private int price;
    private boolean isTotalPrice;
    private String thumbnailUrl;

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRecommendCnt() {
        return recommendCnt;
    }

    public void setRecommendCnt(int recommendCnt) {
        this.recommendCnt = recommendCnt;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getCompanion() {
        return companion;
    }

    public void setCompanion(int companion) {
        this.companion = companion;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPeopleCnt() {
        return peopleCnt;
    }

    public void setPeopleCnt(int peopleCnt) {
        this.peopleCnt = peopleCnt;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
    
    public boolean isTotalPrice() {
		return isTotalPrice;
	}

	public void setTotalPrice(boolean isTotalPrice) {
		this.isTotalPrice = isTotalPrice;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	@Override
	public String toString() {
		return "ReviewDto [reviewId=" + reviewId + ", memberId=" + memberId + ", peopleCnt=" + peopleCnt + ", title="
				+ title + ", content=" + content + ", recommendCnt=" + recommendCnt + ", hit=" + hit + ", createDate="
				+ createDate + ", modifyDate=" + modifyDate + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", planId=" + planId + ", rating=" + rating + ", companion=" + companion + ", price=" + price
				+ ", isTotalPrice=" + isTotalPrice + ", thumbnailUrl=" + thumbnailUrl + "]";
	}

}
