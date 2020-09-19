package basic.entity;

public class InitOrder {
    static {
        System.out.println("1");
    }

    {
        System.out.println("2");
    }

    InitOrder() {
        System.out.println("3");
    }
}
