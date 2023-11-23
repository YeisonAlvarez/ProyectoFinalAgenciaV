package Persistencia.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import logica.Administrador;
import logica.Ciudad;
import logica.Cliente;
import logica.Clima;
import logica.Destino;
import logica.PaqueteTuristico;

public class Negocio {

	public static Negocio miNegocio = instanciar();

	private Cliente miCliente;
	private Destino miDestino;
	private Ciudad miCiudad;
	private PaqueteTuristico miPaqueteTuristico;
	private Cliente clienteLogeado;
	private Administrador administradorLogeado;
	private String nombreClienteLogeado;
	private String nombreadministradorLogeado;
	private Destino destinoActualizar;

	private ArrayList<Cliente> listClientesExistentes = new ArrayList<Cliente>();
	private ArrayList<Destino> listDestinosExistentes = new ArrayList<Destino>();
	private ArrayList<Administrador> listAdministradores = new ArrayList<Administrador>();
	private ArrayList<PaqueteTuristico> listaPaquetesTurisiticos = new ArrayList<>();

	// Patron singleton
	public static Negocio instanciar() {

		if (miNegocio == null) {
			miNegocio = new Negocio();
		}

		return miNegocio;
	}

	// --------Metodos que manejan la logica de la interfaz------------

	public boolean autenticarSesion(String email, String password, boolean isAdministrador) {

		// Es administrador
		if (isAdministrador) {

			// Esta logica se usuo y se usa para llenar la lista de Administradores, solo se
			// uso una vez para llenar el archivo, si se desea cambiar o agregar mas admi se
			// debe descomentar
//			listAdministradores.add(new Administrador("Admin1", "12345"));
//			listAdministradores.add(new Administrador("Admin2", "12345"));
//			listAdministradores.add(new Administrador("Admin3", "12345"));
//
//			serializarAdministradores(listAdministradores);

			// Esta es la logica normal de siempre
			listAdministradores = deserializarAdministradores();
			for (Administrador administrador : listAdministradores) {
				if (administrador.getUser().equals(email) && administrador.getPassword().equals(password)) {
					administradorLogeado = administrador;
					nombreadministradorLogeado = administrador.getUser();
					return true;
				}
			}

		} else {// Es cliente

			listClientesExistentes = deserializarClientes();
			for (Cliente cliente : listClientesExistentes) {
				if (cliente.getEmail().equals(email) && cliente.getPassword().equals(password)) {
					clienteLogeado = cliente;
					nombreClienteLogeado = cliente.getPrimer_nombre() + " " + cliente.getPrimer_apellido();
					return true;
				}
			}
		}

		return false;
	}

	public boolean registrarCliente(String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido, String identificacion, String telefono, String email, String direccion,
			String password) throws FileNotFoundException, IOException, ClassNotFoundException {

		miCliente = new Cliente(identificacion, primerNombre, segundoNombre, primerApellido, segundoApellido, email,
				telefono, direccion, password);
		listClientesExistentes.add(miCliente);
		serializarClientes(listClientesExistentes);

		return true;
	}

	public boolean crearDestino(String nombreDestino, Ciudad ciudad, String descripcion, List<File> images,
			Clima climaDestino) throws FileNotFoundException, IOException, ClassNotFoundException {

		miDestino = new Destino(nombreDestino, ciudad, descripcion, images, climaDestino);
		listDestinosExistentes.add(miDestino);
		serializarDestinos(listDestinosExistentes);

		System.out.println("Destino registrado");
		return true;
	}

	public Cliente leerCliente(String identificacionBuscada)
			throws FileNotFoundException, IOException, ClassNotFoundException {

		Cliente miClienteBuscado = null;
		listClientesExistentes = deserializarClientes();

		for (Cliente cliente : listClientesExistentes) {
			if (cliente.getIdentificacion().equals(identificacionBuscada)) {
				miClienteBuscado = cliente;
			}
		}
		return miClienteBuscado;

	}

	public boolean actualizarCliente(String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido, String identificacion, String telefono, String email, String direccion,
			String passwordConfirmar) {

		boolean isActualizado = false;
		listClientesExistentes = deserializarClientes();

		for (int i = 0; i < listClientesExistentes.size(); i++) {

			if (listClientesExistentes.get(i).getIdentificacion().equals(identificacion)) {

				listClientesExistentes.get(i).setPrimer_nombre(primerNombre);
				listClientesExistentes.get(i).setSegundo_nombre(segundoNombre);
				listClientesExistentes.get(i).setPrimer_apellido(primerApellido);
				listClientesExistentes.get(i).setSegundo_apellido(segundoApellido);
				listClientesExistentes.get(i).setEmail(email);
				listClientesExistentes.get(i).setDireccion_de_residencia(direccion);
				listClientesExistentes.get(i).setPassword(passwordConfirmar);
				listClientesExistentes.get(i).setTelefono(telefono);
				isActualizado = true;
				clienteLogeado = listClientesExistentes.get(i); // Tambien se actualiza en ejecucion el cliente logueado
			}
		}

		serializarClientes(listClientesExistentes);

		return isActualizado;
	}

	// ----Metodos que manipulan los archivos directamente-----------------

	public ArrayList<Destino> listarDestinosExistentes() {
		listDestinosExistentes = deserializarDestinos();
		return listDestinosExistentes;
	}

	// Este es el método para serializar (Guardar el objeto )
	public static void serializarClientes(ArrayList<Cliente> lista) {

		try {
			FileOutputStream fos = new FileOutputStream(
					System.getProperty("user.dir") + "\\src\\Persistencia\\archivos\\archivoClientes.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(lista);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	// deserializar el objeto Cliente del archivo y se guarda en la lista
	public static ArrayList<Cliente> deserializarClientes() {
		ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();

		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\Persistencia\\archivos\\archivoClientes.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);

			listaClientes = (ArrayList) ois.readObject();
			ois.close();
			fis.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();

		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();

		}

		// Devuelvo la lista de Cliente
		return listaClientes;
	}

	public static void serializarDestinos(ArrayList<Destino> lista) {

		try {
			FileOutputStream fos = new FileOutputStream(
					System.getProperty("user.dir") + "\\src\\Persistencia\\archivos\\archivoDestinos.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(lista);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	// deserializar el objeto Cliente del archivo y se guarda en la lista
	public static ArrayList<Destino> deserializarDestinos() {
		ArrayList<Destino> lisDestinos = new ArrayList<Destino>();

		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\Persistencia\\archivos\\archivoDestinos.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);

			lisDestinos = (ArrayList) ois.readObject();
			ois.close();
			fis.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();

		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();

		}

		// Devuelvo la lista de Cliente
		return lisDestinos;
	}

	public Destino leerDestino(String nombreDestino) throws FileNotFoundException, IOException, ClassNotFoundException {

		System.out.println("antes deserializar");

		Destino miDestinoBuscado = null;
		listDestinosExistentes = deserializarDestinos();
		System.out.println("despues deserializar");

		for (Destino destino : listDestinosExistentes) {
			System.out.println(destino.getNombre_destino());
			if (destino.getNombre_destino().equals(nombreDestino)) {

				miDestinoBuscado = destino;
			}
		}
		return miDestinoBuscado;

	}

	public boolean crearPaquete(String nombre, String duracion, String servicios_adicionales, double precio,
			int cupo_maximo, Date fecha_dis_inicio, Date fecha_dis_fin)
			throws FileNotFoundException, IOException, ClassNotFoundException {

		boolean is_registrado = false;

		miPaqueteTuristico = new PaqueteTuristico();
		miPaqueteTuristico.setNombre(nombre);
		miPaqueteTuristico.setDuracion(duracion);
		miPaqueteTuristico.setCupo_maximo(cupo_maximo);
		miPaqueteTuristico.setServicios_adicionales(servicios_adicionales);
		miPaqueteTuristico.setFecha_dis_inicio(fecha_dis_inicio);
		miPaqueteTuristico.setFecha_dis_fin(fecha_dis_fin);

		listaPaquetesTurisiticos.add(miPaqueteTuristico);
		serializarpaqueteTuristico(listaPaquetesTurisiticos);
		is_registrado = true;
		return is_registrado;

	}

	public boolean actualizarDestino(String nombreDestino, Ciudad ciudadDestino, String descripcion,
			List<File> imagenes, Clima clima) {

		boolean isActualizado = false;
		listDestinosExistentes = deserializarDestinos();

		for (int i = 0; i < listDestinosExistentes.size(); i++) {

			if (listDestinosExistentes.get(i).getNombre_destino().equals(nombreDestino)) {

				listDestinosExistentes.get(i).setNombre_destino(nombreDestino);
				listDestinosExistentes.get(i).setCiudad_destino(ciudadDestino);
				listDestinosExistentes.get(i).setDescripcion(descripcion);
				listDestinosExistentes.get(i).setListImagenes(imagenes);
				listDestinosExistentes.get(i).setClima(clima);
				isActualizado = true;
			}
		}

		serializarDestinos(listDestinosExistentes);

		return isActualizado;

	}

	public ObservableList<Ciudad> llenarComboCiudad() {
		return FXCollections.observableArrayList(Ciudad.values());
	}

	public static void serializarAdministradores(ArrayList<Administrador> lista) {

		try {
			FileOutputStream fos = new FileOutputStream(
					System.getProperty("user.dir") + "\\src\\Persistencia\\archivos\\archivoAdministradores.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(lista);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	// deserializar el objeto Administrador del archivo y se guarda en la lista
	public static ArrayList<Administrador> deserializarAdministradores() {
		ArrayList<Administrador> lisAdmins = new ArrayList<Administrador>();

		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\Persistencia\\archivos\\archivoAdministradores.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);

			lisAdmins = (ArrayList) ois.readObject();
			ois.close();
			fis.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();

		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();

		}

		// Devuelvo la lista de admins
		return lisAdmins;
	}

	public static void serializarpaqueteTuristico(ArrayList<PaqueteTuristico> list) {
		try {

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
					System.getProperty("user.dir") + "\\src\\Persistencia\\archivos\\archivoPaqueteTuristico.dat"));
			out.writeObject(list);
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<PaqueteTuristico> deserializarPaqueteTuristico() {
		ArrayList<PaqueteTuristico> list = new ArrayList<>();
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(
					System.getProperty("user.dir") + "\\src\\Persistencia\\archivos\\archivoPaqueteTuristico.dat"));
			list = (ArrayList<PaqueteTuristico>) input.readObject();
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}
		return list;

	}

	public ArrayList<Destino> buscarPorNombre(ArrayList<Destino> list, String nombre) {

		ArrayList<Destino> listResult = (ArrayList<Destino>) list.stream()
				.filter(t -> t.getNombre_destino().equals(nombre)).collect(Collectors.toList());

		return listResult;
	}

	public ArrayList<Destino> buscarPorCiudad(ArrayList<Destino> list, String ciudad) {
		ArrayList<Destino> listResult = (ArrayList<Destino>) list.stream()
				.filter(t -> t.getCiudad_destino().equals(ciudad)).collect(Collectors.toList());

		return listResult;
	}

	public ArrayList<PaqueteTuristico> buscarPorPaqute(ArrayList<PaqueteTuristico> list, String nombre) {
		ArrayList<PaqueteTuristico> listResult = (ArrayList<PaqueteTuristico>) list.stream()
				.filter(t -> t.getNombre().equals(nombre)).collect(Collectors.toList());

		return listResult;
	}

	public ArrayList<PaqueteTuristico> buscarPorPrecio(ArrayList<PaqueteTuristico> list, int precio) {
		ArrayList<PaqueteTuristico> listResult = (ArrayList<PaqueteTuristico>) list.stream()
				.filter(t -> t.getPrecio() == precio).collect(Collectors.toList());

		return listResult;
	}

	public ArrayList<Destino> buscarPorClima(ArrayList<Destino> list, Clima clima) {
		ArrayList<Destino> listResult = (ArrayList<Destino>) list.stream().filter(t -> t.getClima() == clima)
				.collect(Collectors.toList());

		return listResult;
	}

	public ArrayList<PaqueteTuristico> buscarPorFechaInicio(ArrayList<PaqueteTuristico> list, Date fecha) {
		ArrayList<PaqueteTuristico> listResult = (ArrayList<PaqueteTuristico>) list.stream()
				.filter(t -> t.getFecha_dis_inicio() == fecha).collect(Collectors.toList());

		return listResult;
	}

	public ArrayList<PaqueteTuristico> buscarPorFechaFin(ArrayList<PaqueteTuristico> list, Date fecha) {
		ArrayList<PaqueteTuristico> listResult = (ArrayList<PaqueteTuristico>) list.stream()
				.filter(t -> t.getFecha_dis_fin() == fecha).collect(Collectors.toList());

		return listResult;
	}

	// -------Geters an seters de variables locales de miNegocio---
	public Cliente getClienteLogeado() {
		return clienteLogeado;
	}

	public void setClienteLogeado(Cliente clienteLogeado) {
		this.clienteLogeado = clienteLogeado;
	}

	public ArrayList<Cliente> getListClientesExistentes() {
		return listClientesExistentes;
	}

	public void setListClientesExistentes(ArrayList<Cliente> listClientesExistentes) {
		this.listClientesExistentes = listClientesExistentes;
	}

	public ArrayList<Destino> getListDestinos() {
		return listDestinosExistentes;
	}

	public void setListDestinos(ArrayList<Destino> listDestinos) {
		this.listDestinosExistentes = listDestinos;
	}

	public String getNombreClienteLogeado() {
		return nombreClienteLogeado;
	}

	public void setNombreClienteLogeado(String nombreClienteLogeado) {
		this.nombreClienteLogeado = nombreClienteLogeado;
	}

	public Destino getDestinoActualizar() {
		return destinoActualizar;
	}

	public void setDestinoActualizar(Destino destinoActualizar) {
		this.destinoActualizar = destinoActualizar;
	}

	public Administrador getAdministradorLogeado() {
		return administradorLogeado;
	}

	public void setAdministradorLogeado(Administrador administradorLogeado) {
		this.administradorLogeado = administradorLogeado;
	}

	public String getNombreadministradorLogeado() {
		return nombreadministradorLogeado;
	}

	public void setNombreadministradorLogeado(String nombreadministradorLogeado) {
		this.nombreadministradorLogeado = nombreadministradorLogeado;
	}

}
