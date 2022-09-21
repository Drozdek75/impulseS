package com.it.impulseS.mailPack;

public interface EmailService {
	
	void sendTextMessage(String to, String subject, String message);
    void sendHtmlMessage(String to, String subject, String content);

}
