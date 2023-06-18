package com.light.service;

import com.light.aop.LogRecord;
import com.light.model.Student;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashSet;

/**
 * @Author light
 * @Date 2023/2/24 14:56
 * @Desc
 **/
@Service
public class RewriteEqualsService {

    @LogRecord(content = "日志注解测试")
    public void logTest() {
        System.out.println("会在这行的输出上下方打印日志");
    }
}

