package utils;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class TestCompare {

    /*
    source文件
    123
    456
    789
    856
    847

    target文件
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

    source文件的123,456,789转成数组，去target文件找是否存在于target文件
     */
    @Test
    public void ye() throws IOException {
        String sourceText = getTextString("src\\main\\java\\utils\\source");
        String[] sourceArr = sourceText.split("\r\n");

        String targetText = getTextString("src\\main\\java\\utils\\target");

        int count = 0;
        for (String s : sourceArr) {
            if (targetText.contains(s)) {
                count++;
                System.out.println("contain: " + s);
            } else {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>404 not found: "+s);
            }
        }
        System.out.println(count);
    }

    public String getTextString(String path) throws IOException {
        FileInputStream in = new FileInputStream(path);
        byte[] b = new byte[1024];
        int len;
        StringBuilder builder = new StringBuilder();
        while ((len = in.read(b)) != -1) {
            builder.append(new String(b, 0, len));
        }
        return builder.toString();
    }

}
