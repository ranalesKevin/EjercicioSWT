package es.kevinrm.ejercicioswt.dao.nativequery.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import es.kevinrm.ejercicioswt.dao.nativequery.NativeQueryDao;

@Repository
@PropertySource("classpath:database/queries.properties")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class NativeQueryDaoImpl implements NativeQueryDao{

	@PersistenceContext
	protected EntityManager entityManager;


	@Autowired
	Environment env;
	
	public List executeNativeQueryList(String property) {
		try {
			return entityManager.createNativeQuery(env.getProperty(property)).getResultList();
		}catch (Exception e) {
			System.out.println("Error : " + e);
		}
		return null;
	}

}
