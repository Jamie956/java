package design.pattern;

public class BFunction extends AbstractFunction {
    @Override
    public void foo() {
        System.out.println("b-foo");
    }

    @Override
    public void bar() {
        System.out.println("b-bar");
    }
}