package com.bupt.vouching.type.error;

import java.util.HashMap;
import java.util.Map;

import com.bupt.vouching.type.base.ErrorType;

/**
 * 邮件操作错误代码
 * 
 * @author Hogan
 * 
 */
public enum EmailError implements ErrorType{

	/**
	 * 主题不能为空
	 */
	SUBJECT_IS_NULL(0x001,"主题不能为空!"),
	/**
	 * 内容不能为空
	 */
	CONTENT_IS_NULL(0x002,"内容不能为空!"),
	/**
	 * 收件人不能不空
	 */
	RECEIVER_IS_NULL(0x003,"收件人不能不空!"), 
	/**
	 * 收件人邮箱无效
	 */
	RECEIVER_EMAIL_INVALID(0x004,"收件人邮箱无效!"), 
	/**
	 * 发送邮件失败
	 */
	SEND_EMAIL_FAILD(0x005,"收件人邮箱无效!"), 
	/**
	 * 您正在考试中,不能使用该功能
	 */
	USER_IN_EXAM(0x006,"USERINTEXAM"), 
	/**
	 * 查看详情失败
	 */
	SHOW_DETAIL_FAILD(0x007,"查看详情失败!"), 
	/**
	 * 邮件删除失败
	 */
	DEL_EMAIL_FAILD(0x008,"邮件删除失败!"), 
	/**
	 * 选择一个再删除
	 */
	SELECTED_ZERO(0x009,"请选择一个再删除!"),
	;
	
	private int id;
	private String description;

	EmailError(int id, String description) {
		this.id = id;
		this.description = description;
	}

	@Override
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private static final Map<Integer, EmailError> DIRC = new HashMap<>();

	static {
		for (EmailError e : EmailError.values()) {
			DIRC.put(e.getId(), e);
		}
	}

	public static EmailError byId(int id) {
		return DIRC.get(id);
	}
}
