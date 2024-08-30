package com.wjc.dxweprogram.controller;

import com.wjc.dxweprogram.common.Result;
import com.wjc.dxweprogram.entity.User;
import com.wjc.dxweprogram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 用户登录接口
    @PostMapping("/login")
    public Result<User> login(@RequestBody User loginRequest) {
        // 从请求中获取姓名和工号
        String name = loginRequest.getName();
        String workNumber = loginRequest.getWorkNumber();

        // 查询用户是否存在
        User user = userService.findByNameAndWorkNumber(name, workNumber);

        if (user != null) {
            // 登录成功
            return Result.success(user);
        } else {
            // 登录失败
            return Result.error("用户名或工号错误");
        }
    }

    // 查询用户信息
    @GetMapping("/{userId}")
    public Result<User> getUserById(@PathVariable Integer userId) {
        User user = userService.getById(userId);
        if (user != null) {
            return Result.success(user);
        } else {
            return Result.error("用户未找到");
        }
    }
}
