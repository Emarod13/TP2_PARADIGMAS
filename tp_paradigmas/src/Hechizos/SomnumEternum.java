package Hechizos;

import Personajes.Personaje;

public class SomnumEternum extends HechizoStrategy {
	private static int DAÑO = 100;
	private static int COSTO = 30;
	private static int DURACION = 3;

	public SomnumEternum() {
		super(COSTO, "Ataque");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ejecutar(Personaje p) {
		// TODO Auto-generated method stub

		if (p.isProtegido()) {
			System.out.println(p.getNombre() + " esta protegido, repele el ataque");
		} else {
			p.recibirDaño(DAÑO);
			if (p.isVivo()) {
				p.aplicarEfecto("Agotado", DURACION);
			}
		}

	}

	@Override
	public String getTipo() {
		// TODO Auto-generated method stub
		return this.tipo;
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return "SomnumEternum";
	}

}
