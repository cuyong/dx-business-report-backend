package com.wjc.dxweprogram.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjc.dxweprogram.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT * FROM user WHERE work_number = #{workNumber}")
    User findByWorkNumber(String workNumber);

    User findByNameAndWorkNumber(String name, String workNumber);

}
