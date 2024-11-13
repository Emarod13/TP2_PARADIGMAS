package Hechizos;

import Personajes.Personaje;

public class SomnumEternum extends HechizoStrategy {
	private static int DAÑO = 150;
	private static int COSTO = 150;
	private static int DURACION = 2;

	public SomnumEternum() {
		super(COSTO, "Ataque");
	
	}

	@Override
	public void ejecutar(Personaje p) {
		
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
	
		return this.tipo;
	}

	@Override
	public String getNombre() {
		
		return "SomnumEternum";
	}

	public int getDaño() {
	
		return DAÑO;
	}

}
