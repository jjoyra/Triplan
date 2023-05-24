package com.triplan.plan.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class PlanDto {

    private int planId;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String thumbnailUrl;
    private Map<String, Object> members;
    // onwer: id
    // member: 배열
    private List<CourseListDto> courseList;

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Map<String, Object> getMembers() {
        return members;
    }

    public void setMembers(Map<String, Object> members) {
        this.members = members;
    }

    public List<CourseListDto> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseListDto> courseList) {
        this.courseList = courseList;
    }

    @Override
    public String toString() {
        return "PlanDto{" +
                "planId=" + planId +
                ", title='" + title + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", members=" + members +
                ", courseList=" + courseList +
                '}';
    }
}
