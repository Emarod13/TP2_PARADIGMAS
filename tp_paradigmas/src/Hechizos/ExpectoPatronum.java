package Hechizos;

import Personajes.Personaje;

public class ExpectoPatronum extends HechizoStrategy { // PROFESOR
	private static int PODER = 100;

	@Override
	public void ejecutar(Personaje p) {

		p.setPuntos_de_vida(p.getPuntos_de_vida() - PODER); // SI ES MORTIFAGO, LO DAÑA
		if (p.getPuntos_de_vida() <= 0) {
			p.morir();
		}
	}

	@Override
	public Object getTipo() {
		// TODO Auto-generated method stub
		return "Ataque";
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return "ExpectoPatronum";
	}

}
