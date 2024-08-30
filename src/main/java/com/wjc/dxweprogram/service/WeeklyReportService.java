package com.wjc.dxweprogram.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjc.dxweprogram.common.Result;
import com.wjc.dxweprogram.entity.DailyReport;
import com.wjc.dxweprogram.entity.WeeklyReport;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface WeeklyReportService extends IService<WeeklyReport> {

    public boolean updateDeclaredCount(int userId, LocalDate reportDate, int declaredCount);

    public Result<List<DailyReport>> getWeeklyReport(int workNumber);

    public Result<List<List<int[]>>> getBusinessDetails(int workNumber);

    public WeeklyReport findByUserIdAndDate(int userId, LocalDate date);

}
