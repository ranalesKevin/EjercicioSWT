package es.kevinrm.ejercicioswt.view;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.kevinrm.ejercicioswt.dao.especific.CiudadDao;
import es.kevinrm.ejercicioswt.dao.especific.SedeJJOODao;
import es.kevinrm.ejercicioswt.dao.especific.TipoJJOODao;
import es.kevinrm.ejercicioswt.entities.Ciudad;
import es.kevinrm.ejercicioswt.entities.SedeJJOO;
import es.kevinrm.ejercicioswt.entities.TipoJJOO;
import es.kevinrm.ejercicioswt.view.components.ComponentTable;

@Component
public class Window {

	@Autowired
	SedeJJOODao sedeJJOODao;
	
	@Autowired
	CiudadDao ciudadDao;
	
	@Autowired
	TipoJJOODao tipoJJOODao;
	
	protected Shell shell;
	protected Shell child;
	protected Table tableSede;
	protected Button buttonSave;
	// -----------------
	Combo comboBoxCiudades;
	Combo comboBoxTipoSede;
	Text textAno;
	int posicionX = 20;

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		// shell.setLayout(createGeneralLayout());
		shell.setLayout(new FillLayout());
		shell.open();
		// shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(720, 480);
		shell.setText("Ejercicio SWT");
		createTabs();
	}

	/**
	 * Metod para crear los tabs de la pantalla
	 */
	public void createTabs() {
		CTabFolder tabFolder = new CTabFolder(shell, SWT.NONE);
			tabFolder.setLayout(new FillLayout());
			tabFolder.setBackground(new Color(Display.getCurrent(), new RGB(66, 165, 245)));
			tabFolder.setSelectionBackground(new Color(Display.getCurrent(), new RGB(128, 214, 255)));
			
// Tab 1 - Tabla Juegos Olimpicos
			CTabItem tabJJOO = new CTabItem(tabFolder, SWT.NONE);
			tabJJOO.setText("Tab Juegos Olimpicos");
			
			RowLayout layout = new RowLayout(SWT.HORIZONTAL);
				layout.wrap = true;
				layout.fill = false;
				layout.justify = true;
			Group groupJJOO = new Group(tabFolder, SWT.NONE);
				groupJJOO.setLayout(layout);
			ComponentTable.createTablaJuegosOlimpicos(groupJJOO);
			
			tabJJOO.setControl(groupJJOO);

// Tab 2 - Tabla Sede
		CTabItem tabSede = new CTabItem(tabFolder, SWT.NONE);
		tabSede.setText("Tab Sede JJOO");

		final Group groupSede = new Group(tabFolder, SWT.NONE);		
			createLabel(groupSede);
			createText(groupSede);
			createComboBox(groupSede);
			createButtons(groupSede);
		this.tableSede = ComponentTable.createTablaSedes(groupSede);
		
		GridData gridDataTextoInformativo = new GridData();
		gridDataTextoInformativo.horizontalSpan = 2;
		gridDataTextoInformativo.widthHint = 400;
		Label labelTipo = new Label(groupSede, SWT.NONE);
		labelTipo.setText("* Para modificar una fila es necesario pulsar el boton derecho sobre ella");
		labelTipo.setLayoutData(gridDataTextoInformativo);
		
		groupSede.setLayout(new GridLayout(3, true));
		
		menuTableSede(groupSede);
		tabSede.setControl(groupSede);
		
	}

	/**
	 * Metodo que se encarga de crear las labels de la pantalla
	 * @param child
	 */
	private void createLabel(Composite child) {
		Label labelAno = new Label(child, SWT.NONE);
		labelAno.setText("Años Sede");
		labelAno.setLayoutData(new GridData(200, 20));
		
		Label labelCiudad = new Label(child, SWT.NONE);
		labelCiudad.setText("Ciudad Sede");
		labelCiudad.setLayoutData(new GridData(200, 20));
		
		Label labelTipo = new Label(child, SWT.NONE);
		labelTipo.setText("Tipo Sede");
		labelTipo.setLayoutData(new GridData(200, 20));
	}

	/**
	 * Metodo que se encarga de crear los Text de la pantalla
	 * @param child
	 */
	private void createText(Group child) {
		textAno = new Text(child, SWT.BORDER);
		textAno.setText("YYYY");
		textAno.setLayoutData(new GridData(200, 18));
	}

	/**
	 * Metodo que se encarga de crear los botones de la pantalla
	 * @param child
	 */
	private void createButtons(Group child) {
		GridData gridDataBotonSave = new GridData();
		gridDataBotonSave.horizontalSpan = 2;
		gridDataBotonSave.widthHint = 420;
		
		buttonSave = new Button(child, SWT.NONE);
		buttonSave.setText("Nueva Sede");
		buttonSave.setBounds(posicionX, 250, 90, 20);
		buttonSave.setData("NEW");
		buttonSave.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				if ((comboBoxCiudades.getSelectionIndex() > -1) && (comboBoxTipoSede.getSelectionIndex() > -1)) {
					if(buttonSave.getData().toString().equalsIgnoreCase("NEW")){
						prepareInsertSede();
					}else{
						prepareUpdateSede();
					}
					cleanFormSede();
					ComponentTable.reloadTable(tableSede);
				}
			}
		});
		buttonSave.setLayoutData(gridDataBotonSave);
		
		final Button buttonClean = new Button(child, SWT.NONE);
		buttonClean.setText("Limpiar");
		buttonClean.setBounds(posicionX + 100, 250, 90, 20);
		buttonClean.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				cleanFormSede();
			}
		});
		buttonClean.setLayoutData(new GridData(200, 20));

	}

	/**
	 * Metodo que recoge los datos para crear una nueva sede
	 */
	private void prepareInsertSede() {
		SedeJJOO sedeJJOO = new SedeJJOO();
		if (!textAno.getText().equalsIgnoreCase("YYYY")) {
			sedeJJOO.setAno(Integer.valueOf(textAno.getText()));
		}
		sedeJJOO.setCiudad(
				(Ciudad) comboBoxCiudades.getData(comboBoxCiudades.getItem(comboBoxCiudades.getSelectionIndex())));
		sedeJJOO.setTipoJJOO(
				(TipoJJOO) comboBoxTipoSede.getData(comboBoxTipoSede.getItem(comboBoxTipoSede.getSelectionIndex())));
		sedeJJOODao.save(sedeJJOO);
	}

	/**
	 * Metodo que recoge los datos para su actualizacion
	 */
	private void prepareUpdateSede() {
		SedeJJOO sedeJJOO = (SedeJJOO) tableSede.getSelection()[0].getData();
		sedeJJOO.setCiudad(
				(Ciudad) comboBoxCiudades.getData(comboBoxCiudades.getItem(comboBoxCiudades.getSelectionIndex())));
		sedeJJOODao.update(sedeJJOO);
	}

	/**
	 * Metodo encargado de crear los comnos
	 * @param child Group donde se van a localizar los combos
	 */
	private void createComboBox(Group child){
		
		//comboBoxCiudades
		int indexSelected = -1;
		comboBoxCiudades = new Combo(child, SWT.DROP_DOWN);
		List<Ciudad> ciudades = (List<Ciudad>) ciudadDao.findAll();
		if(!ciudades.isEmpty()) {
			for (Ciudad ciudad : ciudades) {
				comboBoxCiudades.add(ciudad.getNombreCiudad());
				comboBoxCiudades.setData(ciudad.getNombreCiudad(), ciudad);
			}
		}
		comboBoxCiudades.select(indexSelected);
		comboBoxCiudades.setLayoutData(new GridData(200, 20));
		
		//tipo JJOO
		comboBoxTipoSede = new Combo(child, SWT.DROP_DOWN);
		List<TipoJJOO> tipoJJOOs = (List<TipoJJOO>) tipoJJOODao.findAll();
		if(!tipoJJOOs.isEmpty()) {
			for (TipoJJOO tipoJJOO : tipoJJOOs) {
				comboBoxTipoSede.add(tipoJJOO.getDescripcionTipo());
				comboBoxTipoSede.setData(tipoJJOO.getDescripcionTipo(), tipoJJOO);
			}
		}
		comboBoxTipoSede.select(indexSelected);
		comboBoxTipoSede.setLayoutData(new GridData(200, 20));

	}

	/**
	 * Metodo que se encarga de cargar los datos de modificar en sus respectivos huecos
	 */
	private void loadSedeUpdate(SedeJJOO sedeJJOO) {
		buttonSave.setData("UPDATE");
		buttonSave.setText("Modificar Sede");
		textAno.setText(String.valueOf(sedeJJOO.getAno()));
		comboBoxCiudades.select(comprobarPosicionComboSede(comboBoxCiudades, sedeJJOO));
		comboBoxTipoSede.select(comprobarPosicionComboTipoJJOO(comboBoxTipoSede, sedeJJOO));
		
		textAno.setEnabled(false);
		comboBoxTipoSede.setEnabled(false);

	}	
	
	/**
	 * Metodo que se encarga de limpiar los datos de nueva sede/modificar
	 */
	private void cleanFormSede() {
		buttonSave.setData("NEW");
		buttonSave.setText("Nueva Sede");
		textAno.setText("YYYY");
		comboBoxCiudades.select(0);
		comboBoxTipoSede.select(0);
		
		textAno.setEnabled(true);
		comboBoxTipoSede.setEnabled(true);
	}
	
	/**
	 * Metodo encargado de colocar en el combo de sedes el valor determinado para modificarlo.
	 * @param comboBox Combo de sedes
	 * @param sedeJJOO Sede que se quiere modificar
	 * @return
	 */
	private int comprobarPosicionComboSede(Combo comboBox, SedeJJOO sedeJJOO) {
		for (int i = 0; i < comboBox.getItems().length; i++) {
			Ciudad ciudad = (Ciudad) comboBox.getData(comboBox.getItem(i));
			if(ciudad.getIdCiudad() == sedeJJOO.getCiudad().getIdCiudad()){
				return i;
			}
		}
		return 0;
	}
	
	/**
	 * Metodo encargado de colocar en el combo de sedes el valor determinado para modificarlo.
	 * @param comboBox Combo de sedes
	 * @param sedeJJOO Sede que se quiere modificar
	 * @return
	 */
	private int comprobarPosicionComboTipoJJOO(Combo comboBox, SedeJJOO sedeJJOO) {
		for (int i = 0; i < comboBox.getItems().length; i++) {
			TipoJJOO tipoJJOO = (TipoJJOO) comboBox.getData(comboBox.getItem(i));
			if(tipoJJOO.getIdTipoJJOO() == sedeJJOO.getTipoJJOO().getIdTipoJJOO()){
				return i;
			}
		}
		return 0;
	}
	
	/**
	 * Metodo que crea el menú desplegable en la tabla de sedes
	 * @param group Group donde va a estar en Menu
	 */
	private void menuTableSede (Group group) {
		// Create context menu
		Menu menuTable = new Menu(group);
		tableSede.setMenu(menuTable);

		// Create menu item
		MenuItem menuUpdate = new MenuItem(menuTable, SWT.NONE);
		menuUpdate.setText("Update");
		menuUpdate.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				TableItem[] item = tableSede.getSelection();
				loadSedeUpdate((SedeJJOO)item[0].getData());
			}
		});
		
		
		MenuItem miDelete = new MenuItem(menuTable, SWT.NONE);
		miDelete.setText("Borrar");
		miDelete.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				TableItem[] item = tableSede.getSelection();
				SedeJJOO sedeToDelete = (SedeJJOO)item[0].getData();
				sedeJJOODao.deleteById((long) sedeToDelete.getAno());
				tableSede.remove(tableSede.getSelectionIndices());
			}
		});
	}
	
}
