package com.rzice.lynley.police.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/6/2 0002.
 */
public class PhoneUtil {

    public static String Phone(String str){
        if(str.trim().equals("")){
            return "手机号不能为空";
        }
        if(!isTelValid(str)){
            return "手机号格式不对" ;
        }
        if(str.length()!=11){
            return "手机号格式不对" ;
        }
        return  "";
    }

    private static boolean isTelValid(String tel) {
        Pattern p = Pattern
                .compile("0?(13|14|15|18|17)[0-9]{9}");
        Matcher matcher = p.matcher(tel);
        return matcher.matches();
    }
}
