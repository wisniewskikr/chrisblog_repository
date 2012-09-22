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
	
	// Displays
	private boolean displayArticleList;
	private boolean displayArticle;
	private boolean displayExplanation;
	private boolean displayAboutMe;
	private boolean displayException;
	
	// Objects with data
	private List<ArticleEntity> articleList;	
	private ArticleEntity article;
	private ExplanationEntity explanation;
	
	// Paths
	private String pathHost;
	private String pathContext;
	
	// Pages
	private Integer pageCurrent;
	private Integer pagesCount;
		
	// Cloud of tags
	private Cloud tagsCloud;
	
	// Locale
	private Locale locale;
	

	public boolean isDisplayArticleList() {
		return displayArticleList;
	}
	public void setDisplayArticleList(boolean displayArticleList) {
		this.displayArticleList = displayArticleList;
	}

	public boolean isDisplayArticle() {
		return displayArticle;
	}
	public void setDisplayArticle(boolean displayArticle) {
		this.displayArticle = displayArticle;
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

	public List<ArticleEntity> getArticleList() {
		return articleList;
	}
	public void setArticleList(List<ArticleEntity> articleList) {
		this.articleList = articleList;
	}

	public ArticleEntity getArticle() {
		return article;
	}
	public void setArticle(ArticleEntity article) {
		this.article = article;
	}

	public ExplanationEntity getExplanation() {
		return explanation;
	}
	public void setExplanation(ExplanationEntity explanation) {
		this.explanation = explanation;
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
	
	public Cloud getTagsCloud() {
		return tagsCloud;
	}
	public void setTagsCloud(Cloud tagsCloud) {
		this.tagsCloud = tagsCloud;
	}

	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	
}
