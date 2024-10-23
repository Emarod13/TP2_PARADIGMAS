package Personajes;

import java.util.Set;

import Hechizos.HechizoStrategy;

public class Mago extends Personaje {
	public Mago(String nombre, int nivel_de_magia, int puntos_de_vida, int energia, Set<HechizoStrategy> hechizos) {
		super(nombre, nivel_de_magia, puntos_de_vida, energia, hechizos);
		// TODO Auto-generated constructor stub
	}

	public void atacar(Mortifago m) {
		if(this.isDesarmado() == false) {
			// funcionalidad
		}
	}
	public void asistir(Mago m) {
		if(this.isDesarmado() == false) {
			//funcionalidad
		}
		
	}
}
