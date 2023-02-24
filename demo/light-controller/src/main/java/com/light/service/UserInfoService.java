package com.light.service;

import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author light
 * @Date 2023/2/24 14:39
 **/
@Service
//@Scope(value = "prototype")
public class UserInfoService {
    public int count = 0;

    public static int countStatic = 0;
}
