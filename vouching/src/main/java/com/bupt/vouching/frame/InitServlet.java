package com.bupt.vouching.frame;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bupt.vouching.bean.Exam;
import com.bupt.vouching.bean.Question;
import com.bupt.vouching.bean.User;

/**
 * 初始化操作
 * 
 * @author Hogan
 * 
 */
@Component
public class InitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		super.init();
		final ApplicationContext app = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		final GlobalContext globalContext = app.getBean(GlobalContext.class);
		//初始化全局变量
		globalContext.setUserToken(new HashMap<String, User>());
		globalContext.setCurrentExam(new HashMap<String, Exam>());
		globalContext.setCurrentPractice(new HashMap<String, List<? extends Question>>());
		globalContext.setEmailDetail(new HashMap<String, Integer[]>());
	}

}
