package pl.kwi.chrisblog.visitors.impl;

import pl.kwi.chrisblog.commands.BlogCommand;
import pl.kwi.chrisblog.visitors.intf.IVisitor;

/**
 * Class of visitor handles page title
 * 
 * @author Krzysztof Wisniewski
 */
public class PageTitleVisitor implements IVisitor {
	
	
	public PageTitleVisitor(BlogCommand command) throws Exception{
		
		if(command.isDisplayArticleList()) {
			new ArticleListPage().accept(this);
		}else if(command.isDisplayArticle()){
			new ArticlePage().accept(this);			
		}else if(command.isDisplayArticleListWithTag()){
			new ArticleListWithTagPage().accept(this);			
		}else if(command.isDisplayExplanation()){
			new ExplanationPage().accept(this);			
		}else if(command.isDisplayAboutMe()){
			new AboutMePage().accept(this);		
		}else if(command.isDisplayException()){
			new ExceptionPage().accept(this);			
		}else if(command.isDisplaySecArticleList()){
			new SecArticleListPage().accept(this);		
		}else if(command.isDisplaySecViewArticle()){
			new SecViewArticlePage().accept(this);		
		}else if(command.isDisplaySecEditArticle()){
			new SecEditArticlePage().accept(this);			
		}else if(command.isDisplaySecCreateArticle()){
			new SecCreateArticlePage().accept(this);			
		}else if(command.isDisplaySecDeleteArticle()){
			new SecDeleteArticlePage().accept(this);			
		}else if(command.isDisplaySecConfirmation()){
			new SecConfirmationPage().accept(this);		
		}else{
			throw new Exception("Can not get title of browser window. Can not find display mode.");
		}
		
	}
	
	
	private String pageTitle;

	
	/* (non-Javadoc)
	 * @see pl.kwi.chrisblog.visitors.intf.Visitor#visit(pl.kwi.chrisblog.visitors.impl.ArticleListPage)
	 */
	public void visit(ArticleListPage p) {
		pageTitle = p.TITLE;
	}
	
	/* (non-Javadoc)
	 * @see pl.kwi.chrisblog.visitors.intf.Visitor#visit(pl.kwi.chrisblog.visitors.impl.ArticlePage)
	 */
	public void visit(ArticlePage p) {
		pageTitle = p.TITLE;
		
	}
	
	/* (non-Javadoc)
	 * @see pl.kwi.chrisblog.visitors.intf.Visitor#visit(pl.kwi.chrisblog.visitors.impl.ArticleListWithTagPage)
	 */
	public void visit(ArticleListWithTagPage p) {
		pageTitle = p.TITLE;		
	}
	
	/* (non-Javadoc)
	 * @see pl.kwi.chrisblog.visitors.intf.Visitor#visit(pl.kwi.chrisblog.visitors.impl.ArticleListWithTagPage)
	 */
	public void visit(ExplanationPage p) {
		pageTitle = p.TITLE;		
	}
	
	/* (non-Javadoc)
	 * @see pl.kwi.chrisblog.visitors.intf.Visitor#visit(pl.kwi.chrisblog.visitors.impl.ArticleListWithTagPage)
	 */
	public void visit(AboutMePage p) {
		pageTitle = p.TITLE;		
	}
	
	/* (non-Javadoc)
	 * @see pl.kwi.chrisblog.visitors.intf.Visitor#visit(pl.kwi.chrisblog.visitors.impl.ArticleListWithTagPage)
	 */
	public void visit(ExceptionPage p) {
		pageTitle = p.TITLE;		
	}
	
	/* (non-Javadoc)
	 * @see pl.kwi.chrisblog.visitors.intf.Visitor#visit(pl.kwi.chrisblog.visitors.impl.ArticleListWithTagPage)
	 */
	public void visit(SecArticleListPage p) {
		pageTitle = p.TITLE;		
	}
	
	/* (non-Javadoc)
	 * @see pl.kwi.chrisblog.visitors.intf.Visitor#visit(pl.kwi.chrisblog.visitors.impl.ArticleListWithTagPage)
	 */
	public void visit(SecViewArticlePage p) {
		pageTitle = p.TITLE;		
	}
	
	/* (non-Javadoc)
	 * @see pl.kwi.chrisblog.visitors.intf.Visitor#visit(pl.kwi.chrisblog.visitors.impl.ArticleListWithTagPage)
	 */
	public void visit(SecEditArticlePage p) {
		pageTitle = p.TITLE;		
	}
	
	/* (non-Javadoc)
	 * @see pl.kwi.chrisblog.visitors.intf.Visitor#visit(pl.kwi.chrisblog.visitors.impl.ArticleListWithTagPage)
	 */
	public void visit(SecCreateArticlePage p) {
		pageTitle = p.TITLE;		
	}
	
	/* (non-Javadoc)
	 * @see pl.kwi.chrisblog.visitors.intf.Visitor#visit(pl.kwi.chrisblog.visitors.impl.ArticleListWithTagPage)
	 */
	public void visit(SecDeleteArticlePage p) {
		pageTitle = p.TITLE;		
	}
	
	/* (non-Javadoc)
	 * @see pl.kwi.chrisblog.visitors.intf.Visitor#visit(pl.kwi.chrisblog.visitors.impl.ArticleListWithTagPage)
	 */
	public void visit(SecConfirmationPage p) {
		pageTitle = p.TITLE;		
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** GETTERS AND SETTERS **************************************** //
	// ************************************************************************************************************ //
	

	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}	
		

}
