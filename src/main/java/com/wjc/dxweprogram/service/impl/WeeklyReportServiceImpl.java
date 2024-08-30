package com.wjc.dxweprogram.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjc.dxweprogram.common.Result;
import com.wjc.dxweprogram.entity.DailyReport;
import com.wjc.dxweprogram.entity.DailyReportBusinessType;
import com.wjc.dxweprogram.entity.User;
import com.wjc.dxweprogram.entity.WeeklyReport;
import com.wjc.dxweprogram.mapper.DailyReportBusinessTypeMapper;
import com.wjc.dxweprogram.mapper.DailyReportMapper;
import com.wjc.dxweprogram.mapper.UserMapper;
import com.wjc.dxweprogram.mapper.WeeklyReportMapper;
import com.wjc.dxweprogram.service.UserService;
import com.wjc.dxweprogram.service.WeeklyReportService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

@Service
public class WeeklyReportServiceImpl extends ServiceImpl<WeeklyReportMapper, WeeklyReport> implements WeeklyReportService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WeeklyReportMapper weeklyReportMapper;

    @Autowired
    private DailyReportMapper dailyReportMapper;

    @Autowired
    private DailyReportBusinessTypeMapper dailyReportBusinessTypeMapper;

    @Autowired
    private UserService userService;

    public boolean updateDeclaredCount(int userId, LocalDate reportDate, int declaredCount) {
        // 获取周的开始和结束日期
        LocalDate startOfWeek = reportDate.with(java.time.DayOfWeek.MONDAY);
        LocalDate endOfWeek = reportDate.with(java.time.DayOfWeek.SUNDAY);

        //根据用户的工号去user表中找其对应的主键值
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("work_number", userId);
        User user = userMapper.selectOne(userQueryWrapper);

        // 查找当前用户的当周周报表
        QueryWrapper<WeeklyReport> weeklyReportQuery = new QueryWrapper<>();
        weeklyReportQuery.eq("user_id", user.getUserId())
                .eq("week_start_date", startOfWeek)
                .eq("week_end_date", endOfWeek);
        WeeklyReport weeklyReport = weeklyReportMapper.selectOne(weeklyReportQuery);

        if (weeklyReport == null) {
            // 如果没有当周周报表，则创建新的周报表
            weeklyReport = new WeeklyReport();
            weeklyReport.setUserId(user.getUserId());
            weeklyReport.setWeekStartDate(startOfWeek);
            weeklyReport.setWeekEndDate(endOfWeek);
            weeklyReportMapper.insert(weeklyReport);

            // 创建当周的七条日报表记录
            List<DailyReport> dailyReports = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                DailyReport dailyReport = new DailyReport();
                dailyReport.setWeeklyReportId(weeklyReport.getWeeklyReportId());
                dailyReport.setReportDate(startOfWeek.plusDays(i));
                dailyReports.add(dailyReport);
            }
            dailyReportMapper.insertBatchSomeColumn(dailyReports); // 假设你使用了批量插入的方法
        }

        // 查找并更新对应日期的日报表
        QueryWrapper<DailyReport> dailyReportQuery = new QueryWrapper<>();
        dailyReportQuery.eq("weekly_report_id", weeklyReport.getWeeklyReportId())
                .eq("report_date", reportDate);
        DailyReport dailyReport = dailyReportMapper.selectOne(dailyReportQuery);
        if (dailyReport != null) {
            dailyReport.setDeclaredCount(declaredCount);
            dailyReportMapper.updateById(dailyReport);
            return true;
        }
        return false;
    }

    public Result<List<DailyReport>> getWeeklyReport(int workNumber) {
        // 获取当前日期和对应的周开始和结束日期
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(java.time.DayOfWeek.MONDAY);
        LocalDate endOfWeek = today.with(java.time.DayOfWeek.SUNDAY);

        //根据用户的工号去user表中找其对应的主键值
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("work_number", workNumber);
        User user = userMapper.selectOne(userQueryWrapper);

        // 查找对应周的所有日报数据
        QueryWrapper<DailyReport> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("weekly_report_id", getWeeklyReportId(user.getUserId(), startOfWeek, endOfWeek))
                .between("report_date", startOfWeek, endOfWeek);

        List<DailyReport> dailyReports = dailyReportMapper.selectList(queryWrapper);

        // 将日报数据按日期分组
        if(dailyReports.size() == 7){
            return Result.success(dailyReports);
        }

        return Result.error("查询错误");

    }

    private int getWeeklyReportId(int userId, LocalDate startOfWeek, LocalDate endOfWeek) {
        // 这里假设你已经有方法来获取WeeklyReport的ID
        // 如果没有，可以实现一个方法来查找WeeklyReport并返回其ID
        // 例如：
         QueryWrapper<WeeklyReport> queryWrapper = new QueryWrapper<>();
         queryWrapper.eq("user_id", userId)
                     .eq("week_start_date", startOfWeek)
                     .eq("week_end_date", endOfWeek);
         WeeklyReport weeklyReport = weeklyReportMapper.selectOne(queryWrapper);
         return weeklyReport != null ? weeklyReport.getWeeklyReportId() : -1;

    }

    public Result<List<List<int[]>>> getBusinessDetails(int workNumber){
        // 获取当前的日期和对应的周开始和周结束日期
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);

        // 根据工号找user表中找该员工对应的id
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("work_number", workNumber);
        User user = userMapper.selectOne(userQueryWrapper);

        // 根据userId查找该员工一周的daily报表，先获得一周商机数表，DailyReport
        QueryWrapper<DailyReport> dailyReportQueryWrapper = new QueryWrapper<>();
        dailyReportQueryWrapper.eq("weekly_report_id", getWeeklyReportId(user.getUserId(), startOfWeek, endOfWeek))
                .between("report_date", startOfWeek, endOfWeek);

        List<DailyReport> dailyReports = dailyReportMapper.selectList(dailyReportQueryWrapper);

        if(dailyReports.size() != 7){
            return Result.error("获取商机详情错误");
        }

        // 循环处理
        List<List<int[]>> res = new ArrayList();
        for(DailyReport report : dailyReports){
            List<int[]> dailyBusiness = new ArrayList<>();
            int dailyBusinessNum = report.getDeclaredCount();
            for(int i = 1; i <= dailyBusinessNum; i++){
                QueryWrapper<DailyReportBusinessType> detailsQueryWrapper = new QueryWrapper<>();
                detailsQueryWrapper.eq("daily_report_id", report.getDailyReportId())
                        .eq("business_order", i);
                List<DailyReportBusinessType> dailyReportBusinessTypes = dailyReportBusinessTypeMapper.selectList(detailsQueryWrapper);
                int detailsNum = dailyReportBusinessTypes.size();
                int[] businessType = new int[detailsNum];
                for(int j = 0; j < detailsNum; j++){
                    businessType[j] = dailyReportBusinessTypes.get(j).getBusinessTypeId();
                }
                dailyBusiness.add(businessType);
            }
            res.add(dailyBusiness);
        }

        return Result.success(res);
    }

    public WeeklyReport findByUserIdAndDate(int workNumber, LocalDate date){
        // 根据workNumber和当天日期，找到对应的week记录
        QueryWrapper<WeeklyReport> weeklyWrapper = new QueryWrapper<>();
        weeklyWrapper.eq("user_id", userService.findByWorkNumber(workNumber).getUserId())
                .le("week_start_date", date)
                .ge("week_end_date", date);
        WeeklyReport wr = weeklyReportMapper.selectOne(weeklyWrapper);

        return wr;
    }

}
