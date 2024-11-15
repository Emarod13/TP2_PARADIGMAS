package Hechizos;

import Personajes.Personaje;

public class SectumSempra extends HechizoStrategy { // COMANDANTE, SEGUIDOR
	private static int DAÑO = 100;
	private static int DURACION = 3;
	private static int COSTO=120;
	public SectumSempra() {
		super(COSTO,"Ataque");
	}

	@Override
	public void ejecutar(Personaje p) {
		
		if(p.isProtegido()) {
			System.out.println(p.getNombre() + " esta protegido, repele el ataque");
		}
		else {
			p.recibirDaño(DAÑO);
			
			if(p.isVivo()) {
				p.aplicarEfecto("Sangrado", DURACION);
			}
		}
		
		

	}

	@Override
	public String getTipo() {

		return this.tipo;
	}

	@Override
	public String getNombre() {
	
		return "SectumSempra";
	}
	public int getDaño() {
		return SectumSempra.DAÑO;
	}

}
