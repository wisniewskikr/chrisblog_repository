package pl.kwi.chrisblog.visitors.impl;

import pl.kwi.chrisblog.visitors.intf.IPage;
import pl.kwi.chrisblog.visitors.intf.IVisitor;

/**
 * Class with data of page with create of article description in secured area.
 * 
 * @author Krzysztof Wisniewski
 *
 */
public class SecCreateArticleDescrPage implements IPage {
	
	public final static String TITLE = "Secured Create of Article Description";

	/* (non-Javadoc)
	 * @see pl.kwi.chrisblog.visitors.intf.Page#accept(pl.kwi.chrisblog.visitors.intf.Visitor)
	 */
	public void accept(IVisitor v) {
		v.visit(this);
	}

}
