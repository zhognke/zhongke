package com.example.busniess.controller;

import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.utiles.CodeUtil;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@RestController
public class CodeController {
    /**
     * 获取验证那个码
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
     *192.168.30.43：8080
     * @param session
     * @param code
     * @return
     */
    @RequestMapping("/verificationCode")
    public ReturnResult verificationCode(HttpSession session, String code) {
        String number = (String) session.getAttribute("code");
        if (number.equals(code)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.CODE_ERROR);
        }
    }

}
