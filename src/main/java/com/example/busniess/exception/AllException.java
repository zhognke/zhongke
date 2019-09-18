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

import javax.mail.MessagingException;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.List;

@RestControllerAdvice
public class AllException {

    /**
     * 登录拦截
     * @param e
     * @return
     */
    @ExceptionHandler(ShiroException.class)
    public ReturnResult loginError(ShiroException e) {
        if (e instanceof UnknownAccountException) {
            return new ReturnResult(CodeMsg.USER_NOT_EXSIS);//用户不存在
        } else if (e instanceof IncorrectCredentialsException) {
            return new ReturnResult(CodeMsg.WRONG_PASSWORD);//密码错误
        }else if(e instanceof UnauthorizedException){
            return new ReturnResult(CodeMsg.NOT_HAVE_LIMITS);//没有权限
        }
        return new ReturnResult(CodeMsg.SERVER_ERROR);//默认异常
    }

    /**
     * 自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(MyException.class)
    public ReturnResult myError(MyException e) {
        return ReturnResult.erro(e.getCodeMsg());

    }

    /**
     * 参数校验异常
     * @param ConstraintViolationException
     * @return
     */
    @ExceptionHandler({BindException.class})
    public ReturnResult parameterValidator(BindException e) {
        List<ObjectError> errors = e.getAllErrors();
        ObjectError error = errors.get(0);
        String msg = error.getDefaultMessage();
        return ReturnResult.erro(CodeMsg.BIND_ERROR.fillArgs(msg));
    }
// e

    /**
     * 邮件异常
     * @param e
     * @return
     */
    @ExceptionHandler({MessagingException.class})
    public ReturnResult emailValidator(MessagingException e){
        return ReturnResult.erro(CodeMsg.EMAIL_ERROR);

    }

    /**
     * 验证码异常
     * @param e
     * @return
     */
    @ExceptionHandler(IOException.class)
    public ReturnResult codeException(IOException e){
        return ReturnResult.erro(CodeMsg.CODE_ERROR);
    }
}
