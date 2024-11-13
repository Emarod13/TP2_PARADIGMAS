package Personajes;

import java.util.Map;

import Hechizos.HechizoStrategy;

public class Estudiante extends Mago {


	private static int puntos_de_vida = 250;
	private static int energia = 150;
	
	public Estudiante(String nombre, Map<String,HechizoStrategy> hechizos ) {
		super(nombre,  puntos_de_vida, energia, hechizos);
	
	}

}
