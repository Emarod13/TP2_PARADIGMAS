package Personajes;

import java.util.Set;

import Hechizos.HechizoStrategy;

public class Seguidor extends Mortifago{
	
	private static int nivel_de_magia = 2;
	private static int puntos_de_vida = 500;
	private static int energia = 300;

	public Seguidor(String nombre, Set<HechizoStrategy> hechizos ) {
		super(nombre, nivel_de_magia, puntos_de_vida, energia, hechizos);
		// TODO Auto-generated constructor stub
	}

}
