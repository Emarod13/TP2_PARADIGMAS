package Objetos;

import Personajes.Personaje;

public class PocionDeProteccion extends Pocion {
	public static int DURACION = 3;
	@Override
	public void consumir(Personaje p) {
		p.aplicarEfecto("Protegido", DURACION);
		
	}

}
