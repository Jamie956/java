package com.jamie.utils;

import java.io.FileInputStream;
import java.io.IOException;

public class AllTools {
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
}
