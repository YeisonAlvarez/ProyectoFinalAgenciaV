package logica;

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
public class PaqueteTuristico {

	private String nombre;
	private String duracion;
	private String servicios_adicionales;
	private double precio;
	private int cupo_maximo;
	private Date fecha_dis_inicio;
	private Date fecha_dis_fin;

}
