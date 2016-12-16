package com.gzq.shop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailUtils {
	/***
	 * @param to:收件人
	 * code：激活码
	 */
	public static final String Host ="smtp.163.com";
	public static final String PROTOCOL ="smtp";
	public static final int PORT=25;
	public static final String FROM="fly_bird_one@163.com";
	public static final String PWD="gzq1108";
	public static void sendMail(String to , String code){
		/****
		 * 1,获取一个seesion对象
		 * 2，创建一个代表邮件的对象message
		 * 3，发送邮件transport
		 */
		//获得链接对象
		
		Properties props=new Properties();
		props.put("mail.smtp.host", Host);
		props.put("mail.store.protocol", PROTOCOL);
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.auth", true);
		
		Session session=Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(FROM, PWD);
			}
		});
		//创建一个邮件对象
		Message message=new MimeMessage(session);
		try {
			//设置发件人
			message.setFrom(new InternetAddress(FROM));
			//设置收件人
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			//设置标题
			message.setSubject("欢迎来到购物天堂");
			//设置邮件正文
			
			message.setContent("<h1>购物天堂传智商城官方激活邮件!点下面链接完成激活操作!</h1><h3><a href='http://192.168.1.2:8080/myshop/user_active.action?code="+code+"'>http://192.168.1.2:8080/shop/user_active.action?code="+code+"</a></h3>", "text/html;charset=UTF-8");
		
		Transport.send(message);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		sendMail("big_zhou@126.com", "123");
	}
}
