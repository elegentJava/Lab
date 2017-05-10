package com.bupt.vouching.frame;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bupt.vouching.bean.Correspondence;
import com.bupt.vouching.bean.Exam;
import com.bupt.vouching.bean.Question;
import com.bupt.vouching.bean.User;
import com.bupt.vouching.bean.UserPaper;
import com.bupt.vouching.service.bean.CompetitionSer;

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
	
	/**
	 * 老师正在批阅的试卷
	 */
	private Map<String,List<UserPaper>> markPapers;

	/**
	 * 匹配等待队列
	 */
	private LinkedList<String> watchingQueue;

	/**
	 * 匹配处理Map[competitionId-competition]
	 */
	private Map<String, CompetitionSer> competitionMap;

	/**
	 * 匹配成功后用户检索Map[tokenId-competitionId]
	 */
	private Map<String, String> matchingMap;

	/**
	 * 用户当前正在访问的函电
	 */
	private Map<String, Correspondence> currentCorrespondence;

	public Map<String, User> getUserToken() {
		return userToken;
	}

	public void setUserToken(Map<String, User> userToken) {
		this.userToken = userToken;
	}

	public Map<String, List<? extends Question>> getCurrentPractice() {
		return currentPractice;
	}

	public void setCurrentPractice(Map<String, List<? extends Question>> currentPractice) {
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

	public LinkedList<String> getWatchingQueue() {
		return watchingQueue;
	}

	public void setWatchingQueue(LinkedList<String> watchingQueue) {
		this.watchingQueue = watchingQueue;
	}

	public Map<String, CompetitionSer> getCompetitionMap() {
		return competitionMap;
	}

	public void setCompetitionMap(Map<String, CompetitionSer> competitionMap) {
		this.competitionMap = competitionMap;
	}

	public Map<String, String> getMatchingMap() {
		return matchingMap;
	}

	public void setMatchingMap(Map<String, String> matchingMap) {
		this.matchingMap = matchingMap;
	}

	public Map<String, Correspondence> getCurrentCorrespondence() {
		return currentCorrespondence;
	}

	public void setCurrentCorrespondence(Map<String, Correspondence> currentCorrespondence) {
		this.currentCorrespondence = currentCorrespondence;
	}

	public Map<String, List<UserPaper>> getMarkPapers() {
		return markPapers;
	}

	public void setMarkPapers(Map<String, List<UserPaper>> markPapers) {
		this.markPapers = markPapers;
	}
	
}
