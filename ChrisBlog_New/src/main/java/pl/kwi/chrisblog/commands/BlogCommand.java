package pl.kwi.chrisblog.commands;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.mcavallo.opencloud.Cloud;
import org.springframework.web.servlet.LocaleResolver;

import pl.kwi.chrisblog.entities.ArticleEntity;
import pl.kwi.chrisblog.entities.ExplanationEntity;

/**
 * Class of command for blog.
 * 
 * @author Krzysztof Wisniewski
 */
public class BlogCommand implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<ArticleEntity> articleList;
	private boolean displayArticleList;
	
	private ArticleEntity article;
	private boolean displayArticle;
	
	private Integer pageCurrent;
	private Integer pagesCount;
	
	private boolean displayExplanation;
	private ExplanationEntity explanation;
	
	// Display
	private boolean displayAboutMe;
	private boolean displayException;
		
	// Cloud of tags
	private Cloud tagsCloud;
	
	private String pathHost;
	private String pathContext;
	
	private Locale locale;
		
	
	public List<ArticleEntity> getArticleList() {
		return articleList;
	}
	public void setArticleList(List<ArticleEntity> articleList) {
		this.articleList = articleList;
	}	
	
	public boolean isDisplayArticleList() {
		return displayArticleList;
	}
	public void setDisplayArticleList(boolean displayArticleList) {
		this.displayArticleList = displayArticleList;
	}	
	
	public ArticleEntity getArticle() {
		return article;
	}
	public void setArticle(ArticleEntity article) {
		this.article = article;
	}
	
	public boolean isDisplayArticle() {
		return displayArticle;
	}
	public void setDisplayArticle(boolean displayArticle) {
		this.displayArticle = displayArticle;
	}
	
	public Integer getPageCurrent() {
		return pageCurrent;
	}
	public void setPageCurrent(Integer pageCurrent) {
		this.pageCurrent = pageCurrent;
	}
	
	public Integer getPagesCount() {
		return pagesCount;
	}
	public void setPagesCount(Integer pagesCount) {
		this.pagesCount = pagesCount;
	}	
	
	public ExplanationEntity getExplanation() {
		return explanation;
	}
	public void setExplanation(ExplanationEntity explanation) {
		this.explanation = explanation;
	}
	
	public boolean isDisplayExplanation() {
		return displayExplanation;
	}
	public void setDisplayExplanation(boolean displayExplanation) {
		this.displayExplanation = displayExplanation;
	}
	
	public boolean isDisplayAboutMe() {
		return displayAboutMe;
	}
	public void setDisplayAboutMe(boolean displayAboutMe) {
		this.displayAboutMe = displayAboutMe;
	}	
	
	public boolean isDisplayException() {
		return displayException;
	}
	public void setDisplayException(boolean displayException) {
		this.displayException = displayException;
	}	
			
	public Cloud getTagsCloud() {
		return tagsCloud;
	}
	public void setTagsCloud(Cloud tagsCloud) {
		this.tagsCloud = tagsCloud;
	}
	
	public String getPathHost() {
		return pathHost;
	}
	public void setPathHost(String pathHost) {
		this.pathHost = pathHost;
	}
	
	public String getPathContext() {
		return pathContext;
	}
	public void setPathContext(String pathContext) {
		this.pathContext = pathContext;
	}
	
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}	
	
	
}
