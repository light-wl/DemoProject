package com.light.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.light.dto.UserInfoDTO;
import com.light.mapper.UserInfoMapper;
import com.light.model.UserInfo;
import com.light.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
//        this.mybatisLessonMethod(dto);
        this.batchUpdateMethod();
    }

    private void mybatisLessonMethod(UserInfoDTO dto) {
        // 开启分页功能，pageNum 表示当前页码，pageSize 表示每页显示的记录数
        PageHelper.startPage(1, 20);
        List<UserInfo> userInfoList = userInfoMapper.listUserInfo(dto);
        PageInfo pageInfo = new PageInfo<>(userInfoList);
        System.out.println(pageInfo.getPageNum());
        System.out.println(userInfoList);
    }

    private void batchUpdateMethod(){
        // 1、构造参数
        List<UserInfo> userInfoList = new ArrayList<>();
        UserInfo userInfoOne = new UserInfo();
        userInfoOne.setId(1L);
        userInfoOne.setName("小明1");

        UserInfo userInfoTwo = new UserInfo();
        userInfoTwo.setId(4L);
        userInfoTwo.setName("小红4");

        userInfoList.add(userInfoOne);
        userInfoList.add(userInfoTwo);

        // 2、批量更新
        userInfoMapper.batchUpdateName(userInfoList);

    }
}
