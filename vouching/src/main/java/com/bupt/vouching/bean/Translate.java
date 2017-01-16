package com.bupt.vouching.bean;

import com.bupt.vouching.type.QuestionCategory;

/**
 * 句子翻译实体
 * 
 * @author Hogan
 * 
 */
public class Translate implements Question{

	private Integer translateId;
	private String question;
	private String answer;
	private Integer level;
	private Integer chapterId;

	public Integer getTranslateId() {
		return translateId;
	}

	public void setTranslateId(Integer translateId) {
		this.translateId = translateId;
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

	@Override
	public Integer getId() {
		return this.translateId;
	}

	@Override
	public String getTag() {
		return QuestionCategory.TRANSLATE.getTag();
	}

	@Override
	public String getTagName() {
		return QuestionCategory.TRANSLATE.getName();
	}

}
