package pl.kwi.chrisblog.exceptions;

/**
 * Class of exception for secured article.
 * 
 * @author Krzysztof Wisniewski
 */
public class SecArticleException extends Exception{
	
	
	private static final long serialVersionUID = 1L;

	
	public SecArticleException(String message){
		super(message);
	}
	
	
}
