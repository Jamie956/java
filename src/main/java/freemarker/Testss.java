package freemarker;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Testss {

    @Test
    public void test1() throws Exception {
        Configuration configuration = new Configuration();
        configuration.setDirectoryForTemplateLoading(new File("src/main/java/freemarker"));

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("greet", "hi");
        dataMap.put("name", "tom");

        Template template = configuration.getTemplate("hello.ftl");
        StringWriter writer = new StringWriter();
        template.process(dataMap, writer);

        System.out.println(writer.toString());
    }

    @Test
    public void test2() {
        try {
            Configuration cfg = new Configuration();
            StringTemplateLoader stringLoader = new StringTemplateLoader();
            stringLoader.putTemplate("myTemplate", "欢迎：${name}");
            cfg.setTemplateLoader(stringLoader);

            Template template = cfg.getTemplate("myTemplate","utf-8");
            Map<String, String> root = new HashMap<>(1);
            root.put("name", "d9od9o");

            StringWriter writer = new StringWriter();
            template.process(root, writer);
            System.out.println(writer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
