package com.light.service;

import com.light.mapper.UserInfoMapper;
import com.light.model.UserInfo;
import com.light.redis.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

/**
 * @Description
 * @Author light
 * @Date 2023/2/24 14:39
 **/
@Service
@Scope(value = "prototype")
public class UserInfoService {

    @Autowired
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
