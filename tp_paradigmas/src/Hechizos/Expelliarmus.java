package Hechizos;

import Personajes.Personaje;

public class Expelliarmus extends HechizoStrategy { // ESTUDIANTE
	private static int DURACION=3;
	private static int COSTO=30;
	public Expelliarmus() {
		super(COSTO,"Ataque");
	}
	@Override
	public void ejecutar(Personaje p) { ///DESARMA A UN ENEMIGO
		p.aplicarEfecto("Desarmado", DURACION);
	}
	@Override
	public String getTipo() {
		// TODO Auto-generated method stub
		return this.tipo;
	}
	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return "Expelliarmus";
	}
	
}
