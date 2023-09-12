package com.light.aop;

import com.light.aop.LogRecord;
import org.springframework.stereotype.Service;

/**
 * @Author light
 * @Date 2023/2/24 14:56
 * @Desc
 **/
@Service
public class AnnotationService {

    @LogRecord(content = "日志注解测试")
    public void logTest() {
        System.out.println("会在这行的输出上下方打印日志");
    }
}

