package com.example.busniess.exception;

import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
    public class AllException {
        @ExceptionHandler(Exception.class)
        public ReturnResult loginError(Exception e){
            if(e instanceof UnknownAccountException){
                return new ReturnResult(CodeMsg.USER_NOT_EXSIS);
            }else  if (e instanceof IncorrectCredentialsException){
                return new ReturnResult(CodeMsg.WRONG_PASSWORD);
            }
     return null;
        }




}
