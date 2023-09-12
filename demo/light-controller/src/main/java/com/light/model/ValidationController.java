package com.light.model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author light
 * @Date 2023/9/6
 * @Desc
 **/
@RestController
@RequestMapping("/validation/")
public class ValidationController {

    /**
     * -@Valid和 @Validated
     *
     * -@Valid 注解，是 Bean Validation 所定义，可以添加在普通方法、构造方法、方法参数、方法返回、成员变量上，表示它们需要进行约束校验。
     * -@Validated 注解，是 Spring Validation 锁定义，可以添加在类、方法参数、普通方法上，表示它们需要进行约束校验。同时，@Validated 有 value 属性，支持分组校验。
     */

    @RequestMapping("verify")
    public void verify() {

    }
}
