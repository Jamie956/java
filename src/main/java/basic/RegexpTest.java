package basic;

import org.junit.Test;
import utils.AllTools;

import java.io.IOException;

public class RegexpTest {

    public static void pr(String[] words, String[] patterns) {
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

    /*
        a
        1
        abc
        abcde987321
        abcdefg
        defghij

        abc
        abc 123
        abc123
        abc  123
        a
        rt
        C
        +
        RtS
        8
        abcdefg
        a+\\
        kava
        sava
        lava
        tava
        java
        Robert
        book
        cat
        job
        Todor
        +ob 123? @ p
        Boooob
        jjobss
        abc135gf
        string '123'
        int a = 5247;
        int A = 5247;
        int b = 5247;
        1int a = 5247;
        string '
        Marko is a good boy.
        Our Marko, is a good boy!
        Nobody is as good as our Marko is!
        2.345,56
        -52.678.110
        235
        128m
        2020-08-03
     */

    /*
        \w
        \w*
        \w+
        abc\w*
        \w+\s\d+
        \w+\s*\d+
        \w+\s+\d+
        \w+\s?\d+
        \D+
        \W
        [a-zA-Z0-9]
        \w{1}
        \w{1,2}
        \w{1,3}
        \w{1,7}
        \w{0,7}
        [kj]ava
        [^kj]ava
        .[o][b].*
        .ob.*
        .*
        \w+
        \w+\s\'.+
        \w+\sa.+
        \D+\sa.+
        \D+\s.+
        \w+\s?\'?\d{0,3}\'?
        \w+(\s\')?\d{0,3}\'?
        \w+(\s\'(\d*)\')?
        M.+
        ^M.+
        .+(\d)$
        \d{4}-\d{2}-\d{2}
    */

    /**
     * \w               匹配一个数字或字母
     * \w*              0或以上个数字或字母
     * \w+              1或以上个数字或字母
     * abc\w*           abc开头并连接0或以上个数字字母
     * \d               匹配一个数字字符。等价于[0-9]
     * \s               匹配任何空白字符，包括空格、制表符、换页符等等。等价于[ \f\n\r\t\v]。
     * ?                贪婪模式则尽可能多的匹配所搜索的字符串
     * \s               一个空格
     * \s*              0或以上个空格
     * \s+              1或以上个空格
     * \s?              0或1个空格
     * \D               匹配一个非数字字符。等价于[^0-9]。
     * \W               匹配任何非单词字符。等价于“[^A-Za-z0-9_]”。
     * []               字符范围。匹配指定范围内的任意字符。
     * {n}              n是一个非负整数。匹配确定的n次
     * {n,m}            m和n均为非负整数，其中n<=m。最少匹配n次且最多匹配m次。
     * [a-zA-Z0-9]      一个a-z的字母或A-Z的字母或0-9的数字
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
     * \w+
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
        String wordsText = AllTools.fileTextString("src\\main\\java\\utils\\source");
        String[] words = wordsText.split("\r\n");
        String patternsText = AllTools.fileTextString("src\\main\\java\\utils\\target");
        String[] patterns = patternsText.split("\r\n");

        pr(words, patterns);
    }
}
