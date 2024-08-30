package com.wjc.dxweprogram.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.time.LocalDateTime;

@TableName("DailyReport")
public class DailyReport {
    @TableId
    private Integer dailyReportId;
    private Integer weeklyReportId;
    private LocalDate reportDate;
    private Integer declaredCount;
    private Integer actualCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // getters and setters

    public Integer getDailyReportId() {
        return dailyReportId;
    }

    public void setDailyReportId(Integer dailyReportId) {
        this.dailyReportId = dailyReportId;
    }

    public Integer getWeeklyReportId() {
        return weeklyReportId;
    }

    public void setWeeklyReportId(Integer weeklyReportId) {
        this.weeklyReportId = weeklyReportId;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public Integer getDeclaredCount() {
        return declaredCount;
    }

    public void setDeclaredCount(Integer declaredCount) {
        this.declaredCount = declaredCount;
    }

    public Integer getActualCount() {
        return actualCount;
    }

    public void setActualCount(Integer actualCount) {
        this.actualCount = actualCount;
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
