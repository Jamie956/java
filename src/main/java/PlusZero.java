import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.text.DecimalFormat;

public class PlusZero {

    //补零
    @Test
    public void add(){
        System.out.println(String.format("%05d", 77));

        String fileName = "130181";
        DecimalFormat g2=new DecimalFormat("00000000");
        String endZeroStr = g2.format(Integer.valueOf(fileName));
        System.out.println(endZeroStr);


        String a = StringUtils.rightPad("440", 8, "0");
        System.out.println(a);
    }
}
