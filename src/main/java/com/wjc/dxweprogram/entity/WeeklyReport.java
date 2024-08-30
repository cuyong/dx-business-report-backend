package com.wjc.dxweprogram.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.time.LocalDateTime;

@TableName("WeeklyReport")
public class WeeklyReport {
    @TableId
    private Integer weeklyReportId;
    private Integer userId;
    private LocalDate weekStartDate;
    private LocalDate weekEndDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // getters and setters

    public Integer getWeeklyReportId() {
        return weeklyReportId;
    }

    public void setWeeklyReportId(Integer weeklyReportId) {
        this.weeklyReportId = weeklyReportId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDate getWeekStartDate() {
        return weekStartDate;
    }

    public void setWeekStartDate(LocalDate weekStartDate) {
        this.weekStartDate = weekStartDate;
    }

    public LocalDate getWeekEndDate() {
        return weekEndDate;
    }

    public void setWeekEndDate(LocalDate weekEndDate) {
        this.weekEndDate = weekEndDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

