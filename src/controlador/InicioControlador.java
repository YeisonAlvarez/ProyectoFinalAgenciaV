package controlador;

import Persistencia.java.Negocio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InicioControlador {

	@FXML
	private Button login_btn;
	@FXML
	private Button registro_btn;
	@FXML
	private Button destinos_btn;
	@FXML
	private Button paquetes_btn;

	private static Negocio miNegocio;

	@FXML
	public void initialize() {
		miNegocio = Negocio.miNegocio;
	}

	@FXML
	public void redireccionarLogin(ActionEvent e) throws Exception {

		// Valida si el usuario esta logeado ingresa a su perfil, de lo contrario si
		// loguea
		if (miNegocio.getClienteLogeado() != null) {

			Stage Stagep = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/vista/PrincipalClienteView.fxml"));

			Parent p = loader.load();
			Scene s = new Scene(p, 1400, 700);

			Stagep.setScene(s);
			Stagep.show();
			cerrar();
		} else {

			//Lo mismo con elAdministrador
			if (miNegocio.getAdministradorLogeado() != null) {

				Stage Stagep = new Stage();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/vista/PrincipalAdministradorView.fxml"));

				Parent p = loader.load();
				Scene s = new Scene(p, 1400, 700);

				Stagep.setScene(s);
				Stagep.show();
				cerrar();
			} else {

				Stage Stagep = new Stage();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/vista/LoginView.fxml"));

				Parent p = loader.load();
				Scene s = new Scene(p, 1400, 700);

				Stagep.setScene(s);
				Stagep.show();
				cerrar();
			}

		}

	}

	@FXML
	public void redireccionarRegistro(ActionEvent e) throws Exception {

		Stage Stagep = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/vista/RegistrarClienteView.fxml"));

		Parent p = loader.load();
		Scene s = new Scene(p, 1400, 700);

		Stagep.setScene(s);
		Stagep.show();
		cerrar();

	}

	@FXML
	public void verPaquetes(ActionEvent e) throws Exception {

		Stage Stagep = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/vista/CrearDestinoControlador.fxml"));

		Parent p = loader.load();
		Scene s = new Scene(p, 1000, 800);

		Stagep.setScene(s);
		Stagep.show();
		cerrar();

	}

	@FXML
	public void verDestinos(ActionEvent e) throws Exception {

		Stage Stagep = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/vista/CrearDestinoControlador.fxml"));

		Parent p = loader.load();
		Scene s = new Scene(p, 1400, 700);

		Stagep.setScene(s);
		Stagep.show();
		cerrar();

	}

	private void cerrar() {
		// TODO Auto-generated method stub
		Stage stageCerrar = (Stage) login_btn.getScene().getWindow();
		stageCerrar.hide();

	}
}
