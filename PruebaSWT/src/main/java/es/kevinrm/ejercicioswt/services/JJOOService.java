package es.kevinrm.ejercicioswt.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.kevinrm.ejercicioswt.dao.nativequery.NativeQueryDao;
import es.kevinrm.ejercicioswt.dto.JuegosOlimpicosDTO;

@Service
public class JJOOService {
	
	@Autowired
	NativeQueryDao nativeQueryDao;

	public List<JuegosOlimpicosDTO> getListaJJOONativeQuery(){
		
		return convertirLista(nativeQueryDao.executeNativeQueryList("nativeQuery.JJOO"));
	
	}
	
	/**
	 * Metodo encargado de mappear los objetos genericos en objetos JuegosOlimpicosDTO
	 * @param queries resultado de la consulta
	 * @return
	 */
	private List<JuegosOlimpicosDTO> convertirLista(List<Query> queries){
		List<JuegosOlimpicosDTO> juegosOlimpicos = new ArrayList<JuegosOlimpicosDTO>();
		for (Object o : queries) {
			if (o instanceof Object[]) {
				juegosOlimpicos.add(new JuegosOlimpicosDTO().mapperResult((Object[]) o));
			}
		}
		return juegosOlimpicos;
	}
}
