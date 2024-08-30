package com.wjc.dxweprogram.domain.param;

public class BusinessTypeParam {

    private int workNumber;
    private int businessOrder;
    private String day;
    private String businessTypeName;

    public int getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(int workNumber) {
        this.workNumber = workNumber;
    }

    public int getBusinessOrder() {
        return businessOrder;
    }

    public void setBusinessOrder(int businessOrder) {
        this.businessOrder = businessOrder;
    }

    public String getBusinessTypeName() {
        return businessTypeName;
    }

    public void setBusinessTypeName(String businessTypeName) {
        this.businessTypeName = businessTypeName;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
