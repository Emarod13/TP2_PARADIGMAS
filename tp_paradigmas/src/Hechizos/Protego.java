package Hechizos;

import Personajes.Personaje;

public class Protego extends HechizoStrategy { // ESTUDIANTE
	
	private static int DURACION=1;
	private static int COSTO=40;
	public Protego() {
		super(COSTO,"Defensa");
	}
	@Override
	public void ejecutar(Personaje p) {
		p.aplicarEfecto("Protegido", DURACION);
		
	}
	@Override
	public String getTipo() {
	
		return this.tipo;
	}
	@Override
	public String getNombre() {
		
		return "Protego";
	}

}
