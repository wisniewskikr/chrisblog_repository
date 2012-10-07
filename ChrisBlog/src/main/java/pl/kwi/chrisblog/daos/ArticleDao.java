package pl.kwi.chrisblog.daos;

import org.springframework.stereotype.Repository;

import pl.kwi.chrisblog.entities.ArticleEntity;
import pl.kwi.db.abstr.AbstractHibernateTemplateDao;

@Repository
public class ArticleDao extends AbstractHibernateTemplateDao<ArticleEntity>{
	
	public ArticleDao(){
		setClazz(ArticleEntity.class);
	}

}
