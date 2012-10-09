package pl.kwi.chrisblog.daos;

import java.util.List;

import org.springframework.stereotype.Repository;

import pl.kwi.chrisblog.entities.ArticleEntity;
import pl.kwi.chrisblog.entities.ArticleTagEntity;
import pl.kwi.db.abstr.AbstractHibernateTemplateDao;

@Repository
public class ArticleDao extends AbstractHibernateTemplateDao<ArticleEntity>{
	
	public ArticleDao(){
		setClazz(ArticleEntity.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<ArticleEntity> findAllSortedByDateDesc(){
		
		return hibernateTemplate.findByNamedQuery("ArticleEntity.findAllSortedByDateDesc");
		
	}
	
	/**
	 * Method counts amount of all articles in db.
	 * 
	 * @return int with amount of all articles in db
	 */
	public int getCountOfAllArticles(){
		
		Long articlesCount = (Long)hibernateTemplate.findByNamedQuery("ArticleEntity.getCountOfAllArticles").get(0);
		return articlesCount.intValue();
		
	}
	
	/**
	 * Method counts amount of articles having specified tags.
	 * 
	 * @param articleTagList list of specified tags
	 * @return int with amount of articles having specified tags
	 */
	public int getCountArticlesWithTags(List<ArticleTagEntity> articleTagList){
		
		Long articlesCount = (Long)hibernateTemplate.findByNamedQueryAndNamedParam("ArticleEntity.getCountArticlesWithTags", "articleTagList", articleTagList).get(0);
		return articlesCount.intValue();
		
	}

}
