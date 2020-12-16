package com.jamie;

import cn.hutool.core.util.ReUtil;
import org.junit.Test;
import com.jamie.utils.AllTools;

import java.io.IOException;

public class RegexpTest {
    /**
     * \w               一个  数字或字母
     * \w*              0或以上个   数字或字母
     * \w+              1或以上个   数字或字母
     * abc\w*           abc     连接  0或以上个   数字字母
     * \d               匹配一个数字字符。等价于[0-9]
     * \s               匹配一个任何空白字符，包括空格、制表符、换页符等等。等价于[ \f\n\r\t\v]。
     * ?                贪婪模式则尽可能多的匹配所搜索的字符串
     * \s*              0或以上个   空格
     * \s+              1或以上个   空格
     * \s?              0或1个空格
     * \D               匹配一个非数字字符。等价于[^0-9]。
     * \W               匹配任何非单词字符。等价于“[^A-Za-z0-9_]”。
     * {n}              n是一个非负整数。匹配确定的n次
     * {n,m}            m和n均为非负整数，其中n<=m。最少匹配n次且最多匹配m次。
     * [a-zA-Z0-9]      一个a-z的字母或A-Z的字母或0-9的数字，[]字符范围。匹配指定范围内的任意字符。
     * w{1}             一个字母或数字
     * \w{1,2}          1-2个字母或数字
     * \w{1,3}          1-3个字母或数字
     * \w{1,7}          1-7个字母或数字
     * \w{0,7}          0-7个字母或数字
     * [xyz]            字符集合。匹配所包含的任意一个字符
     * [^xyz]           负值字符集合。匹配未包含的任意字符。
     * [kj]ava          k 或 j 连接ava
     * [^kj]ava         除了 k 或 j 连接ava
     * .                匹配除“\n”之外的任何单个字符
     * .[o][b].*        第1个任意，第2个是o，第3个是b，后面0或多个任意
     * .ob.*            同上
     * .*               0个或多个 任意
     * \w+\s\'.+        一个或多个字母或数字 + 一个空格 + 一个' + 一个或多个任意
     * \w+\sa.+         一个或多个字母或数字 + 一个空格 + 一个字母a + 一个或多个任意
     * \D+\sa.+         1个或多个(排除数字) + 一个空格 + 一个字母a + 一个或多个任意
     * \D+\s.+          1个或多个(排除数字) + 一个空格 + 一个或多个任意
     * \w+\s?\'?\d{0,3}\'?          一个或多个字母或数字 + 0或1个空格 + 0或1个' + 0-3个数字 + 0或1个'
     * \w+(\s\')?\d{0,3}\'?         同上
     * \w+(\s\'(\d*)\')?
     * .+(\\d)$        数字结尾
     *
     */
    @Test
    public void test01() throws IOException {
        String wordsText = AllTools.fileTextString("src/main/resources/regWords");
        String[] words = wordsText.split("\r\n");
        String patternsText = AllTools.fileTextString("src/main/resources/regPatterns");
        String[] patterns = patternsText.split("\r\n");

        for (String pattern : patterns) {
            System.out.println(">>>>>>>>>>>>>> pattern: " + pattern + " <<<<<<<<<<<<<<");
            for (String word : words) {
                if (word.matches(pattern)) {
                    System.out.println(String.format("MATCHES!          word: %s", word));
                } else {
                    System.out.println(String.format("NOT MATCHES!      word: %s", word));
                }
            }
        }
    }

    @Test
    public void asdasd() {
        String content = "<p>asdasdas</p>\n" + "<p>sdfsd</p>\n" + "<p>bbb</p>\n" + "<p>cccc</p>";

        String a = ReUtil.delFirst("<p>", content);
        String b = content.replaceFirst("<p>", "<p>1:  " );
        String c = content.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "").replaceAll("[(/>)<]", "");
    }
}
