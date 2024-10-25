package Hechizos;

import Personajes.Personaje;

public class Expelliarmus implements HechizoStrategy {
	private static int DURACION=3;
	@Override
	public void ejecutar(Personaje p) { ///DESARMA A UN ENEMIGO
		p.aplicarEfecto("Desarmado", DURACION);
	}
	
}
