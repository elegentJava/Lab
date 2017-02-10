package com.bupt.vouching.service.bean;

import java.util.ArrayList;
import java.util.List;

import com.bupt.vouching.bean.Question;
import com.bupt.vouching.bean.User;
import com.bupt.vouching.util.Utils;

/**
 * 竞技业务实体
 * 
 * @author Hogan
 * 
 */
public class CompetitionSer {

	private String id;
	private List<User> users;
	private List<? extends Question> questions;
	private Boolean scoreHandle;
	private String answers;

	public CompetitionSer() {
		setId(Utils.UUID());
		setUsers(new ArrayList<User>());
		setScoreHandle(false);
		setAnswers("");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<? extends Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<? extends Question> questions) {
		this.questions = questions;
	}

	public Boolean getScoreHandle() {
		return scoreHandle;
	}

	public void setScoreHandle(Boolean scoreHandle) {
		this.scoreHandle = scoreHandle;
	}

	public String getAnswers() {
		if (Utils.isNullOrBlank(this.answers)) {
			if (this.questions != null && this.questions.size() > 0) {
				for (int i = 0; i < this.questions.size(); i++) {
					answers += this.questions.get(i).getAnswer();
				}
			}
		}
		return answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}

}
