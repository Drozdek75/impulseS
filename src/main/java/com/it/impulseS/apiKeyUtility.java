package com.it.impulseS;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;

public class apiKeyUtility {
	
	@Value("${secret_api_key}")
	private String secret;
	
	
	public String generateMD5HashValue(String telephoneNumber) {
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMMddHHmmss", Locale.ITALIAN);
		String dateF = date.format(formatter);
		MessageDigest md;
		byte[] hashKey = null;

		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException(e);
		}

		hashKey = md.digest((dateF + telephoneNumber + secret).getBytes(StandardCharsets.UTF_8));

		return this.bytesToHex(hashKey);
		
		
	}

	private String bytesToHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}


}
