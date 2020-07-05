package basic;

import java.awt.*;

public class InnerClassTest {
    private int i = 1;

    //成员内部类
    class A {
        public void pr() {
            System.out.println(i);
        }
    }

    //局部内部类
    public List test() {
        class C extends List {

        }
        return new C();
    }

    //静态内部类
    static class B {

    }


    public static void main(String[] args) {
        new InnerClassTest().new A().pr();
        new InnerClassTest.B();

        //匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
