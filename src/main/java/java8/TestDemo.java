package java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestDemo {

    @Test
    public void test () {
//        FilterPhoneFuction phoneFuction = (phone)->{
//            System.out.println(phone);
//            return true ;
//        };

//        phoneFuction.filter("aa");
//        hancle(phoneFuction);
    }

    private static void hancle(FilterPhoneFuction phoneFuction) {
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        list.add("cc") ;
        list.stream().forEach(phoneFuction::filter);
//        List<String> phoneList = readTxtFile("");//获取手机号
    }
}
