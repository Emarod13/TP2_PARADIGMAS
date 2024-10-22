package Hechizos;

import Personajes.Personaje;

public class Expelliarmus implements HechizoStrategy {

	@Override
	public void ejecutar(Personaje p) { ///DESARMA A UN ENEMIGO
		p.setDesarmado(true);
	}
	
}
