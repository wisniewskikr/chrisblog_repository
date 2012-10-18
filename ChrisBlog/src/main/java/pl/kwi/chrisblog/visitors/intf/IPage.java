package pl.kwi.chrisblog.visitors.intf;

/**
 * Interface for pages of blog.
 * 
 * @author Krzysztof Wisniewski
 */
public interface IPage {
	
	/**
	 * Method accepts object Visitor.
	 * 
	 * @param v object Visitor
	 */
	public void accept(IVisitor v);

}
