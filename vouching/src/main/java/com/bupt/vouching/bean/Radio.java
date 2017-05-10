package com.bupt.vouching.bean;

import com.bupt.vouching.frame.Consts;
import com.bupt.vouching.type.QuestionCategory;

/**
 * 单选题实体
 * 
 * @author Hogan
 * 
 */
public class Radio implements Question {

	private Integer radioId;
	private String question;
	private String answer;
	private String option;
	private Integer level;
	private Integer chapterId;

	private String[] options;

	public Integer getRadioId() {
		return radioId;
	}

	public void setRadioId(Integer radioId) {
		this.radioId = radioId;
	}

	@Override
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Override
	public Integer getChapterId() {
		return chapterId;
	}

	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
	}

	public String[] getOptions() {
		if (this.option != null) {
			options = this.option.split(Consts.COMMON_SEPARATOR);
		}
		return options;
	}

	public void setOptions(String[] options) {
		this.options = options;
	}

	@Override
	public Integer getId() {
		return this.radioId;
	}

	@Override
	public String getTag() {
		return QuestionCategory.RADIO.getTag();
	}

	@Override
	public String getTagName() {
		return QuestionCategory.RADIO.getName();
	}

}
