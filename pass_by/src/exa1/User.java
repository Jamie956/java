package exa1;

public class User {
	public static void main(String[] args) {
		User u = new User();
		change(u);
		System.out.println(u);
	}
	
	public static void change(User u) {//值传递，形式参数是副本，是复制的引用
		u = new User();
		System.out.println(u);
	}

}