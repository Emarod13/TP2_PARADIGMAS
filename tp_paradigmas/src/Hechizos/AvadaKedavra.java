package Hechizos;

import Personajes.Personaje;

public class AvadaKedavra implements HechizoStrategy {
	private static double PORCENTAJE = 0.5;
	@Override
	public void ejecutar(Personaje p) {
		if(p.getPuntos_de_vida()/p.getVida_inicial() <= PORCENTAJE) {
			p.morir();
		}
		
	}
	
	
}
