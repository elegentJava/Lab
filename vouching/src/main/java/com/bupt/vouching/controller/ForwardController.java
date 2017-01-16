package com.bupt.vouching.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bupt.vouching.frame.BaseController;
import com.bupt.vouching.frame.Consts;
import com.bupt.vouching.frame.GlobalContext;

/**
 * 转发资源控制类
 * 
 * @author Hogan
 * 
 */
@Controller("forwardControler")
@RequestMapping("/forward/")
public class ForwardController extends BaseController {

	@Resource
	private GlobalContext globalContext;

	/**
	 * 跳转到前台登录页面
	 * 
	 * @return
	 */
	@RequestMapping("forwardFrontLogin")
	public String forwardFrontLogin() {
		return formFrontResult(login);
	}
	
	/**
	 * 跳转到前台首页
	 * 
	 * @return
	 */
	@RequestMapping("forwardFrontIndex")
	public String forwardFrontIndex(String token, HttpServletRequest request) {
		return formFrontResult(token, request, index);
	}
	
	/**
	 * 跳转到修改密码页面
	 * 
	 * @return
	 */
	@RequestMapping("forwardModifyPW")
	public String forwardModifyPW(String token, HttpServletRequest request) {
		return formFrontResult(token, request, modifyPW);
	}
	
	/**
	 * 跳转到错误页面
	 * 
	 * @return
	 */
	@RequestMapping("forwardError")
	public String forwardError() {
		return formFrontResult(error);
	}
	
	/**
	 * 跳转到公共页尾页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardFooter")
	public String forwardFooter(String token, HttpServletRequest request){
		return formFrontResult(common,foot);
	}

	/**
	 * 跳转到导航栏，并装载用户数据
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardNavigate")
	public String forwardNavigate(String token, HttpServletRequest request) {
		return formFrontResult(token, request, common, navigate);
	}
	
	/**
	 * 跳转首页
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardMain")
	public String forwardMain(String token, HttpServletRequest request) {
		return formFrontResult(token, request, main);
	}
	
	//////////////////////Email模块相关//////////////////////
	
	/**
	 * 跳转到站内信首页
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardEmailStation")
	public String forwardEmailStation(String token, HttpServletRequest request){
		return formFrontResult(token, request, email, station);
	}
	
	/**
	 * 跳转到发送站内信页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardSendEmail")
	public String forwardSendEmail(String token, HttpServletRequest request){
		return formFrontResult(token, request, email, sendEmail);
	}
	
	/**
	 * 跳转到收件箱页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardReceiveBox")
	public String forwardReceiveBox(String token, HttpServletRequest request){
		return formFrontResult(token, request, email, receiveBox);
	}
	
	/**
	 * 跳转到发件箱页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardSendBox")
	public String forwardSendBox(String token, HttpServletRequest request){
		return formFrontResult(token, request, email, sendBox);
	}
	
	//////////////////////////////资源平台模块相关//////////////////////////////
	
	/**
	 * 跳转到函电页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardCorrespondence")
	public String forwardCorrespondence(String token, HttpServletRequest request){
		return formFrontResult(token, request, resource, correspondence);
	}
	
	/**
	 * 跳转到词汇查询页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardGlossary")
	public String forwardGlossary(String token, HttpServletRequest request) {
		return formFrontResult(token, request, resource, glossary);
	}
	
	/**
	 * 跳转到语句查询页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardSentence")
	public String forwardSentence(String token, HttpServletRequest request){
		return formFrontResult(token, request, resource, sentence);
	}
	
	/**
	 * 跳转到上传文件页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardUpload")
	public String forwardUpload(String token, HttpServletRequest request){
		return formFrontResult(token, request, resource, upload);
	}
	
	/**
	 * 跳转到下载文件页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardDownload")
	public String forwardDownload(String token, HttpServletRequest request){
		return formFrontResult(token, request, resource, download);
	}
	
	///////////////////////////////考试平台模块相关///////////////////////////////
	
	/**
	 * 跳转到手工组卷页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardManual")
	public String forwardManual(String token, HttpServletRequest request){
		return formFrontResult(token, request, exam, manual);
	}
	
	/**
	 * 跳转到手工组卷结果页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardAuto")
	public String forwardAuto(String token, HttpServletRequest request){
		return formFrontResult(token, request, exam, auto);
	}
	
	/**
	 * 跳转到章节设置页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardChapter")
	public String forwardChapter(String token, HttpServletRequest request){
		return formFrontResult(token, request, exam, chapter);
	}
	
	/**
	 * 跳转到考试设置页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardExamSetting")
	public String forwardExamSetting(String token, HttpServletRequest request){
		return formFrontResult(token, request, exam, examSetting);
	}
	
	/**
	 * 跳转到考试设置页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardJoinExam")
	public String forwardJoinExam(String token, HttpServletRequest request){
		return formFrontResult(token, request, exam, joinExam);
	}
	
	/**
	 * 跳转到考试设置页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardStartExam")
	public String forwardStartExam(String token, HttpServletRequest request){
		return formFrontResult(token, request, exam, startExam);
	}
	
	/**
	 * 跳转到成绩记录页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardExamRecord")
	public String forwardExamRecord(String token, HttpServletRequest request){
		return formFrontResult(token, request, exam, examRecord);
	}
	
	/**
	 * 跳转到试卷预览页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardPreview")
	public String forwardPreview(String token, String examId, HttpServletRequest request){
		if (token != null) {
			request.setAttribute(Consts.LABEL_DETAIL, globalContext.getUserToken().get(token));
			request.setAttribute(Consts.LABEL_TOKEN, token);
			request.setAttribute("examId", examId);
			return formFrontResult(exam,preview);
		} 
		return formFrontResult(error);
	}
	
	//////////////////////////////竞技平台模块相关//////////////////////////////
	
	/**
	 * 跳转到竞技桌位页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardCompetitionStation")
	public String forwardCompetitionStation(String token, HttpServletRequest request){
		return formFrontResult(token, request, competition, station);
	}
	
	/**
	 * 跳转到竞技试卷页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardEnterCompetition")
	public String forwardEnterCompetition(String token, HttpServletRequest request){
		return formFrontResult(token, request, competition, exam);
	}
	
	//////////////////////////////练习平台模块相关//////////////////////////////
	/**
	 * 跳转到练习选题页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardTestSelect")
	public String forwardTestSelect(String token, HttpServletRequest request){
		return formFrontResult(token, request, practice, testSelect);
	}
	
	/**
	 * 跳转到练习页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardStartTest")
	public String forwardStartTest(String token, HttpServletRequest request){
		return formFrontResult(token, request, practice, startTest);
	}
	
	/**
	 * 跳转到练习记录页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardShowRecord")
	public String forwardShowRecord(String token, HttpServletRequest request){
		return formFrontResult(token, request, practice, record);
	}
	
	
	//////////////////////////////后台//////////////////////////////

	/**
	 * 跳转到后台登录页面
	 * 
	 * @return
	 */
	@RequestMapping("forwardBackLogin")
	public String forwardBackLogin() {
		return formBackResult(login);
	}
	
	/**
	 * 跳转到后台首页
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardBackIndex")
	public String forwardBackIndex(String token, HttpServletRequest request){
		return formBackResult(token, request, index);
	}
	
	/**
	 * 跳转到学生管理列表页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardStudentList")
	public String forwardStudentList(String token, HttpServletRequest request){
		return formBackResult(token, request, user, studentList);
	}
	
	/**
	 * 跳转到添加学生页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardAddStudent")
	public String forwardAddStudent(String token, HttpServletRequest request){
		return formBackResult(token, request, user, addStudent);
	}
	
	/**
	 * 跳转到教师管理列表页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardTeacherList")
	public String forwardTeacherList(String token, HttpServletRequest request){
		return formBackResult(token, request, user, teacherList);
	}
	
	/**
	 * 跳转到添加教师页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardAddTeacher")
	public String forwardAddTeacher(String token, HttpServletRequest request){
		return formBackResult(token, request, user, addTeacher);
	}
	
	/**
	 * 跳转到班级管理列表页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardClassList")
	public String forwardClassList(String token, HttpServletRequest request){
		return formBackResult(token, request, clas, classList);
	}

	/**
	 * 跳转到班级管理列表页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardAddClass")
	public String forwardAddClass(String token, HttpServletRequest request){
		return formBackResult(token, request, clas, addClass);
	}
	
	/**
	 * 跳转到章节管理页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardChapterList")
	public String forwardChapterList(String token, HttpServletRequest request){
		return formBackResult(token, request, resource, chapter);
	}
	
	/**
	 * 跳转到词汇管理页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardGlossaryList")
	public String forwardGlossaryList(String token, HttpServletRequest request) {
		return formBackResult(token, request, resource, glossary);
	}
	
}
