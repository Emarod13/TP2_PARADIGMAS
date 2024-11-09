package Personajes;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


import Hechizos.AvadaKedavra;
import Hechizos.Episkey;
import Hechizos.ExpectoPatronum;
import Hechizos.Expelliarmus;
import Hechizos.HechizoStrategy;
import Hechizos.LaceroMortis;
import Hechizos.LuxExterminus;
import Hechizos.Protego;
import Hechizos.SectumSempra;
import Hechizos.SomnumEternum;
import Hechizos.TenebrisScutum;

public class PersonajeFactory {
	private static Random rand = new Random();

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

	private static Map<String, HechizoStrategy> inicializarAuror() {
		Map<String, HechizoStrategy> hechizos = new HashMap<>();

		/// MAGO OFENSIVO
		hechizos.put("LuxExterminus", new LuxExterminus());
		hechizos.put("Expelliarmus", new Expelliarmus());
		hechizos.put("SomnumEternum", new SomnumEternum());
		hechizos.put("Protego", new Protego());
		return hechizos;

	}
	
	private static Map<String, HechizoStrategy> inicializarEstudiante() {
		Map<String, HechizoStrategy> hechizos = new HashMap<>();
		
		/// MAGO DE APOYO
		hechizos.put("Expelliarmus", new Expelliarmus());
		hechizos.put("Protego", new Protego());
		hechizos.put("ExpectoPatronum", new ExpectoPatronum());

		return hechizos;
	}

	private static Map<String, HechizoStrategy> inicializarProfesor() {
		Map<String, HechizoStrategy> hechizos = new HashMap<>();
		/// MAGO EQUILIBRADO
		hechizos.put("SomnumEternum", new SomnumEternum());
		hechizos.put("Expelliarmus", new Expelliarmus());
		hechizos.put("Protego", new Protego());
		hechizos.put("ExpectoPatronum", new ExpectoPatronum());

		return hechizos;
	}

	private static Map<String, HechizoStrategy> inicializarComandante() {
		Map<String, HechizoStrategy> hechizos = new HashMap<>();
		/// MORTIFAGO PODEROSO
		hechizos.put("AvadaKedavra", new AvadaKedavra());
		hechizos.put("TenebrisScutum", new TenebrisScutum());
		hechizos.put("SectumSempra", new SectumSempra());
		hechizos.put("LaceroMortis", new LaceroMortis());

		return hechizos;
	}

	private static Map<String, HechizoStrategy> inicializarSeguidor() {
		Map<String, HechizoStrategy> hechizos = new HashMap<>();
		
		
		boolean aleatorio = rand.nextBoolean();
		/// MORTIFAGO OFENSIVO
		if(aleatorio) {
			hechizos.put("LaceroMortis", new LaceroMortis());
			hechizos.put("SectumSempra", new SectumSempra());
			hechizos.put("Episkey", new Episkey());
			return hechizos;
		}
		// MORTIFAGO DEFENSIVO
		hechizos.put("TenebrisScutum", new TenebrisScutum());
		hechizos.put("LaceroMortis", new LaceroMortis());

		return hechizos;
	}

	public static List<Personaje> generarMagos() {
		List<Personaje> magos = new ArrayList<>();

	
		magos.add(new Auror("Harry Potter", PersonajeFactory.inicializarAuror()));
		magos.add(new Estudiante("Hermione Granger", PersonajeFactory.inicializarEstudiante()));
		magos.add(new Profesor("Albus Dumbledore", PersonajeFactory.inicializarProfesor()));
		magos.add(new Estudiante("Neville", PersonajeFactory.inicializarEstudiante()));
		magos.add(new Auror("Nymphadora Tonks", PersonajeFactory.inicializarAuror()));

		return magos;
	}

	public static List<Personaje> generarMortifagos() {
		List<Personaje> mortifagos = new ArrayList<>();

	
	
		// Agregar distintos tipos de mortífagos a la lista
		mortifagos.add(new Comandante("Bellatrix Lestrange", PersonajeFactory.inicializarComandante()));
		mortifagos.add(new Seguidor("Lucius Malfoy", PersonajeFactory.inicializarSeguidor()));
		mortifagos.add(new Comandante("Severus Snape", PersonajeFactory.inicializarComandante()));
		mortifagos.add(new Seguidor("Antonin Dolohov", PersonajeFactory.inicializarSeguidor()));
		mortifagos.add(new Seguidor("Barty Crouch Jr.", PersonajeFactory.inicializarSeguidor()));

		return mortifagos;
	}

}
