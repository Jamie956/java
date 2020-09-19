package basic.aenum;

//public enum Season {
//    SPRING, SUMMER, AUTUMN, WINER;
//}

/**
 * 枚举类型（enum type）是指由一组固定的常量组成合法的类型。
 */
public enum Season {
    /**
     *
     */
    SPRING(1), SUMMER(2), AUTUMN(3), WINTER(4);

    private int code;
    Season(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}

