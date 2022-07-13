package com.qst.yunpan.utils;

import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;

public class UserUtils {
    public static String getUsername(HttpServletRequest request){
        return (String) request.getSession().getAttribute("username");
    }

    public static String MD5(String password){
        if(password!=null){
            return DigestUtils.md5DigestAsHex(password.getBytes()).toUpperCase();
        }else{
            return null;
        }
    }
}
