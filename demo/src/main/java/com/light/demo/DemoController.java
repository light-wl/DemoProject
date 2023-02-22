package com.light.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description
 * @Author light
 * @Date 2023/2/22 11:18
 **/
@Controller
//@Scope("prototype")
public class DemoController {
    private  static  int  st =  0 ;       //静态的
     private  int  index =  0 ;     //非静态

    @Autowired
    private ServiceOne serviceOne;

     @RequestMapping( "/test" )
     public  String  test() {

         serviceOne.count++;
         System.out.println(serviceOne.count);
         return "success";
     }
}
