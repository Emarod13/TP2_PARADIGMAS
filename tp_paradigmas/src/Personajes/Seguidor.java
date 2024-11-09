package Personajes;

import java.util.Map;


import Hechizos.HechizoStrategy;

public class Seguidor extends Mortifago{
	

	private static int puntos_de_vida = 500;
	private static int energia = 300;

	public Seguidor(String nombre, Map<String,HechizoStrategy> hechizos ) {
		super(nombre,  puntos_de_vida, energia, hechizos);
		// TODO Auto-generated constructor stub
	}

}
