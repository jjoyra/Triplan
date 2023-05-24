package com.triplan.plan.model;

import java.time.LocalDateTime;

public class ReviewDto {
    private String reviewId;
    private String memberId;
    private String title;
    private String content;
    private int recommendCnt;
    private int hit;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private int planId;
    private int rating;
    private int companion;
    private int price;

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
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

    public LocalDateTime getModify_date() {
        return modifyDate;
    }

    public void setModify_date(LocalDateTime modifyDate) {
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

    @Override
    public String toString() {
        return "ReviewDto{" +
                "reviewId='" + reviewId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", recommendCnt=" + recommendCnt +
                ", hit=" + hit +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", planId=" + planId +
                ", rating=" + rating +
                ", companion=" + companion +
                ", price=" + price +
                '}';
    }
}
