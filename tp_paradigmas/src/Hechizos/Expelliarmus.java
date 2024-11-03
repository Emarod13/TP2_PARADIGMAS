package Hechizos;

import Personajes.Personaje;

public class Expelliarmus extends HechizoStrategy { // ESTUDIANTE
	private static int DURACION=3;
	@Override
	public void ejecutar(Personaje p) { ///DESARMA A UN ENEMIGO
		p.aplicarEfecto("Desarmado", DURACION);
	}
	@Override
	public Object getTipo() {
		// TODO Auto-generated method stub
		return "Defensa";
	}
	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return "Expelliarmus";
	}
	
}
