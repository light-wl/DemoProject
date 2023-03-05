package com.light.controller;

import com.light.model.Response;
import com.light.service.UserInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @Description
 * @Author light
 * @Date 2023/2/24 14:37
 **/
@RestController
//@Scope("prototype")
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;

    @PostMapping("/test")
    public Response test() {
//         userInfoService = SpringUtils.getBean("userInfoService");
        userInfoService.count++;
        UserInfoService.countStatic++;
        System.out.println(this);
        System.out.println(userInfoService);
        System.out.println(userInfoService.count + "|" + UserInfoService.countStatic);
        return new Response();
    }
}
