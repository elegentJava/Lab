package com.bupt.vouching.service.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 函电业务实体
 * 
 * @author Hogan
 * 
 */
public class CorrespondenceSer {

	private String english;
	private List<List<String>> list;

	public CorrespondenceSer() {
		this.english = "";
		list = new ArrayList<List<String>>();
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public List<List<String>> getList() {
		return list;
	}

	public void setList(List<List<String>> list) {
		this.list = list;
	}

}
