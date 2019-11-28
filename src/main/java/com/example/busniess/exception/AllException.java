package com.example.busniess.exception;

import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.InvalidSessionException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.mail.MessagingException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
public class AllException {

    /**
     * 登录拦截
     *
     * @param e
     * @return UnauthorizedException
     */
    @ExceptionHandler(ShiroException.class)
    public ReturnResult loginError(ShiroException e) {

        if (e instanceof UnknownAccountException) {
            return new ReturnResult(CodeMsg.USER_NOT_EXISTS);//用户不存在
        } else if (e instanceof IncorrectCredentialsException) {
            return new ReturnResult(CodeMsg.WRONG_PASSWORD);//密码错误
        } else if (e instanceof UnauthorizedException) {
            return new ReturnResult(CodeMsg.NOT_HAVE_LIMITS);//没有权限
        }
        return new ReturnResult(CodeMsg.SERVER_ERROR);//默认异常
    }

//
//    @ExceptionHandler(UnknownAccountException.class)
//    public ReturnResult shiroAccountError(UnknownAccountException e) {
//        return new ReturnResult(CodeMsg.USER_NOT_EXISTS);//用户不存在
//    }
//

//
//    @ExceptionHandler( UnauthorizedException.class)
//    public ReturnResult shiroUnauthorizedError(UnauthorizedException e) {
//        return new ReturnResult(CodeMsg.NOT_HAVE_LIMITS);//没有权限
//    }


    /**
     * 自定义异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MyException.class)
    public ReturnResult myError(MyException e) {
        return ReturnResult.erro(e.getCodeMsg());

    }

    /**
     * session失效
     * @param e
     * @return
     */
    @ExceptionHandler(InvalidSessionException.class)
    public ReturnResult shiroSessionError(MyException e) {
        return ReturnResult.success("登出成功!");

    }
    /**
     * 参数校验异常
     *
     * @param
     * @return
     */
    @ExceptionHandler({BindException.class})
    public ReturnResult parameterValidator(BindException e) {
        List<ObjectError> errors = e.getAllErrors();
        ObjectError error = errors.get(0);
        String msg = error.getDefaultMessage();
        return ReturnResult.erro(CodeMsg.BIND_ERROR.fillArgs(msg));
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ReturnResult parameterValidator(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> errors = e.getConstraintViolations();

        String message = errors.iterator().next().getMessage();
        CodeMsg codeMsg = new CodeMsg(500104, message);
        return ReturnResult.erro(codeMsg);
    }

    /**
     * 邮件异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({MessagingException.class})
    public ReturnResult emailValidator(MessagingException e) {
        return ReturnResult.erro(CodeMsg.EMAIL_ERROR);

    }

    /**
     * 验证码异常
     *
     * @param e
     * @return IOException
     */
    @ExceptionHandler(IOException.class)
    public ReturnResult codeException(IOException e) {
        return ReturnResult.erro(CodeMsg.File_ERROR);
    }
}
