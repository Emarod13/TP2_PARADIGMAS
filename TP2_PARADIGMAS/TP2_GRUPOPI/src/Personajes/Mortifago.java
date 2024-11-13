package Personajes;

import java.util.Map;

import Hechizos.HechizoStrategy;

public abstract class Mortifago extends Personaje {

	public Mortifago(String nombre,  int puntos_de_vida, int energia, Map<String,HechizoStrategy> hechizos) {
		super(nombre,  puntos_de_vida, energia, hechizos);
	
	}

	

	@Override
	public String getTipo() {
	
		return "Mortifago";
	}

}
