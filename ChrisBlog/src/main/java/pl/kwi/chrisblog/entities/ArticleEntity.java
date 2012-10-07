package pl.kwi.chrisblog.entities;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

import pl.kwi.db.abstr.AbstractEntity;

@NamedQueries(value = { 
		@NamedQuery(name="ArticleEntity.findAllSortedByDateDesc", 
			query="SELECT a FROM ArticleEntity a ORDER BY a.creationDate DESC")	
	})

@Entity
@Table(name="articles")
public class ArticleEntity extends AbstractEntity{	
	
	
	private static final long serialVersionUID = 1L;
	
	private String uniqueName;
	private String title;
	private String description;
	private String content;
	private Integer pagesCount;
	private Calendar creationDate;
	private String author;
	private String demoName;
	private String exampleFileName;
	private String sourceFileName;
	
	// Tmp transient
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
	
	
	@Transient
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
	
	@Transient
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
	
	
	@Column(name="UNIQUE_NAME", length=100, nullable=false)
	public String getUniqueName() {
		return uniqueName;
	}
	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}	

	@Column(name="TITLE", length=100, nullable=false)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name="DESCRIPTION", length=100, nullable=false)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="CONTENT", length=100, nullable=false)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name="PAGES_COUNT", nullable=false)
	public Integer getPagesCount() {
		return pagesCount;
	}
	public void setPagesCount(Integer pagesCount) {
		this.pagesCount = pagesCount;
	}
	
	@Column(name="CREATION_DATE", nullable=false)
	public Calendar getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}
	
	@Column(name="AUTHOR", length=50, nullable=false)
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}	
	
	@Column(name="DEMO_NAME", length=100, nullable=true)
	public String getDemoName() {
		return demoName;
	}
	public void setDemoName(String demoName) {
		this.demoName = demoName;
	}

	@Column(name="EXAMPLE_FILE_NAME", length=100, nullable=true)
	public String getExampleFileName() {
		return exampleFileName;
	}
	public void setExampleFileName(String exampleFileName) {
		this.exampleFileName = exampleFileName;
	}

	@Column(name="SOURCE_FILE_NAME", length=100, nullable=true)
	public String getSourceFileName() {
		return sourceFileName;
	}
	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}

	@Transient
	public List<ArticleTagEntity> getArticleTagFrontEndList() {
		return articleTagFrontEndList;
	}
	public void setArticleTagFrontEndList(
			List<ArticleTagEntity> articleTagFrontEndList) {
		this.articleTagFrontEndList = articleTagFrontEndList;
	}
		
	@Transient
	public List<ArticleTagEntity> getArticleTagBackEndList() {
		return articleTagBackEndList;
	}
	public void setArticleTagBackEndList(
			List<ArticleTagEntity> articleTagBackEndList) {
		this.articleTagBackEndList = articleTagBackEndList;
	}
	
	@Transient
	public List<ArticleTagEntity> getArticleTagList() {
		return articleTagList;
	}
	public void setArticleTagList(List<ArticleTagEntity> articleTagList) {
		this.articleTagList = articleTagList;
	}
	
	@Transient
	public String getDemoPath() {
		return demoPath;
	}
	public void setDemoPath(String demoPath) {
		this.demoPath = demoPath;
	}
	
	@Transient
	public String getExamplePath() {
		return examplePath;
	}
	public void setExamplePath(String examplePath) {
		this.examplePath = examplePath;
	}
	
	@Transient
	public String getSourcePath() {
		return sourcePath;
	}
	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}
	
	@Transient
	public String getCreationDateAsString() {
		return creationDateAsString;
	}
	public void setCreationDateAsString(String creationDateAsString) {
		this.creationDateAsString = creationDateAsString;
	}

	
}
