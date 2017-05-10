package com.bupt.vouching.type;

/**
 * 分页显示记录数
 * 
 * @author Hogan
 * 
 */
public enum PageSize {

	/**
	 * 词汇查询结果记录数
	 */
	GLOSSARY_RESULT(10),
	/**
	 * 语句查询结果记录数
	 */
	SENTENCE_RESULT(5),
	/**
	 * 考试设置显示记录数
	 */
	EXAM_SETTING_RESULT(4),
	/**
	 * 手工组卷题目显示记录数
	 */
	MANUAL_QUESTION_RESULT(10),
	/**
	 * 竞技排行榜显示记录数
	 */
	COMPETITION_RANK_RESULT(5),
	/**
	 * 测试记录数
	 */
	TEST_RECORD(8),
	/**
	 * 参加考试的记录数
	 */
	JOIN_EXAM_RECORD(3),
	/**
	 * 用户列表纪录数
	 */
	USER_LIST_RECORD(10),
	/**
	 * 邮箱显示记录数
	 */
	EMAIL_BOX_RECORD(8),
	/**
	 * 班级列表记录数
	 */
	CLASS_LIST_RECORD(8),
	/**
	 * 用户成绩记录数
	 */
	EXAM_RECORD_COUNT(10),
	/**
	 * 资源列表记录数
	 */
	RESOURCE_LIST_RECORD(10), 
	/**
	 * 竞技记录数
	 */
	COMPETITION_RECORD(5), 
	/**
	 * 批改试卷记录数
	 */
	USER_PAPER_RECORD(10), 
	/**
	 * 成绩显示记录数
	 */
	EXAM_GRADE_RECORD(2),
	;

	private int pageSize;

	PageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
