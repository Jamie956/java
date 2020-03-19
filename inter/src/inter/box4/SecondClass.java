package inter.box4;

import inter.box3.FirstClass;

public class SecondClass extends FirstClass {
    
    public SecondClass(String name) {
        super(name);
        System.out.println("SecondClass name is " + this.getName());
        this.name = "new name";
    } 
}