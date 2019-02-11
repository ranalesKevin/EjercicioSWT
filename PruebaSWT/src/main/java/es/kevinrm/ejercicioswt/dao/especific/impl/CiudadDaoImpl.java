package es.kevinrm.ejercicioswt.dao.especific.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import es.kevinrm.ejercicioswt.dao.especific.CiudadDao;
import es.kevinrm.ejercicioswt.dao.generic.impl.GenericDaoImpl;
import es.kevinrm.ejercicioswt.entities.Ciudad;

@Repository
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class CiudadDaoImpl extends GenericDaoImpl<Ciudad> implements CiudadDao{
 
	@PostConstruct
	public void addClazz() {
		setClazz(Ciudad.class);
	}

}
