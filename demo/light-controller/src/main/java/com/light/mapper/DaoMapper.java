package com.light.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.light.model.ShardingUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author light
 * @Date 2023/6/29
 * @Desc
 **/
@Mapper
public interface DaoMapper extends BaseMapper<ShardingUser> {
    /**
     * 1、传递零散参数
     * 方式一：传入一个参数时，可以不用添加 Param 注解
     * 方式二：传入多个参数时，也可以不用添加 Param 注解，用 #{0} #{1} 区分
     * 方式三：使用 @Param 注解
     * 前两种方式现在都不用了，一般零散参数都是这么传递的
     */
    void createShardingTable(@Param("date") String date);

    /**
     * 2、传入实体类
     * 可以直接调用里面的参数
     */
    UserInfo getUserInfoOne(UserInfo user);

    /**
     * 3、传入Map
     * 当传入的是 map 时，只需要取对应的 key 即可
     */
    UserInfo getUserInfoTwo(Map<String, String> map);

    /**
     * 3、传入Map
     * 当传入map的值是一个对象时，也一样，只需要取key就能拿到这个对象
     */
    UserInfo getUserInfoThree(Map<String, UserInfo> map);

    /**
     * 4、传入List
     * 需要循环构造条件
     */
    UserInfo getUserInfoFour(List<Integer> ids);

    /**
     * 5、传入数组
     * 需要循环构造条件
     */
    UserInfo getUserInfoFive(Integer[] ids);

    /**
     * 6、传入实体类 + 单个参数
     * 需要循环构造条件
     */
    UserInfo getUserInfoSix(@Param("userInfo") UserInfo user, @Param("age") Integer age);
}
