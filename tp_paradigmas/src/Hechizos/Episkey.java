package Hechizos;

import Personajes.Personaje;

public class Episkey implements HechizoStrategy {
	private static int CURACION = 100;
	@Override
	public void ejecutar(Personaje p) {
		p.setPuntos_de_vida(p.getPuntos_de_vida()+CURACION);
		
	}
	
	
}
