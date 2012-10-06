package pl.kwi.chrisblog.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class ArticleEntity implements Serializable{	
	
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String uniqueName;
	private String title;
	private String description;
	private String contentPath;
	private Integer pagesCount;
	private Calendar creationDate;
	private String author;
	private String demoName;
	private String exampleFileName;
	private String sourceFileName;
	private List<ArticleTagEntity> articleTagList;
	private List<ArticleTagEntity> articleTagFrontEndList;
	private List<ArticleTagEntity> articleTagBackEndList;
	
	// Transient
	private String demoPath;
	private String examplePath;
	private String sourcePath;
	private String creationDateAsString;
	private String frontEndAsString;
	private String backEndAsString;
	
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //
	
	
	public String getFrontEndAsString() {
		
		StringBuffer sb = new StringBuffer();
		for (ArticleTagEntity articleTag : articleTagFrontEndList) {
			sb.append(articleTag.getName());
			sb.append(", ");
		}
		
		frontEndAsString = sb.toString();
		
		if(StringUtils.isBlank(frontEndAsString)){
			frontEndAsString = "none";
		}else{
			frontEndAsString = frontEndAsString.substring(0, frontEndAsString.length() - 2);			
		}		
		
		return frontEndAsString;
		
	}
	
	public String getBackEndAsString() {
		
		StringBuffer sb = new StringBuffer();
		for (ArticleTagEntity articleTag : articleTagBackEndList) {
			sb.append(articleTag.getName());
			sb.append(", ");
		}
		
		backEndAsString = sb.toString();
		
		if(StringUtils.isBlank(backEndAsString)){
			backEndAsString = "none";
		}else{
			backEndAsString = backEndAsString.substring(0, backEndAsString.length() - 2);			
		}		
		
		return backEndAsString;
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** GETTERS AND SETTERS **************************************** //
	// ************************************************************************************************************ //
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	
	public String getUniqueName() {
		return uniqueName;
	}
	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}	

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getContentPath() {
		return contentPath;
	}
	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}
	
	public Integer getPagesCount() {
		return pagesCount;
	}
	public void setPagesCount(Integer pagesCount) {
		this.pagesCount = pagesCount;
	}
	
	public Calendar getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}	
	
	public String getDemoName() {
		return demoName;
	}
	public void setDemoName(String demoName) {
		this.demoName = demoName;
	}

	public String getExampleFileName() {
		return exampleFileName;
	}
	public void setExampleFileName(String exampleFileName) {
		this.exampleFileName = exampleFileName;
	}

	public String getSourceFileName() {
		return sourceFileName;
	}
	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}

	public List<ArticleTagEntity> getArticleTagFrontEndList() {
		return articleTagFrontEndList;
	}
	public void setArticleTagFrontEndList(
			List<ArticleTagEntity> articleTagFrontEndList) {
		this.articleTagFrontEndList = articleTagFrontEndList;
	}
		
	public List<ArticleTagEntity> getArticleTagBackEndList() {
		return articleTagBackEndList;
	}
	public void setArticleTagBackEndList(
			List<ArticleTagEntity> articleTagBackEndList) {
		this.articleTagBackEndList = articleTagBackEndList;
	}
	
	public List<ArticleTagEntity> getArticleTagList() {
		return articleTagList;
	}
	public void setArticleTagList(List<ArticleTagEntity> articleTagList) {
		this.articleTagList = articleTagList;
	}
	
	public String getDemoPath() {
		return demoPath;
	}
	public void setDemoPath(String demoPath) {
		this.demoPath = demoPath;
	}
	
	public String getExamplePath() {
		return examplePath;
	}
	public void setExamplePath(String examplePath) {
		this.examplePath = examplePath;
	}
	
	public String getSourcePath() {
		return sourcePath;
	}
	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}
	
	public String getCreationDateAsString() {
		return creationDateAsString;
	}
	public void setCreationDateAsString(String creationDateAsString) {
		this.creationDateAsString = creationDateAsString;
	}

	
}
