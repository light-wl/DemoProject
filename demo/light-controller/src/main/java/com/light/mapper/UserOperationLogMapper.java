package com.light.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.light.model.UserOperationLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author light
 * @Date 2023/6/28
 * @Desc
 **/
@Mapper
public interface UserOperationLogMapper extends BaseMapper<UserOperationLog> {
}
