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
 * 根据entity，freemarker创建类（mapper, service, controller）
 */
public class GenerateMvcByDdl {

    public static void main(String[] args) {
        createMapper("Guarantee", "com.ccr.qc.company");
    }


    public static void createMapper(String entity, String classPath) {
        try {
            Map<String, Object> dataMap = new HashMap<>(1);
            dataMap.put("entity", entity);
            dataMap.put("class_path", classPath);

            Configuration configuration = new Configuration();
            configuration.setDirectoryForTemplateLoading(new File("basic/src/main/resources"));
            Template template = configuration.getTemplate("mapper.ftl");

            String outDir = "basic/src/main/resources/mapper";

            File path = new File(outDir);
            if(!path.isDirectory()){
                boolean ret = path.mkdir();
                System.out.println("创建文件夹： " + ret);
            }
            File docFile = new File(outDir  +"/" + entity + ".java");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            template.process(dataMap, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
