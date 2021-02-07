package com.jamie.project;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyUtils {
    public static String fileTextString(String path) {
        try {
            FileInputStream in = new FileInputStream(path);
            byte[] b = new byte[1024];
            int len;
            StringBuilder builder = new StringBuilder();
            while ((len = in.read(b)) != -1) {
                builder.append(new String(b, 0, len));
            }
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
database.driver=11
database.url=22
database.user=33
 */
    @Test
    public void getProperty() throws IOException {
        InputStream in = new BufferedInputStream(new FileInputStream("src/main/resources/test.properties")) ;
        Properties p = new Properties();
        p.load(in) ;

        String className2 = p.getProperty("database.driver");
        String url = p.getProperty("database.url");
        String user = p.getProperty("database.user");
        String no = p.getProperty("database.no");
    }
}
