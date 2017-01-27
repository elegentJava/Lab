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

	public CompetitionSer() {
		setId(Utils.UUID());
		setUsers(new ArrayList<User>());
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

}
