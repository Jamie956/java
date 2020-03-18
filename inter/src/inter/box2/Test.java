package inter.box2;


public class Test{
	public static void main(String[] args) {
		Fader sn = new Sn();
		System.out.println(sn.Greet());
		System.out.println(sn.SpecialGreet());
		
		Fader gir = new Gir();
		System.out.println(gir.Greet());
		System.out.println(gir.SpecialGreet());
	}
}
