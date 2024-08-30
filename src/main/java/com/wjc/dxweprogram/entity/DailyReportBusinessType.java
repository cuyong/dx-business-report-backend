package com.wjc.dxweprogram.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

@TableName("DailyReportBusinessType")
public class DailyReportBusinessType implements Serializable {

    @TableId
    private Integer dailyReportId;

    @TableField
    private Integer businessTypeId;

    @TableField
    private Integer businessOrder;

    @TableField
    private String customType;

    // Getters and Setters
    public Integer getDailyReportId() {
        return dailyReportId;
    }

    public void setDailyReportId(Integer dailyReportId) {
        this.dailyReportId = dailyReportId;
    }

    public Integer getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Integer businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public Integer getBusinessOrder() {
        return businessOrder;
    }

    public void setBusinessOrder(Integer businessOrder) {
        this.businessOrder = businessOrder;
    }

    public String getCustomType() {
        return customType;
    }

    public void setCustomType(String customType) {
        this.customType = customType;
    }
}
