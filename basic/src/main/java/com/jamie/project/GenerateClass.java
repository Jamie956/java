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
//        createClass("Guarantee", "Mapper", "com.ccr.qc.company");
//        createClass("Guarantee", "Service", "com.ccr.qc.company");
        createClass("Guarantee", "Controller", "com.ccr.qc.company");

    }


    public static void createClass(String entity, String type, String classPath) {
        try {
            Map<String, Object> dataMap = new HashMap<>(1);
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
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            template.process(dataMap, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void createService(String entity, String classPath) {
//        try {
//            Map<String, Object> dataMap = new HashMap<>(1);
//            dataMap.put("entity", entity);
//            dataMap.put("class_path", classPath);
//
//            Configuration configuration = new Configuration();
//            configuration.setDirectoryForTemplateLoading(new File("basic/src/main/resources"));
//            Template template = configuration.getTemplate("service.ftl");
//
//            String outDir = "basic/src/main/resources/service";
//
//            File path = new File(outDir);
//            if(!path.isDirectory()){
//                boolean ret = path.mkdir();
//                System.out.println("创建文件夹： " + ret);
//            }
//            File docFile = new File(outDir  +"/" + entity + ".java");
//            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
//            template.process(dataMap, out);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
