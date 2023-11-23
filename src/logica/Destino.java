package logica;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Destino implements Serializable {

	private String nombre_destino;
	private Ciudad ciudad_destino;
	private String descripcion;
	List<File> listImagenes;
	private Clima clima;

}
