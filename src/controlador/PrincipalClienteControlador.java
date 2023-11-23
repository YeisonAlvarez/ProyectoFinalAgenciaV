package controlador;

import java.io.IOException;

import Persistencia.java.Negocio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logica.Ciudad;
import logica.GuiaTuristico;
import logica.Reserva;

public class PrincipalClienteControlador {

	private static Negocio miNegocio;

	// Labels
	@FXML
	private Label nombreUsuarioLogin_lbl;

	// Text Fields
	@FXML
	private TextField busquedaDestino_field;

	// Dates
	@FXML
	private DatePicker fechaInicio_date;
	@FXML
	private DatePicker fechaFin_date;

	// ComboBoxs
	@FXML
	private ComboBox<Ciudad> ciudad_combo;

	// Sliders
	@FXML
	private Slider precio_slider;

	// RadioButtons
	@FXML
	private RadioButton climaTrop_radiobtn;
	@FXML
	private RadioButton climaTempl_radiobtn;
	@FXML
	private RadioButton climaSeco_radiobtn;

	// Buttons
	@FXML
	private Button buscarPaquetes_btn;
	@FXML
	private Button buscarReservasPasadas_btn;
	@FXML
	private Button buscarReservasFururas_btn;
	@FXML
	private Button cancelarReserva_btn;
	@FXML
	private Button verDetallePaquete_btn;
	@FXML
	private Button guardarCalificacionGuia_btn;
	@FXML
	private Button cerrarSesion_btn;
	@FXML
	private Button actualizarPerfil_btn;
	@FXML
	private Button inicio_btn;

	// Tablas
	@FXML
	private TableView<Reserva> reservas_tabla;
	@FXML
	private TableView<GuiaTuristico> guias_tabla;

	// ----------Inician Actions Buttons---------------
	@FXML
	public void initialize() {
		miNegocio = Negocio.miNegocio;
		nombreUsuarioLogin_lbl.setText(miNegocio.getNombreClienteLogeado());
		ciudad_combo.setItems(miNegocio.llenarComboCiudad());
	}

	@FXML
	public void cerrarSesion(ActionEvent e) throws Exception {

		miNegocio.setClienteLogeado(null);
		miNegocio.setNombreClienteLogeado(null);
		redireccionarPrincipalCliente();
	}

	@FXML
	public void actualizarPerfil(ActionEvent e) throws Exception {
		
		Stage Stagep = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/vista/ActualizarDatosCliente.fxml"));

		Parent p = loader.load();
		Scene s = new Scene(p,1400,700);

		Stagep.setScene(s);
		Stagep.show();
		cerrar();
	}

	@FXML
	public void buscarPaquetes(ActionEvent e) throws Exception {

	}

	@FXML
	public void clickTemplado(ActionEvent e) throws Exception {

	}

	@FXML
	public void clickTropical(ActionEvent e) throws Exception {

	}

	@FXML
	public void clickSeco(ActionEvent e) throws Exception {

	}

	@FXML
	public void buscarReservasPasadas(ActionEvent e) throws Exception {

	}

	@FXML
	public void buscarReservasFuturas(ActionEvent e) throws Exception {

	}

	@FXML
	public void cancelarReserva(ActionEvent e) throws Exception {

	}

	@FXML
	public void verDetallePaquete(ActionEvent e) throws Exception {

	}

	@FXML
	public void guardarCalificacionGuia(ActionEvent e) throws Exception {

	}

	@FXML
	public void dar1Estrella(ActionEvent e) throws Exception {

	}

	@FXML
	public void dar2Estrellas(ActionEvent e) throws Exception {

	}

	@FXML
	public void dar3Estrellas(ActionEvent e) throws Exception {

	}

	@FXML
	public void dar4Estrellas(ActionEvent e) throws Exception {

	}

	@FXML
	public void dar5Estrellas(ActionEvent e) throws Exception {

	}
	
	//Redirecion entre pantallas
	
	private void redireccionarPrincipalCliente() throws IOException {
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
	@FXML
	public void volverInicio(ActionEvent e) throws Exception {

		Stage Stagep = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/vista/InicioView.fxml"));

		Parent p = loader.load();
		Scene s = new Scene(p,1400, 700);

		Stagep.setScene(s);
		Stagep.show();
		cerrar();
	}
	private void cerrar() {
		// TODO Auto-generated method stub
		Stage stageCerrar = (Stage) cerrarSesion_btn.getScene().getWindow();
		stageCerrar.hide();

	}

}
