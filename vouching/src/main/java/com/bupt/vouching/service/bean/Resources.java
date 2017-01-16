package com.bupt.vouching.service.bean;

/**
 * 文件的资源类型实体
 * 
 * @author Hogan
 * 
 */
public class Resources {
	
	private int id;
	private String name;
	private String href;
	
	public Resources(int id, String name, String href) {
		this.id = id;
		this.name = name;
		this.href = href;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

}
