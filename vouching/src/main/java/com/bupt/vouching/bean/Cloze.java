package com.bupt.vouching.bean;

import java.util.ArrayList;
import java.util.List;

import com.bupt.vouching.type.QuestionCategory;


/**
 * 完型实体
 * 
 * @author Hogan
 * 
 */
public class Cloze implements Question{

	private Integer clozeId;
	private String question;
	private String answer;
	private Integer level;
	private Integer chapterId;

	private List<String> answers;

	public Integer getClozeId() {
		return clozeId;
	}

	public void setClozeId(Integer clozeId) {
		this.clozeId = clozeId;
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

	public List<String> getAnswers() {
		if (this.answer != null) {
			answers = new ArrayList<String>();
			String[] temp = this.answer.split(";");
			for (String e : temp) {
				answers.add(e);
			}
		}
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	@Override
	public Integer getId() {
		return this.clozeId;
	}

	@Override
	public String getTag() {
		return QuestionCategory.CLOZE.getTag();
	}

	@Override
	public String getTagName() {
		return QuestionCategory.CLOZE.getName();
	}

}
