package Personajes;

import java.util.Map;
import java.util.Random;
import java.util.Set;

import Hechizos.HechizoStrategy;

public class PersonajeFactory {

	public static Mago crearMago(String nombre, String tipo) {
		Mago m = null;

		switch (tipo) {
		case "Auror":
			m = new Auror(nombre, inicializarAuror());
			return m;
		case "Profesor":
			m = new Profesor(nombre, inicializarProfesor());
	
			return m;
		case "Estudiante":
			m = new Estudiante(nombre, inicializarEstudiante());

			return m;
		default:
			return m;
		}

	}
	public static Mago crearMago(String tipo) {
		

		Mago m = null;

		switch (tipo) {
		case "Auror":
			m = new Auror("", inicializarAuror());

			return m;
		case "Profesor":
			m = new Profesor("", inicializarProfesor());

			return m;
		case "Estudiante":
			m = new Estudiante("", inicializarEstudiante());
	
			return m;
		default:
			return m;
		}

	}

	
	public static Mortifago crearMortifago(String nombre, String tipo) {
		

		switch (tipo) {
		case "Seguidor":
			return new Seguidor(nombre, inicializarSeguidor());
		case "Comandante":
			return new Comandante(nombre, inicializarComandante());
		default:
			return null;
		}

	}
	
	
	public static Mortifago crearMortifago(String tipo) {

		switch (tipo) {
		case "Seguidor":
			return new Seguidor("",inicializarSeguidor());
		case "Comandante":
			return new Comandante("", inicializarComandante());
		default:
			return null;
		}

	}

	public Mago crearMagoAleatorio() {
		Random rand = new Random();
		int numeroAleatorio = rand.nextInt(2);

		switch (numeroAleatorio) {
		case 0:
			return crearMago("Profesor");
		case 1:
			return crearMago("Auror");

		}
		return crearMago("Estudiante"); // case 2:

	}

	public Mortifago crearMortifagoAleatorio() {
		Random rand = new Random();
		int numeroAleatorio = rand.nextInt(1);

		if (numeroAleatorio == 0) {
			return crearMortifago("Comandante");
		}
		return crearMortifago("Seguidor");

	}
	
	private static Map<String, HechizoStrategy> inicializarAuror(){
		return null;
	}
	
	private static Map<String, HechizoStrategy> inicializarEstudiante() {
		// TODO Auto-generated method stub
		return null;
	}
	private static Map<String, HechizoStrategy> inicializarProfesor() {
		// TODO Auto-generated method stub
		return null;
	}
	private static Map<String, HechizoStrategy> inicializarComandante() {
		// TODO Auto-generated method stub
		return null;
	}
	private static Map<String, HechizoStrategy> inicializarSeguidor() {
		// TODO Auto-generated method stub
		return null;
	}

}

