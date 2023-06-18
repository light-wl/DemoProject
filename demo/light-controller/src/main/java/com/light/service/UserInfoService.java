package com.light.service;

import com.light.mapper.UserInfoMapper;
import com.light.model.UserInfo;
import com.light.redis.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

/**
 * @Description
 * @Author light
 * @Date 2023/2/24 14:39
 * 注意：当一个单例的 Bean，使用 autowired 注解标记其属性时，你一定要注意这个属性值会被固定下来，就是会不生效；
 **/
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    public int count = 0;

    public static int countStatic = 0;

    public int register(String name, int age) {
        UserInfo user = UserInfo.builder()
                .name(name)
                .age(age).build();
        int result = userInfoMapper.insert(user);
        return result;
    }

    public UserInfo getUserInfoById(int id) {
        return userInfoMapper.selectById(id);
    }

    /**
     * 1、先查询Redis；
     * 2、如果Redis没有，则查询MySQL；
     * 3、然后同步到Redis中；
     */
    public String getUserNameById(String userId) {
        JedisUtil.connect();
        Jedis jedis = JedisUtil.getRedis();
        String name = jedis.get(userId);
        if (!StringUtils.hasLength(name)) {
            UserInfo userInfo = this.getUserInfoById(Integer.parseInt(userId));
            name = userInfo.getName();
            jedis.set(userId, name);
        }
        return name;
    }

}
