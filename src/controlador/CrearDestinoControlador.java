package controlador;

import Persistencia.java.Negocio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;
import logica.Ciudad;
import logica.Clima;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javafx.stage.FileChooser;
import javafx.scene.image.Image;

public class CrearDestinoControlador {

	// Texts Fields
	@FXML
	private TextField nombre_destino_field;
	@FXML
	private TextField descripcion_field;
	// Combobox
	@FXML
	private ComboBox<Ciudad> ciudad_combo;

	// Radio Buttons
	@FXML
	private RadioButton climaTrop_radiobtn;
	@FXML
	private RadioButton climaTempl_radiobtn;
	@FXML
	private RadioButton climaSeco_radiobtn;

	// Buttons
	@FXML
	private Button crear_btn;
	@FXML
	private Button inicio_btn;
	@FXML
	private Button agregar_img_btn;
	@FXML
	private Button remover_img_btn;
	@FXML
	private Button atras_btn;
	@FXML
	private Button adelante_btn;

	@FXML
	// Image View
	private ImageView image_carga_view;
	// List of Images
	List<File> images = new ArrayList<>();
	private int imgPosition, imgTotal;

	// File Chooser
	final FileChooser fileChooser = new FileChooser();

	// Patron singleton
	private static Negocio miNegocio;
	// variable de trabajo
	private Clima climaDestino;

	@FXML
	public void initialize() {
		miNegocio = Negocio.miNegocio;
		ciudad_combo.setItems(miNegocio.llenarComboCiudad());
	}

	@FXML
	public void crearDestino(ActionEvent e) throws Exception {

		String nombreDestino = nombre_destino_field.getText();
		Ciudad ciudadDestino = ciudad_combo.getValue();
		String descripcion = descripcion_field.getText();

		if (images.isEmpty() || images == null) {
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("Error");
			a.setContentText("Debe cargar como mínimo una imagen");
			a.showAndWait();
		} else {
			if (nombreDestino != null && !nombreDestino.equals("") && !nombreDestino.isEmpty() && ciudadDestino != null
					&& !ciudadDestino.equals("") && descripcion != null && !descripcion.equals("")
					&& !descripcion.isEmpty() && images != null && !images.equals("") && !images.isEmpty()
					&& climaDestino != null) {

				boolean isRegistrado = miNegocio.crearDestino(nombreDestino, ciudadDestino, descripcion, images,
						climaDestino);

				if (isRegistrado == true) {
					Alert a = new Alert(AlertType.CONFIRMATION);
					a.setTitle("Confirmación");
					a.setContentText("Destino registrado correctamente");
					a.showAndWait();

				} else {
					Alert a = new Alert(AlertType.ERROR);
					a.setTitle("Error");
					a.setContentText("No se pudo registrar el Destino");
					a.showAndWait();
				}

			} else {

				Alert a = new Alert(AlertType.WARNING);
				a.setTitle("Advertencia");
				a.setContentText("Diligencie y/o seleccione todos los campos");
				a.showAndWait();

			}
		}

	}

	// ---Gestión de las imagenes-------------
	@FXML
	public void agregarImagen(ActionEvent event) throws IOException {

		try {
			// Get Image URL

			Stage Stagep = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/vista/CrearDestinoView.fxml"));

			Parent p = loader.load();
			Scene s = new Scene(p, 1400, 700);

			Stagep.setScene(s);

			Window stage = Stagep;

			File file = fileChooser.showOpenDialog(stage);

			if (file.isFile() && (file.getName().contains(".jpg") || file.getName().contains(".png")
					|| file.getName().contains(".bmp") || file.getName().contains(".gif")
					|| file.getName().contains(".jpeg"))) {

				images.add(file);
				imgTotal = images.size();

				if (imgTotal > 1) {
					imgPosition++;
				}

				// Loading the Image in Memory
				// convirtiendolo a URI primero nos seguramos que no habra error al pasarlo a
				// URL
				String thumbURL = file.toURI().toURL().toString();
				Image imgLoad = new Image(thumbURL);

				// Pass Image to ImageView
				image_carga_view.setImage(imgLoad);
			} else {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Error Leyendo imagen");
				a.setContentText("Formato no Compatible.Use jpg,png,bmp o gif");
				a.showAndWait();

			}

		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	public void removerImagen(ActionEvent event) {

		try {
			if (imgTotal == 0) {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Error");
				a.setContentText("No hay imagenes que borrar");
				a.showAndWait();
				return;
			}

			if (imgTotal > 1) {

				images.remove(imgPosition);

				if (imgPosition == imgTotal - 1) {
					imgPosition--;
				}

				imgTotal = images.size();

				// Loading the Image in Memory
				String thumbURL3 = images.get(imgPosition).toURI().toURL().toString();
				Image imgLoad = new Image(thumbURL3);
				image_carga_view.setImage(imgLoad);

			} else {

				images.remove(imgPosition);
				imgPosition = 0;
				imgTotal = images.size();
				image_carga_view.setImage(null);

			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void siguienteImagen(ActionEvent event) {

		if (imgPosition < imgTotal - 1) {

			try {

				imgPosition++;

				// loading the Image in Memory
				String thumbURL1 = images.get(imgPosition).toURI().toURL().toString();
				Image imgLoad = new Image(thumbURL1);
				image_carga_view.setImage(imgLoad);

			} catch (MalformedURLException m) {
				m.printStackTrace();
			}
		}
	}

	@FXML
	public void atrasImagen(ActionEvent event) {

		if (imgPosition > 0) {

			try {
				imgPosition--;
				// loading the Image in Memory
				String thumbURL2 = images.get(imgPosition).toURI().toURL().toString();
				Image imgLoad = new Image(thumbURL2);
				image_carga_view.setImage(imgLoad);

			} catch (MalformedURLException m) {
				m.printStackTrace();
			}
		}
	}

	@FXML
	public void clickTemplado(ActionEvent e) throws Exception {

		climaDestino = Clima.Templado;
		climaSeco_radiobtn.setSelected(false);
		climaTrop_radiobtn.setSelected(false);
	}

	@FXML
	public void clickTropical(ActionEvent e) throws Exception {
		climaDestino = Clima.Tropical;
		climaSeco_radiobtn.setSelected(false);
		climaTempl_radiobtn.setSelected(false);
	}

	@FXML
	public void clickSeco(ActionEvent e) throws Exception {
		climaDestino = Clima.Seco;
		climaTempl_radiobtn.setSelected(false);
		climaTrop_radiobtn.setSelected(false);
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
}
