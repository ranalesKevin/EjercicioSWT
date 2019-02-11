package es.kevinrm.ejercicioswt.view.components;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.kevinrm.ejercicioswt.dao.especific.SedeJJOODao;
import es.kevinrm.ejercicioswt.dto.JuegosOlimpicosDTO;
import es.kevinrm.ejercicioswt.entities.SedeJJOO;
import es.kevinrm.ejercicioswt.services.JJOOService;

@Component
public class ComponentTable {
	
	@Autowired
	JJOOService jjooService;
	
	@Autowired
	SedeJJOODao sedeJJOODao;
	
	private static SedeJJOODao staticSedeJJOODao;
	private static 	JJOOService staticJJOOService;
	
	@Autowired
	public void loadContextStatic() {
		ComponentTable.staticSedeJJOODao = sedeJJOODao;
		ComponentTable.staticJJOOService = jjooService;
	}
	
	Table table;
	

	public static void createTablaJuegosOlimpicos(Group group) {
		
		Table table = createTable(group);
		table.setBounds(20,20,150,400);
		
		String[] titles = { "Cod. Pais", "Nombre Pais", "Cod. Ciudad", "Nombre Ciudad", "Valor", "Tipo JJOO",
				"Veces Sede" };
		
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(titles[i]);
		}

		List<JuegosOlimpicosDTO> listJJOO = staticJJOOService.getListaJJOONativeQuery();

		for (JuegosOlimpicosDTO jjoo : listJJOO) {
			TableItem item = new TableItem(table, SWT.NONE);
				item.setText(0, String.valueOf(jjoo.getIdPais()));
				item.setText(1, jjoo.getNombrePais());
				item.setText(2, String.valueOf(jjoo.getIdCiudad()));
				item.setText(3, jjoo.getNombreCiudad());
				item.setText(4, jjoo.getValor().toString());
				item.setText(5, jjoo.getTipoJJOO());
				item.setText(6, jjoo.getVecesSede().toString());
		}

		for (int i = 0; i < titles.length; i++) {

			table.getColumn(i).pack();
		}

		table.setSize(table.computeSize(SWT.DEFAULT, 200));

	}

	public static Table createTablaSedes(final Group group) {

		Table aux = createTable(group);
		aux.setBounds(300,300,200,200);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.horizontalSpan = 3;
		aux.setLayoutData(gridData);
		
		final Table table = aux;
		
		String[] titles = { "Año", "ID Ciudad", "Nombre Ciudad", "ID Tipo JJOO", "Descripción Tipo JJOO"};
		
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(titles[i]);
		}
		reloadTable(table);
		
		for (int i = 0; i < titles.length; i++) {
			table.getColumn(i).pack();
		}
		table.setSize(table.computeSize(SWT.DEFAULT, 150));
		return table;
		
	}
	
	public static Table createTable(Group shell) {
		Table table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		//table.setBounds(x, y, width, height);
		return table;
	}
	
	/**
	 * Metodo encargado de refrescar la Tabla de Sedes
	 * @param tableSede
	 */
	public static void reloadTable(Table tableSede) {
		tableSede.removeAll();
		List<SedeJJOO> sedesJJOO = (List<SedeJJOO>) staticSedeJJOODao.findAll();
		if (sedesJJOO != null) {
			for (SedeJJOO sedeJJOO : sedesJJOO) {
				TableItem item = new TableItem(tableSede, SWT.NONE);
				item.setText(0, String.valueOf(sedeJJOO.getAno()));
				item.setText(1, String.valueOf(sedeJJOO.getCiudad().getIdCiudad()));
				item.setText(2, sedeJJOO.getCiudad().getNombreCiudad());
				item.setText(3, String.valueOf(sedeJJOO.getTipoJJOO().getIdTipoJJOO()));
				item.setText(4, sedeJJOO.getTipoJJOO().getDescripcionTipo());
				item.setData(sedeJJOO);
			}
		}
	}
}
