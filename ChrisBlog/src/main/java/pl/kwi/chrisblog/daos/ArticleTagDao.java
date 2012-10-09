package pl.kwi.chrisblog.daos;

import java.util.List;

import org.springframework.stereotype.Repository;

import pl.kwi.chrisblog.entities.ArticleTagEntity;
import pl.kwi.db.abstr.AbstractHibernateTemplateDao;

@Repository
public class ArticleTagDao extends AbstractHibernateTemplateDao<ArticleTagEntity>{
	
	public ArticleTagDao(){
		setClazz(ArticleTagEntity.class);
	}
	
	/**
	 * Method gets list of tags with specified unique names
	 * 
	 * @param uniqueNameList list of specified unique names
	 * @return list of tags with specified unique names
	 */
	@SuppressWarnings("unchecked")
	public List<ArticleTagEntity> findByUniqueNameList(List<String> uniqueNameList){
		
		return hibernateTemplate.findByNamedQueryAndNamedParam("ArticleTagEntity.findByUniqueNameList", "uniqueNameList", uniqueNameList);
				
	}

}
