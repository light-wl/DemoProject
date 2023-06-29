package com.light.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.light.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author light
 * @Date 2023/5/15
 * @Desc 1、添加mapper 注解
 * 2、在启动类中，添加扫描位置，否则无法识别出此mapper
 **/
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
