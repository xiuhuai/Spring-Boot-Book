package com.example.demo.email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
/**
 * @author longzhonghua
 * @data 2019/01/27 13:55
 */
@Component
@Async   //这是一个异步方法,方法测试见test单元
public class MailServiceImpl implements MailService{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private JavaMailSender  javaMailSender;

/* 发送文本邮件*//*
    @Override
    public void sendMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        try {
            javaMailSender.send(message);
            logger.info("已发送。");
        } catch (Exception e) {

            logger.error("发送时发生异常！", e);
        }

    }*/

    /**
     * 发送文本、html邮件
     */
    @Override
    public void sendMail(String to, String subject, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            //multipart true
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            javaMailSender.send(message);
            logger.info("发送成功");
        } catch (MessagingException e) {
            logger.error("发送时发生异常！", e);
        }
    }


    /**
     * 发送带附件的邮件
     */

    @Override
    public void sendMail(String to, String subject, String content, String filePath){
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
            javaMailSender.send(message);
            logger.info("已经发送。");
        } catch (MessagingException e) {
            logger.error("发送时发生异常！", e);
        }
    }


    /**
     * 发送正文中有静态图片邮件
     */
    @Override
    public void sendMail(String to, String subject, String content, String rsPath, String rsId){
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rsPath));
            helper.addInline(rsId, res);

            javaMailSender.send(message);
            logger.info("已经发送。");
        } catch (MessagingException e) {
            logger.error("发送时发生异常！", e);
        }
    }
}
