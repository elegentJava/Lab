package com.bupt.vouching.bean;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.bupt.vouching.frame.Consts;

/**
 * 用户答卷实体
 * 
 * @author Hogan
 * 
 */
/**
 * @author Hogan
 *
 */
public class UserPaper {

	private Integer userPaperId;
	private Date answerDate;
	private String radios;
	private String blanks;
	private String phrases;
	private String translates;
	private String clozes;
	private Integer examId;
	private Integer score;
	private Integer userId;
	private Integer status;

	private Integer[] radioAnswers;
	private String[] blankAnswers;
	private String[] phraseAnswers;
	private String[] translateAnswers;
	private String[] clozeAnswers;
	private Exam exam;
	private User user;

	private String statusName;
	private String formatAnswerDate;

	public Integer getUserPaperId() {
		return userPaperId;
	}

	public void setUserPaperId(Integer userPaperId) {
		this.userPaperId = userPaperId;
	}

	public Date getAnswerDate() {
		return answerDate;
	}

	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}

	public String getRadios() {
		return radios;
	}

	public void setRadios(String radios) {
		this.radios = radios;
	}

	public String getBlanks() {
		return blanks;
	}

	public void setBlanks(String blanks) {
		this.blanks = blanks;
	}

	public String getPhrases() {
		return phrases;
	}

	public void setPhrases(String phrases) {
		this.phrases = phrases;
	}

	public String getTranslates() {
		return translates;
	}

	public void setTranslates(String translates) {
		this.translates = translates;
	}

	public String getClozes() {
		return clozes;
	}

	public void setClozes(String clozes) {
		this.clozes = clozes;
	}

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFormatAnswerDate() {
		if (this.answerDate != null) {
			formatAnswerDate = DateFormatUtils.format(this.answerDate, Consts.DATE_SIMPLE_PATTERN);
		}
		return formatAnswerDate;
	}

	public void setFormatAnswerDate(String formatAnswerDate) {
		this.formatAnswerDate = formatAnswerDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusName() {
		if (this.status != null) {
			statusName = this.status == 0 ? "还未批阅" : "已经批阅";
		}
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer[] getRadioAnswers() {
		if(this.radios != null){
			String[] temp = radios.split(Consts.COMMON_SEPARATOR);
			Integer[] result = new Integer[temp.length];
			for (int i = 0; i < result.length; i++) {
				result[i] = Integer.parseInt(temp[i]);
			}
			radioAnswers = result;
		}
		return radioAnswers;
	}

	public void setRadioAnswers(Integer[] radioAnswers) {
		this.radioAnswers = radioAnswers;
	}

	public String[] getBlankAnswers() {
		if(this.blanks != null){
			blankAnswers = blanks.split(Consts.COMMON_SEPARATOR);
		}
		return blankAnswers;
	}

	public void setBlankAnswers(String[] blankAnswers) {
		this.blankAnswers = blankAnswers;
	}

	public String[] getPhraseAnswers() {
		if(this.phrases != null){
			phraseAnswers = phrases.split(Consts.COMMON_SEPARATOR);
		}
		return phraseAnswers;
	}

	public void setPhraseAnswers(String[] phraseAnswers) {
		this.phraseAnswers = phraseAnswers;
	}

	public String[] getTranslateAnswers() {
		if(this.translates != null){
			translateAnswers = translates.split(Consts.COMMON_SEPARATOR);
		}
		return translateAnswers;
	}

	public void setTranslateAnswers(String[] translateAnswers) {
		this.translateAnswers = translateAnswers;
	}

	public String[] getClozeAnswers() {
		if(this.clozes != null){
			clozeAnswers = clozes.split(Consts.COMMON_SEPARATOR);
		}
		return clozeAnswers;
	}

	public void setClozeAnswers(String[] clozeAnswers) {
		this.clozeAnswers = clozeAnswers;
	}

}
