package Hechizos;

import Personajes.Mortifago;
import Personajes.Personaje;

public class ExpectoPatronum implements HechizoStrategy {
	private static int PODER = 100;

	@Override
	public void ejecutar(Personaje p) {

		if (p instanceof Mortifago) {
			p.setPuntos_de_vida(p.getPuntos_de_vida() - PODER); // SI ES MORTIFAGO, LO DAÑA
			if (p.getPuntos_de_vida() <= 0) {
				p.morir();
			}
		} else {
			p.setPuntos_de_vida(p.getPuntos_de_vida() + PODER); // SI ES MAGO, LO CURA
		}

	}

}
