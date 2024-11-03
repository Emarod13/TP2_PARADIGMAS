package Hechizos;

import Personajes.Personaje;

public class Protego extends HechizoStrategy { // ESTUDIANTE
	
	public static int DURACION=3;
	@Override
	public void ejecutar(Personaje p) {
		p.aplicarEfecto("Protegido", DURACION);
		
	}
	@Override
	public Object getTipo() {
		// TODO Auto-generated method stub
		return "Defensa";
	}
	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return "Protego";
	}

}
