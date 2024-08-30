package com.wjc.dxweprogram.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjc.dxweprogram.entity.DailyReport;

import java.time.LocalDate;

public interface DailyReportService extends IService<DailyReport> {

    DailyReport findByWeeklyIdAndDate(int weeklyReportId, LocalDate date);

}
