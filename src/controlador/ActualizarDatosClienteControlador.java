package controlador;

import java.io.FileNotFoundException;
import java.io.IOException;

import Persistencia.java.Negocio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import logica.Cliente;

public class ActualizarDatosClienteControlador {

	// Texts Fields
	@FXML
	private TextField primerNombre_field;
	@FXML
	private TextField segundoNombre_field;
	@FXML
	private TextField primerApellido_field;
	@FXML
	private TextField segundoApellido_field;
	@FXML
	private TextField identificacion_field;
	@FXML
	private TextField email_field;
	@FXML
	private TextField telefono_field;
	@FXML
	private TextField direccion_field;
	@FXML
	private TextField password_field;
	@FXML
	private TextField passwordConfirmar_field;

	// Buttons
	@FXML
	private Button actualizar_btn;
	@FXML
	private Button inicio_btn;

	private static Negocio miNegocio;

	public ActualizarDatosClienteControlador() {
	}

	@FXML
	public void initialize() throws FileNotFoundException, ClassNotFoundException, IOException {

		miNegocio = Negocio.miNegocio;

		Cliente clientePerfil = miNegocio.getClienteLogeado();
		primerNombre_field.setText(clientePerfil.getPrimer_nombre());
		segundoNombre_field.setText(clientePerfil.getSegundo_nombre());
		primerApellido_field.setText(clientePerfil.getPrimer_apellido());
		segundoApellido_field.setText(clientePerfil.getSegundo_apellido());
		identificacion_field.setText(clientePerfil.getIdentificacion());
		email_field.setText(clientePerfil.getEmail());
		telefono_field.setText(clientePerfil.getTelefono());
		direccion_field.setText(clientePerfil.getDireccion_de_residencia());
		password_field.setText(clientePerfil.getPassword());
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
	public void actualizarCliente(ActionEvent e) throws Exception {

		String primerNombre = primerNombre_field.getText();
		String segundoNombre = segundoNombre_field.getText();
		String primerApellido = primerApellido_field.getText();
		String segundoApellido = segundoApellido_field.getText();
		String identificacion = identificacion_field.getText();
		String email = email_field.getText();
		String telefono = telefono_field.getText();
		String direccion = direccion_field.getText();
		String password = password_field.getText();
		String passwordConfirmar = passwordConfirmar_field.getText();

		if (primerNombre != null && !primerNombre.equals("") && !primerNombre.isEmpty() && primerApellido != null
				&& !primerApellido.equals("") && !primerApellido.isEmpty() && segundoApellido != null
				&& !segundoApellido.equals("") && !segundoApellido.isEmpty() && identificacion != null
				&& !identificacion.equals("") && !identificacion.isEmpty() && email != null && !email.equals("")
				&& !email.isEmpty() && telefono != null && !telefono.equals("") && !telefono.isEmpty()
				&& direccion != null && !direccion.equals("") && !direccion.isEmpty() && password != null
				&& !password.equals("") && !password.isEmpty() && passwordConfirmar != null
				&& !passwordConfirmar.equals("") && !passwordConfirmar.isEmpty()) {

			if (passwordConfirmar.equals(password)) {
				boolean isActualizado = miNegocio.actualizarCliente(primerNombre, segundoNombre, primerApellido,
						segundoApellido, identificacion, telefono, email, direccion, passwordConfirmar);

				if (isActualizado == true) {
					Alert a = new Alert(AlertType.CONFIRMATION);
					a.setTitle("Confirmación");
					a.setContentText("Cliente actualizado correctamente");
					a.showAndWait();

				} else {
					Alert a = new Alert(AlertType.ERROR);
					a.setTitle("Error");
					a.setContentText("No se pudo actualizar el cliente");
					a.showAndWait();
				}

			} else {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Error");
				a.setContentText("Las contraseñas no coinciden");
				a.showAndWait();
			}

		} else {

			Alert a = new Alert(AlertType.WARNING);
			a.setTitle("Advertencia");
			a.setContentText("Diligencie todos los campos");
			a.showAndWait();

		}

	}
}
