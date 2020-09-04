package java8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Query {
    private static Map<String, Consumer<QueryWrapper>> map = new HashMap<>();

    public Map<String, Consumer<QueryWrapper>> getMap() {
        return map;
    }

    static {
        System.out.println("init map");
        map.put("eq", (queryWrapper) -> queryWrapper.setEq("setting default eq"));
        map.put("lt", (queryWrapper) -> queryWrapper.setLt("setting default lt"));

    }

    public QueryWrapper getWrapper() {
        QueryWrapper queryWrapper = new QueryWrapper();
        for (String exp : Arrays.asList("eq", "lt")) {
            switch (exp) {
                case "eq":
                    map.get("eq").accept(queryWrapper);
                    break;
                case "lt":
                    map.get("lt").accept(queryWrapper);
                    break;
                default:
                    break;
            }
        }
        return queryWrapper;
    }
}
