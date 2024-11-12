package Hechizos;

import Personajes.Personaje;

public class ExpectoPatronum extends HechizoStrategy { // PROFESOR
	private static int DAÑO = 150;
	private static int COSTO=100;
	public ExpectoPatronum() {
		super(COSTO,"Ataque");
	}

	@Override
	public void ejecutar(Personaje p) {
		
		if(p.isProtegido()) {
			System.out.println(p.getNombre() + " esta protegido, repele el ataque");
		}
		else {
			p.recibirDaño(DAÑO);
		}
		
	}

	@Override
	public String getTipo() {

		return this.tipo;
	}

	@Override
	public String getNombre() {
	
		return "ExpectoPatronum";
	}
	public int getDaño() {
		return DAÑO;
	}

}
