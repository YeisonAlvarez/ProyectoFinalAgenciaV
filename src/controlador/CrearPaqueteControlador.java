package controlador;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;
import logica.Ciudad;
import logica.Clima;

import java.util.*;

import Persistencia.java.Negocio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javafx.stage.FileChooser;
import javafx.scene.image.Image;
import javafx.util.*;
import Persistencia.*;

public class CrearPaqueteControlador {
	
	@FXML
    private AnchorPane anchorPane;
    
    @FXML
    private Button inicio_btn;
    
    @FXML
    private Button guardar_btn;

    @FXML
    private TextField nombre_paquete;
    @FXML
    private TextField duracion;
    @FXML
    private TextField precio;
    @FXML
    private TextField cupo_maximo;
    @FXML
    private DatePicker fechaInicio_date;
    @FXML
    private DatePicker fechaFin_date;
    @FXML
    private TextField servicios_adicionales;

    @FXML
    private Label nombrePaqueteLabel;

    @FXML
    private Label serviciosAdicionalesLabel;
    private static Negocio miNegocio;
    
    @FXML
    private void initialize() {
    	
    	miNegocio = Negocio.miNegocio;
    }

    @FXML
    private void crearPaquete() throws FileNotFoundException, ClassNotFoundException, IOException {
    	int precioTS = Integer.parseInt(precio.getText());
    	int cupo = Integer.parseInt(cupo_maximo.getText());
    	Date fecha_Ini = null;
    	Date fecha_Fin = null;
		try {
			fecha_Ini = new SimpleDateFormat("yyyy-MM-DD").parse(fechaInicio_date.getValue().toString());
			fecha_Fin =new SimpleDateFormat("yyyy-MM-DD").parse(fechaFin_date.getValue().toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
        if(!nombre_paquete.getText().equals("")||!duracion.getText().equals("")||!servicios_adicionales.equals("")||precioTS>0 || cupo>0) {
        	boolean isRegistrado = miNegocio.crearPaquete(nombre_paquete.getText(),duracion.getText() ,servicios_adicionales.getText(), precioTS, cupo, fecha_Ini, fecha_Fin);

			if (isRegistrado == true) {
				Alert a = new Alert(AlertType.CONFIRMATION);
				a.setTitle("Confirmación");
				a.setContentText("Paquete registrado correctamente");
				a.showAndWait();

			} else {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Error");
				a.setContentText("No se pudo registrar el Paquete");
				a.showAndWait();
			}
        }
    }

    @FXML
    private void volverInicio() {
    	Stage Stagep = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/vista/InicioView.fxml"));

		Parent p;
		try {
			p = loader.load();
			Scene s = new Scene(p, 1400, 700);

			Stagep.setScene(s);
			Stagep.show();
			cerrar();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }


	private void cerrar() {
		// TODO Auto-generated method stub
		Stage stageCerrar = (Stage) inicio_btn.getScene().getWindow();
		stageCerrar.hide();

	}
	
}
