package pl.kwi.chrisblog.visitors.impl;

import pl.kwi.chrisblog.visitors.intf.IPage;
import pl.kwi.chrisblog.visitors.intf.IVisitor;

/**
 * Class with data of page with article list.
 * 
 * @author Krzysztof Wisniewski
 *
 */
public class ArticleListPage implements IPage {
	
	public final static String TITLE = "List of Articles";

	/* (non-Javadoc)
	 * @see pl.kwi.chrisblog.visitors.intf.Page#accept(pl.kwi.chrisblog.visitors.intf.Visitor)
	 */
	public void accept(IVisitor v) {
		v.visit(this);
	}

}
