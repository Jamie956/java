package inter.box1;

public class Sdd2 {
	public static void main(String[] args) {
		System.out.println(name());
		System.out.println(name("Tom"));
		System.out.println(name("Zhou","Jamie"));
	}
	
	public static String name() {
		return "default";
	}
	//Overload
	public static String name(String name) {
		return name;
	}
	public static String name(String first, String last) {
		return first +" "+ last;
	}
}
