import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.kevinrm.ejercicioswt.dto.JuegosOlimpicosDTO;
import es.kevinrm.ejercicioswt.services.JJOOService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/spring-context.xml" })
public class juegosOlimpicosTest {
	
	@Autowired
	JJOOService jjooService;
	
	@Test
	public void test() {
		try {
			List<JuegosOlimpicosDTO> aux = jjooService.getListaJJOONativeQuery();
			for (JuegosOlimpicosDTO juegosOlimpicosDTO : aux) {
				System.out.println(juegosOlimpicosDTO.toString());
			}
		}catch (Exception e) {
			System.out.println("Error : " + e);
		}
	}
}
