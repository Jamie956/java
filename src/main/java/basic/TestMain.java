package basic;

import org.junit.Test;

import java.lang.reflect.Method;

public class TestMain{

    @Test
    @MyAnnotation(value="a")
    public void annotationTest() throws ClassNotFoundException, NoSuchMethodException {
        Class clz = Class.forName("basic.TestMain");
        Method method = clz.getMethod("annotationTest");
        MyAnnotation a = method.getAnnotation(MyAnnotation.class);
        System.out.println(a.value());
    }
}

