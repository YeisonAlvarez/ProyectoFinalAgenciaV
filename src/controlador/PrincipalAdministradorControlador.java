package controlador;

import java.io.File;
import java.io.IOException;
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

public class PrincipalAdministradorControlador {

	@FXML
	private Label nombreAdmiLogin_lbl;
	@FXML
	private TextField busquedaDestino_field;

	// Buttons
	@FXML
	private Button crearDestino_btn;
	@FXML
	private Button listarDestinos_btn;
	@FXML
	private Button crearGuia_btn;
	@FXML
	private Button listarGuias_btn;
	@FXML
	private Button crearPaquete_btn;
	@FXML
	private Button listarPaquetes_btn;
	@FXML
	private Button verEstadisticas_btn;
	@FXML
	private Button inicio_btn;
	@FXML
	private Button cerrarSesion_btn;

	private static Negocio miNegocio;

	@FXML
	public void initialize() {

		miNegocio = Negocio.miNegocio;
		nombreAdmiLogin_lbl.setText(miNegocio.getNombreadministradorLogeado());

	}

	// -------Inicia eventos botones-----

	@FXML
	public void crearDestino(ActionEvent e) throws Exception {

		Stage Stagep = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/vista/CrearDestinoView.fxml"));

		Parent p = loader.load();
		Scene s = new Scene(p, 1400, 700);

		Stagep.setScene(s);
		Stagep.show();
		cerrar();
	}

	@FXML
	public void listarDestinos(ActionEvent e) throws Exception {

		Stage Stagep = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/vista/ListarDestinosView.fxml"));

		Parent p = loader.load();
		Scene s = new Scene(p, 1400, 700);

		Stagep.setScene(s);
		Stagep.show();
		cerrar();
	}

	@FXML
	public void crearGuia(ActionEvent e) throws Exception {

	}

	@FXML
	public void listarGuias(ActionEvent e) throws Exception {

	}

	@FXML
	public void crearPaquete(ActionEvent e) throws Exception {

		Stage Stagep = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/vista/CrearPaqueteView.fxml"));

		Parent p = loader.load();
		Scene s = new Scene(p, 1400, 700);

		Stagep.setScene(s);
		Stagep.show();
		cerrar();
	}

	@FXML
	public void listarPaquetes(ActionEvent e) throws Exception {

	}

	@FXML
	public void verEstadisticas(ActionEvent e) throws Exception {

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
		miNegocio.setAdministradorLogeado(null);
		miNegocio.setNombreadministradorLogeado(null);
		redireccionarLogin();
	}
	
	private void redireccionarLogin() throws IOException {
		// TODO Auto-generated method stub
		
		Stage Stagep = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/vista/InicioView.fxml"));

		Parent p = loader.load();
		Scene s = new Scene(p,1400, 700);

		Stagep.setScene(s);
		Stagep.show();
		cerrar();
		
	}

}
