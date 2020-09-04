package java8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Query {
    private static Map<String, Consumer<QueryWrapper>> queryWrapperMap = new HashMap<>();

    public Map<String, Consumer<QueryWrapper>> getMap() {
        return queryWrapperMap;
    }

    static {
        System.out.println("init queryWrapperMap");
        queryWrapperMap.put("eq", (queryWrapper) -> queryWrapper.setEq("setting default eq"));
        queryWrapperMap.put("lt", (queryWrapper) -> queryWrapper.setLt("setting default lt"));

    }

    public QueryWrapper getWrapper() {
        QueryWrapper queryWrapper = new QueryWrapper();
        for (String exp : Arrays.asList("eq", "lt")) {
            switch (exp) {
                case "eq":
                    queryWrapperMap.get("eq").accept(queryWrapper);
                    break;
                case "lt":
                    queryWrapperMap.get("lt").accept(queryWrapper);
                    break;
                default:
                    break;
            }
        }
        return queryWrapper;
    }
}
