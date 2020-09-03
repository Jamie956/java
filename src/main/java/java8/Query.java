package java8;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Consumer;

public class Query {
    public QueryWrapper getWrapper(Map<String, Consumer> map) {
        QueryWrapper queryWrapper = new QueryWrapper();
        for (String exp : Arrays.asList("eq", "lt")) {
            switch (exp) {
                case "eq":
                    Consumer consumer = map.get("eq");
                    if (consumer != null) {
                        consumer.accept(queryWrapper);
                    } else {
                        queryWrapper.setEq("default eq");
                    }
                    break;
                case "lt":
                    Consumer consumer2 = map.get("lt");
                    if (consumer2 != null) {
                        consumer2.accept(queryWrapper);
                    } else {
                        queryWrapper.setLt("default eq");
                    }
                    break;
                default:
                    break;
            }
        }
        return queryWrapper;
    }
}
