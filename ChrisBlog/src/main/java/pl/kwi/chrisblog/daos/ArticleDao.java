package pl.kwi.chrisblog.daos;

import java.util.List;

import org.springframework.stereotype.Repository;

import pl.kwi.chrisblog.entities.ArticleEntity;
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
	
	public int getCountOfAllArticles(){
		
		Long articlesCount = (Long)hibernateTemplate.findByNamedQuery("ArticleEntity.getCountOfAllArticles").get(0);
		return articlesCount.intValue();
		
	}

}
