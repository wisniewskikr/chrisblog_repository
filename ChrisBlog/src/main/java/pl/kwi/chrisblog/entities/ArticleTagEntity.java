package pl.kwi.chrisblog.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	@Transient
	public Integer getOccurencesCount() {
		return occurencesCount;
	}
	public void setOccurencesCount(Integer occurencesCount) {
		this.occurencesCount = occurencesCount;
	}	

}
