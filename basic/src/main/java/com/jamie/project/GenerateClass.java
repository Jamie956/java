package com.jamie.project;

/**
 * @Author: Zjm
 * @Date: 2021/1/22 11:55
 */

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.*;

/**
 * 根据entity，freemarker创建类（mapper, service, controller.ftl）
 */
public class GenerateClass {

    public static void main(String[] args) {
        String[] arr = {"Guarantee","Violation","Pledge","JudgeDoc","TaxRating","Register","Punish","ConsumeLimit","Executed","StockFreeze"};

        for (String entity : arr) {
            createClass(entity, "Mapper", "com.ccr.qc.company");
            createClass(entity, "Service", "com.ccr.qc.company");
            createClass(entity, "Controller", "com.ccr.qc.company");
            createClass(entity, "Entity", "com.ccr.qc.company");
            createClass(entity, "Model", "com.ccr.qc.company");
        }
    }

    public static void createClass(String entity, String type, String classPath) {
        try {
            Map<String, Object> dataMap = new HashMap<>(2);
            dataMap.put("entity", entity);
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

}
