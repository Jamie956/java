package design.pattern;

import java.util.HashMap;
import java.util.Map;

public class FunctionFactory {
    private static Map<String, AbstractFunction> map = new HashMap<>();

    public static AbstractFunction get(String name) {
        return map.get(name);
    }

    public static void register(String name, AbstractFunction function) {
        map.put(name, function);
    }
}