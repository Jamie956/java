package inter.box4;

public class SecondGenericClass {
	 
    public static void main(String[] args) {
    	/**
    	 *  The constructor FirstClass(String) is not visible
			The method getName() from the type FirstClass is not visible
			The field FirstClass.name is not visible
    	 */
        FirstClass first = new FirstClass("random name");
        System.out.println("FirstClass name is "+ first.getName());
        first.name = "new name";
    }
}