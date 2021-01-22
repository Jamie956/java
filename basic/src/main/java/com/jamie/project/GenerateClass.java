package com.jamie.project;

/**
 * @Author: Zjm
 * @Date: 2021/1/22 11:55
 */

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.*;

import static com.jamie.utils.AllTools.fileTextString;

/**
 * 根据entity，freemarker创建类（mapper, service, controller.ftl）
 */
public class GenerateClass {

    private static final String RESOURCES_PATH = "basic/src/main/resources/";

    public static void main(String[] args) {
//        String[] arr = {"Guarantee","Violation","Pledge","JudgeDoc","TaxRating","Register","Punish","ConsumeLimit","Executed","StockFreeze"};
//        for (String entity : arr) {
//            createClass(entity, "Mapper", "com.ccr.qc.business");
//            createClass(entity, "Service", "com.ccr.qc.business");
//            createClass(entity, "Entity", "com.ccr.qc.business");
//            createClass(entity, "Model", "com.ccr.qc.business");
//        }

        createController();
    }

    public static void createClass(String entity, String type, String classPath) {
        try {
            Map<String, Object> dataMap = new HashMap<>(2);
            dataMap.put("entity", entity);
            dataMap.put("entity_first_low", entity.substring(0, 1).toLowerCase() + entity.substring(1));
            dataMap.put("class_path", classPath);

            Configuration configuration = new Configuration();
            configuration.setDirectoryForTemplateLoading(new File("basic/src/main/resources"));
            Template template = configuration.getTemplate(type.toLowerCase() + ".ftl");

            String outDir = "basic/src/main/resources/" + type.toLowerCase();

            File path = new File(outDir);
            if (!path.isDirectory()) {
                boolean ret = path.mkdir();
                System.out.println("创建文件夹： " + ret);
            }
            File docFile = new File(outDir + "/" + entity + type + ".java");

            if ("Entity".equals(type)) {
                docFile = new File(outDir + "/" + entity + ".java");
            }

            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            template.process(dataMap, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据文本 实体英文名,实体中文
     * 生成controller代码
     */
    public static void createController() {
        String text = fileTextString(RESOURCES_PATH + "tableinfo");
        String[] lines = text.split("\r\n");
        Map<String, String> entityMap = new HashMap<>();
        for (String line : lines) {
            String[] words = line.split(",");
            entityMap.put(words[0], words[1]);
        }
        createController(entityMap);
    }

    public static void createController(Map<String, String> entityMap) {
        try {
            Map<String, Object> dataMap = new HashMap<>(1);
            dataMap.put("entityMap", entityMap);

            Configuration configuration = new Configuration();
            configuration.setDirectoryForTemplateLoading(new File(RESOURCES_PATH));
            Template template = configuration.getTemplate("controller2.ftl");

            StringWriter writer = new StringWriter();
            template.process(dataMap, writer);

            System.out.println(writer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
