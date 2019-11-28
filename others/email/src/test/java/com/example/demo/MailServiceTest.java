package com.example.demo;
 

import com.example.demo.email.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@RunWith(SpringRunner.class)
@SpringBootTest
/**
 * @author longzhonghua
 * @data 2019/01/27 14:03
 */
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void simpleMail() throws Exception {
        mailService.sendMail("363694485@qq.com","简单文本邮件"," 邮件body");
    }

    @Test
    public void htmlMail() throws Exception {
        String content="<html>\n" +
                "<body>\n" +
                "    <h1>html邮件!</h1>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendMail("363694485@qq.com","html邮件",content);
    }

    @Test
    public void attachmentsMail() {
        String filePath="i:\\UPLOAD\\img\\二维码解析.png";
        mailService.sendMail("363694485@qq.com", "主题：附件的邮件", "邮件body", filePath);
    }


    @Test
    public void resourceMail() {
        String rsId = "001";
        String content="<html><body>邮件body图片邮件,但是大部分邮箱接收时候回屏蔽,所以接收后,需要点击显示图片才能显示,并不是程序问题：<img src=\'cid:" + rsId + "\' ></body></html>";
        String imgPath = "i:\\UPLOAD\\img\\二维码解析.png";

        mailService.sendMail("363694485@qq.com", "主题：图片邮件", content, imgPath, rsId);
    }


    @Test
    public void sendTemplateMail() {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "888");
        String emailContent = templateEngine.process("emailTemplate", context);
        mailService.sendMail("363694485@qq.com","主题：模板邮件",emailContent);
    }
}