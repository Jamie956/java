package basic;

public class ReflectTest {
    /**
     * 实例化构造一个class
     */
    public static void test02() {
        try {
            Class clz = Class.forName("basic.Person");
            clz.newInstance();

            clz.getDeclaredFields();


        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        test02();
    }
}

class Person {

    private String name;

    public Person() {
        System.out.println(1);
    }

}
