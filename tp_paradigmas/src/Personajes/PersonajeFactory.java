package Personajes;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import Hechizos.AvadaKedavra;
import Hechizos.Episkey;
import Hechizos.Expelliarmus;
import Hechizos.HechizoStrategy;
import Hechizos.Protego;
import Hechizos.SectumSempra;

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
	
	public static List<Personaje> generarMagos() {
        List<Personaje> magos = new ArrayList<>();

        // Crear hechizos básicos
        HechizoStrategy expelliarmus = new Expelliarmus();
        HechizoStrategy protego = new Protego();

        // Crear lista de hechizos compartida
        Map<String, HechizoStrategy> hechizosMago = new HashMap<>();
        hechizosMago.put("Expelliarmus", expelliarmus);
        hechizosMago.put("Protego", protego);

        // Agregar distintos tipos de magos a la lista
        magos.add(new Auror("Harry Potter", new HashMap<>(hechizosMago)));
        magos.add(new Estudiante("Hermione Granger", new HashMap<>(hechizosMago)));
        magos.add(new Profesor("Albus Dumbledore", new HashMap<>(hechizosMago)));
        magos.add(new Estudiante("Ron Weasley", new HashMap<>(hechizosMago)));
        magos.add(new Auror("Nymphadora Tonks", new HashMap<>(hechizosMago)));

        return magos;
    }

    public static List<Personaje> generarMortifagos() {
        List<Personaje> mortifagos = new ArrayList<>();

        // Crear hechizos básicos para mortífagos
        HechizoStrategy episkey = new Episkey();
        HechizoStrategy sectumsempra = new SectumSempra();
        HechizoStrategy avadakedavra = new AvadaKedavra();

        // Crear lista de hechizos compartida para mortífagos
        Map<String, HechizoStrategy> hechizosMortifago = new HashMap<>();
        hechizosMortifago.put("Episkey", episkey);
        hechizosMortifago.put("SectumSempra", sectumsempra);
        hechizosMortifago.put("AvadaKedavra", avadakedavra);

        // Agregar distintos tipos de mortífagos a la lista
        mortifagos.add(new Comandante("Bellatrix Lestrange", new HashMap<>(hechizosMortifago)));
        mortifagos.add(new Seguidor("Lucius Malfoy", new HashMap<>(hechizosMortifago)));
        mortifagos.add(new Comandante("Severus Snape", new HashMap<>(hechizosMortifago)));
        mortifagos.add(new Seguidor("Antonin Dolohov", new HashMap<>(hechizosMortifago)));
        mortifagos.add(new Comandante("Barty Crouch Jr.", new HashMap<>(hechizosMortifago)));

        return mortifagos;
    }

}

