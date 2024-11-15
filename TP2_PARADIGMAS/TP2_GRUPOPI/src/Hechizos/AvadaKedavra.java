package Hechizos;

import Personajes.Personaje;

public class AvadaKedavra extends HechizoStrategy { // COMANDANTE
	private static double PORCENTAJE = 0.3;
	private static int COSTO = 180;

	public AvadaKedavra() {
		super(COSTO, "Ataque");
	}

	@Override
	public void ejecutar(Personaje p) {
		if (p.isProtegido())

		{
			System.out.println(p.getNombre() + " esta protegido, repele el ataque");
		} else {
			if (p.getPuntos_de_vida() / p.getVida_inicial() <= PORCENTAJE) {
				p.morir();
			}
		}
	}

	@Override
	public String getTipo() {
		
		return this.tipo;
	}

	@Override
	public String getNombre() {
	
		return "AvadaKedavra";
	}

}
