package com.light.service.impl;

import com.light.dto.UserInfoDTO;
import com.light.mapper.UserInfoMapper;
import com.light.model.UserInfo;
import com.light.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：light
 * @date ：2025/1/24 11:09:45
 * @description :
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void getUserInfo(UserInfoDTO dto) {
        this.mybatisLessonMethod(dto);
    }

    private void mybatisLessonMethod(UserInfoDTO dto){
        List<UserInfo> result = userInfoMapper.listUserInfo(dto);
        System.out.println(result.toString());
    }
}
