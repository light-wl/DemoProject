package com.light.mapper;

import com.light.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author light
 * @Date 2023/9/6
 * @Desc 学习DAO层如何传参
 **/
@RestController
@RequestMapping("/DAO/")
public class DaoController {

    @Autowired
    private DaoMapper daoMapper;

    @RequestMapping("getUserInfoOne")
    public Response getUserInfoOne() {
        UserInfo userInfo = new UserInfo();
        userInfo.setSex(1);
        UserInfo result = daoMapper.getUserInfoOne(userInfo);
        return Response.success(result);
    }

    @RequestMapping("getUserInfoTwo")
    public Response getUserInfoTwo() {
        Map<String, String> map = new HashMap<>();
        map.put("sex", "1");
        UserInfo result = daoMapper.getUserInfoTwo(map);
        return Response.success(result);
    }

    @RequestMapping("getUserInfoThree")
    public Response getUserInfoThree() {
        UserInfo userInfo = new UserInfo();
        userInfo.setSex(1);
        Map<String, UserInfo> map = new HashMap<>();
        map.put("user", userInfo);
        UserInfo result = daoMapper.getUserInfoThree(map);
        return Response.success(result);
    }

    @RequestMapping("getUserInfoFour")
    public Response getUserInfoFour() {
        List<Integer> ids = new ArrayList<>();
        ids.add(6);
        UserInfo result = daoMapper.getUserInfoFour(ids);
        return Response.success(result);
    }

    @RequestMapping("getUserInfoFive")
    public Response getUserInfoFive() {
        Integer[] ids = new Integer[]{6};
        UserInfo result = daoMapper.getUserInfoFive(ids);
        return Response.success(result);
    }

    @RequestMapping("getUserInfoSix")
    public Response getUserInfoSix() {
        UserInfo userInfo = new UserInfo();
        userInfo.setSex(1);
        UserInfo result = daoMapper.getUserInfoSix(userInfo, 12);
        return Response.success(result);
    }
}
