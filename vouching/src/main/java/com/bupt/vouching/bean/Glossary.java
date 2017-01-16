package com.bupt.vouching.bean;

import com.bupt.vouching.type.GlossarySourceType;

/**
 * 词汇查询实体
 * 
 * @author Hogan
 * 
 */
public class Glossary {

	private Integer glossaryId;
	private String originalWord;
	private String translate;
	private Integer source;

	// 页面中使用
	private String sourceName;

	public Integer getGlossaryId() {
		return glossaryId;
	}

	public void setGlossaryId(Integer glossaryId) {
		this.glossaryId = glossaryId;
	}

	public String getOriginalWord() {
		return originalWord;
	}

	public void setOriginalWord(String originalWord) {
		this.originalWord = originalWord;
	}

	public String getTranslate() {
		return translate;
	}

	public void setTranslate(String translate) {
		this.translate = translate;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getSourceName() {
		if(this.source != null){
			sourceName = GlossarySourceType.byId(this.source).getDescription();
		}
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

}
