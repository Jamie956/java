package utils;

import org.junit.Test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestMain {
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

    source文件的每一行数据转成数组元素，去target文件找是否存
     */
    @Test
    public void myCompare() throws IOException {
        String sourceText = AllTools.fileTextString("src\\main\\java\\utils\\source");
        String[] sourceArr = sourceText.split("\r\n");

        String targetText = AllTools.fileTextString("src\\main\\java\\utils\\target");

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

    //正则表达式解析sql
    /*
    CREATE TABLE `userinfo` (
    `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username` varchar(255) NOT NULL COMMENT '用户名',
    `addtime` datetime NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`user_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息'
     */
    @Test
    public void sqlRegParse() throws IOException {
        //匹配整个ddl，将ddl分为表名，列sql部分，表注释
        String ddlReg = "\\s*create\\s+table\\s+(?<tableName>\\S+)[^\\(]*\\((?<columnsSQL>[\\s\\S]+)\\)[^\\)]+?(comment\\s*(=|on\\s+table)\\s*'(?<tableComment>.*?)'\\s*;?)?$";
        //匹配列sql部分，分别解析每一列的列名 类型 和列注释
        String colReg = "\\s*(?<fieldName>\\S+)\\s+(?<fieldType>\\w+)\\s*(?:\\([\\s\\d,]+\\))?((?!comment).)*(comment\\s*'(?<fieldComment>.*?)')?\\s*(,|$)";

        Pattern ddlPattern = Pattern.compile(ddlReg, Pattern.CASE_INSENSITIVE);
        Pattern colPattern = Pattern.compile(colReg, Pattern.CASE_INSENSITIVE);
        String sql = AllTools.fileTextString("src\\main\\java\\utils\\source");

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


    /**
     * 从text中找出匹配正则表达式的字符
     * 我的QQ是:456456 我的电话是:0532214 我的邮箱是:aaa123@aaa.com
     */
    @Test
    public void findMatchTest() throws IOException {
        //找出文本的全部中文
//        String reg = "[\\u4E00-\\u9FA5]+";
        //找出文本的全部数字
        String reg = "\\d+";

        Pattern pattern = Pattern.compile(reg);

        String text = AllTools.fileTextString("src\\main\\java\\utils\\source");
        Matcher m = pattern.matcher(text);
        while (m.find()) {
            System.out.println(m.group());
        }
    }
}
