package com.wjc.dxweprogram.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjc.dxweprogram.entity.BusinessType;

public interface BusinessTypeService extends IService<BusinessType> {
    // 这里可以定义业务相关的接口方法

    BusinessType findByBusinessTypeName(String name);

}
