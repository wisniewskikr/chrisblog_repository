package pl.kwi.db.abstr;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Abstract class for daos using hibernate template. It implements methods of CRUD.
 * 
 * @author Krzysztof Wisniewski
 *
 * @param <T> object implements Serializable
 */
public class AbstractHibernateTemplateDao < T extends Serializable >{

	
		private HibernateTemplate hibernateTemplate;
		private Class< T > clazz;
		
	
		@Autowired
		public void setSessionFactory(SessionFactory sessionFactory) {
			hibernateTemplate = new HibernateTemplate(sessionFactory);
		}
			   
	   public void setClazz( final Class< T > clazzToSet ){
	      this.clazz = clazzToSet;
	   }
	   
	   
	   public T findOne( final Long id ){
	      return hibernateTemplate.get( clazz, id );
	   }
	   
	   @SuppressWarnings("unchecked")
	   public List< T > findAll(){
	      return hibernateTemplate.find( "from " + clazz.getName() );
	   }
	   
	   public void create( final T entity ){
		   hibernateTemplate.persist( entity );
	   }
	   
	   public void update( final T entity ){
		   hibernateTemplate.merge( entity );
	   }
	   
	   public void delete( final T entity ){
		   hibernateTemplate.delete( entity );
	   }
	   public void deleteById( final Long entityId ){
	      final T entity = findOne( entityId );
	      
	      delete( entity );
	   }

}
