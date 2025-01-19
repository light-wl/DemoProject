package light.lesson.ligntdao.controller;

import light.lesson.ligntdao.common.model.Response;
import light.lesson.ligntdao.common.model.User;
import light.lesson.ligntdao.mapper.UserMapper;
import light.lesson.ligntdao.service.IUserService;
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
@RequestMapping("/MybatisLesson/")
public class MybatisLessonController {

    @Autowired
    private IUserService userService;

    /**
     * userService测试Controller
     */
    @RequestMapping("getUserInfoOne")
    public Response getUserInfoOne() {
        User userInfoModel = new User();
        userInfoModel.setSex(1);
        return Response.success(null);
    }

    @RequestMapping("getUserInfoTwo")
    public Response getUserInfoTwo() {
        Map<String, String> map = new HashMap<>();
        map.put("sex", "1");
        User result = userService.getUserInfoTwo(map);
        return Response.success(result);
    }

    @RequestMapping("getUserInfoThree")
    public Response getUserInfoThree() {
        User userInfoModel = new User();
        userInfoModel.setSex(1);
        Map<String, User> map = new HashMap<>();
        map.put("user", userInfoModel);
        User result = userService.getUserInfoThree(map);
        return Response.success(result);
    }

    @RequestMapping("getUserInfoFour")
    public Response getUserInfoFour() {
        List<Integer> ids = new ArrayList<>();
        ids.add(6);
        User result = userService.getUserInfoFour(ids);
        return Response.success(result);
    }

    @RequestMapping("getUserInfoFive")
    public Response getUserInfoFive() {
        Integer[] ids = new Integer[]{6};
        User result = userService.getUserInfoFive(ids);
        return Response.success(result);
    }

    @RequestMapping("getUserInfoSix")
    public Response getUserInfoSix() {
        User userInfoModel = new User();
        userInfoModel.setSex(1);
        User result = userService.getUserInfoSix(userInfoModel, 12);
        return Response.success(result);
    }

    /**
     * xmlMapper测试Controller
     * */
}


