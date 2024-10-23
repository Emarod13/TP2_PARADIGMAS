package Hechizos;

import Personajes.Personaje;

public class Protego implements HechizoStrategy {
	
	public static int DURACION=3;
	@Override
	public void ejecutar(Personaje p) {
		p.aplicarEfecto("Protegido", DURACION);
		
	}

}
