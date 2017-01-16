package com.bupt.vouching.bean;

import com.bupt.vouching.type.QuestionCategory;

/**
 * 填空题实体
 * 
 * @author Hogan
 * 
 */
public class Blank implements Question{

	private Integer blankId;
	private String question;
	private String answer;
	private Integer level;
	private Integer chapterId;

	public Integer getBlankId() {
		return blankId;
	}

	public void setBlankId(Integer blankId) {
		this.blankId = blankId;
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
		return this.blankId;
	}

	@Override
	public String getTag() {
		return QuestionCategory.BLANK.getTag();
	}

	@Override
	public String getTagName() {
		return QuestionCategory.BLANK.getName();
	}

}
