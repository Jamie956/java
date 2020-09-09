package basic;

import org.junit.Test;

import java.lang.reflect.Field;

public class ReflectTest {
    /**
     * 实例化构造一个class
     */
    @Test
    public void test02() {
        try {
            Class clz = Class.forName("basic.Person");
            clz.newInstance();
            clz.getDeclaredFields();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void asd() throws NoSuchFieldException, IllegalAccessException {
        Object person = new Person();
        Field personField = person.getClass().getDeclaredField("name");
        personField.setAccessible(true);
        personField.set(person, "111");

        String name = (String) personField.get(person);
    }

    @Test
    public void asdasd() throws NoSuchFieldException, IllegalAccessException {
        Object person = new Person();
        Field personField = person.getClass().getDeclaredField("name");
        personField.setAccessible(true);
        String name = (String) personField.get(person);
    }
}

class Person {

    private String name = "0";

    public Person() {
        System.out.println(1);
    }

}
