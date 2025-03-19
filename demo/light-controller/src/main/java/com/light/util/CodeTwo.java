package com.light.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.*;
import java.util.*;

public class CodeTwo {

    private static String TEMPLATE_DIR = "/light-controller/src/main/resources/templates";
    private static String OUTPUT_DIR = "/light-controller/src/main/java/com/light";
    private static String MAPPER_XML_OUTPUT_DIR = "/light-controller/src/main/resources/mapper";

    // 数据库连接信息，需要根据实际情况修改
    private static final String DB_URL = "jdbc:mysql://localhost:3306/info_share";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";

    public static void main(String[] args) {
        // 修改目录为绝对路径
        String projectRoot = new File("").getAbsolutePath();
        TEMPLATE_DIR = projectRoot + TEMPLATE_DIR;
        OUTPUT_DIR = projectRoot + OUTPUT_DIR;
        MAPPER_XML_OUTPUT_DIR = projectRoot + MAPPER_XML_OUTPUT_DIR;

        String entityName = "Article";
        String tableName = "article"; // 这里需要根据实际表名修改
        generateCode(entityName, tableName);
    }

    public static void generateCode(String entityName, String tableName) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        try {
            File templateDir = new File(TEMPLATE_DIR);
            if (!templateDir.exists()) {
                System.err.println("模板目录不存在: " + TEMPLATE_DIR);
                return;
            }
            cfg.setDirectoryForTemplateLoading(templateDir);
            cfg.setDefaultEncoding("UTF-8");

            // 获取表字段信息
            List<Map<String, String>> fields = getTableFields(tableName);

            // 生成 Controller 代码
            generateFile(cfg, "controller.ftl", entityName, tableName, "controller");
            // 生成 Service 代码
            generateFile(cfg, "service.ftl", entityName, tableName, "service");
            // 生成 Mapper 代码
            generateFile(cfg, "mapper.ftl", entityName, tableName, "mapper");
            // 生成 Model 代码
            generateModelFile(cfg, entityName, tableName, "model", fields);
            // 生成 Mapper.xml 代码
            generateMapperXmlFile(cfg, "mapper.xml.ftl", entityName, tableName, fields);

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static List<Map<String, String>> getTableFields(String tableName) throws SQLException {
        List<Map<String, String>> fields = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " LIMIT 0")) {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                Map<String, String> field = new HashMap<>();
                field.put("name", metaData.getColumnName(i));
                field.put("type", getJavaType(metaData.getColumnType(i)));
                fields.add(field);
            }
        }
        return fields;
    }

    private static String getJavaType(int sqlType) {
        switch (sqlType) {
            case Types.INTEGER:
                return "Integer";
            case Types.BIGINT:
                return "Long";
            case Types.VARCHAR:
                return "String";
            case Types.DATE:
                return "java.util.Date";
            case Types.TIMESTAMP:
                return "java.sql.Timestamp";
            default:
                return "Object";
        }
    }

    private static void generateFile(Configuration cfg, String templateName, String entityName, String tableName, String packageName) {
        try {
            Template template = cfg.getTemplate(templateName);
            Map<String, Object> data = new HashMap<>();
            data.put("entityName", entityName);
            data.put("entityNameLowercase", entityName.substring(0, 1).toLowerCase() + entityName.substring(1));
            data.put("tableName", tableName);

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

    private static void generateModelFile(Configuration cfg, String entityName, String tableName, String packageName, List<Map<String, String>> fields) {
        try {
            StringBuilder modelCode = new StringBuilder();
            modelCode.append("package com.example.demo.entity;\n\n");
            modelCode.append("import java.io.Serializable;\n\n");
            modelCode.append("public class ").append(entityName).append(" implements Serializable {\n\n");
            modelCode.append("    private static final long serialVersionUID = 1L;\n\n");

            // 生成字段
            for (Map<String, String> field : fields) {
                String fieldName = field.get("name");
                String fieldType = field.get("type");
                modelCode.append("    private ").append(fieldType).append(" ").append(fieldName).append(";\n");
            }

            modelCode.append("\n");

            // 生成 getter 和 setter 方法
            for (Map<String, String> field : fields) {
                String fieldName = field.get("name");
                String fieldType = field.get("type");
                String capitalizedFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                modelCode.append("    public ").append(fieldType).append(" get").append(capitalizedFieldName).append("() {\n");
                modelCode.append("        return ").append(fieldName).append(";\n");
                modelCode.append("    }\n\n");
                modelCode.append("    public void set").append(capitalizedFieldName).append("(").append(fieldType).append(" ").append(fieldName).append(") {\n");
                modelCode.append("        this.").append(fieldName).append(" = ").append(fieldName).append(";\n");
                modelCode.append("    }\n\n");
            }

            modelCode.append("}");

            String outputPath = OUTPUT_DIR + "/" + packageName + "/" + entityName + ".java";
            File outputFile = new File(outputPath);
            outputFile.getParentFile().mkdirs();

            try (Writer writer = new FileWriter(outputFile)) {
                writer.write(modelCode.toString());
            }
            System.out.println("Generated: " + outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateMapperXmlFile(Configuration cfg, String templateName, String entityName, String tableName, List<Map<String, String>> fields) {
        try {
            Template template = cfg.getTemplate(templateName);
            Map<String, Object> data = new HashMap<>();
            data.put("entityName", entityName);
            data.put("tableName", tableName);
            data.put("fields", fields);

            String outputPath = MAPPER_XML_OUTPUT_DIR + "/" + entityName + "Mapper.xml";
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