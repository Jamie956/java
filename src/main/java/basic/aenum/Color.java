package basic.aenum;

/**
 * 枚举类型（enum type）是指由一组固定的常量组成合法的类型。
 *
 * 在数学和计算机科学理论中，一个集的枚举是列出某些有穷序列集的所有成员的程序，
 * 或者是一种特定类型对象的计数。这两种类型经常（但不总是）重叠.。
 * 枚举是一个被命名的整型常数的集合，枚举在日常生活中很常见，
 * 例如表示星期的SUNDAY、MONDAY、TUESDAY、WEDNESDAY、THURSDAY、FRIDAY、SATURDAY就是一个枚举。
 *
 */
public enum Color {
    //每个颜色都是枚举类的一个实例，并且构造方法要和枚举类的格式相符合。
    Red("红色", 1), Green("绿色", 2), Blue("蓝色", 3), Yellow("黄色", 4);
    public String name;
    public int index;

    Color(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public void showAllColors() {
        //values是Color实例的数组，在通过index和name可以获取对应的值。
        for (Color color : Color.values()) {
            System.out.println(color.index + ":" + color.name);
        }
    }
}

