package freemarker;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.*;
import java.util.*;

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

    @Test
    public void test3() {
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            configuration.setDirectoryForTemplateLoading(new File("src/main/java/freemarker"));
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("name", "jamie");
            dataMap.put("dateTime", new Date());

            List<User> users = new ArrayList<User>();
            users.add(new User(1, "Jamie"));
            users.add(new User(2, "欢迎"));
            users.add(new User(3, "You！"));
            dataMap.put("users", users);

            // step4 加载模版文件
            Template template = configuration.getTemplate("hihi.ftl");
            // step5 生成数据
            out = new OutputStreamWriter(System.out);
            // step6 输出文件
            template.process(dataMap, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
