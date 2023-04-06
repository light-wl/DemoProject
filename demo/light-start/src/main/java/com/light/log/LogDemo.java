package com.light.log;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
@Slf4j
public class LogDemo {
    //直接加注解可以不写下面这句
//    private static final Logger log = LoggerFactory.getLogger("类名");

    public static void main(String[] args) {
        log.info("xx");
    }
}
