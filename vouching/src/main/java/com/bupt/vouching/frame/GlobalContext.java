package com.bupt.vouching.frame;

import java.util.Deque;
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
	 * 用户当前登录的IP地址
	 */
	private Map<String, String> userIP;

	/**
	 * 当前用户的练习试题
	 */
	private Map<String, List<? extends Question>> currentPractice;

	/**
	 * 用户正在参加的考试
	 */
	private Map<String, Exam> currentExam;

	/**
	 * 用户是否在考试标签
	 */
	private Map<String, Boolean> userInExamMap;

	/**
	 * 用户加入的临时竞技队列
	 */
	private Deque<User> competitonTempQueue;

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

	public Map<String, Boolean> getUserInExamMap() {
		return userInExamMap;
	}

	public void setUserInExamMap(Map<String, Boolean> userInExamMap) {
		this.userInExamMap = userInExamMap;
	}

	public Map<String, String> getUserIP() {
		return userIP;
	}

	public void setUserIP(Map<String, String> userIP) {
		this.userIP = userIP;
	}

	public Deque<User> getCompetitonTempQueue() {
		return competitonTempQueue;
	}

	public void setCompetitonTempQueue(Deque<User> competitonTempQueue) {
		this.competitonTempQueue = competitonTempQueue;
	}

}
