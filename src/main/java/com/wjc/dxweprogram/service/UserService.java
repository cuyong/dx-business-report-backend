package com.wjc.dxweprogram.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjc.dxweprogram.entity.User;

public interface UserService extends IService<User> {

    User findByNameAndWorkNumber(String name, String workNumber);

    User findByWorkNumber(int workNumber);

}
