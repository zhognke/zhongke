package com.example.busniess.utiles;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class Md5Utiles {

    /**
     * 加密
     * @param hashAlgorithnName//密码加密方式
     * @param credentials//要加密的字符
     * @param saltName//盐
     * @param hashIterations//加密次数
     * @return
     */
    public static  String reeturnMd5(String hashAlgorithnName,Object credentials,String saltName , Integer hashIterations){
        Object salt = ByteSource.Util.bytes(saltName);
        String result=  new SimpleHash(hashAlgorithnName,credentials,salt,hashIterations).toString();

        return result;
    }


}
