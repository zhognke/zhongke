package com.example.busniess.utiles;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.Random;

public class Md5Utiles {

    /**
     * 加密
     * @param hashAlgorithnName//密码加密方式
     * @param credentials//要加密的字符
     * @param saltName//盐
     * @param hashIterations//加密次数
     * @return
     */
    public static  String returnMd5(String hashAlgorithnName,Object credentials,String saltName , Integer hashIterations){
        Object salt = ByteSource.Util.bytes(saltName);
        String result=  new SimpleHash(hashAlgorithnName,credentials,salt,hashIterations).toString();

        return result;
    }

    /**
     * 产生随机数
     * @param digit
     * @return
     */

    public static String getNum(int digit) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < digit; i++) {
            if (i == 0 && digit > 1)
                str.append(new Random().nextInt(9) + 1);
            else
                str.append(new Random().nextInt(10));
        }
        return str.toString();
    }

    public static void main(String[] args) {
        System.out.println(returnMd5("md5", "123456", "admin", 1024));
    }

}
