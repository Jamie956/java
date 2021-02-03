package com.jamie.project;

/**
 * @Author: Zjm
 * @Date: 2021/1/27 9:20
 */

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.jamie.project.MyUtils.fileTextString;


public class SuperCompare {
    /**
     * 文件A
     * salegoodsservicerec: "713.49亿",
     * nideposit: "-6.64亿",
     * niborrowfromcbank: "-",
     * niborrowfromfi: "-",
     * premiumrec: "-",
     *
     * 文件B
     * 经营活动产生的现金流量
     * 销售商品、提供劳务收到的现金:713.49亿464.46亿218.66亿949.80亿
     * 客户存款和同业存放款项净增加额:-6.64亿-14.06亿-30.13亿-4.37亿
     *
     * 输出
     * salegoodsservicerec:销售商品、提供劳务收到的现金
     * nideposit:客户存款和同业存放款项净增加额
     *
     * 拿文件a的value 去和文件b的value匹配，如果匹配，就把文件b的key与文件a的key组合起来
     */
    @Test
    public void t0 () throws IOException {
        Map<String, String> mapa =  getMap("a");
        Map<String, String> mapb =  getMap("b");

        for (String ka : mapa.keySet()) {
            for (String kb : mapb.keySet()) {
                if(mapb.get(kb).contains(mapa.get(ka))){
                    System.out.println(ka + ">>>>"+ mapb.get(kb));
                }
            }
        }

    }

    @Test
    public void t1 () throws IOException {
        Map<String, String> mapa =  getMap("a");
        Map<String, String> mapb =  getMap("b");

        for (String ka : mapa.keySet()) {
            for (String kb : mapb.keySet()) {
                if(mapb.get(kb).contains(mapa.get(ka))){
                    System.out.println(ka + ">>>>"+ mapb.get(kb));
                }
            }
        }

    }

    public static Map<String, String> getMap(String fileName) throws IOException {
        String resourcePath = new File("").getCanonicalPath() + "/src/main/resources/";

        String text = fileTextString(resourcePath + fileName);
        String[] lines = text.split(System.getProperty("line.separator", "\n"));

        Map<String, String> map = new HashMap<>();
        for (String line : lines) {
            String[] words = line.split(":");
            map.put(words[0], words[1].trim());
        }

        return map;
    }


}
