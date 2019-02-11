package es.kevinrm.ejercicioswt.dao.generic;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

public interface GenericDao<T extends Serializable> extends CrudRepository<T, Long>{
	
	T getById(long id);
	
	void update(T entity);
	
}
