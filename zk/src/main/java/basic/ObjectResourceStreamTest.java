package basic;

import java.io.IOException;
import java.util.Properties;

public class ObjectResourceStreamTest {
    public static void main(String[] args) {
        Properties props = new Properties();
        try {
            props.load(ObjectResourceStreamTest.class.getClassLoader().getResourceAsStream("com//example//app.properties"));

            System.out.println(props.getProperty("key"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
