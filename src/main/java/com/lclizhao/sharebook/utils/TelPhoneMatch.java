package com.lclizhao.sharebook.utils;/**
 * Created by lizhaoz on 2015/11/30.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Name:
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-26（创建日期）
 * @Description:判断是否是一个正确地手机号码,针对国内
 */
public class TelPhoneMatch  {
    public static boolean isTel(String tel){
        Pattern p = Pattern.compile("^^[1][3,4,5,8][0-9]{9}$");//电话号码

        Matcher m = p.matcher(tel);
        return m.matches();
    }
    public static  boolean isPassword(String password){
        Pattern p=Pattern.compile("^[a-zA-Z][a-zA-Z0-9\\-`=\\[\\];',./~!@#$%^&*()_+|\\{\\}:\"<>?]{5,15}$");// 	以字母开头，长度在6~18之间，只能包含字符、数字和下划线
        Matcher m=p.matcher(password);
        return m.matches();
    }
//    @Test
//    public void test1(){
//        Pattern p = Pattern.compile("[0-9]{1,6}");//电话号码
//
//        Matcher m = p.matcher("12345j");
//        System.out.println(m.matches());
//    }
}
