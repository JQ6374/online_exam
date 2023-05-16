package com.zz.Service.impl;

import com.zz.Service.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService {
    /**
     * springboot专门发送邮件接口
     */
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${my-email.title}")
    private String emailTitle;

    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public boolean sendMail(String to, String code) {
        MimeMessage msg = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            //发送邮件的邮箱
            helper.setFrom(from);
            //发送到哪(邮箱)
            helper.setTo(to);
            //邮箱标题
            helper.setSubject(emailTitle);
            //邮箱文本
            String text = "<h1>" +
                    "            亲爱的," +
                    "        </h1>" +
                    "        <h3>" +
                    "            欢迎来到 <b>在线考试系统</b>!" +
                    "        </h3>" +
                    "        <p>" +
                    "            您的验证码为：<b>%s</b>，赶快去完善注册信息吧！！！" +
                    "        </p>" +
                    "        <p>感谢您的支持和理解！</p>" +
                    "        <p>来自：电影分析系统！</p>" +
                    "        <p>验证码五分钟后过期！</p>";
            msg.setText(String.format(text, code), "utf-8", "html");
            mailSender.send(msg);
        } catch (MailException | MessagingException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }


}