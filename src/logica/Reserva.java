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
public class Reserva {

	private Date fecha_solicitud;
	private Date fecha_planificado_viaje;
	private Cliente cliente_reserva;
	private int cantidad_personas;
	private PaqueteTuristico miPaquete;
	private GuiaTuristico mi_guia;
	private EstadoReserva estado_reserva;

}
