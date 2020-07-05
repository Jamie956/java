package basic;

public class InitialTest {
    public static void main(String[] args) {
        new B();
    }
}

class A {
    static {
        System.out.println("a1");
    }

    {
        System.out.println("a2");
    }

    A() {
        System.out.println("a3");
    }
}

class B extends A {
    static {
        System.out.println("b1");
    }

    {
        System.out.println("b2");
    }

    B() {
        System.out.println("b3");
    }
}
