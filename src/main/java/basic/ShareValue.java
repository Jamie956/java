package basic;

//静态变量全局共享
public class ShareValue {
	static int x = 0;
	ShareValue(){
		System.out.println(x);
		x++;
	}
	
	public static void main(String[] args) {
		new ShareValue();
		new ShareValue();
	}
}
