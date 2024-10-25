package Personajes;

import java.util.Random;
import java.util.Set;

import Hechizos.HechizoStrategy;

public class PersonajeFactory {

	public static Mago crearMago(String nombre, String tipo, Set<HechizoStrategy> hechizos) {
		
		switch(tipo) {
		case "Auror":
			return new Auror(nombre, hechizos);
		case "Profesor":
			return new Profesor(nombre,hechizos);
		case "Estudiante":
			return new Estudiante(nombre,hechizos);
		default:
			return null;
		}
		
		
	}
public static Mortifago crearMortifago(String nombre, String tipo, Set<HechizoStrategy> hechizos) {
		
		switch(tipo) {
		case "Auror":
			return new Seguidor(nombre, hechizos);
		case "Profesor":
			return new Comandante(nombre,hechizos);
		default:
			return null;
		}
		
		
	}

}
/*
 * public Mago crearMagoAleatorio() { Random rand = new Random(); int
 * numeroAleatorio = rand.nextInt(2);
 * 
 * switch (numeroAleatorio) { case 0: return crearProfesor(); case 1: return
 * crearAuror();
 * 
 * } return crearEstudiante(); // case 2:
 * 
 * }
 * 
 * public Mortifago crearMortifagoAleatorio() { Random rand = new Random(); int
 * numeroAleatorio = rand.nextInt(1);
 * 
 * if(numeroAleatorio == 0) { return crearComandante(); } return
 * crearSeguidor();
 * 
 * }
 */

