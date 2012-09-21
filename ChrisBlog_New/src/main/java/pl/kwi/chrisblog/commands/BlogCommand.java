package pl.kwi.chrisblog.commands;

import java.io.Serializable;
import java.util.List;

import org.mcavallo.opencloud.Cloud;

import pl.kwi.chrisblog.entities.ArticleEntity;
import pl.kwi.chrisblog.entities.CategoryEntity;
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
		
	// Category list
	private List<CategoryEntity> categoryList;
	
	// Selected category
	private Long selectedCategoryId;
	private String selectedCategoryUniqueName;
	private CategoryEntity selectedCategory;
	private Integer selectedCategoryPageCurrent;
	private Integer selectedCategoryPagesCount;
	
	// Selected article
	private Long selectedArticleId;
	private String selectedArticleUniqueName;
	private ArticleEntity selectedArticle;
	private Integer selectedArticlePageCurrent;
	private Integer selectedArticlePagesCount;
	
	// Dictionary
	private Long selectedExplanationId;
	private String selectedExplanationUniqueName;
	private ExplanationEntity selectedExplanation;
	
	// Display
	private boolean displaySelectedCategory;
	private boolean displaySelectedArticle;
	private boolean displaySelectedExplanation;
	private boolean displayAboutMe;
	private boolean displayException;
		
	// Cloud of tags
	private Cloud tagsCloud;
	
	private String pathHost;
	private String pathContext;
		
	
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
	
	public List<CategoryEntity> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<CategoryEntity> categoryList) {
		this.categoryList = categoryList;
	}
	
	public Long getSelectedCategoryId() {
		return selectedCategoryId;
	}
	public void setSelectedCategoryId(Long selectedCategoryId) {
		this.selectedCategoryId = selectedCategoryId;
	}	
	
	public String getSelectedCategoryUniqueName() {
		return selectedCategoryUniqueName;
	}
	public void setSelectedCategoryUniqueName(String selectedCategoryUniqueName) {
		this.selectedCategoryUniqueName = selectedCategoryUniqueName;
	}
	
	public CategoryEntity getSelectedCategory() {
		return selectedCategory;
	}
	public void setSelectedCategory(CategoryEntity selectedCategory) {
		this.selectedCategory = selectedCategory;
	}	
	
	public Integer getSelectedCategoryPageCurrent() {
		return selectedCategoryPageCurrent;
	}
	public void setSelectedCategoryPageCurrent(Integer selectedCategoryPageCurrent) {
		this.selectedCategoryPageCurrent = selectedCategoryPageCurrent;
	}
	
	public Integer getSelectedCategoryPagesCount() {
		return selectedCategoryPagesCount;
	}
	public void setSelectedCategoryPagesCount(Integer selectedCategoryPagesCount) {
		this.selectedCategoryPagesCount = selectedCategoryPagesCount;
	}
	
	public ArticleEntity getSelectedArticle() {
		return selectedArticle;
	}
	public void setSelectedArticle(ArticleEntity selectedArticle) {
		this.selectedArticle = selectedArticle;
	}	
	
	public Integer getSelectedArticlePageCurrent() {
		return selectedArticlePageCurrent;
	}
	public void setSelectedArticlePageCurrent(Integer selectedArticlePageCurrent) {
		this.selectedArticlePageCurrent = selectedArticlePageCurrent;
	}
	
	public Integer getSelectedArticlePagesCount() {
		return selectedArticlePagesCount;
	}
	public void setSelectedArticlePagesCount(Integer selectedArticlePagesCount) {
		this.selectedArticlePagesCount = selectedArticlePagesCount;
	}
	public Long getSelectedArticleId() {
		return selectedArticleId;
	}
	public void setSelectedArticleId(Long selectedArticleId) {
		this.selectedArticleId = selectedArticleId;
	}	
	
	public String getSelectedArticleUniqueName() {
		return selectedArticleUniqueName;
	}
	public void setSelectedArticleUniqueName(String selectedArticleUniqueName) {
		this.selectedArticleUniqueName = selectedArticleUniqueName;
	}	
	
	
	
	public Long getSelectedExplanationId() {
		return selectedExplanationId;
	}
	public void setSelectedExplanationId(Long selectedExplanationId) {
		this.selectedExplanationId = selectedExplanationId;
	}
	
	public String getSelectedExplanationUniqueName() {
		return selectedExplanationUniqueName;
	}
	public void setSelectedExplanationUniqueName(
			String selectedExplanationUniqueName) {
		this.selectedExplanationUniqueName = selectedExplanationUniqueName;
	}
	
	public ExplanationEntity getSelectedExplanation() {
		return selectedExplanation;
	}
	public void setSelectedExplanation(ExplanationEntity selectedExplanation) {
		this.selectedExplanation = selectedExplanation;
	}
	
	public boolean isDisplaySelectedCategory() {
		return displaySelectedCategory;
	}
	public void setDisplaySelectedCategory(boolean displaySelectedCategory) {
		this.displaySelectedCategory = displaySelectedCategory;
	}
	
	public boolean isDisplaySelectedArticle() {
		return displaySelectedArticle;
	}
	public void setDisplaySelectedArticle(boolean displaySelectedArticle) {
		this.displaySelectedArticle = displaySelectedArticle;
	}
	
	public boolean isDisplaySelectedExplanation() {
		return displaySelectedExplanation;
	}
	public void setDisplaySelectedExplanation(boolean displaySelectedExplanation) {
		this.displaySelectedExplanation = displaySelectedExplanation;
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
	
	
}
