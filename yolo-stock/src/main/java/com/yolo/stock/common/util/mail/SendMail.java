package com.yolo.stock.common.util.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail {
    /**
     * 功能描述
     *
     * @param: 发送邮件
     * @return:
     * @date: 2018/11/29   9:59
     **/
    public static void createSimpleMail(Mail mail) {
        try {
            Properties prop = new Properties();
            prop.put("mail.host", "smtp.163.com");
            prop.put("mail.transport.protocol", "smtp");
            prop.put("mail.smtp.auth", true);
            //默认的25端口被阿里云禁了用465
            prop.setProperty("mail.smtp.port", "465");
            prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            prop.setProperty("mail.smtp.socketFactory.fallback", "false");
            prop.setProperty("mail.smtp.socketFactory.port", "465");
            //使用java发送邮件5步骤
            //1.创建sesssion
            Session session = Session.getInstance(prop);
            //开启session的调试模式，可以查看当前邮件发送状态
            session.setDebug(true);
            //2.通过session获取Transport对象（发送邮件的核心API）
            Transport ts = null;
            ts = session.getTransport();
            //3.通过邮件用户名密码链接(邮箱和授权码)
            ts.connect(mail.getSendMail(), mail.getPassword());
            //4.创建邮件对象
            MimeMessage mm = new MimeMessage(session);
            //设置发件人
            mm.setFrom(new InternetAddress(mail.getSendMail()));
            //设置收件人
            mm.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getReceiveMail()));
            //设置抄送人
//        mm.setRecipient(Message.RecipientType.CC, new InternetAddress(sendMail));
            mm.setSubject(mail.getSubject());
            mm.setContent(mail.getContent(), "text/html;charset=utf-8");
            //5.发送电子邮件
            ts.sendMessage(mm, mm.getAllRecipients());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws MessagingException {
        Mail mail = new Mail();
        mail.setSendMail("15051959350@163.com");
        mail.setReceiveMail("1272220612@qq.com");
        mail.setPassword("zhuri123");
        mail.setSubject("逐日信息科技有限公司！");
        mail.setContent("<div>您的账号是：");
        createSimpleMail(mail);
    }
}
