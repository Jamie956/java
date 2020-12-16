package com.jamie.utils;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilsMain {
    /*
    //source文件
    123
    456
    789
    856
    847

    //target文件
    {
    "key" : "123",
    },
    {
    "key" : "845",
    },
    {
    "key" : "856",
    },
    {
    "key" : "412",
    },
     */

    /**
     * source文件的每一行数据转成数组元素，去target文件找是否存
     */
    @Test
    public void myCompare() throws IOException {
        String sourceText = AllTools.fileTextString("src\\main\\java\\com.jamie.utils\\source");
        String[] sourceArr = sourceText.split("\r\n");

        String targetText = AllTools.fileTextString("src\\main\\java\\com.jamie.utils\\target");

        int matchCount = 0;
        int notMatchCount = 0;
        for (String s : sourceArr) {
            if (targetText.contains(s)) {
                matchCount++;
                System.out.println("found: " + s);
            } else {
                notMatchCount++;
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> not found: " + s);
            }
        }
        System.out.println("matchCount: " + matchCount);
        System.out.println("notMatchCount: " + notMatchCount);
    }

    /*
     CREATE TABLE `userinfo` (
     `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
     `username` varchar(255) NOT NULL COMMENT '用户名',
     `addtime` datetime NOT NULL COMMENT '创建时间',
     PRIMARY KEY (`user_id`)
     ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息'
     */

    /**
     * 正则表达式解析sql
     */
    @Test
    public void sqlRegParse() throws IOException {
        //匹配整个ddl，将ddl分为表名，列sql部分，表注释
        String ddlReg = "\\s*create\\s+table\\s+(?<tableName>\\S+)[^\\(]*\\((?<columnsSQL>[\\s\\S]+)\\)[^\\)]+?(comment\\s*(=|on\\s+table)\\s*'(?<tableComment>.*?)'\\s*;?)?$";
        //匹配列sql部分，分别解析每一列的列名 类型 和列注释
        String colReg = "\\s*(?<fieldName>\\S+)\\s+(?<fieldType>\\w+)\\s*(?:\\([\\s\\d,]+\\))?((?!comment).)*(comment\\s*'(?<fieldComment>.*?)')?\\s*(,|$)";

        Pattern ddlPattern = Pattern.compile(ddlReg, Pattern.CASE_INSENSITIVE);
        Pattern colPattern = Pattern.compile(colReg, Pattern.CASE_INSENSITIVE);
        String sql = AllTools.fileTextString("src\\main\\java\\com.jamie.utils\\source");

        Matcher matcher = ddlPattern.matcher(sql);
        if (matcher.find()) {
            String tableName = matcher.group("tableName");
            String tableComment = matcher.group("tableComment");
            System.out.println(tableName + "\t\t" + tableComment);
            System.out.println("==========");
            String columnsSql = matcher.group("columnsSQL");

            if (columnsSql != null && columnsSql.length() > 0) {
                Matcher colMatcher = colPattern.matcher(columnsSql);
                while (colMatcher.find()) {
                    String fieldName = colMatcher.group("fieldName");
                    String fieldType = colMatcher.group("fieldType");
                    String fieldComment = colMatcher.group("fieldComment");
                    if (!"key".equalsIgnoreCase(fieldType)) {
                        System.out.println(fieldName + "\t\t" + fieldType + "\t\t" + fieldComment);
                    }
                }
            }
        }
    }

    /*
     我的QQ是:456456 我的电话是:0532214 我的邮箱是:aaa123@aaa.com
     =111&
     88=222&99
     PIN=1111
     PIN=2222
     x8465y
     111x56duy222
     444d|dffdf|888
     {
     "key" : "123",
     },
     {
     "key" : "845",
     },
     {
     "key" : "856",
     },
     {
     "key" : "412",
     },

     //reg
    [\\u4E00-\\u9FA5]+
    \d+
    x.+.y
    (?<=PIN=).\S*
    (?<==).*?(?=(&|$))
    (?<="key" : ").*?(?=(",|$))
    (\|.*)
     */

    /**
     * 从text中找出匹配正则表达式的字符
     * 找出文本的全部中文                         [\\u4E00-\\u9FA5]+
     * 找出文本的全部数字                        \d+
     * 找出指定开头x和结尾y的文本                 x.+.y
     * 取PIN=为开头的内容                          (?<=PIN=).\S*
     * 取以=开头 以&结尾 取得的中间的内容            (?<==).*?(?=(&|$))
     * 取"key" : "开头，",结尾的中间的内容            (?<="key" : ").*?(?=(",|$))
     * 取某个字符串|开头的内容                      (\|.*)
     */
    @Test
    public void findMatchTest() throws IOException {
        String regsText = AllTools.fileTextString("src\\main\\java\\com.jamie.utils\\source");
        String[] regs = regsText.split("\r\n");

        for (String reg : regs) {
            System.out.println(String.format("<<<<<<<<<<<<<<<< reg: %s >>>>>>>>>>>>>>>", reg));
            Pattern pattern = Pattern.compile(reg);
            String text = AllTools.fileTextString("src\\main\\java\\com.jamie.utils\\target");
            Matcher m = pattern.matcher(text);
            while (m.find()) {
                System.out.println(m.group());
            }
        }

    }

    /**
     * 将文本以某个字符分割成数组，再去除某些字符
     */
    @Test
    public void splitAndRemove() throws IOException {
        String sourceText = AllTools.fileTextString("src\\main\\java\\com.jamie.utils\\source");
        String[] sourceArr = sourceText.split(" ");
        for (String s : sourceArr) {
            String a = StringUtils.remove(s, "\"");
            String b = StringUtils.remove(a, ",");
            System.out.println(b);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
    }
}
