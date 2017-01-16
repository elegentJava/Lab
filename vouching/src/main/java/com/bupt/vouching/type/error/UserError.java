package com.bupt.vouching.type.error;

import java.util.HashMap;
import java.util.Map;

import com.bupt.vouching.type.base.ErrorType;

/**
 * 用户操作错误代码
 * 
 * @author Hogan
 * 
 */
public enum UserError implements ErrorType{

	/**
	 * 用户名不能为空
	 */
	ACCOUNT_NULL(0x001,"用户名不能为空!"),
	/**
	 * 密码不能为空
	 */
	PASSWORD_NULL(0x002,"密码不能为空!"), 
	/**
	 * 账号密码不匹配
	 */
	AP_NOT_MATCH(0x003,"账号密码不匹配!"), 
	/**
	 * 用户注销失败
	 */
	LOGOUT_FAILD(0x004,"用户注销失败!"), 
	/**
	 * 原密码不正确
	 */
	OLD_PASSWORD_INCORRECT(0x005,"原密码不正确!"), 
	/**
	 * 修改密码失败
	 */
	MODIFY_PASSWORD_FAILD(0x006,"修改密码失败!"), 
	/**
	 * 用户未登陆或Session失效
	 */
	NO_ACCESS_PRIVILEGE(0x007,"用户未登陆或Session失效"),
	/**
	 * 权限不足
	 */
	NO_PRIVILEGE(0x008,"权限不足!"), 
	/**
	 * 重置密码失败
	 */
	RESET_PASSWORD_FAILD(0x009,"重置密码失败!"), 
	/**
	 * 删除用户失败
	 */
	DELETE_USER_FAILD(0x010,"删除用户失败!"), 
	/**
	 * 添加用户失败
	 */
	ADD_USER_FAILD(0x011,"添加用户失败!"), 
	/**
	 * Email格式错误
	 */
	EMAIL_INVALID(0x012,"Email格式错误!"), 
	/**
	 * Email不能为空
	 */
	EMAIL_IS_NULL(0x013,"Email不能为空!"), 
	/**
	 * 姓名不能为空
	 */
	NAME_IS_NULL(0x014,"姓名不能为空!"), 
	/**
	 * 账号不能为空
	 */
	ACCOUNT_IS_NULL(0x015,"账号不能为空!"), 
	/**
	 * 用户已经登录，不能重复登录
	 */
	USER_ALREADY_LOGIN(0x016,"用户已经登录，不能重复登录!"), 
	/**
	 * 账号(学号)已经存在
	 */
	ACCOUNT_EXISTED(0x017,"账号(学号)已经存在!"), 
	/**
	 * 两次输入的密码不一致
	 */
	TWICE_PASSWORD_NOT_MATCH(0x018,"两次输入的密码不一致!");
	;
	
	private int id;
	private String description;

	UserError(int id, String description) {
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

	private static final Map<Integer, UserError> DIRC = new HashMap<>();

	static {
		for (UserError e : UserError.values()) {
			DIRC.put(e.getId(), e);
		}
	}

	public static UserError byId(int id) {
		return DIRC.get(id);
	}
}
