package Objetos;

import Personajes.Personaje;

public class PocionDeProteccion extends Pocion {

	@Override
	public void consumir(Personaje p) {
		p.setProtegido(true);
		
	}

}
