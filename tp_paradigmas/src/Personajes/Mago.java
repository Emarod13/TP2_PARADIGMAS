package Personajes;

import java.util.Map;
import java.util.Set;

import Hechizos.HechizoStrategy;

public abstract class  Mago extends Personaje {
	public Mago(String nombre, int nivel_de_magia, int puntos_de_vida, int energia, Map<String,HechizoStrategy> hechizos) {
		super(nombre, nivel_de_magia, puntos_de_vida, energia, hechizos);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getTipo() {
		// TODO Auto-generated method stub
		return "Mago";
	}

	
}
