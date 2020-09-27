package basic;

public class MyCompare {
    public static void main(String[] args) {
        String[] arra = {};

        String[] arrb = {};

        //每个arra的元素去arrb找是否有相同的，没有找到相同的打印出来
        for (String a : arra) {
            boolean isMatch = false;
            for (String b : arrb) {
                if (a.equals(b)) {
                    isMatch = true;
                }
            }
            if (!isMatch) {
                System.out.println(a);
            }
        }
    }
}
