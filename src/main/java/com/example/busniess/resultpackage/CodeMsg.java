package com.example.busniess.resultpackage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeMsg {
    public static CodeMsg USER_ALREADY_EXSIS = new CodeMsg(500100, "用户以存在,请重新输入");
    public static CodeMsg USER_NOT_EXSIS = new CodeMsg(500101, "用户不存在");
    public static CodeMsg WRONG_PASSWORD = new CodeMsg(500102, "密码错误");
    public static CodeMsg NOT_HAVE_LIMITS = new CodeMsg(500103, "对不起您还没有该权限");
    public static CodeMsg BIND_ERROR = new CodeMsg(500104, "参数校验异常:%s");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500105, "服务端未知异常!");
    public static CodeMsg EMAIL_ERROR = new CodeMsg(500106, "邮件发送异常!");
    public static CodeMsg CODE_ERROR = new CodeMsg(500107, "验证码错误!");
    public static CodeMsg FIND_PASSWORD_ERROR = new CodeMsg(500108, "密码找回错误请稍后再试!");
    public static CodeMsg UPDATE_PASSWORD_ERROR = new CodeMsg(500109, "密码修改失败!");
    public static CodeMsg CODE_SEND_ERROR = new CodeMsg(500110, "邮件验证码发送失败");
    public static CodeMsg CODE_TIMEOUT_ERROR = new CodeMsg(500111, "邮件验证码已失效");
    public static CodeMsg CODE_NOTBLANK_ERROR = new CodeMsg(500112, "邮件验证码不能为空");
    public static CodeMsg CODE_CHECK_ERROR = new CodeMsg(500113, "邮件验证码验证失败");
    public static CodeMsg EMAIL_Have_EXIST = new CodeMsg(500114, "该邮箱已注册");
    public static CodeMsg File_ERROR = new CodeMsg(500115, "文件上传错误!");
    public static CodeMsg DATA_FAIL = new CodeMsg(500116, "获取数据失败");
    public static CodeMsg DATA_EMPTY = new CodeMsg(500117, "暂无数据");
    public static CodeMsg AUDITOR_ERROR = new CodeMsg(500118, "授权失败!");
    public static CodeMsg ISSUE_ERROR = new CodeMsg(500119, "更新状态失败!");
    public static CodeMsg SUMIT_ERROR = new CodeMsg(500120, "提交失败!");
    public static CodeMsg DELECT_ERROR = new CodeMsg(500121, "删除失败!");
    public static CodeMsg UPDATE_ERROR = new CodeMsg(500122, "更新失败!");
    public static CodeMsg DATA_DUPLICATION = new CodeMsg(500201, "当前用户已提交企业认证，请勿重复提交!");
    public static CodeMsg ACCESS_DENIED = new CodeMsg(500202, "请提交企业认证，或等待企业认证通过!");

    private Integer code;
    private String msg;


    //返回一个带参数的错误码
    public CodeMsg fillArgs(Object... args) {//变参
        int code = this.code;
        //message是填充了参数的message
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }
}
