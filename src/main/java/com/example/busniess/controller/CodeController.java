package com.example.busniess.controller;

import com.example.busniess.exception.MyException;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.ForgetPasswordImplement;
import com.example.busniess.utiles.CodeUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.Map;

@RestController
@Validated
public class CodeController {

    @Autowired
    ForgetPasswordImplement forgetPasswordImplement;

    /**
     * 获取前端验证那个码
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @RequestMapping("/getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException {

        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment;filename=" + "vcode.jpeg");
        String number = CodeUtil.getNumber(4);
        session.setAttribute("code", number);
        CodeUtil.getImage(response.getOutputStream(), number);

    }

    /**
     * 对比验证码
     * 192.168.30.43：8080
     *
     * @param session
     * @param code
     * @return
     */
    @RequestMapping("/verificationCode")
    public ReturnResult verificationCode(HttpSession session, @NotBlank(message = "验证码不能为空") String code) {


        String number = (String) session.getAttribute("code");
        if (code.equalsIgnoreCase(number)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.CODE_ERROR);
        }
    }

    /**
     * 获取邮箱验证功码
     * @param session
     * @param userName  用户名
     * @return
     * @throws MessagingException
     * @throws MyException
     */
    @RequestMapping("getEmailCode")
    public ReturnResult getEmailCode(HttpSession session,@NotBlank(message = "用户名不能为空")String userName) throws MessagingException, MyException {
      if(forgetPasswordImplement.checkEmail(userName,session)) {
          return  ReturnResult.success();
      } else {
          return ReturnResult.erro(CodeMsg.SERVER_ERROR);
      }

    }


}
