package pl.kwi.chrisblog.daos;

import org.springframework.stereotype.Repository;
import pl.kwi.chrisblog.entities.UserEntity;
import pl.kwi.db.abstr.AbstractHibernateTemplateDao;

@Repository
public class UserDao extends AbstractHibernateTemplateDao<UserEntity>{
	
	public UserDao(){
		setClazz(UserEntity.class);
	}

}
