package com.light.demo;

import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author light
 * @Date 2023/2/22 10:14
 **/
@Component
public class ServiceThree {
    private  static  int  st =  0 ;       //静态的
     private  int  index =  0 ;     //非静态

     public void test() {
         System.out.println(st++ +  " | "  + index++);
     }
}
