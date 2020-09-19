package basic.aenum;

//public enum Season {
//    SPRING, SUMMER, AUTUMN, WINER;
//}

/**
 *
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

