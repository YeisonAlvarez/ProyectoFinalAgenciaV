package controlador;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Persistencia.java.Negocio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import logica.Ciudad;
import logica.Cliente;
import logica.Clima;
import logica.Destino;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListarDestinosControlador {

	@FXML
	private Label nombreAdmiLogin_lbl;
	@FXML
	private TextField busquedaDestino_field;

	// Buttons
	@FXML
	private Button buscarDestino_btn;
	@FXML
	private Button actualizarDestino_btn;
	@FXML
	private Button eliminarDestino_btn;
	@FXML
	private Button inicio_btn;
	@FXML
	private Button cerrarSesion_btn;

	// ComboBoxs
	@FXML
	private ComboBox<Ciudad> ciudad_combo;

	// Tablas y columns de la tabla
	@FXML
	private TableView<Destino> destinos_table;
	@FXML
	private TableColumn<Destino, String> nombre_column;
	@FXML
	private TableColumn<Destino, Ciudad> ciudad_column;
	@FXML
	private TableColumn<Destino, Clima> clima_colum;
	@FXML
	private TableColumn<Destino, String> descripcion_column;
	@FXML
	private TableColumn<Destino, List<File>> imagenes_column;

	// Gestion de listas destino
	private ArrayList<Destino> listDestinosExistentes;
	private ObservableList<Destino> listaDestinosObservable;
	private ArrayList<Destino> listDestinosFiltros = new ArrayList<Destino>();

	private static Negocio miNegocio;

	@FXML
	public void initialize() {

		miNegocio = Negocio.miNegocio;

		ciudad_combo.setItems(miNegocio.llenarComboCiudad());

//		nombreAdmiLogin_lbl.setText(miNegocio.getNombreAdminLogeado());

		listDestinosExistentes = miNegocio.listarDestinosExistentes();

		if (listDestinosExistentes != null) {
			llenarTableView(listDestinosExistentes);// Se deben de guardas los destinos en un Observable list

		} else {
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("Error en Lectura de Destinos");
			a.setContentText("No existen destinos creados");
			a.showAndWait();
		}

	}

	// -------Inicia eventos botones-----

	private void llenarTableView(ArrayList<Destino> listDestinos) {

		this.nombre_column.setCellValueFactory(new PropertyValueFactory<>("nombre_destino"));
		this.ciudad_column.setCellValueFactory(new PropertyValueFactory<>("ciudad_destino"));
		this.clima_colum.setCellValueFactory(new PropertyValueFactory<>("clima"));
		this.descripcion_column.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
//		imagenes_column.setCellValueFactory(new PropertyValueFactory<>("telefono"));

		listaDestinosObservable = FXCollections.observableArrayList(listDestinos);
		this.destinos_table.setItems(listaDestinosObservable);

	}

	@FXML
	public void buscarDestino(ActionEvent e) throws Exception {
		System.out.println(ciudad_combo.getValue());

		if (listDestinosExistentes != null) {

			// Diferentes combinaciones de los filtros
			if (busquedaDestino_field.getText() != null && !busquedaDestino_field.getText().equals("")
					&& ciudad_combo.getValue() != null) {

				for (Destino destino : listDestinosExistentes) {
					if (destino.getNombre_destino().equalsIgnoreCase(busquedaDestino_field.getText())
							&& destino.getCiudad_destino().equals(ciudad_combo.getValue().toString())) {
						listDestinosFiltros.add(destino);
					}
				}

				llenarTableView(listDestinosFiltros);

			}
			// Filtro nombre y destinos que contengan el nombre
			if (busquedaDestino_field.getText() != null && !busquedaDestino_field.getText().equals("")
					&& ciudad_combo.getValue() == null) {

				for (Destino destino : listDestinosExistentes) {
					if (destino.getNombre_destino().equalsIgnoreCase(busquedaDestino_field.getText())
							|| destino.getNombre_destino().contains(busquedaDestino_field.getText())) {
						listDestinosFiltros.add(destino);
					}
				}

				llenarTableView(listDestinosFiltros);

			}
			//Filtro por ciudad
			if (busquedaDestino_field.getText() == null && busquedaDestino_field.getText().equals("")
					&& ciudad_combo.getValue() != null) {

				for (Destino destino : listDestinosExistentes) {
					if (destino.getCiudad_destino().equals(ciudad_combo.getValue().toString())) {
						listDestinosFiltros.add(destino);
					}
				}

				llenarTableView(listDestinosFiltros);

			}

		} else {
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("Error en Lectura de Destinos");
			a.setContentText("No existen destinos creados");
			a.showAndWait();
		}

	}

	@FXML
	public void actualizarDestino(ActionEvent e) throws Exception {
		
		Destino destinoSelecionado = destinos_table.getSelectionModel().getSelectedItem();
		miNegocio.setDestinoActualizar(destinoSelecionado);
		
		Stage Stagep = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/vista/ActualizarDestinoView.fxml"));

		Parent p = loader.load();
		Scene s = new Scene(p, 1400, 700);

		Stagep.setScene(s);
		Stagep.show();
		cerrar();

	}

	@FXML
	public void eliminarDestino(ActionEvent e) throws Exception {

	}

	@FXML
	public void volverInicio(ActionEvent e) throws Exception {

		Stage Stagep = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/vista/InicioView.fxml"));

		Parent p = loader.load();
		Scene s = new Scene(p, 1400, 700);

		Stagep.setScene(s);
		Stagep.show();
		cerrar();

	}

	private void cerrar() {
		// TODO Auto-generated method stub
		Stage stageCerrar = (Stage) inicio_btn.getScene().getWindow();
		stageCerrar.hide();

	}

	@FXML
	public void cerrarSesion(ActionEvent e) throws Exception {

	}

}
