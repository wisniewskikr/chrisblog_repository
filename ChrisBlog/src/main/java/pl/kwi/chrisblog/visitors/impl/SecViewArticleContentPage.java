package pl.kwi.chrisblog.visitors.impl;

import pl.kwi.chrisblog.visitors.intf.IPage;
import pl.kwi.chrisblog.visitors.intf.IVisitor;

/**
 * Class with data of page with view of article content in secured area.
 * 
 * @author Krzysztof Wisniewski
 *
 */
public class SecViewArticleContentPage implements IPage {
	
	public final static String TITLE = "Secured View of Article Content";

	/* (non-Javadoc)
	 * @see pl.kwi.chrisblog.visitors.intf.Page#accept(pl.kwi.chrisblog.visitors.intf.Visitor)
	 */
	public void accept(IVisitor v) {
		v.visit(this);
	}

}
