package pl.kwi.chrisblog.daos;

import org.springframework.stereotype.Repository;
import pl.kwi.chrisblog.entities.UserEntity;
import pl.kwi.db.abstr.AbstractDao;

@Repository
public class UserDao extends AbstractDao<UserEntity>{
	
	public UserDao(){
		setClazz(UserEntity.class);
	}

}
