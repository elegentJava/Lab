package com.bupt.vouching.bean;

/**
 * 抽象的问题实体
 * 
 * @author Hogan
 * 
 */
public interface Question {

	/**
	 * 获取问题内容
	 * 
	 * @return
	 */
	public String getQuestion();

	/**
	 * 获取问题答案
	 * 
	 * @return
	 */
	public String getAnswer();

	/**
	 * 获取题目ID
	 * 
	 * @return
	 */
	public Integer getId();

	/**
	 * 获取标签
	 * 
	 * @return
	 */
	public String getTag();

	/**
	 * 获取标签名称
	 * 
	 * @return
	 */
	public String getTagName();

	/**
	 * 获取章节ID
	 * 
	 * @return
	 */
	public Integer getChapterId();
}
