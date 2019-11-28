package com.example.demo.util;


import com.example.demo.repository.member.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.sql.Timestamp;
import java.util.UUID;

@Service

public class AsyncSendEmailService {
    @Autowired
    private UserRepository userRepository;
    @Resource
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String mailFrom;
    @Value("${mail.subject.forgotpassword}")
    private String mailSubject;
    @Value("${mail.subject.active}")
    private String mailActiveSubject;

    @Value("${mail.content.forgotpassword}")
    private String mailContent;
    @Value("${mail.content.active}")
    private String mailActiveContent;
    @Value("${forgotpassword.url}")
    private String forgotpasswordUrl;
    @Value("${activeuser.url}")
    private String activeuserUrl;

    @Async   //这是一个异步方法

    public void sendVerifyemail(String email) {
        try {
            Thread.sleep(3000);
            String secretKey = UUID.randomUUID().toString(); // 密钥
            Timestamp outDate = new Timestamp(System.currentTimeMillis() + 30 * 60 * 1000);// 30分钟后过期
            long date = outDate.getTime() / 1000 * 1000;
            userRepository.setOutDateAndValidataCode(outDate + "", secretKey, email);
            String key = email + "$" + date + "$" + secretKey;
            String digitalSignature = MD5Util.encode(key);// 数字签名
//            String basePath = this.getRequest().getScheme() + "://" + this.getRequest().getServerName() + ":" + this.getRequest().getServerPort() + this.getRequest().getContextPath() + "/newPassword";
            String resetPassHref = activeuserUrl + "?sid="
                    + digitalSignature + "&email=" + email;
            String emailContent = MessageUtil.getMessage(mailActiveContent, resetPassHref);
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(mailFrom);
            helper.setTo(email);

            helper.setSubject(mailActiveSubject);
            helper.setText(emailContent, true);

            mailSender.send(mimeMessage);
        } catch   (Exception e)  {
            e.printStackTrace();
        }
        System.out.println(email);

    }
}