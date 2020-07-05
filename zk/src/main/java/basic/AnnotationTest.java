package basic;

@MyAnnotation(value="a")
public class AnnotationTest{
    public static void main(String[] args) {
        try {
            Class clz = Class.forName("basic.AnnotationTest");
            MyAnnotation halo = (MyAnnotation) clz.getAnnotation(MyAnnotation.class);
            System.out.println(halo.value());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

