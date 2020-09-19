package basic.entity;

public class InitOrderB extends InitOrder {
    static {
        System.out.println("4");
    }

    {
        System.out.println("5");
    }

    public InitOrderB() {
        System.out.println("6");
    }
}
