package com.wjc.dxweprogram.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjc.dxweprogram.common.Result;
import com.wjc.dxweprogram.entity.BusinessType;
import com.wjc.dxweprogram.entity.DailyReport;
import com.wjc.dxweprogram.entity.DailyReportBusinessType;
import com.wjc.dxweprogram.entity.WeeklyReport;
import com.wjc.dxweprogram.mapper.DailyReportBusinessTypeMapper;
import com.wjc.dxweprogram.mapper.DailyReportMapper;
import com.wjc.dxweprogram.mapper.UserMapper;
import com.wjc.dxweprogram.mapper.WeeklyReportMapper;
import com.wjc.dxweprogram.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DailyReportBusinessTypeServiceImpl extends ServiceImpl<DailyReportBusinessTypeMapper, DailyReportBusinessType> implements DailyReportBusinessTypeService {

    @Autowired
    private DailyReportBusinessTypeMapper dailyReportBusinessTypeMapper;

    @Autowired
    private WeeklyReportMapper weeklyReportMapper;

    @Autowired
    private DailyReportMapper dailyReportMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private WeeklyReportService weeklyReportService;

    @Autowired
    private DailyReportService dailyReportService;

    @Autowired
    private BusinessTypeService businessTypeService;

    // 添加商机对应的商机类型
    public Result<BusinessType> addBusinessType(int workNumber, LocalDate date, int businessOrder, String businessTypeName){
        // 根据userId和当天日期，找到对应的week记录
        WeeklyReport wr = weeklyReportService.findByUserIdAndDate(workNumber, date);

        // 根据week_report_id和date，找到对应的date记录
        DailyReport dr = dailyReportService.findByWeeklyIdAndDate(wr.getWeeklyReportId(), date);

        // 根据businessTypeName，找到对应的typeId
        BusinessType businessType = businessTypeService.findByBusinessTypeName(businessTypeName);

        if(businessType.getBusinessTypeId() != 8){
            DailyReportBusinessType type = new DailyReportBusinessType();
            type.setDailyReportId(dr.getDailyReportId());
            type.setBusinessTypeId(businessType.getBusinessTypeId());
            type.setBusinessOrder(businessOrder);

            int res = dailyReportBusinessTypeMapper.insert(type);
            if(res > 0){
                return Result.success(businessType);
            }
        }

        return Result.error("添加商机类型错误");
    }

    public Result<String> deleteBusinessType(int workNumber, LocalDate date, int businessOrder, String businessTypeName){
        // 根据userId和当天日期，找到对应的week记录
        WeeklyReport wr = weeklyReportService.findByUserIdAndDate(workNumber, date);
        // 根据week_report_id和date，找到对应的date记录
        DailyReport dr = dailyReportService.findByWeeklyIdAndDate(wr.getWeeklyReportId(), date);
        // 根据businessTypeName，找到对应的typeId
        BusinessType businessType = businessTypeService.findByBusinessTypeName(businessTypeName);

        if(businessType.getBusinessTypeId() != 8){
            QueryWrapper<DailyReportBusinessType> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("daily_report_id", dr.getDailyReportId())
                        .eq("business_type_id", businessType.getBusinessTypeId())
                        .eq("business_order", businessOrder);

            int res = dailyReportBusinessTypeMapper.delete(queryWrapper);
            if(res > 0){
                return Result.success("删除成功");
            }
        }

        return Result.error("删除失败");

    }

}
