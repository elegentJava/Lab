package com.bupt.vouching.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.bupt.vouching.frame.Consts;

/**
 * 用户答卷实体
 * 
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

	private List<Integer> radioIds;
	private List<Integer> blankIds;
	private List<Integer> phraseIds;
	private List<Integer> translateIds;
	private List<Integer> clozeIds;
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

	public List<Integer> getRadioIds() {
		if (this.radios != null) {
			radioIds = generateIds(this.radios);
		}
		return radioIds;
	}

	public void setRadioIds(List<Integer> radioIds) {
		this.radioIds = radioIds;
	}

	public List<Integer> getBlankIds() {
		if (this.blanks != null) {
			blankIds = generateIds(this.blanks);
		}
		return blankIds;
	}

	public void setBlankIds(List<Integer> blankIds) {
		this.blankIds = blankIds;
	}

	public List<Integer> getPhraseIds() {
		if (this.phrases != null) {
			phraseIds = generateIds(this.phrases);
		}
		return phraseIds;
	}

	public void setPhraseIds(List<Integer> phraseIds) {
		this.phraseIds = phraseIds;
	}

	public List<Integer> getTranslateIds() {
		if (this.translates != null) {
			translateIds = generateIds(this.translates);
		}
		return translateIds;
	}

	public void setTranslateIds(List<Integer> translateIds) {
		this.translateIds = translateIds;
	}

	public List<Integer> getClozeIds() {
		if (this.clozes != null) {
			clozeIds = generateIds(this.clozes);
		}
		return clozeIds;
	}

	public void setClozeIds(List<Integer> clozeIds) {
		this.clozeIds = clozeIds;
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
		if(this.status != null){
			statusName = this.status == 0 ? "还未批阅":"已经批阅";
		}
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	private List<Integer> generateIds(String id) {
		List<Integer> result = new ArrayList<Integer>();
		String[] ids = id.split(Consts.COMMON_SEPARATOR);
		for (int i = 0; i < ids.length; i++) {
			result.add(Integer.parseInt(ids[i]));
		}
		return result;
	}
}
