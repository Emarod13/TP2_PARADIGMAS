package Personajes;

import java.util.Map;

import Hechizos.HechizoStrategy;

public class Profesor extends Mago{

	
	
	private static int puntos_de_vida = 300;
	private static int energia = 200;
	
	public Profesor(String nombre, Map<String,HechizoStrategy> hechizos ) {
		super(nombre,  puntos_de_vida, energia, hechizos);
	}

}