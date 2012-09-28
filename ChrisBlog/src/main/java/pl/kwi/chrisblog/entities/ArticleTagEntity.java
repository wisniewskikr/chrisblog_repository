package pl.kwi.chrisblog.entities;

import java.io.Serializable;

/**
 * Entity of tag (IT topic).
 * 
 * @author Krzysztof Wisniewski
 */
public class ArticleTagEntity implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String uniqueName;
	private String name;
	private Integer occurencesCount;
		
	
	public ArticleTagEntity() {
	}
	
	public ArticleTagEntity(Long id) {
		this.id = id;
	}
	
	public ArticleTagEntity(String uniqueName) {
		this.uniqueName = uniqueName;
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

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getOccurencesCount() {
		return occurencesCount;
	}
	public void setOccurencesCount(Integer occurencesCount) {
		this.occurencesCount = occurencesCount;
	}	

}
