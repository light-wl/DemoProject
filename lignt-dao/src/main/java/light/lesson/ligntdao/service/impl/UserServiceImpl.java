package light.lesson.ligntdao.service.impl;

import light.lesson.ligntdao.common.model.User;
import light.lesson.ligntdao.mapper.UserMapper;
import light.lesson.ligntdao.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Author light
 * @Date 2025/1/19
 * @Desc
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void createShardingTable(String date) {
        userMapper.createShardingTable(date);
    }

    @Override
    public List<User> listUserInfo(User user) {
        return Collections.emptyList();
    }

    @Override
    public User getUserInfoTwo(Map<String, String> map) {
        return null;
    }

    @Override
    public User getUserInfoThree(Map<String, User> map) {
        return null;
    }

    @Override
    public User getUserInfoFour(List<Integer> ids) {
        return null;
    }

    @Override
    public User getUserInfoFive(Integer[] ids) {
        return null;
    }

    @Override
    public User getUserInfoSix(User user, Integer age) {
        return null;
    }
}
