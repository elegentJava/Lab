package com.bupt.vouching.type.error;

import java.util.HashMap;
import java.util.Map;

import com.bupt.vouching.type.base.ErrorType;

/**
 * 资源操作错误代码
 * 
 * @author Hogan
 * 
 */
public enum ResourceError implements ErrorType{

	/**
	 * 上传资源失败
	 */
	UPLOAD_FAILD(0x001,"上传资源失败!"), 
	/**
	 * 上传文件不匹配
	 */
	UPLOAD_FILE_NOT_MATCH(0x002,"上传文件不匹配!"), 
	;
	
	private int id;
	private String description;

	ResourceError(int id, String description) {
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

	private static final Map<Integer, ResourceError> DIRC = new HashMap<>();

	static {
		for (ResourceError e : ResourceError.values()) {
			DIRC.put(e.getId(), e);
		}
	}

	public static ResourceError byId(int id) {
		return DIRC.get(id);
	}
}
