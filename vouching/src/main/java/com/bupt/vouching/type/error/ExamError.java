package com.bupt.vouching.type.error;

import java.util.HashMap;
import java.util.Map;

import com.bupt.vouching.type.base.ErrorType;

/**
 * 考试操作错误代码
 * 
 * @author Hogan
 * 
 */
public enum ExamError implements ErrorType{

	/**
	 * 章节修改状态失败
	 */
	CHAPTER_UPDATE_STATUS(0x001,"章节修改状态失败!"), 
	/**
	 * 添加试卷失败
	 */
	ADD_EXAM_FAILD(0x002,"添加试卷失败"), 
	/**
	 * 试卷章节修改失败
	 */
	EXAM_UPDATE_STATUS(0x003,"试卷章节修改失败!"), 
	/**
	 * 试卷删除失败
	 */
	EXAM_DELETE(0x004,"试卷删除失败!"), 
	/**
	 * 题目删除失败
	 */
	QUESTION_DELETE(0x005,"题目删除失败!"), 
	/**
	 * 题目类型无效
	 */
	QUESTION_CATEGORY_INVALID(0x006,"题目类型无效!"), 
	/**
	 * 保存用户试卷失败
	 */
	SAVE_USER_EXAM_FAILD(0x007,"保存用户试卷失败!"), 
	/**
	 * 考试名称已经存在
	 */
	AUTO_EXAM_NAME_EXISTED(0x008,"考试名称已经存在!"), 
	/**
	 * 考试名称不能为空
	 */
	AUTO_EXAM_NAME_IS_NULL(0x009,"考试名称不能为空!"), 
	/**
	 * 试卷已经提交过了
	 */
	ALREADY_SUBMIT_EXAM(0x010,"试卷已经提交过了!"),
	;
	
	private int id;
	private String description;

	ExamError(int id, String description) {
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

	private static final Map<Integer, ExamError> DIRC = new HashMap<>();

	static {
		for (ExamError e : ExamError.values()) {
			DIRC.put(e.getId(), e);
		}
	}

	public static ExamError byId(int id) {
		return DIRC.get(id);
	}
}
