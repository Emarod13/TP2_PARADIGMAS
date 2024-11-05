package Hechizos;

import Personajes.Personaje;

public class ExpectoPatronum extends HechizoStrategy { // PROFESOR
	private static int PODER = 100;
	private static int COSTO=30;
	public ExpectoPatronum() {
		super(COSTO,"Ataque");
	}

	@Override
	public void ejecutar(Personaje p) {

		p.recibirDaño(PODER);
	}

	@Override
	public String getTipo() {
		// TODO Auto-generated method stub
		return this.tipo;
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return "ExpectoPatronum";
	}

}
