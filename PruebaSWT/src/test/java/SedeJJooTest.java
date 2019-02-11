
import java.util.IllegalFormatConversionException;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.kevinrm.ejercicioswt.dao.especific.CiudadDao;
import es.kevinrm.ejercicioswt.dao.especific.SedeJJOODao;
import es.kevinrm.ejercicioswt.dao.especific.TipoJJOODao;
import es.kevinrm.ejercicioswt.entities.SedeJJOO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/spring-context.xml" })
public class SedeJJooTest {
	
	@Autowired
	SedeJJOODao sedeDao;	
	
	@Autowired
	CiudadDao ciudadDao;	
	
	@Autowired
	TipoJJOODao tipoJJOODao;
	
	
	@Test
	@Transactional
	@Rollback
	public void test() {
		listSedesJJooTest();
		insertSedesJJooTest();
		listSedesJJooTest();
		updateSedesJJooTest();
		listSedesJJooTest();
		deleteSedesJJooTest();
		listSedesJJooTest();
	}

	public void listSedesJJooTest() {
		try {
			List<SedeJJOO> aux = (List<SedeJJOO>) sedeDao.findAll();
			for (SedeJJOO sedeJJOO : aux) {
				System.out.println(sedeJJOO.toString());
			}
		} catch (Exception e) {
			System.out.println(String.format("Error al recuperar todas las Sede: %s", e));
		}
	}

	public void insertSedesJJooTest() {

		try {
			SedeJJOO sedeJJOO =  mockSedeInsert();
			if(sedeJJOO != null){
				sedeDao.save(sedeJJOO);
				System.out.println(String.format("[insertSedesJJooTest]: Se ha insertado la sede %s ", sedeJJOO.toString()));
			}
		} catch (IllegalFormatConversionException e) {
			System.out.println(String.format("Error de formato al insertar la Sede: %s", e));
		}catch (Exception e) {
			System.out.println(String.format("Error al insertar la Sede: %s", e));
		}

	}

	public void updateSedesJJooTest() {
		try {
			SedeJJOO sedeJJOO =  mockSedeUpdate();
			if(sedeJJOO != null){
				sedeDao.update(sedeJJOO);
				System.out.println(String.format("[updateSedesJJooTest]: Se ha modificado la sede %s ", sedeJJOO.toString()));
			}
		} catch (IllegalFormatConversionException e) {
			System.out.println(String.format("Error de formato al insertar la Sede: %s", e));
		} catch (Exception e) {
			System.out.println(String.format("Error al modificar la Sede: %s", e));
		}
	}

	public void deleteSedesJJooTest() {
		try {
			sedeDao.deleteById(1993l);
			System.out.println(String.format("[deleteSedesJJooTest]: Se ha eliminado la sede con ID: %d ", 1993));
		} catch (Exception e) {
			System.out.println(String.format("Error al modificar la Sede: %s", e));
		}
	}
	
	private SedeJJOO mockSedeInsert() {
		try {
			SedeJJOO sede = new SedeJJOO();
			sede.setAno(1993);
			sede.setCiudad(ciudadDao.getById(3l));
			sede.setTipoJJOO(tipoJJOODao.getById(2l));
			return sede;
		} catch (IllegalArgumentException e) {
			System.out.println(String.format("Error al crear el mockSedeInsert la Sede: %s", e));
		}
		return null;
	}

	private SedeJJOO mockSedeUpdate() {
		try {
			SedeJJOO sedeToUpdate = sedeDao.getById(1993l);
			sedeToUpdate.setCiudad(ciudadDao.getById(8l));
			sedeToUpdate.setTipoJJOO(tipoJJOODao.getById(1l));
			return sedeToUpdate;
		} catch (IllegalArgumentException e) {
			System.out.println(String.format("Error al crear el mockSedeUpdate la Sede: %s", e));
		}
		return null;
	}

}
