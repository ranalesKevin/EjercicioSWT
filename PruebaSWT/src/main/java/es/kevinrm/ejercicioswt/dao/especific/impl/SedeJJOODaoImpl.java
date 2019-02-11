package es.kevinrm.ejercicioswt.dao.especific.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import es.kevinrm.ejercicioswt.dao.especific.SedeJJOODao;
import es.kevinrm.ejercicioswt.dao.generic.impl.GenericDaoImpl;
import es.kevinrm.ejercicioswt.entities.SedeJJOO;

@Repository
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class SedeJJOODaoImpl extends GenericDaoImpl<SedeJJOO> implements SedeJJOODao{
 
	@PostConstruct
	public void addClazz() {
		setClazz(SedeJJOO.class);
	}

}
