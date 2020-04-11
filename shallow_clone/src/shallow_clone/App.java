package shallow_clone;

public class App {
	public static void main(String[] args) {
		try {
			User user = new User();
			user.setAddress(new Address("stress1"));
			User clone = (User) user.clone();
			
			System.out.println(user == clone);
			System.out.println(user.getAddress() == clone.getAddress());//浅克隆不克隆引用类型
			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
