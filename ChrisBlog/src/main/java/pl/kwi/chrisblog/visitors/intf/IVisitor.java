package pl.kwi.chrisblog.visitors.intf;

import pl.kwi.chrisblog.visitors.impl.AboutMePage;
import pl.kwi.chrisblog.visitors.impl.ArticleListPage;
import pl.kwi.chrisblog.visitors.impl.ArticleListWithTagPage;
import pl.kwi.chrisblog.visitors.impl.ArticlePage;
import pl.kwi.chrisblog.visitors.impl.ExceptionPage;
import pl.kwi.chrisblog.visitors.impl.ExplanationPage;
import pl.kwi.chrisblog.visitors.impl.SecArticleListPage;
import pl.kwi.chrisblog.visitors.impl.SecConfirmationPage;
import pl.kwi.chrisblog.visitors.impl.SecCreateArticlePage;
import pl.kwi.chrisblog.visitors.impl.SecDeleteArticlePage;
import pl.kwi.chrisblog.visitors.impl.SecEditArticlePage;
import pl.kwi.chrisblog.visitors.impl.SecViewArticlePage;

/**
 * Interface for visitors.
 * 
 * @author Krzysztof Wisniewski
 */
public interface IVisitor {
	
	/**
	 * Method visits object ArticleListPage
	 * 
	 * @param p object ArticleListPage which is visited
	 */
	public void visit(ArticleListPage p);
	
	/**
	 * Method visits object ArticlePage
	 * 
	 * @param p object ArticlePage which is visited
	 */
	public void visit(ArticlePage p);
	
	/**
	 * Method visits object ArticleListWithTagPage
	 * 
	 * @param p object ArticleListWithTagPage which is visited
	 */
	public void visit(ArticleListWithTagPage p);
	
	/**
	 * Method visits object ExplanationPage
	 * 
	 * @param p object ExplanationPage which is visited
	 */
	public void visit(ExplanationPage p);
	
	/**
	 * Method visits object AboutMePage
	 * 
	 * @param p object AboutMePage which is visited
	 */
	public void visit(AboutMePage p);
	
	/**
	 * Method visits object ExceptionPage
	 * 
	 * @param p object ExceptionPage which is visited
	 */
	public void visit(ExceptionPage p);
	
	/**
	 * Method visits object SecArticleListPage
	 * 
	 * @param p object SecArticleListPage which is visited
	 */
	public void visit(SecArticleListPage p);
	
	/**
	 * Method visits object SecViewArticlePage
	 * 
	 * @param p object SecViewArticlePage which is visited
	 */
	public void visit(SecViewArticlePage p);
	
	/**
	 * Method visits object SecEditArticlePage
	 * 
	 * @param p object SecEditArticlePage which is visited
	 */
	public void visit(SecEditArticlePage p);
	
	/**
	 * Method visits object SecCreateArticlePage
	 * 
	 * @param p object SecCreateArticlePage which is visited
	 */
	public void visit(SecCreateArticlePage p);
	
	/**
	 * Method visits object SecDeleteArticlePage
	 * 
	 * @param p object SecDeleteArticlePage which is visited
	 */
	public void visit(SecDeleteArticlePage p);
	
	/**
	 * Method visits object SecConfirmationPage
	 * 
	 * @param p object SecConfirmationPage which is visited
	 */
	public void visit(SecConfirmationPage p);

}
