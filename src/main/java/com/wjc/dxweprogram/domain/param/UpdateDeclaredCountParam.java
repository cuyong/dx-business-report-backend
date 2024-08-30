package com.wjc.dxweprogram.domain.param;

public class UpdateDeclaredCountParam {

    private int userId;
    private String reportDate;
    private int declaredCount;

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public int getDeclaredCount() {
        return declaredCount;
    }

    public void setDeclaredCount(int declaredCount) {
        this.declaredCount = declaredCount;
    }

}
