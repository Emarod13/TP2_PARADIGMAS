package Hechizos;

import Personajes.Personaje;

public class SectumSempra implements HechizoStrategy { // HACE DAÑO Y APLICA SANGRADO
	private static int DAÑO = 100;
	private static int DURACION = 3;

	@Override
	public void ejecutar(Personaje p) {
		p.setPuntos_de_vida(p.getPuntos_de_vida() - DAÑO);
		if (p.getPuntos_de_vida() <= 0) {
			p.morir();
		}
		else {
			p.aplicarEfecto("Sangrado", DURACION);
		}
		

	}

}
