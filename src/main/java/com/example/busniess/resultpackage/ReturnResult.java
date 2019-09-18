package com.example.busniess.resultpackage;


import lombok.Data;
@Data
public class ReturnResult <T>{
    private  Integer code;
    private String msg;
    private T data;

private ReturnResult(T data){
    this.code=0;
    this.msg="成功";
    this.data=data;

    }


public ReturnResult(CodeMsg codeMsg){
    if(codeMsg==null){
        return;
    }
    this.code=codeMsg.getCode();
    this.msg=codeMsg.getMsg();
}

        /**
     * 成功调用的方法
     * @param data
     * @param <T>
     * @return
     */
    public static<T> ReturnResult success(T data){
    return new ReturnResult(data);
}

    /**
     * 成功调用不需要传值
     * @return
     */
    public  static ReturnResult success(){
        return success("");
}
/**
 * 失败调用
 */
public  static <T> ReturnResult erro(CodeMsg codeMsg){
    return new ReturnResult(codeMsg);
}
/**
 * 失败调用
 */


}
