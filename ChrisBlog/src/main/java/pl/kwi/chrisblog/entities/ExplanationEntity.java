package pl.kwi.chrisblog.entities;

import java.io.Serializable;

/**
 * Entity of explanation.
 * 
 * @author Krzysztof Wisniewski
 */
public class ExplanationEntity implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String uniqueName;
	private String title;
	private String content;
		
	
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
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}	
	

}
