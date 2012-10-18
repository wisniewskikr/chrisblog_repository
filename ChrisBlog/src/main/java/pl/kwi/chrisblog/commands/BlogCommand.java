package pl.kwi.chrisblog.commands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.mcavallo.opencloud.Cloud;
import pl.kwi.chrisblog.entities.ArticleEntity;
import pl.kwi.chrisblog.entities.ArticleTagEntity;
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
	private boolean displayArticleListWithTag;
	private boolean displayExplanation;
	private boolean displayAboutMe;
	private boolean displayException;
	
	// Displays secured
	private boolean displaySecArticleList;
	private boolean displaySecViewArticle;
	private boolean displaySecEditArticle;
	private boolean displaySecCreateArticle;
	private boolean displaySecDeleteArticle;
	private boolean displaySecConfirmation;
	
	// Objects with data
	private List<ArticleEntity> articleList;	
	private ArticleEntity article;
	private ArticleTagEntity articleTag;
	private ExplanationEntity explanation;
	
	// Paths
	private String pathHost;
	private String pathContext;
	
	// Pages
	private Integer pageCurrent;
	private Integer pagesCount;
		
	// Clouds
	private Cloud tagsCloudFooter;
	private Cloud tagsCloudRightSpace;
	
	// Locale
	private Locale locale;
	
	// Title of browser window
	private String windowTitle;
	
	// Messages
	private List<String> errorMsgs = new ArrayList<String>();
	private List<String> infoMsgs = new ArrayList<String>();
	private List<String> okMsgs = new ArrayList<String>();
	private List<String> warnMsgs = new ArrayList<String>();
	
	private String selectedRows;
	

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

	public boolean isDisplayArticleListWithTag() {
		return displayArticleListWithTag;
	}
	public void setDisplayArticleListWithTag(boolean displayArticleListWithTag) {
		this.displayArticleListWithTag = displayArticleListWithTag;
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

	public boolean isDisplaySecArticleList() {
		return displaySecArticleList;
	}
	public void setDisplaySecArticleList(boolean displaySecArticleList) {
		this.displaySecArticleList = displaySecArticleList;
	}
	
	public boolean isDisplaySecViewArticle() {
		return displaySecViewArticle;
	}
	public void setDisplaySecViewArticle(boolean displaySecViewArticle) {
		this.displaySecViewArticle = displaySecViewArticle;
	}
	
	public boolean isDisplaySecEditArticle() {
		return displaySecEditArticle;
	}
	public void setDisplaySecEditArticle(boolean displaySecEditArticle) {
		this.displaySecEditArticle = displaySecEditArticle;
	}	
	
	public boolean isDisplaySecCreateArticle() {
		return displaySecCreateArticle;
	}
	public void setDisplaySecCreateArticle(boolean displaySecCreateArticle) {
		this.displaySecCreateArticle = displaySecCreateArticle;
	}	
	
	public boolean isDisplaySecDeleteArticle() {
		return displaySecDeleteArticle;
	}
	public void setDisplaySecDeleteArticle(boolean displaySecDeleteArticle) {
		this.displaySecDeleteArticle = displaySecDeleteArticle;
	}	
	
	public boolean isDisplaySecConfirmation() {
		return displaySecConfirmation;
	}
	public void setDisplaySecConfirmation(boolean displaySecConfirmation) {
		this.displaySecConfirmation = displaySecConfirmation;
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

	public ArticleTagEntity getArticleTag() {
		return articleTag;
	}
	public void setArticleTag(ArticleTagEntity articleTag) {
		this.articleTag = articleTag;
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
	
	public Cloud getTagsCloudFooter() {
		return tagsCloudFooter;
	}
	public void setTagsCloudFooter(Cloud tagsCloudFooter) {
		this.tagsCloudFooter = tagsCloudFooter;
	}

	public Cloud getTagsCloudRightSpace() {
		return tagsCloudRightSpace;
	}
	public void setTagsCloudRightSpace(Cloud tagsCloudRightSpace) {
		this.tagsCloudRightSpace = tagsCloudRightSpace;
	}
	
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	public String getWindowTitle() {
		return windowTitle;
	}
	public void setWindowTitle(String windowTitle) {
		this.windowTitle = windowTitle;
	}
	
	public List<String> getErrorMsgs() {
		return errorMsgs;
	}
	public void setErrorMsgs(List<String> errorMsgs) {
		this.errorMsgs = errorMsgs;
	}
	
	public List<String> getInfoMsgs() {
		return infoMsgs;
	}
	public void setInfoMsgs(List<String> infoMsgs) {
		this.infoMsgs = infoMsgs;
	}
	
	public List<String> getOkMsgs() {
		return okMsgs;
	}
	public void setOkMsgs(List<String> okMsgs) {
		this.okMsgs = okMsgs;
	}
	
	public List<String> getWarnMsgs() {
		return warnMsgs;
	}
	public void setWarnMsgs(List<String> warnMsgs) {
		this.warnMsgs = warnMsgs;
	}
	
	public String getSelectedRows() {
		return selectedRows;
	}
	public void setSelectedRows(String selectedRows) {
		this.selectedRows = selectedRows;
	}	
	
}
