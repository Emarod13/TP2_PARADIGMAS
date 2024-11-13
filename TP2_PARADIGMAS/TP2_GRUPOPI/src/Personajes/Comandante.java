package Personajes;

import java.util.Map;

import Hechizos.HechizoStrategy;

public class Comandante extends Mortifago {
	
	
	private static int puntos_de_vida = 500;
	private static int energia = 300;
	public Comandante(String nombre, Map<String,HechizoStrategy> hechizos ) {
		super(nombre, puntos_de_vida, energia, hechizos);

	}

}
