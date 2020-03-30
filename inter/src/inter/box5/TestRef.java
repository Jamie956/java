package inter.box5;

import java.lang.reflect.*;

public class TestRef {
	public static void main(String[] args) {
		test3();
	}

	public static void test1() {
		Object person = new Person();
		Class<?> clazz = person.getClass();

		System.out.println(clazz.getName());// inter.box5.Person
		System.out.println(clazz.getPackage().getName());//inter.box5

		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {
			System.out.println(field.getName());// name, age
		}
	}

	public static void test2() {
		Class<?> personClazz;
		try {
			personClazz = Class.forName("inter.box5.Person");
			int perMods = personClazz.getModifiers();

			System.out.println(Modifier.isPublic(perMods));// true
			System.out.println(Modifier.isPrivate(perMods));// false
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void test3() {
		try {
			Object person = new Person();
			Class<?> clazz = person.getClass();
			Person p = (Person) clazz.newInstance();
			
			Field fie = clazz.getDeclaredField("sex");
			
			p.whatSex();
			fie.setAccessible(true);
			fie.set(p, "man");
			p.whatSex();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
