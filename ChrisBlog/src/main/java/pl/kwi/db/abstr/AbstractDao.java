package pl.kwi.db.abstr;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AbstractDao < T extends Serializable >{
	
		   
	   private Class< T > clazz;
	   
	   @PersistenceContext
	   private EntityManager entityManager;
	   
	   public void setClazz( final Class< T > clazzToSet ){
	      this.clazz = clazzToSet;
	   }
	   
	   public T findOne( final Long id ){
	      return entityManager.find( clazz, id );
	   }
	   public List< T > findAll(){
	      return entityManager.createQuery( "from " + clazz.getName() )
	       .getResultList();
	   }
	   
	   public void create( final T entity ){
	      entityManager.persist( entity );
	   }
	   
	   public void update( final T entity ){
	      entityManager.merge( entity );
	   }
	   
	   public void delete( final T entity ){
	      entityManager.remove( entity );
	   }
	   public void deleteById( final Long entityId ){
	      final T entity = findOne( entityId );
	      
	      delete( entity );
	   }

}
