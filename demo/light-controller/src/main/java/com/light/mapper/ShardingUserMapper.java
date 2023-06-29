package com.light.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.light.model.ShardingUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author light
 * @Date 2023/6/29
 * @Desc
 **/
@Mapper
public interface ShardingUserMapper extends BaseMapper<ShardingUser> {
    void createShardingTable(@Param("date") String date);
}
