package com.example.busniess.exception;

import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class AllException {
    @ExceptionHandler(ShiroException.class)
    public ReturnResult loginError(ShiroException e) {
        if (e instanceof UnknownAccountException) {
            return new ReturnResult(CodeMsg.USER_NOT_EXSIS);
        } else if (e instanceof IncorrectCredentialsException) {
            return new ReturnResult(CodeMsg.WRONG_PASSWORD);
        }else if(e instanceof UnauthorizedException){
            return new ReturnResult(CodeMsg.NOT_HAVE_LIMITS);
        }
        return new ReturnResult(CodeMsg.SERVER_ERROR);
    }

    @ExceptionHandler(MyException.class)
    public ReturnResult myError(MyException e) {
        return ReturnResult.erro(e.getCodeMsg());

    }


    @ExceptionHandler(BindException.class)
    public ReturnResult parameterValidator(BindException e) {
        List<ObjectError> errors = e.getAllErrors();
        ObjectError error = errors.get(0);
        String msg = error.getDefaultMessage();
        return ReturnResult.erro(CodeMsg.BIND_ERROR.fillArgs(msg));
    }
}
