package logica;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Cliente implements Serializable{

	private String identificacion;
	private String primer_nombre;
	private String segundo_nombre;
	private String primer_apellido;
	private String segundo_apellido;
	private String email;
	private String telefono;
	private String direccion_de_residencia;
	private String password;
	
	
	
}
