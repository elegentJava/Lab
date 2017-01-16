package com.bupt.vouching.mapper;

import java.util.List;
import java.util.Map;

import com.bupt.vouching.bean.Exam;

/**
 * 试卷数据层接口
 * 
 * @author Hogan
 * 
 */
public interface ExamMapper {

	/**
	 * 保存试卷信息
	 * 
	 * @param exam
	 * @return
	 */
	public int saveExam(Exam exam);

	/**
	 * 根据激活状态和教师ID查询试卷详情信息
	 * 
	 * @param map
	 * @return
	 */
	public List<Exam> findExamDetailByStatusAndTeacher(Map<String, Object> map);

	/**
	 * 根据试卷ID修改试卷状态
	 * 
	 * @param map
	 * @return
	 */
	public Integer updateExamStatusById(Map<String, Object> map);

	/**
	 * 根据ID删除试卷
	 * 
	 * @param examId
	 * @return
	 */
	public Integer deleteExamById(Integer examId);

	/**
	 * 通过ID查询指定试卷
	 * 
	 * @param examId
	 * @return
	 */
	public Exam findExamById(Integer examId);

	/**
	 * 修改试卷的题目ID集合字符串
	 * 
	 * @param exam
	 * @return
	 */
	public Integer updateQuestionId(Exam exam);

	/**
	 * 查询用户已经参加过的考试
	 * 
	 * @param map
	 * @return
	 */
	public List<Exam> findJoinedExam(Map<String, Object> map);

	/**
	 * 查询用户未参加过的考试
	 * 
	 * @param map
	 * @return
	 */
	public List<Exam> findUnjoinedExam(Map<String, Object> map);

	/**
	 * 根据试卷状态查询试卷信息
	 * 
	 * @param active
	 * @return
	 */
	public List<Exam> findExamsBySatus(Integer active);

}