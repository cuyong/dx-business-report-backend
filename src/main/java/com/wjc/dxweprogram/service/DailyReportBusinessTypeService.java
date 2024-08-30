package com.wjc.dxweprogram.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjc.dxweprogram.common.Result;
import com.wjc.dxweprogram.entity.BusinessType;
import com.wjc.dxweprogram.entity.DailyReportBusinessType;

import java.time.LocalDate;

public interface DailyReportBusinessTypeService extends IService<DailyReportBusinessType> {
    // 这里可以定义业务相关的接口方法

    Result<BusinessType> addBusinessType(int workNumber, LocalDate date, int businessOrder, String businessTypeName);

    Result<String> deleteBusinessType(int workNumber, LocalDate date, int businessOrder, String businessTypeName);

}
