package Hechizos;

import Personajes.Personaje;

public class Protego implements HechizoStrategy {
	

	@Override
	public void ejecutar(Personaje p) {
		p.setProtegido(true);
		
	}

}
