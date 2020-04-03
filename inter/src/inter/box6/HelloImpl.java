package inter.box6;

public class HelloImpl implements IHello {

	@Override
	public String sayHello(String string) {
		return "hello:".concat(string);
	}
}