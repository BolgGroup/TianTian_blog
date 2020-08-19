package com.tiantian.utils.security;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.Random;

/**
 * @author qi_bingo
 */
public class PasswordUtil {

    /**
     * 定义使用的算法为:MD5
     */
    private static final String ALGORITHM = "MD5";

    /**
     * 定义迭代次数为1000次
     */
    private static final int ITERATIONCOUNT = 1000;

    /**
     * 生成10位随机盐值，从26个大小写字母中抽取
     * @return 盐值
     */
    public static String getSalt(){
        int saltLength = 10;
        String model = "abcdefghijklnmopqrstvuwxyzABCDEFGHIJKLNMOPQRSTVUWXYZ0123456789";
        StringBuilder salt = new StringBuilder();
        char[] saltChars = model.toCharArray();
        for (int i = 0; i < saltLength; i++){
            char saltChar = saltChars[new Random().nextInt(model.length()-1)];
            salt.append(saltChar);
        }
        return salt.toString();
    }

    /**
     * 根据26位大小写字母及10位阿拉伯数字的盐值，加密密码
     * @param password 原生密码
     * @param salt 盐值
     * @return  加密后的密码
     */
    public static String encrypt(String password, String salt){
        Object obj = new SimpleHash(ALGORITHM, password, ByteSource.Util.bytes(salt), ITERATIONCOUNT);
        return obj.toString();
    }

    public static void main(String[] args) {
        try {
            //获取盐值
            String salt = String.valueOf(getSalt());
            String pwd = "tt@123_blog";
            //加密后密码
            String password = PasswordUtil.encrypt(pwd, salt);
            System.out.println(password+":"+salt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
