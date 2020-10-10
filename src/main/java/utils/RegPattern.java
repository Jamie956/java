package utils;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegPattern {
    //匹配整个ddl，将ddl分为表名，列sql部分，表注释
    private static final Pattern DDL_PATTERN = Pattern.compile("\\s*create\\s+table\\s+(?<tableName>\\S+)[^\\(]*\\((?<columnsSQL>[\\s\\S]+)\\)[^\\)]+?(comment\\s*(=|on\\s+table)\\s*'(?<tableComment>.*?)'\\s*;?)?$", Pattern.CASE_INSENSITIVE);
    //匹配列sql部分，分别解析每一列的列名 类型 和列注释
    private static final Pattern COL_PATTERN = Pattern.compile("\\s*(?<fieldName>\\S+)\\s+(?<fieldType>\\w+)\\s*(?:\\([\\s\\d,]+\\))?((?!comment).)*(comment\\s*'(?<fieldComment>.*?)')?\\s*(,|$)", Pattern.CASE_INSENSITIVE);

    public static void parse(String sql) {
        Matcher matcher = DDL_PATTERN.matcher(sql);
        if (matcher.find()) {
            String tableName = matcher.group("tableName");
            String tableComment = matcher.group("tableComment");
            System.out.println(tableName + "\t\t" + tableComment);
            System.out.println("==========");
            String columnsSQL = matcher.group("columnsSQL");
            if (columnsSQL != null && columnsSQL.length() > 0) {
                Matcher colMatcher = COL_PATTERN.matcher(columnsSQL);
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

    @Test
    public void asdasdasd() {
        parse("CREATE TABLE `userinfo` (\n" + "  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',\n" + "  `username` varchar(255) NOT NULL COMMENT '用户名',\n" + "  `addtime` datetime NOT NULL COMMENT '创建时间',\n" + "  PRIMARY KEY (`user_id`)\n" + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息'");
    }

    @Test
    public void sadasd() {
        Pattern pattern = Pattern.compile("\\d+");
        String str = "我的QQ是:456456 我的电话是:0532214 我的邮箱是:aaa123@aaa.com";
        Matcher m = pattern.matcher(str);
        while (m.find()) {
            System.out.println(m.group());
        }
    }

    @Test
    public void asdas() {
        Pattern pattern2 = Pattern.compile("[\\u4E00-\\u9FA5]+");
        String str = "dsada 哈桑sdfdsff第三方51255dsf锁定";
        Matcher m = pattern2.matcher(str);
        while (m.find()) {
            System.out.println(m.group());
        }
    }

}
