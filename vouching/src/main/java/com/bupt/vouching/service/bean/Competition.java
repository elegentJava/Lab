package com.bupt.vouching.service.bean;

import java.util.ArrayList;
import java.util.List;

import com.bupt.vouching.bean.Exam;
import com.bupt.vouching.bean.User;

/**
 * 竞技实体
 * 
 * @author Hogan
 * 
 */
public class Competition {

	private List<User> users;
	private Exam exam;
	private List<String> answer;

	public Competition() {
		setUsers(new ArrayList<User>());
		setAnswer(new ArrayList<String>());
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public List<String> getAnswer() {
		return answer;
	}

	public void setAnswer(List<String> answer) {
		this.answer = answer;
	}

}
