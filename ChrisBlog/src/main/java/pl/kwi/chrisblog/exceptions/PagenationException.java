package pl.kwi.chrisblog.exceptions;

/**
 * Class of exception for article.
 * 
 * @author Krzysztof Wisniewski
 */
public class PagenationException extends Exception{
	
	
	private static final long serialVersionUID = 1L;

	
	public PagenationException(String message){
		super(message);
	}
	
	
}
