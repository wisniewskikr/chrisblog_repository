package pl.kwi.chrisblog.enums;

/**
 * Status of article.
 * 
 * @author 
 */
public enum ArticleStatusEnum {
	
	
	ACTIVE("Active"),
	NOT_ACTIVE("Not active");
	
	
	private String displayedName;
	
	private ArticleStatusEnum(String displayedName){
		this.displayedName = displayedName;
	}
	

	public String getDisplayedName() {
		return displayedName;
	}
	public void setDisplayedName(String displayedName) {
		this.displayedName = displayedName;
	}	
	

}
