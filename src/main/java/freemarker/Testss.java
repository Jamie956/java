package freemarker;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
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


    @Test
    public void test4() throws Exception {
        Configuration configuration = new Configuration();
        configuration.setDirectoryForTemplateLoading(new File("src/main/java/freemarker"));

        //遍历
        Map<String,List<String>> kindsMap  = new HashMap<>();
        List<String> aList = new ArrayList<String>();
        aList.add("a1");
        aList.add("a2");

        List<String> bList = new ArrayList<String>();
        bList.add("b1");
        bList.add("b2");

        kindsMap.put("a",aList);
        kindsMap.put("b", bList);

        //
        Map<String, List<User>> kindsMap2  = new HashMap<>();
        List<User> aList2 = new ArrayList<>();
        aList2.add(new User(1, "tim"));
        aList2.add(new User(2, "tim"));

        List<User> bList2 = new ArrayList<User>();
        bList2.add(new User(3, "tim"));
        bList2.add(new User(4, "tim"));

        kindsMap2.put("a",aList2);
        kindsMap2.put("b", bList2);


        //
        Map<String, String> kindsMap3  = new HashMap<>();
//        kindsMap3.put("1", "v1");
//        kindsMap3.put("2", "v2");

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("kindsMap", kindsMap);
        dataMap.put("kindsMap2", kindsMap2);
        dataMap.put("kindsMap3", kindsMap3);

        Template template = configuration.getTemplate("test4.ftl");
        StringWriter writer = new StringWriter();
        template.process(dataMap, writer);

        System.out.println(writer.toString());
    }

}
