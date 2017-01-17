package com.bupt.vouching.frame;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bupt.vouching.bean.Exam;
import com.bupt.vouching.bean.Question;
import com.bupt.vouching.bean.User;

/**
 * 全局上下文
 * 
 * @author Hogan
 * 
 */
@Component("globalContext")
@Scope("singleton")
public class GlobalContext {
	
	/**
	 * 用户Token
	 */
	private Map<String, User> userToken;

	/**
	 * 邮件类型
	 */
	private Map<String, Integer[]> emailDetail;

	/**
	 * 当前用户的练习试题
	 */
	private Map<String, List<? extends Question>> currentPractice;

	/**
	 * 用户正在参加的考试
	 */
	private Map<String, Exam> currentExam;

	public Map<String, User> getUserToken() {
		return userToken;
	}

	public void setUserToken(Map<String, User> userToken) {
		this.userToken = userToken;
	}

	public Map<String, List<? extends Question>> getCurrentPractice() {
		return currentPractice;
	}

	public void setCurrentPractice(
			Map<String, List<? extends Question>> currentPractice) {
		this.currentPractice = currentPractice;
	}

	public Map<String, Exam> getCurrentExam() {
		return currentExam;
	}

	public void setCurrentExam(Map<String, Exam> currentExam) {
		this.currentExam = currentExam;
	}

	public Map<String, Integer[]> getEmailDetail() {
		return emailDetail;
	}

	public void setEmailDetail(Map<String, Integer[]> emailDetail) {
		this.emailDetail = emailDetail;
	}

}
