package com.light.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;

/**
 * @Author light
 * @Date 2023/5/15
 * @Desc 根据数据库表，生成对应的文件
 **/
public class TableGeneratorUtil {
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        // 全局策略配置
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir"); //项目路径

    }
}
