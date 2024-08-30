package com.wjc.dxweprogram.controller;

import com.wjc.dxweprogram.common.Result;
import com.wjc.dxweprogram.domain.param.BusinessTypeParam;
import com.wjc.dxweprogram.domain.param.UpdateDeclaredCountParam;
import com.wjc.dxweprogram.entity.BusinessType;
import com.wjc.dxweprogram.entity.DailyReport;
import com.wjc.dxweprogram.service.DailyReportBusinessTypeService;
import com.wjc.dxweprogram.service.WeeklyReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/reports")
public class WeeklyReportController {

    @Autowired
    private WeeklyReportService weeklyReportService;

    @Autowired
    private DailyReportBusinessTypeService dailyReportBusinessTypeService;

    @PutMapping("/updateDeclaredCount")
    public Result<List<DailyReport>> updateDeclaredCount(@RequestBody UpdateDeclaredCountParam param) {
        // 下行 向数据库更新数据
        int userId = param.getUserId();
        String reportDate = param.getReportDate();
        int declaredCount = param.getDeclaredCount();
        LocalDate date = LocalDate.parse(reportDate);
        if(weeklyReportService.updateDeclaredCount(userId, date, declaredCount)){
            return weeklyReportService.getWeeklyReport(userId);
        }

        return Result.error("更新错误");
    }

    @GetMapping("/getWeeklyReport")
    public Result<List<DailyReport>> getWeeklyReport(@RequestParam int workNumber) {
        return weeklyReportService.getWeeklyReport(workNumber);
    }

    @GetMapping("/getBusinessDetails")
    public Result<List<List<int[]>>> getBusinessDetails(@RequestParam int workNumber){
        return weeklyReportService.getBusinessDetails(workNumber);
    }

    @PostMapping("/addBusinessType")
    public Result<BusinessType> addBusinessType(@RequestBody BusinessTypeParam param){
        // 根据用户的工号、修改的report date、当天商机的顺次、商机类型进行新增
        int userNumber = param.getWorkNumber();
        LocalDate date = getDateOfCurrentWeek(param.getDay(), LocalDate.now());
        int businessOrder = param.getBusinessOrder() + 1; // 传入的顺序数据是数组的index，所以要加1
        String businessTypeName = param.getBusinessTypeName();

        return dailyReportBusinessTypeService.addBusinessType(userNumber, date, businessOrder, businessTypeName);
    }

    @PostMapping("/deleteBusinessType")
    public Result<String> deleteBusinessType(@RequestBody BusinessTypeParam param){
        // 根据用户的工号、修改的report date、当天商机的顺次、商机类型进行删除
        int userNumber = param.getWorkNumber();
        LocalDate date = getDateOfCurrentWeek(param.getDay(), LocalDate.now());
        int businessOrder = param.getBusinessOrder() + 1; // 传入的顺序数据是数组的index，所以要加1
        String businessTypeName = param.getBusinessTypeName();

        return dailyReportBusinessTypeService.deleteBusinessType(userNumber, date, businessOrder, businessTypeName);
    }

    public static LocalDate getDateOfCurrentWeek(String day, LocalDate referenceDate) {
        // 将字符串形式的星期几转换为 DayOfWeek 枚举
        DayOfWeek targetDayOfWeek = DayOfWeek.valueOf(day.toUpperCase(Locale.ROOT));

        // 获取参考日期对应的星期几
        DayOfWeek currentDayOfWeek = referenceDate.getDayOfWeek();

        // 计算当前日期与本周一的偏移量
        int dayDifference = targetDayOfWeek.getValue() - currentDayOfWeek.getValue();

        // 计算目标日期
        LocalDate targetDate = referenceDate.plusDays(dayDifference);

        return targetDate;
    }

}
