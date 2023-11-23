package controlador;

import Persistencia.java.Negocio;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EliminarClienteControlador {

	@FXML
	private Label identificacion_lbl;
	
	@FXML
	private TextField identificacion_field;
	
	@FXML
	private Button eliminar_btn;
	
	
	@FXML
	public void eliminarCliente() {
		
		identificacion_field.setText("Te amo");
		
	}
	//Patron Singleton
	private static Negocio miNegocio;
	
	@FXML
	public void initialize() {
		miNegocio = Negocio.miNegocio;
	}
}
