package java8;

@FunctionalInterface
public interface  FilterPhoneFuction {
    boolean filter(String phone);

    default String getInfo() {
        return "过滤手机号函数";
    }

    default void handleEQ (String a) {

    };

    ;
//
//    void handleGT ();

}