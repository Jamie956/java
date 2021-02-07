package com.jamie.project;

import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static com.jamie.project.MyUtils.fileTextString;

public class MyStringUtils {
    /**
     * 数据比对
     * source文件的每一行数据转成数组元素，去target文件找是否存
     */
    @Test
    public void myCompare() throws IOException {
//        String resourcePath = new File("").getCanonicalPath() + "/src/main/resources/";

//        String text = fileTextString(resourcePath + "/source");
        String text = FileUtils.readFileToString(new File("src/main/resources/source"), "UTF-8");

        String[] lines = text.split("\r\n");

//        String targetText = fileTextString(resourcePath + "/target");
        String targetText = FileUtils.readFileToString(new File("src/main/resources/target"), "UTF-8");

        int matchCount = 0;
        int notMatchCount = 0;
        for (String line : lines) {
            if (targetText.contains(line)) {
                matchCount++;
                System.out.println("source文件当前行在target找到匹配数据: " + line);
            } else {
                notMatchCount++;
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>source文件当前行在target找不到匹配数据: " + line);
            }
        }

        System.out.println("---------------总数统计---------------");
        System.out.println("匹配数目: " + matchCount);
        System.out.println("不匹配数目: " + notMatchCount);
    }

    /**
     * 驼峰、下划线字符串转换 by guava
     */
    @Test
    public void guavaConvert() {
        // 驼峰转下划线, userName -> user_name
        Converter<String, String> converter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE);
        // 输出: user_name
        System.out.println(converter.convert("userName"));
        System.out.println(converter.convert("user_name"));

        // 驼峰转连接符, userName -> user-name
        converter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_HYPHEN);
        // 输出: user-name
        System.out.println(converter.convert("userName"));

        // 驼峰转首字符大写驼峰, userName -> UserName
        converter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);
        // 输出: UserName
        System.out.println(converter.convert("userName"));

        // 驼峰转大写下划线, userName -> USER_NAME
        converter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_UNDERSCORE);
        // 输出: USER_NAME
        System.out.println(converter.convert("userName"));
    }
}
