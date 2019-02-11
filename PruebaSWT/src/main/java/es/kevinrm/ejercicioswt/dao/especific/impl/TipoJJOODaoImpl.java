package es.kevinrm.ejercicioswt.dao.especific.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import es.kevinrm.ejercicioswt.dao.especific.TipoJJOODao;
import es.kevinrm.ejercicioswt.dao.generic.impl.GenericDaoImpl;
import es.kevinrm.ejercicioswt.entities.TipoJJOO;

@Repository
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class TipoJJOODaoImpl extends GenericDaoImpl<TipoJJOO> implements TipoJJOODao{
 
	@PostConstruct
	public void addClazz() {
		setClazz(TipoJJOO.class);
	}

}
