package controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

import Persistencia.java.Negocio;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import logica.Administrador;
import logica.Cliente;

public class LoginControlador {

	// Buttons
	@FXML
	private Button login_btn;
	@FXML
	private Button inicio_btn;
	@FXML
	private Button recuperarPassword_btn;
	@FXML
	private Button recordarPassword_btn;
	@FXML
	private CheckBox administrador_btn;

	// Texts Fields
	@FXML
	private TextField usuario_field;
	@FXML
	private TextField password_field;

	private boolean isAdministrador = false;

	private static Negocio miNegocio;

	@FXML
	public void initialize() {
		miNegocio = Negocio.miNegocio;
	}

	public LoginControlador() {

	}

	// Buttons Actions
	@FXML
	public void isAdministrador(ActionEvent e) throws Exception {
		this.isAdministrador = true;

	}

	@FXML
	public void loguearUsuario(ActionEvent e) throws Exception {

		String usuario = usuario_field.getText();
		String password = password_field.getText();

		boolean ingresar = false;

		// Validacion ingreso de datos
		if (usuario != null && !usuario.equals("") && !usuario.isEmpty() && password != null && !password.equals("")
				&& !password.isEmpty()) {

			if (this.isAdministrador == true) {

				ingresar = miNegocio.autenticarSesion(usuario, password, isAdministrador);

				if (ingresar) {

					redireccionarPrincipalAdministrador();

				}else {

					Alert a = new Alert(AlertType.WARNING);
					a.setTitle("Error");
					a.setContentText("Credenciales incorrectas");
					a.showAndWait();
				}

			} else {

				ingresar = miNegocio.autenticarSesion(usuario, password, isAdministrador);

				if (ingresar) {

					redireccionarPrincipalCliente();

				}else {

					Alert a = new Alert(AlertType.WARNING);
					a.setTitle("Error");
					a.setContentText("Credenciales incorrectas");
					a.showAndWait();
				}

			}

		} else {

			Alert a = new Alert(AlertType.WARNING);
			a.setTitle("Advertencia");
			a.setContentText("Diligencie todos los campos");
			a.showAndWait();
		}

	}

	private void redireccionarPrincipalAdministrador() throws IOException {
		Stage Stagep = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/vista/PrincipalAdministradorView.fxml"));

		Parent p = loader.load();
		Scene s = new Scene(p, 1400, 700);

		Stagep.setScene(s);
		Stagep.show();
		cerrar();

	}

	private void redireccionarPrincipalCliente() throws IOException {
		// TODO Auto-generated method stub

		Stage Stagep = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/vista/PrincipalClienteView.fxml"));

		Parent p = loader.load();
		Scene s = new Scene(p, 1400, 700);

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
		Scene s = new Scene(p, 1400, 700);

		Stagep.setScene(s);
		Stagep.show();
		cerrar();
	}

	@FXML
	public void recordarPassword(ActionEvent e) throws Exception {

	}

	@FXML
	public void recuperarPassword(ActionEvent e) throws Exception {

	}

	private void cerrar() {
		// TODO Auto-generated method stub
		Stage stageCerrar = (Stage) login_btn.getScene().getWindow();
		stageCerrar.hide();

	}

}
