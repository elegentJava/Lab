package com.bupt.vouching.util;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 邮件工具类
 * 
 * @author Hogan
 * 
 */
@Component("emailUtils")
@Scope("singleton")
public class EmailUtils {
	
	private static final String HOST_NAME = "smtp.163.com";
	private static final String USERNAME="18800162572@163.com";
	private static final String PASSWORD = "2050233a";

	/**
	 * 
	 * @param receiver
	 * @param subject
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public boolean sendHtmlEmail(String receiver,String subject,String content) throws Exception {
		HtmlEmail email = new HtmlEmail();
		email.setHostName(HOST_NAME);
		email.setAuthentication(USERNAME, PASSWORD);
		email.addTo(receiver, "John Doe");
		email.setFrom(USERNAME, "Me");
		email.setSubject(subject);
		email.setHtmlMsg("<html>The apache logo </html>");
		email.setTextMsg("Your email client does not support HTML messages");
		email.send();
		return true;
	}
	
}
