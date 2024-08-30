package com.wjc.dxweprogram.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjc.dxweprogram.entity.BusinessType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BusinessTypeMapper extends BaseMapper<BusinessType> {
    // 这里可以添加自定义的查询方法
}
