package com.bupt.vouching.frame;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 抽象的控制层实现
 * 
 * @author Hogan
 * 
 */
public abstract class BaseController {
	
	@Resource
	private GlobalContext globalContext;

	////////////////////////////第一级目录////////////////////////////
	
	public static final String PATH_FRONT = "/front";
	public String front = PATH_FRONT;

	public static final String PATH_BACK = "/back";
	public String back = PATH_BACK;
	
	public static final String PATH_ERROR = "/error";
	public String error = PATH_ERROR;
	
	////////////////////////////第二级目录////////////////////////////

	public static final String PATH_COMMON = "/common";
	public String common = PATH_COMMON;
	
	public static final String PATH_COMPEPITION = "/competition";
	public String competition = PATH_COMPEPITION;
	
	public static final String PATH_COMPEPITION_EXAM = "/competitionexam";
	public String competitionExam = PATH_COMPEPITION_EXAM;

	public static final String PATH_EMAIL = "/email";
	public String email = PATH_EMAIL;
	
	public static final String PATH_RESOURCE = "/resource";
	public String resource = PATH_RESOURCE;
	
	public static final String PATH_EXAM = "/exam";
	public String exam = PATH_EXAM;
	
	public static final String PATH_PRACTICE = "/practice";
	public String practice = PATH_PRACTICE;
	
	public static final String PATH_INDEX = "/index";
	public String index = PATH_INDEX;
	
	public static final String PATH_LOGIN = "/login";
	public String login = PATH_LOGIN;
	
	public static final String PATH_MODIFY_PW= "/modifypw";
	public String modifyPW = PATH_MODIFY_PW;
	
	////////////////////////////第三级目录////////////////////////////
	
	/*
	 * common目录下
	 */
	public static final String PATH_MAIN = "/main";
	public String main = PATH_MAIN;
	
	public static final String PATH_FOOT= "/foot";
	public String foot = PATH_FOOT;
	
	/*
	 * Email目录下
	 */
	public static final String PATH_STATION = "/station";
	public String station = PATH_STATION;
	
	public static final String PATH_RECEIVE_BOX= "/receivebox";
	public String receiveBox = PATH_RECEIVE_BOX;
	
	public static final String PATH_SEND_BOX= "/sendbox";
	public String sendBox = PATH_SEND_BOX;
	
	public static final String PATH_SEND_EMAIL= "/sendemail";
	public String sendEmail = PATH_SEND_EMAIL;
	
	public static final String PATH_EMAIL_DETAIL= "/emaildetail";
	public String emailDetail = PATH_EMAIL_DETAIL;
	
	/*
	 * Exam目录下
	 */
	public static final String PATH_AUTO = "/auto";
	public String auto = PATH_AUTO;
	
	public static final String PATH_CHAPTER = "/chapter";
	public String chapter = PATH_CHAPTER;
	
	public static final String PATH_MANUAL = "/manual";
	public String manual = PATH_MANUAL;
	
	public static final String PATH_PREVIEW = "/preview";
	public String preview = PATH_PREVIEW;
	
	public static final String PATH_JOIN_EXAM = "/joinExam";
	public String joinExam = PATH_JOIN_EXAM;
	
	public static final String PATH_START_EXAM = "/startExam";
	public String startExam = PATH_START_EXAM;
	
	public static final String PATH_EXAM_RECORD = "/examrecord";
	public String examRecord = PATH_EXAM_RECORD;
	/*
	 * practice目录下
	 */
	public static final String PATH_TEST_SELECT = "/testselect";
	public String testSelect = PATH_TEST_SELECT;
	
	public static final String PATH_START_TEST = "/starttest";
	public String startTest = PATH_START_TEST;
	
	public static final String PATH_RECORD = "/testrecord";
	public String record = PATH_RECORD;
	
	/*
	 * resource目录下
	 */
	public static final String PATH_CORRESPONDENCE = "/correspondence";
	public String correspondence = PATH_CORRESPONDENCE;
	
	public static final String PATH_FILE = "/file";
	public String file = PATH_FILE;
	
	public static final String PATH_GLOSSARY = "/glossary";
	public String glossary = PATH_GLOSSARY;

	public static final String PATH_SENTENCE = "/sentence";
	public String sentence = PATH_SENTENCE;
	
	////////////////////////////第四级目录////////////////////////////
	
	public static final String PATH_EXAM_SETTING = "/examSetting";
	public String examSetting = PATH_EXAM_SETTING;
	
	public static final String PATH_UPLOAD= "/upload";
	public String upload = PATH_UPLOAD;
	
	public static final String PATH_DOWNLOAD= "/download";
	public String download = PATH_DOWNLOAD;
	
	
	///////////////////////////////////////后台/////////////////////////////////
	
	public static final String PATH_USER = "/user";
	public String user = PATH_USER;
	
	public static final String PATH_CLASS = "/class";
	public String clas = PATH_CLASS;
	
	public static final String PATH_STUDENT_LIST = "/studentList";
	public String studentList = PATH_STUDENT_LIST;
	
	public static final String PATH_ADD_STUDENT = "/addStudent";
	public String addStudent = PATH_ADD_STUDENT;
	
	public static final String PATH_TEACHER_LIST = "/teacherlist";
	public String teacherList = PATH_TEACHER_LIST;
	
	public static final String PATH_ADD_TEACHER = "/addteacher";
	public String addTeacher = PATH_ADD_TEACHER;
	
	public static final String PATH_CLASS_LIST = "/classlist";
	public String classList = PATH_CLASS_LIST;
	
	public static final String PATH_ADD_CLASS = "/addclass";
	public String addClass = PATH_ADD_CLASS;
	
	/**
	 * 组合返回路径字符串
	 * 
	 * @param path
	 * @return
	 */
	public String formResult(String... path) {
		StringBuilder sb = new StringBuilder();
		for (String e : path) {
			sb.append(e);
		}
		return sb.toString();
	}

	/**
	 * 组合前台返回字符串
	 * 
	 * @param path
	 * @return
	 */
	public String formFrontResult(String... path) {
		return front + formResult(path);
	}

	/**
	 * 组合后台返回字符串
	 * 
	 * @param path
	 * @return
	 */
	public String formBackResult(String... path) {
		return back + formResult(path);
	}
	
	/**
	 * 形成前台统一的返回字符串
	 * 
	 * @param token		
	 * @param request
	 * @param path
	 * @return
	 */
	public String formFrontResult(String token, HttpServletRequest request, String... path) {
		if (token != null) {
			request.setAttribute(Consts.LABEL_DETAIL, globalContext.getUserToken().get(token));
			request.setAttribute(Consts.LABEL_TOKEN, token);
			return formFrontResult(path);
		} 
		return formResult(error);
	}
	
	/**
	 * 形成后台统一的返回字符串
	 * 
	 * @param token		
	 * @param request
	 * @param path
	 * @return
	 */
	public String formBackResult(String token, HttpServletRequest request, String... path) {
		if (token != null) {
			request.setAttribute(Consts.LABEL_DETAIL, globalContext.getUserToken().get(token));
			request.setAttribute(Consts.LABEL_TOKEN, token);
			return formBackResult(path);
		} 
		return formResult(error);
	}
}
