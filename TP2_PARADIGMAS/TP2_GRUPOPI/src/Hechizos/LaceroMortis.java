package Hechizos;

import Personajes.Personaje;

public class LaceroMortis extends HechizoStrategy {
	private static int DAÑO = 100;
	private static int COSTO = 120;
	private static int DURACION = 1;

	public LaceroMortis() {
		super(COSTO, "Ataque");
		
	}

	@Override
	public void ejecutar(Personaje p) {

		if (p.isProtegido()) {
			System.out.println(p.getNombre() + " esta protegido, repele el ataque");
		} else {
			p.recibirDaño(DAÑO);
			if (p.isVivo()) {
				p.aplicarEfecto("Desarmado", DURACION);
			}
		}

	}

	@Override
	public String getTipo() {
	
		return this.tipo;
	}

	@Override
	public String getNombre() {
		
		return "LaceroMortis";
	}

}
