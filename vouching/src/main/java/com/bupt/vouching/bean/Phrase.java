package com.bupt.vouching.bean;

import com.bupt.vouching.type.QuestionCategory;

/**
 * 名词解释实体
 * 
 * @author Hogan
 * 
 */
public class Phrase implements Question{

	private Integer phraseId;
	private String question;
	private String answer;
	private Integer level;
	private Integer chapterId;

	public Integer getPhraseId() {
		return phraseId;
	}

	public void setPhraseId(Integer phraseId) {
		this.phraseId = phraseId;
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
		return this.phraseId;
	}

	@Override
	public String getTag() {
		return QuestionCategory.PHRASE.getTag();
	}

	@Override
	public String getTagName() {
		return QuestionCategory.PHRASE.getName();
	}

}
