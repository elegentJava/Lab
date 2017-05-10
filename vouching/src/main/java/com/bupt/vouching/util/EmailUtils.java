package com.bupt.vouching.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;
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
	
	private Logger log = Logger.getLogger(getClass());
	
	private static final String HOST_NAME = "smtp.163.com";
	private static final String USERNAME="18800162572@163.com";
	private static final String PASSWORD = "2050233a";
	private static final String NICK_NAME = "国贸管理系统";

	/**
	 * 
	 * @param receiver
	 * @param subject
	 * @param content
	 * @return
	 */
	public boolean sendHtmlEmail(String receiver,String subject,String content) {
		HtmlEmail email = new HtmlEmail();
		email.setHostName(HOST_NAME);
		email.setAuthentication(USERNAME, PASSWORD);
		try {
			email.addTo(receiver);
			email.setFrom(USERNAME, NICK_NAME);
			email.setSubject(subject);
			email.setHtmlMsg("<html>"+content+"</html>");
			email.send();
		} catch (EmailException e) {
			log.error("邮件发送失败!",e);
			return false;
		}
		return true;
	}
	
}
