package pl.kwi.chrisblog.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryEntity implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String uniqueName;
	private String title;
	private Integer countArticlesOnPage;
	private List<ArticleEntity> articleList = new ArrayList<ArticleEntity>();
	
	
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
	
	public Integer getCountArticlesOnPage() {
		return countArticlesOnPage;
	}
	public void setCountArticlesOnPage(Integer countArticlesOnPage) {
		this.countArticlesOnPage = countArticlesOnPage;
	}
	
	public List<ArticleEntity> getArticleList() {
		return articleList;
	}
	public void setArticleList(List<ArticleEntity> articleList) {
		this.articleList = articleList;
	}	
	
	
}
