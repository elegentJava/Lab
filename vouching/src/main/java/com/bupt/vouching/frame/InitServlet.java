package com.bupt.vouching.frame;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bupt.vouching.bean.Correspondence;
import com.bupt.vouching.bean.Exam;
import com.bupt.vouching.bean.Question;
import com.bupt.vouching.bean.User;
import com.bupt.vouching.bean.UserPaper;
import com.bupt.vouching.service.TimeService;
import com.bupt.vouching.service.bean.CompetitionSer;

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
		globalContext.setWatchingQueue(new LinkedList<String>());
		globalContext.setCompetitionMap(new ConcurrentHashMap<String,CompetitionSer>());
		globalContext.setMatchingMap(new ConcurrentHashMap<String,String>());
		globalContext.setCurrentCorrespondence(new HashMap<String, Correspondence>());
		globalContext.setMarkPapers(new HashMap<String,List<UserPaper>>());
		
		//启动检测竞技队列
		app.getBean(TimeService.class).competitionQueueListener();
		app.getBean(TimeService.class).competitionCreditListener();
	}

}
