package com.triplan.plan.model;

import java.time.LocalDateTime;

public class PlanDto {

    private String planId;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String thumnailUrl;

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
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

    public String getThumnailUrl() {
        return thumnailUrl;
    }

    public void setThumnailUrl(String thumnailUrl) {
        this.thumnailUrl = thumnailUrl;
    }

    @Override
    public String toString() {
        return "PlanDto{" +
                "planId='" + planId + '\'' +
                ", title='" + title + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", thumnailUrl='" + thumnailUrl + '\'' +
                '}';
    }
}
