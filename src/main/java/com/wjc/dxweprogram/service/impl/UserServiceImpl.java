package com.wjc.dxweprogram.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjc.dxweprogram.entity.User;
import com.wjc.dxweprogram.mapper.UserMapper;
import com.wjc.dxweprogram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByNameAndWorkNumber(String name, String workNumber) {
        User u = userMapper.findByWorkNumber(workNumber);
        if(u.getName().equals(name)){
            return u;
        }
        else
            return null;
    }

    public User findByWorkNumber(int workNumber){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("work_number", workNumber);

        return userMapper.selectOne(queryWrapper);
    }

}
