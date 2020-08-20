package design.pattern;

import org.junit.Test;

public class DemoTest {

    @Test
    public void contextLoads() {
        FunctionFactory.register("a", new AFunction());
        FunctionFactory.register("b", new BFunction());

        AbstractFunction functionA = FunctionFactory.get("a");
        functionA.foo();
        functionA.bar();


        AbstractFunction functionB = FunctionFactory.get("b");
        functionB.foo();
        functionB.bar();
    }
}
