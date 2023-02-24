package com.light.controller;

import com.light.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description
 * @Author light
 * @Date 2023/2/24 14:37
 **/
@Controller
//@Scope("prototype")
public class UserInfoController {
        private  static  int  st =  0 ;       //静态的
     private  int  index =  0 ;     //非静态

//    @Autowired
    private UserInfoService userInfoService;

     @RequestMapping( "/test" )
     public  String  test() {
//         userInfoService = SpringUtils.getBean("userInfoService");
         userInfoService.count++;
         UserInfoService.countStatic++;
         System.out.println(this);
         System.out.println(userInfoService);
         System.out.println(userInfoService.count + "|" + UserInfoService.countStatic);
         return "success";
     }
}
