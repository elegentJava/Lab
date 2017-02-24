package com.bupt.vouching.type.error;

import java.util.HashMap;
import java.util.Map;

import com.bupt.vouching.type.base.ErrorType;

/**
 * 管理员操作错误代码
 * 
 * @author Hogan
 * 
 */
public enum AdminError implements ErrorType{
	
	/**
	 * 班级删除失败
	 */
	CLASS_DEL_FAILD(0x001,"班级删除失败!"), 
	/**
	 * 班级删除失败
	 */
	CLASS_UPDATE_FAILD(0x002,"班级删除失败!"), 
	/**
	 * 请选择一个再删除
	 */
	SELECTED_ONE_FAILD(0x003,"请选择一个再删除!"), 
	/**
	 * 班级名称不能为空
	 */
	CLASS_NAME_IS_NULL(0x004,"班级名称不能为空!"), 
	/**
	 * 班级名称已经存在
	 */
	CLASS_NAME_EXISTED(0x005,"班级名称已经存在!"), 
	/**
	 * 添加班级失败
	 */
	CLASS_ADD_FAILD(0x006,"添加班级失败！"), 
	/**
	 * 删除用户失败
	 */
	USER_DELETE_FAILD(0x008,"删除用户失败!"), 
	/**
	 * 该资源已经处理完成了
	 */
	RESOURCE_ALREADY_DEAL(0x009,"该资源已经处理过了！"), 
	/**
	 * 资源处理失败
	 */
	RESOURCE_DEAL_FAILD(0x010,"资源处理失败!"), 
	/**
	 * 发送函电失败
	 */
	SEND_HD_FAILD(0x011,"发送函电失败!"), 
	;
	
	private int id;
	private String description;

	AdminError(int id, String description) {
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

	private static final Map<Integer, AdminError> DIRC = new HashMap<>();

	static {
		for (AdminError e : AdminError.values()) {
			DIRC.put(e.getId(), e);
		}
	}

	public static AdminError byId(int id) {
		return DIRC.get(id);
	}
}
