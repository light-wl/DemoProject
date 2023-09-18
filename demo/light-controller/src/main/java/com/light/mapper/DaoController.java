package com.light.mapper;

import com.light.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
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
    private DaoParamMapper paramMapper;

    @Autowired
    private DaoXMLMapper xmlMapper;

    /**
     * paramMapper测试Controller
     */
    @RequestMapping("getUserInfoOne")
    public Response getUserInfoOne() {
        UserInfo userInfo = new UserInfo();
        userInfo.setSex(1);
        UserInfo result = paramMapper.getUserInfoOne(userInfo);
        return Response.success(result);
    }

    @RequestMapping("getUserInfoTwo")
    public Response getUserInfoTwo() {
        Map<String, String> map = new HashMap<>();
        map.put("sex", "1");
        UserInfo result = paramMapper.getUserInfoTwo(map);
        return Response.success(result);
    }

    @RequestMapping("getUserInfoThree")
    public Response getUserInfoThree() {
        UserInfo userInfo = new UserInfo();
        userInfo.setSex(1);
        Map<String, UserInfo> map = new HashMap<>();
        map.put("user", userInfo);
        UserInfo result = paramMapper.getUserInfoThree(map);
        return Response.success(result);
    }

    @RequestMapping("getUserInfoFour")
    public Response getUserInfoFour() {
        List<Integer> ids = new ArrayList<>();
        ids.add(6);
        UserInfo result = paramMapper.getUserInfoFour(ids);
        return Response.success(result);
    }

    @RequestMapping("getUserInfoFive")
    public Response getUserInfoFive() {
        Integer[] ids = new Integer[]{6};
        UserInfo result = paramMapper.getUserInfoFive(ids);
        return Response.success(result);
    }

    @RequestMapping("getUserInfoSix")
    public Response getUserInfoSix() {
        UserInfo userInfo = new UserInfo();
        userInfo.setSex(1);
        UserInfo result = paramMapper.getUserInfoSix(userInfo, 12);
        return Response.success(result);
    }

    /**
     * xmlMapper测试Controller
     * */
}
