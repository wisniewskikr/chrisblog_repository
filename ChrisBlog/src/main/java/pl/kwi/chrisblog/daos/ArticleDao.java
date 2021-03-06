package pl.kwi.chrisblog.daos;

import java.sql.SQLException;
import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import pl.kwi.chrisblog.entities.ArticleEntity;
import pl.kwi.chrisblog.entities.ArticleTagEntity;
import pl.kwi.db.abstr.AbstractHibernateTemplateDao;

@Repository
public class ArticleDao extends AbstractHibernateTemplateDao<ArticleEntity>{
	
	public ArticleDao(){
		setClazz(ArticleEntity.class);
	}
	
	/**
	 * Method gets all active articles.
	 * 
	 * @return list of all actives articles
	 */
	@SuppressWarnings("unchecked")
	public List<ArticleEntity> findAllActive(){
		
		return hibernateTemplate.findByNamedQuery("ArticleEntity.findAllActive");
		
	}
	
	/**
	 * Method finds and paginates all articles from db sorted descending by date.
	 * 
	 * @param firstResult int with number of first article which should be found in db
	 * @param maxResults int with amount of articles which should be found in db
	 * @return list of articles sorted descending by date
	 */
	@SuppressWarnings("unchecked")
	public List<ArticleEntity> findAllSortedByDateDesc(final int firstResult, final int maxResults){
		
		return hibernateTemplate.executeFind(new HibernateCallback<List<ArticleEntity>>() {
			
            public List<ArticleEntity> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.getNamedQuery("ArticleEntity.findAllSortedByDateDesc");
                query.setFirstResult(firstResult);
                query.setMaxResults(maxResults);
                return query.list();
            }
            
        });
		
	}
	
	/**
	 * Method finds and paginates all active articles from db sorted descending by date.
	 * 
	 * @param firstResult int with number of first article which should be found in db
	 * @param maxResults int with amount of articles which should be found in db
	 * @return list of articles sorted descending by date
	 */
	@SuppressWarnings("unchecked")
	public List<ArticleEntity> findAllActiveSortedByDateDesc(final int firstResult, final int maxResults){
		
		return hibernateTemplate.executeFind(new HibernateCallback<List<ArticleEntity>>() {
			
            public List<ArticleEntity> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.getNamedQuery("ArticleEntity.findAllActiveSortedByDateDesc");
                query.setFirstResult(firstResult);
                query.setMaxResults(maxResults);
                return query.list();
            }
            
        });
		
	}
	
	/**
	 * Method finds and paginates articles from db with specified tags sorted descending by date.
	 * 
	 * @param firstResult int with number of first article which should be found in db
	 * @param maxResults int with amount of articles which should be found in db
	 * @param articleTagList list of specified tags
	 * @return list of articles with specified tags sorted descending by date
	 */
	@SuppressWarnings("unchecked")
	public List<ArticleEntity> findAllWithTagsSortedByDateDesc(final int firstResult, final int maxResults, 
			final List<ArticleTagEntity> articleTagList){
				
		return hibernateTemplate.executeFind(new HibernateCallback<List<ArticleEntity>>() {
			
            public List<ArticleEntity> doInHibernate(Session session) throws HibernateException, SQLException {
            	
                Query query = session.getNamedQuery("ArticleEntity.findAllWithTagsSortedByDateDesc");
                query.setParameterList("articleTagList", articleTagList);
                query.setFirstResult(firstResult);
                query.setMaxResults(maxResults);
                return query.list();
            }
            
        });
		
	}
	
	/**
	 * Method gets article with specified unique name.
	 * 
	 * @param uniqueName object String with specified unique name
	 * @return object ArticleEntity with article with specified unique name
	 */
	public ArticleEntity getArticleByUniqueName(String uniqueName){
		
		@SuppressWarnings("unchecked")
		List<ArticleEntity> resultList = hibernateTemplate.findByNamedQueryAndNamedParam("ArticleEntity.getArticleByUniqueName", "uniqueName", uniqueName);
		if(!resultList.isEmpty()){
			return resultList.get(0);
		}else{
			return null;
		}
		
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
	
	/**
	 * Method deletes article with unique name.
	 * 
	 * @param uniqueName object String with unique name of deleted article
	 */
	public void deleteByUniqueName(String uniqueName){
		
		ArticleEntity article = getArticleByUniqueName(uniqueName);
		delete(article);
		
	}

}
