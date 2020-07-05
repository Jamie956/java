package basic.io;

import java.io.*;

public class ByteIOTest {
    public static void main(String[] args) {
        test1();
//        test2();
    }

    //file output
    public static void test1() {
        OutputStream out = null;
        try {
            out = new FileOutputStream("a");
            out.write(new byte[]{65, 66, 67});
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

    //file input and output
    public static void test2() {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream("a");
            out = new FileOutputStream("b");
            /**
             * way1, read byte by byte
             */
            int b = 0;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            /**
             * way2, read by bytes[]
             */
//            byte[] b = new byte[20];
//            int len = -1;
//            while ((len = in.read(b)) != -1) {
//                out.write(b, 0, len);
//            }
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