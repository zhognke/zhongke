package com.example.busniess.utiles;

import com.example.busniess.entity.MsendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Component
public class EmailUtiles {


    private static String from;

    public static String getFrom() {
        return from;
    }

    @Value("${spring.mail.username}")
    public void setFrom(String from) {
        EmailUtiles.from = from;
    }

    @Autowired
    private JavaMailSender mailSender0;
    private static JavaMailSender mailSender;

    @PostConstruct
    public void init() {
        EmailUtiles.mailSender = mailSender0;
    }

    public static Boolean sendEmail(String subject, String email, String text) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setText(text);
        mimeMessageHelper.setFrom(from);
        mailSender.send(mimeMessage);
        return true;
    }


    //发送验证码邮件
    public static void sendMailCode(MsendMail mail, String peopleCode, String email) {
        String host = mail.getServer();
        int port = mail.getPort();
        String mailName = mail.getMail();
        String mailPassword = mail.getPassword();
        String mailFormName = mail.getName();
        String title = "注册验证";
        String context = "您的验证码为:" + peopleCode;
        String[] toUser = email.split(",");
        EmailUtiles.sendHtml(host, port, mailName, mailPassword, mailFormName, title, context, toUser);
    }

    /**
     * 发送简html内容的邮件
     *
     * @param host     主机
     * @param port     　端口
     * @param userName 　账号
     * @param password 　密码
     * @param formName 　发送人姓名
     * @param title    　标题
     * @param content  　html内容
     * @param toUser   　接收人
     * @throws javax.mail.MessagingException
     */
    public static void sendHtml(String host, int port, String userName, String password, String formName, String title, String content, String[] toUser) {
        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
        // 设定mail server
        senderImpl.setHost(host);
        senderImpl.setPort(port);
        // 建立邮件消息,发送简单邮件和html邮件的区别
        MimeMessage mailMessage = senderImpl.createMimeMessage();

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "UTF-8");

            try {
                // 设置收件人，寄件人
                messageHelper.setTo(toUser);
                try {
                    messageHelper.setFrom(userName, formName);
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                messageHelper.setSubject(title);
                // true 表示启动HTML格式的邮件
                messageHelper.setText(content, true);
            } catch (javax.mail.MessagingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            senderImpl.setUsername(userName); // 根据自己的情况,设置username
            senderImpl.setPassword(password); // 根据自己的情况, 设置password
            Properties prop = new Properties();
            prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
            prop.put("mail.smtp.timeout", "25000");
            prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            senderImpl.setJavaMailProperties(prop);
            // 发送邮件
            senderImpl.send(mailMessage);
        } catch (javax.mail.MessagingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


    }
}
