package com.light.controller;

import com.alibaba.fastjson.JSONObject;
import com.light.dto.UserInfoDTO;
import com.light.model.Response;
import com.light.service.BeanService;
import com.light.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author light
 * @Date 2023/8/29
 * @Desc 将日常使用到的 Controller 中的注解，使用并解释一下
 * @RestController = @ResponseBody + @Controller
 * @ResponseBody : 用于返回 JSON 数据，加在类上相当于给每个方法都加上了这个注解
 * @Controller：将这个类交给 Spring 管理，类似还有 @Service, @Component
 **/

@RestController
@RequestMapping("/RestControllerDemo")
public class RestLessonController {
    /**
     * @Autowired 默认根据类型进行自动注入
     * @Qualifier 当出现多个相同类型时，可以指定名称
     */
    @Autowired
    @Qualifier("beanService")
    private BeanService beanService;

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping
    public Response getUserInfo(@RequestBody UserInfoDTO dto) {
        userInfoService.getUserInfo(dto.getId());
        return Response.success();
    }


    /**
     * 1、常见写法，后出现 @PostMapping 简便的写法，缺点是 @PostMapping 只能作用于方法上，不能作用于类上
     */
    @RequestMapping(value = "/postOne", method = RequestMethod.POST)
    public Response postOne(@RequestBody UserInfoDTO dto) {
        return Response.success(dto.getName());
    }

    /**
     * POST 有三种接收参数的方式，但是只能用 @RequestBody 接受参数，因为前端默认 POST 传递 JSON 格式的数据
     * 当然也可以让前端使用POST，同时使用 ContentType:x-www-form-urlencoded 把参数拼接到URL上，但是不推荐这么做。
     * 方式一：最常用，使用一个对象接受参数，适用于传递较多参数的场景
     */
    @PostMapping("/postTwo")
    public Response postTwo(@RequestBody UserInfoDTO dto) {
        return Response.success(dto.getName());
    }

    /**
     * 当 POST 只有一个参数时，封装个对象有点太过繁琐，可以使用以下两种做法：
     * 第二种做法：
     */
    @PostMapping("/postThree")
    public Response postThree(@RequestBody Map<String, Object> param) {
        String name = (String) param.get("name");
        return Response.success(name);
    }

    /**
     * 第三种方式：接收的是一个 JSON 字符串，所以需要使用 FastJSON 进行解析
     */
    @PostMapping("/postFour")
    public Response postFour(@RequestBody String jsonStr) {
        JSONObject parse = JSONObject.parseObject(jsonStr);
        String name = (String) parse.get("name");
        return Response.success(name);
    }

    /**
     * POST：错误示例一 这样是接收不到任何参数的
     */
    @PostMapping("/postFive")
    public Response postFive(String name) {
        return Response.success(name);
    }

    /**
     * POST：错误示例二 这样写会直接导致报错，因为 @RequestParam 需要从URL上获取参数，并且默认是必填字段，否则无法进入方法
     */
    @PostMapping("/postSix")
    public Response postSix(@RequestParam("name") String name) {
        return Response.success(name);
    }

    /**
     * GET：方式一
     * GET 默认使用 @RequestParam，只能接收拼接到 URL 地址上的参数，因为前端默认 GET 是拼接在 URL 上的
     * 注意1：这里默认参数 id 是必填，如果不是必填，
     * 需要修改属性 @RequestParam(value = "id", required = false, default = 1)
     */
    @GetMapping("/getOne")
    public Response getOne(@RequestParam("id") Integer id) {
        return Response.success(id);
    }

    /**
     * GET：方式二
     * 当是get请求时，如果前端传递的参数过多，可以使用map接收
     */
    @GetMapping("/getTwo")
    public Response getOne(@RequestParam Map<String, Object> paramMap) {
        return Response.success(paramMap);
    }

    /**
     * GET：方式三
     * 获取链接上的ID
     */
    @GetMapping("/getTwo/{id}")
    public Response getTwo(@PathVariable("id") Integer id) {
        return Response.success(id);
    }

}
