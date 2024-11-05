package Hechizos;

import Personajes.Personaje;

public class Protego extends HechizoStrategy { // ESTUDIANTE
	
	private static int DURACION=3;
	private static int COSTO=30;
	public Protego() {
		super(COSTO,"Defensa");
	}
	@Override
	public void ejecutar(Personaje p) {
		p.aplicarEfecto("Protegido", DURACION);
		
	}
	@Override
	public String getTipo() {
		// TODO Auto-generated method stub
		return this.tipo;
	}
	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return "Protego";
	}

}
