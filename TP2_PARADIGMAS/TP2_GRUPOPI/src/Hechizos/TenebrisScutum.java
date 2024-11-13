package Hechizos;

import Personajes.Personaje;

public class TenebrisScutum extends HechizoStrategy {

	private static int DURACION = 1;
	private static int COSTO = 75;

	public TenebrisScutum() {
		super(COSTO, "Defensa");
	
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
		
		return "TenebrisScutum";
	}

}
