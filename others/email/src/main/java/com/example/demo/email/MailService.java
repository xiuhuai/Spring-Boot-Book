package com.example.demo.email;

/**
 * @author longzhonghua
 * @data 2019/01/27 13:55
 */

public interface MailService {
    public void sendMail(String to, String subject, String content);

    public void sendMail(String to, String subject, String content, String filePath);

    public void sendMail(String to, String subject, String content, String rsPath, String rsId);
}

