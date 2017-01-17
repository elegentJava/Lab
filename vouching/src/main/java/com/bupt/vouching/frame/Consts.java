package com.bupt.vouching.frame;

/**
 * 系统所使用的常量类
 * 
 * @author Hogan
 * 
 */
public class Consts {

	/**
	 * 简单的日期格式
	 */
	public static final String DATE_SIMPLE_PATTERN = "yyyy-MM-dd HH:mm";
	
	/**
	 * 逻辑时间格式
	 */
	public static final String DATE_LOGICTIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 文件名存储格式
	 */
	public static final String DATE_NUMERIC="yyyyMMddHHmmss";

	/**
	 * 错误页面
	 */
	public static final String ERROR_PAGE = "error";
	
	public static final String COMMON_SEPARATOR = ";";
	
	public static final String RESULT_SUCCESS = "success";
	public static final String RESULT_FAILD = "faild";
	/**
	 * 修改单条数据成功
	 */
	public static final Integer DATA_SINGLE_SUCCESS = 1;
	
	public static final String LABEL_RESULT = "result";
	public static final String LABEL_ERROR_CODE = "errorCode";
	public static final String LABEL_DETAIL = "detail";
	public static final String LABEL_TOKEN = "token";
	public static final String LABEL_PARAMS = "params";
	/**
	 * 用户在线状态--不在线
	 */
	public static final Integer USER_NOT_ONLINE = 0;
	/**
	 * 用户在线状态--在线
	 */
	public static final Integer USER_IS_ONLINE = 1;

	public static final int EAMIL_RECEIVE_BOX = 1;
	public static final int EAMIL_SEND_BOX = 2;

	public static final int PAGE_SIZE_RECEIVE_BOX = 10;
	public static final int PAGE_SIZE_SEND_BOX = 10;

	public static final String PAGE_NUM = "pageNum";
	public static final String PAGE_HAVE_NEXT = "hasNextPage";
	public static final String PAGE_SUM = "pageSum";
	public static final String PAGE_RECORD_COUNT = "total";
	public static final String PAGE_LABEL = "page";
	/**
	 * 上传教学资源处理状态--未处理
	 */
	public static final Integer TEACH_RESOURCE_UNDO = 0;
	/**
	 * 上传教学资源处理状态--已处理
	 */
	public static final Integer TEACH_RESOURCE_DONE = 1;

	/**
	 * 不可用/未激活
	 */
	public static final Integer INACTIVE = 0;
	/**
	 * 可用/激活
	 */
	public static final Integer ACTIVE = 1;
	/**
	 * 上传文件保存的根路径
	 */
	public static final String PATH_UPLOAD = "upload";

	public static final int TEST_SUM_SCORE = 5;

	public static final Integer EXAM_JOINED = 1;
	/**
	 * 答案为空
	 */
	public static final String ANSWER_NULL = "??**&|@";
	/**
	 * 角色类型--管理员
	 */
	public static final Integer ROLE_ADMIN = 0;
	/**
	 * 角色类型--学生
	 */
	public static final Integer ROLE_STUDENT = 1;
	/**
	 * 角色类型--教师
	 */
	public static final Integer ROLE_TEACHER = 2;
	/**
	 * 默认密码 1
	 */
	public static final String DEFAULT_PASSWORD = "c4ca4238a0b923820dcc509a6f75849b";
	/**
	 * 邮件类型--发件箱
	 */
	public static final Integer EMAIL_TYPE_SEND = 0;
	/**
	 * 邮件类型--收件箱
	 */
	public static final Integer EMAIL_TYPE_RECEIVE = 1;
	/**
	 * 邮件未读
	 */
	public static final Integer EMAIL_UNREAD = 0;
	/**
	 * 邮件已读
	 */
	public static final Integer EMAIL_READ = 1;
	/**
	 * 邮件没有删除
	 */
	public static final Integer EMAIL_NOT_DELETE = 0;
	/**
	 * 邮件已经删除
	 */
	public static final Integer EMAIL_IS_DELETE = 1;
	/**
	 * 竞技队列匹配上限
	 */
	public static final int MATECHED_COUNT = 5;
	
	/**
	 * 默认的班级ID(创建用户没有选择班级)
	 */
	public static final int DEFAULT_CLASS_ID = 3;
	
	/**
	 * 资源还没有处理
	 */
	public static final int RESOURCE_UN_DEAL = 0;
	
	/**
	 * 资源已经处理
	 */
	public static final int RESOURCE_DEAL = 1;
	
	/**
	 * 默认为前台登录
	 */
	public static final String DEFAULT_LOGIN_URL = "frontLogin";
}
