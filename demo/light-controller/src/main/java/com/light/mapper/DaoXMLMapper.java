package com.light.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Author light
 * @Date 2023/9/19
 * @Desc 常见自定义XML语句
 **/
@Mapper
public interface DaoXMLMapper {
    /**
     *
     */
    UserInfoModel getUserInfo(String name);
}
