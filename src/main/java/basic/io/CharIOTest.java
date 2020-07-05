package basic.io;

import java.io.*;

public class CharIOTest {
    public static void main(String[] args) {
//        test1();
        test2();
    }

    public static void test1() {
        Writer out = null;
        try {
            out = new FileWriter("a");
            out.write("hi");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void test2() {
        Reader in = null;
        Writer out = null;
        try {
            in = new FileReader("a");
            out = new FileWriter("b");
            char[] cbuf = new char[10];
            int len = -1;

            while ((len = in.read(cbuf)) != -1) {
                out.write(cbuf, 0, len);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
