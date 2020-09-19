package basic.aenum;

public enum WeekDay {
    /**
     *
     */
    Mon("Monday"), Tue("Tuesday"), Wed("Wednesday"), Thu("Thursday"), Fri("Friday"), Sat("Saturday"), Sun("Sunday");
    //以上是枚举的成员，必须先定义，而且使用分号结束
    private final String day;

    WeekDay(String day) {
        this.day = day;
    }


}