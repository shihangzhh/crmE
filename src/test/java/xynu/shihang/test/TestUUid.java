package xynu.shihang.test;

import java.util.UUID;

public class TestUUid {

    public static  void  main(String [] args){
        String s = UUID.randomUUID().toString().replace('-','1');

        System.out.println(s);

  String str = "aaa.jpg";
        String str1 = str.substring(str.indexOf("."));
        System.out.println(str1);

    }
}
