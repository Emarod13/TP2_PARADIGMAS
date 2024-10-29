package Personajes;

import java.util.Set;

import Hechizos.HechizoStrategy;

public class Mago extends Personaje {
	public Mago(String nombre, int nivel_de_magia, int puntos_de_vida, int energia, Set<HechizoStrategy> hechizos) {
		super(nombre, nivel_de_magia, puntos_de_vida, energia, hechizos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void atacar(Personaje p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void defender(Personaje p) {
		// TODO Auto-generated method stub
		
	}

	
}
