package pl.kwi.chrisblog.daos;

import org.springframework.stereotype.Repository;

import pl.kwi.chrisblog.entities.ArticleTagEntity;
import pl.kwi.db.abstr.AbstractHibernateTemplateDao;

@Repository
public class ArticleTagDao extends AbstractHibernateTemplateDao<ArticleTagEntity>{
	
	public ArticleTagDao(){
		setClazz(ArticleTagEntity.class);
	}

}
