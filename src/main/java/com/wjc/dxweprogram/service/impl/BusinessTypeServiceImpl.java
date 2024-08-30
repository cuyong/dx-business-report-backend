package com.wjc.dxweprogram.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjc.dxweprogram.entity.BusinessType;
import com.wjc.dxweprogram.mapper.BusinessTypeMapper;
import com.wjc.dxweprogram.service.BusinessTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessTypeServiceImpl extends ServiceImpl<BusinessTypeMapper, BusinessType> implements BusinessTypeService {
    // 这里可以实现自定义的业务逻辑

    @Autowired
    BusinessTypeMapper businessTypeMapper;

    public BusinessType findByBusinessTypeName(String name){
        QueryWrapper<BusinessType> businessWrapper = new QueryWrapper<>();
        businessWrapper.eq("name", name);

        return businessTypeMapper.selectOne(businessWrapper);
    }

}

