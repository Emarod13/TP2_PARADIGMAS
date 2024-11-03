package Hechizos;

import Personajes.Personaje;

public class SectumSempra extends HechizoStrategy { // COMANDANTE, SEGUIDOR
	private static int DAÑO = 100;
	private static int DURACION = 3;
	
	public SectumSempra() {
		super();
	}

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

	@Override
	public Object getTipo() {
		// TODO Auto-generated method stub
		return "Ataque";
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return "SectumSempra";
	}

}
