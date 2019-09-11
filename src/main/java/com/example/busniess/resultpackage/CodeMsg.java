package com.example.busniess.resultpackage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeMsg {
    public static CodeMsg USER_ALREADY_EXSIS  = new CodeMsg(500100, "用户以存在,请重新输入");
    public static CodeMsg USER_NOT_EXSIS = new CodeMsg(500101, "用户不存在");
    public static CodeMsg WRONG_PASSWORD = new CodeMsg(500102, "密码错误");
    public static CodeMsg BIND_ERROR=new CodeMsg(500103,"参数校验异常:%s");
    public static CodeMsg SERVER_ERROR=new CodeMsg(500104,"服务端未知异常!");
    private Integer code;
    private String msg;


    //返回一个带参数的错误码
    public CodeMsg fillArgs(Object...args) {//变参
        int code=this.code;
        //message是填充了参数的message
        String message=String.format(this.msg, args);
        return new CodeMsg(code,message);
    }
}
