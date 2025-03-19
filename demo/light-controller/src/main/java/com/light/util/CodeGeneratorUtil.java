package com.light.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author light
 * @Date 2025/3/19
 * @Desc
 **/
public class CodeGeneratorUtil {

    private static String TEMPLATE_DIR = "/light-controller/src/main/resources/templates";
    private static String OUTPUT_DIR = "/light-controller/src/main/java/com/light";

    public static void main(String[] args) {
        String projectRoot = new File("").getAbsolutePath();
        TEMPLATE_DIR = projectRoot + TEMPLATE_DIR;
        OUTPUT_DIR = projectRoot + OUTPUT_DIR;
        String entityName = "Article";
        generateCode(entityName);
    }

    public static void generateCode(String entityName) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        try {
            cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_DIR));
            cfg.setDefaultEncoding("UTF-8");

            // 生成 Controller 代码
            generateFile(cfg, "controller.ftl", entityName, "controller");
            // 生成 Service 代码
            generateFile(cfg, "service.ftl", entityName, "service");
            // 生成 Mapper 代码
            generateFile(cfg, "mapper.ftl", entityName, "mapper");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateFile(Configuration cfg, String templateName, String entityName, String packageName) {
        try {
            Template template = cfg.getTemplate(templateName);
            Map<String, Object> data = new HashMap<>();
            data.put("entityName", entityName);
            data.put("entityNameLowercase", entityName.substring(0, 1).toLowerCase() + entityName.substring(1));

            String outputPath = OUTPUT_DIR + "/" + packageName + "/" + entityName + templateName.replace(".ftl", ".java");
            File outputFile = new File(outputPath);
            outputFile.getParentFile().mkdirs();

            try (Writer writer = new FileWriter(outputFile)) {
                template.process(data, writer);
            }
            System.out.println("Generated: " + outputPath);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}

