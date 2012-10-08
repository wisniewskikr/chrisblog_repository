package pl.kwi.chrisblog.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import pl.kwi.db.abstr.AbstractEntity;

/**
 * Entity of tag (IT topic).
 * 
 * @author Krzysztof Wisniewski
 */
@Entity
@Table(name="article_tags")
public class ArticleTagEntity extends AbstractEntity{

	
	private static final long serialVersionUID = 1L;
	
	private String uniqueName;
	private String name;
	private List<ArticleEntity> articleList = new ArrayList<ArticleEntity>();
	
	// Transient
	private Integer occurencesCount;
		
	
	public ArticleTagEntity() {
	}
	
	public ArticleTagEntity(String uniqueName) {
		this.uniqueName = uniqueName;
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

	@Column(name="NAME", length=100, nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(mappedBy="articleTagList", fetch=FetchType.EAGER)
	public List<ArticleEntity> getArticleList() {
		return articleList;
	}
	public void setArticleList(List<ArticleEntity> articleList) {
		this.articleList = articleList;
	}

	@Transient
	public Integer getOccurencesCount() {
		occurencesCount = articleList.size();
		return occurencesCount;
	}
	public void setOccurencesCount(Integer occurencesCount) {
		this.occurencesCount = occurencesCount;
	}	

}
