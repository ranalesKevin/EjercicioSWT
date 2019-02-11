package es.kevinrm.ejercicioswt.dao.generic.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.kevinrm.ejercicioswt.dao.generic.GenericDao;

@Repository
@Transactional
public abstract class GenericDaoImpl<T extends Serializable> implements GenericDao<T> {
	
	private Class<T> clazz;

	@PersistenceContext
	protected EntityManager entityManager;
	
	public void setClazz(Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}	
	
	public boolean existsById(Long id) {
		try {
			if (getById(id) != null)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Iterable<T> findAllById(Iterable<Long> ids) {
		List<T> result = new ArrayList<T>();
		for (Long id : ids)
			result.add(getById(id));
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return entityManager.createQuery("from " + clazz.getName()).getResultList();
	}

	public long count() {
		return findAll().size();
	}
	
	public T getById(long id) {
		return entityManager.find(clazz, id);
	}

	public void delete(T entity) {
		entityManager.remove(entity);
	}

	public void deleteAll() {
		for (T obj : findAll()) {
			delete(obj);
		}
	}
	
	public void deleteAll(Iterable<? extends T> list) {
		for (T obj : list)
			delete(obj);
	}
	
	public void deleteById(Long entityId) {
		T entity = getById(entityId);
		delete(entity);
	}
	
	public void update(T entity) {
		entityManager.merge(entity);
	}
	
	public <S extends T> S save(S entity) {
		entityManager.persist(entity);
		return entity;
	}
	
	public <S extends T> Iterable<S> saveAll(Iterable<S> values) {
		for (T s : values)
			s = save(s);
		return values;
	}

	public Optional<T> findById(Long id) {
		// unused
		return null;
	}

}