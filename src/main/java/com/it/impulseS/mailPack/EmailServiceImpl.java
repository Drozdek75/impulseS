package com.it.impulseS.mailPack;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender emailSender;

	@Override
	public void sendTextMessage(String to, String subject, String txt) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("noreply@impulse.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(txt);
		emailSender.send(message);
	}

	@Override
	public void sendHtmlMessage(String to, String subject, String content) {
		MimeMessage message = this.emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(to);
			helper.setFrom("noreply@impulse.com");
			helper.setSubject(subject);
			helper.setText(content, true);
			this.emailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
