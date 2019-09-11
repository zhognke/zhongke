package com.example.busniess.resultpackage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeMsg {
public static CodeMsg USER_NOT_EXSIS=new CodeMsg(500101,"用户不存在");
    public static CodeMsg WRONG_PASSWORD=new CodeMsg(500102,"密码错误");
    private  Integer code;
    private  String msg;
}
