package design.pattern;

public class AFunction extends AbstractFunction {
    @Override
    public void foo() {
        System.out.println("a-foo");
    }

    @Override
    public void bar() {
        System.out.println("a-bar");
    }
}