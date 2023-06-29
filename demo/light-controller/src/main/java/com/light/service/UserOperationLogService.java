package com.light.service;

import com.light.mapper.UserOperationLogMapper;
import com.light.model.UserOperationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author light
 * @Date 2023/6/28
 * @Desc
 **/
@Service
public class UserOperationLogService {
    @Autowired
    private UserOperationLogMapper operationLogMapper;

    public UserOperationLog getUserDataById(int id){
        return operationLogMapper.selectById(id);
    }
}
