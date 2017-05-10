package com.bupt.vouching.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.bupt.vouching.bean.Correspondence;
import com.bupt.vouching.bean.CorrespondenceCategory;
import com.bupt.vouching.bean.Glossary;
import com.bupt.vouching.bean.Sentence;
import com.bupt.vouching.bean.SentenceCategory;
import com.bupt.vouching.bean.TeachResource;
import com.bupt.vouching.bean.User;
import com.bupt.vouching.frame.Consts;
import com.bupt.vouching.frame.GlobalContext;
import com.bupt.vouching.frame.MJSONObject;
import com.bupt.vouching.mapper.CorrespondenceCategoryMapper;
import com.bupt.vouching.mapper.CorrespondenceMapper;
import com.bupt.vouching.mapper.GlossaryMapper;
import com.bupt.vouching.mapper.SentenceCategoryMapper;
import com.bupt.vouching.mapper.SentenceMapper;
import com.bupt.vouching.mapper.TeachResourceMapper;
import com.bupt.vouching.service.ResourceService;
import com.bupt.vouching.service.bean.CorrespondenceSer;
import com.bupt.vouching.service.bean.GlossarySource;
import com.bupt.vouching.service.bean.Resources;
import com.bupt.vouching.service.bean.SentenceLevel;
import com.bupt.vouching.service.bean.SentenceType;
import com.bupt.vouching.type.GlossarySourceType;
import com.bupt.vouching.type.PageSize;
import com.bupt.vouching.type.LevelType;
import com.bupt.vouching.type.ResourceType;
import com.bupt.vouching.type.SentenceTypes;
import com.bupt.vouching.type.error.AdminError;
import com.bupt.vouching.type.error.ErrorCode;
import com.bupt.vouching.type.error.ResourceError;
import com.bupt.vouching.util.EmailUtils;
import com.bupt.vouching.util.Utils;
import com.bupt.vouching.util.page.PageHelper;
import com.bupt.vouching.util.page.PageInfo;

/**
 * 资源平台业务实现
 * 
 * @author Hogan
 * 
 */
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
	
	private Logger log = Logger.getLogger(getClass());

	@Resource
	private GlobalContext globalContext;
	
	@Resource
	private CorrespondenceCategoryMapper categoryMapper;
	
	@Resource
	private SentenceCategoryMapper sentenceCategoryMapper;
	
	@Resource
	private SentenceMapper sentenceMapper;
	
	@Resource
	private GlossaryMapper glossaryMapper;
	
	@Resource
	private TeachResourceMapper teachResourceMapper;
	
	@Resource
	private CorrespondenceMapper correspondenceMapper;
	
	@Resource
	private EmailUtils emailUtils;

	@Override
	public MJSONObject loadCategoryTree(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		List<CorrespondenceCategory> categories = categoryMapper.findAllCategories();
		detail.put("categories", categories);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public MJSONObject loadGlossarySource(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		List<GlossarySource> glossarySources = new ArrayList<GlossarySource>();
		for (GlossarySourceType source : GlossarySourceType.values()) {
			glossarySources.add(new GlossarySource(source.getId(), source.getDescription()));
		}
		detail.put("glossarySources", glossarySources);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}
	
	@Override
	public MJSONObject queryGlossary(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		String word = jParams.getString("word");
		Integer[] sources = jParams.getObject("sources", Integer[].class);
		Integer pageNum = jParams.getInteger("pageNum");
		PageHelper.startPage(pageNum, PageSize.GLOSSARY_RESULT);
		List<Glossary> glossaries = null;
		PageInfo<Glossary> pageInfo = null;
		if (sources != null) {
			if (Utils.isNullOrBlank(word) && sources.length == 0) {//未输入单词和来源
				glossaries = glossaryMapper.findAllGlossaries();
			} else if (!Utils.isNullOrBlank(word) && sources.length == 0) {//只输入单词，未选择来源
				glossaries = glossaryMapper.findGlossariesByWord(word);
			} else if (Utils.isNullOrBlank(word) && sources.length > 0){//只选择来源，未输入单词
				glossaries = glossaryMapper.findGlossariesBySource(sources);
			} else {//输入了单词，也选择了来源
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("word", word);
				map.put("array", sources);
				glossaries = glossaryMapper.findGlossariesBySourceAndWord(map);
			}
			pageInfo = new PageInfo<Glossary>(glossaries);
			detail.put("glossaries", glossaries);
			result.setDetail(detail);
			result.setPageInfo(pageInfo);
			result.setErrorCode(ErrorCode.SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}
	
	@Override
	public MJSONObject loadSentenceTree(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		List<SentenceCategory> categories = sentenceCategoryMapper.findAllCategories();
		detail.put("categories", categories);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}
	
	@Override
	public MJSONObject querySentence(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		int pageNum = jParams.getIntValue("pageNum");
		Integer level = jParams.getInteger("level");
		Integer type = jParams.getInteger("type");
		Integer categoryId = jParams.getInteger("categoryId");
		if (level != null && type != null && categoryId != null) {
			PageHelper.startPage(pageNum, PageSize.SENTENCE_RESULT);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("categoryId", categoryId);
			map.put("level", level);
			map.put("type", type);
			List<Sentence> sentences = sentenceMapper.findSentencesByCTL(map);
			PageInfo<Sentence> pageInfo = new PageInfo<Sentence>(sentences);
			detail.put("sentences", pageInfo.getList());
			result.setDetail(detail);
			result.setPageInfo(pageInfo);
			result.setErrorCode(ErrorCode.SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}
	
	@Override
	public HttpServletRequest loadSentence(Map<String, Object> params, HttpServletRequest request){
		Integer pageNum = Integer.parseInt((String) params.get("pageNum"));
		Integer categoryId = Integer.parseInt((String) params.get("categoryId"));
		String token = (String) params.get("token");
		PageHelper.startPage(pageNum, PageSize.SENTENCE_RESULT);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categoryId", categoryId);
		map.put("level", 0);
		map.put("type", 0);
		List<Sentence> sentences = sentenceMapper.findSentencesByCTL(map);
		PageInfo<Sentence> pageInfo = new PageInfo<Sentence>(sentences);
		request.setAttribute(Consts.LABEL_DETAIL, pageInfo);
		request.setAttribute(Consts.LABEL_TOKEN, token);
		request.setAttribute(Consts.LABEL_ERROR_CODE, ErrorCode.SUCCESS);
		return request;
	}
	
	@Override
	public MJSONObject loadSentenceLevelAndType(JSONObject jParams){
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		List<SentenceLevel> levels = new ArrayList<SentenceLevel>();
		List<SentenceType> types = new ArrayList<SentenceType>();
		for (LevelType level : LevelType.values()) {
			levels.add(new SentenceLevel(level.getId(), level.getDescription()));
		}
		for (SentenceTypes type : SentenceTypes.values()) {
			types.add(new SentenceType(type.getId(), type.getDescription()));
		}
		detail.put("levels", levels);
		detail.put("types", types);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}
	
	@Override
	public MJSONObject loadFilePage(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		List<Resources> resources = new ArrayList<Resources>();
		for (ResourceType e : ResourceType.values()) {
			resources.add(new Resources(e.getId(), e.getDescription(), e.getHref()));
		}
		detail.put("resources", resources);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}
	
	@Override
	public MJSONObject uploadResource(Map<String, Object> params, HttpServletRequest request) {
		MJSONObject result = new MJSONObject();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> ite = multiRequest.getFileNames();
			while (ite.hasNext()) {
				MultipartFile file = multiRequest.getFile(ite.next());
				if (file != null) {
					try{
						String token = (String) params.get("token");
						Integer type = Integer.parseInt((String) params.get("type"));
						User user = globalContext.getUserToken().get(token);
						if (user != null) {
							String originalFilename = file.getOriginalFilename();
							if (ResourceType.byId(type).getHref().contains(originalFilename)) {
								String uploadPath = request.getSession().getServletContext().getRealPath("/")
										 + Consts.PATH_UPLOAD + File.separator + ResourceType.byId(type).getPathName();
								String dealFileName = DateFormatUtils.format(new Date(), Consts.DATE_NUMERIC) 
													+ originalFilename.substring(originalFilename.indexOf("."));
								TeachResource teachResource = new TeachResource();
								teachResource.setPath(uploadPath+File.separator+dealFileName);
								teachResource.setUserId(user.getUserId());
								teachResource.setFlag(Consts.TEACH_RESOURCE_UNDO);
								teachResource.setType(type);
								teachResource.setDate(new Date());
								File saveFile = null;
								try {
									saveFile = new File(uploadPath, dealFileName);
									file.transferTo(saveFile);
									if (teachResourceMapper.saveTeachResource(teachResource) == Consts.DATA_SINGLE_SUCCESS) {
										result.setErrorCode(ErrorCode.SUCCESS);
									} else {
										result.setErrorCode(ResourceError.UPLOAD_FAILD);
									}
								} catch (Exception e) {
									saveFile.delete();
									result.setErrorCode(ResourceError.UPLOAD_FAILD);
									log.error("上传文件失败!", e);
								}
							} else {
								result.setErrorCode(ResourceError.UPLOAD_FILE_NOT_MATCH);
							}
						}
					} catch (Exception e) {
						log.error("上传文件失败!", e);
						result.setErrorCode(ResourceError.UPLOAD_FAILD);
					}
				}
			}
		}
		return result;
	}

	@Override
	public MJSONObject sendHD(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		String receiver = jParams.getString("receiver");
		String subject = jParams.getString("subject");
		String content = jParams.getString("content");
		String token = jParams.getString("token");
		if ("默认发送给当前用户的邮箱".equals(receiver)) {
			receiver = globalContext.getUserToken().get(token).getEmail();
		}
		if (emailUtils.sendHtmlEmail(receiver, subject, content)) {
			result.setErrorCode(ErrorCode.SUCCESS);
		} else {
			result.setErrorCode(AdminError.SEND_HD_FAILD);
		}
		return result;
	}
	
	@Override
	public MJSONObject loadHD(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		Integer categoryId = jParams.getInteger("categoryId");
		String token = jParams.getString("token");
		Correspondence correspondence = correspondenceMapper.findCorrespondenceByCategoryId(categoryId);
		if (correspondence != null) {
			globalContext.getCurrentCorrespondence().put(token, correspondence);
			detail.put("correspondence", generateCorrespondenceStr(correspondence.getEnglish()));
			detail.put("translate", correspondence.getTranslate());
		} else {
			detail.put("correspondence", null);
		}
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}
	
	@Override
	public MJSONObject replaceChange(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		String token = jParams.getString("token");
		String key = jParams.getString("key");
		Integer listNo = Integer.parseInt(key.split("-")[0]);
		Integer innerNo = Integer.parseInt(key.split("-")[1]);
		Correspondence correspondence = globalContext.getCurrentCorrespondence().get(token);
		detail.put("correspondence", generateCorrespondenceStr(correspondence.getEnglish(),listNo,innerNo));
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}
	
	/**
	 * 组成前台函电字符串
	 * 
	 * @param correspondenceEnglish
	 * @return
	 */
	private CorrespondenceSer generateCorrespondenceStr(String correspondenceEnglish){
		CorrespondenceSer correspondenceSer = new CorrespondenceSer();
		Stack<String> stack = new Stack<>();
		String[] item = correspondenceEnglish.split("");
		for (int i = item.length - 1; i >= 0; i--) {
			stack.push(item[i]);
		}
		while (!stack.isEmpty()) {
			String outLayer = stack.pop();
			switch (outLayer) {
			// 需要提示的信息
			case "{":
				String content = "";
				String tipContent = "";
				while (!stack.isEmpty() && !"}".equals(stack.peek())) {
					String inLayer = stack.pop();
					if (!"<".equals(inLayer)) {
						content += inLayer;
					} else if ("<".equals(inLayer)) {
						while (!stack.isEmpty() && !stack.peek().equals(">")) {
							tipContent += stack.pop();
						}
						stack.pop();
					}
				}
				if (!stack.isEmpty()) {
					stack.pop();
					String combination = "<a style='color:blue' title='" + tipContent + "'>" + content + "</a>";
					correspondenceSer.setEnglish(correspondenceSer.getEnglish()+combination);
				}
				break;
			case "[":
				String content1 = "";
				while (!stack.isEmpty() && !"]".equals(stack.peek())) {
					content1 += stack.pop();
				}
				stack.pop();
				String[] temp = content1.split("\\|");
				List<String> list = new ArrayList<String>();
				for (int i = 0; i < temp.length; i++) {
					if(temp[i].contains("{")){
						String front = temp[i].substring(0, temp[i].indexOf("{"));
						String middle = temp[i].substring(temp[i].indexOf("{")+1, temp[i].indexOf("<"));
						String tip = temp[i].substring(temp[i].indexOf("<")+1, temp[i].indexOf(">"));
						String back = temp[i].substring(temp[i].indexOf("}")+1, temp[i].length());
						temp[i]=front+"<a style='color:blue' title='" + tip + "'>" + middle + "</a>"+back;
					}
					list.add(temp[i]);
				}
				correspondenceSer.setEnglish(correspondenceSer.getEnglish()+"<span style='color:#996699'>"+temp[0]+"</span>");
				correspondenceSer.getList().add(list);
				break;
			default:
				String content2 = outLayer;
				while (!stack.isEmpty() && !"{".equals(stack.peek()) && !"[".equals(stack.peek())) {
					content2 += stack.pop();
				}
				correspondenceSer.setEnglish(correspondenceSer.getEnglish()+content2);
				break;
			}
		}
		return correspondenceSer;
	}
	
	/**
	 * 替换后组合函电电文
	 * 
	 * @param correspondenceEnglish
	 * @param listNo
	 * @param innerNo
	 * @return
	 */
	private CorrespondenceSer generateCorrespondenceStr(String correspondenceEnglish,Integer listNo,Integer innerNo){
		CorrespondenceSer correspondenceSer = new CorrespondenceSer();
		Stack<String> stack = new Stack<>();
		String[] item = correspondenceEnglish.split("");
		for (int i = item.length - 1; i >= 0; i--) {
			stack.push(item[i]);
		}
		while (!stack.isEmpty()) {
			String outLayer = stack.pop();
			switch (outLayer) {
			// 需要提示的信息
			case "{":
				String content = "";
				String tipContent = "";
				while (!stack.isEmpty() && !"}".equals(stack.peek())) {
					String inLayer = stack.pop();
					if (!"<".equals(inLayer)) {
						content += inLayer;
					} else if ("<".equals(inLayer)) {
						while (!stack.isEmpty() && !stack.peek().equals(">")) {
							tipContent += stack.pop();
						}
						stack.pop();
					}
				}
				if (!stack.isEmpty()) {
					stack.pop();
					String combination = "<a style='color:blue' title='" + tipContent + "'>" + content + "</a>";
					correspondenceSer.setEnglish(correspondenceSer.getEnglish()+combination);
				}
				break;
			case "[":
				String content1 = "";
				while (!stack.isEmpty() && !"]".equals(stack.peek())) {
					content1 += stack.pop();
				}
				stack.pop();
				String[] temp = content1.split("\\|");
				List<String> list = new ArrayList<String>();
				for (int i = 0; i < temp.length; i++) {
					if(temp[i].contains("{")){
						String front = temp[i].substring(0, temp[i].indexOf("{"));
						String middle = temp[i].substring(temp[i].indexOf("{")+1, temp[i].indexOf("<"));
						String tip = temp[i].substring(temp[i].indexOf("<")+1, temp[i].indexOf(">"));
						String back = temp[i].substring(temp[i].indexOf("}")+1, temp[i].length());
						temp[i]=front+"<a style='color:blue' title='" + tip + "'>" + middle + "</a>"+back;
					}
					list.add(temp[i]);
				}
				correspondenceSer.getList().add(list);
				if (correspondenceSer.getList().size() != listNo + 1) {
					correspondenceSer.setEnglish(correspondenceSer.getEnglish() + "<span style='color:#996699'>"+temp[0]+"</span>");
				} else {
					correspondenceSer.setEnglish(correspondenceSer.getEnglish() + "<span style='color:#996699'>"+correspondenceSer.getList().get(listNo).get(innerNo)+"</span>");
				}
				break;
			default:
				String content2 = outLayer;
				while (!stack.isEmpty() && !"{".equals(stack.peek()) && !"[".equals(stack.peek())) {
					content2 += stack.pop();
				}
				correspondenceSer.setEnglish(correspondenceSer.getEnglish()+content2);
				break;
			}
		}
		return correspondenceSer;
	}

}
