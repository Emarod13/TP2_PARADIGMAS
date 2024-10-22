package Hechizos;

import Personajes.Personaje;

public class Protego implements HechizoStrategy {
	
	private static final int nivel=1;

	@Override
	public void ejecutar(Personaje p) {
		p.setProtegido(true);
		
	}

}
