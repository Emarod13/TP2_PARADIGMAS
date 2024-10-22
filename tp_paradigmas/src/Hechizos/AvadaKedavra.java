package Hechizos;

import Personajes.Personaje;

public class AvadaKedavra implements HechizoStrategy {

	@Override
	public void ejecutar(Personaje p) {
		if(p.getPuntos_de_vida()/p.getVida_inicial() <= 0.5) {
			p.morir();
		}
		
	}
	
	
}
