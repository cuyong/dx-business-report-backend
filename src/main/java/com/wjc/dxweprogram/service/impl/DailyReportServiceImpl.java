package com.wjc.dxweprogram.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjc.dxweprogram.entity.DailyReport;
import com.wjc.dxweprogram.mapper.DailyReportMapper;
import com.wjc.dxweprogram.service.DailyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DailyReportServiceImpl extends ServiceImpl<DailyReportMapper, DailyReport> implements DailyReportService {

    @Autowired
    DailyReportMapper dailyReportMapper;

    public DailyReport findByWeeklyIdAndDate(int weeklyReportId, LocalDate date){

        QueryWrapper<DailyReport> dailyWrapper = new QueryWrapper<>();
        dailyWrapper.eq("weekly_report_id", weeklyReportId)
                .eq("report_date", date);

        return dailyReportMapper.selectOne(dailyWrapper);

    }

}
