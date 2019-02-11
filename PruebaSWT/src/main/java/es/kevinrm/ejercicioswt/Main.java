package es.kevinrm.ejercicioswt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.kevinrm.ejercicioswt.view.Window;

@Configuration
@ComponentScan
public class Main {

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		try {
			 ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/spring-context.xml");
			 Window miMain = context.getBean(Window.class);
			 miMain.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
