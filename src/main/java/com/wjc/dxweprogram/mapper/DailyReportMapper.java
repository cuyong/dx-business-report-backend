package com.wjc.dxweprogram.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjc.dxweprogram.entity.DailyReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DailyReportMapper extends BaseMapper<DailyReport> {

    void insertBatchSomeColumn(@Param("dailyReports") List<DailyReport> dailyReports);

}
