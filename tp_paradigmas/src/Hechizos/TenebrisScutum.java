package Hechizos;

import Personajes.Personaje;

public class TenebrisScutum extends HechizoStrategy {

	private static int DURACION = 3;
	private static int COSTO = 30;

	public TenebrisScutum() {
		super(COSTO, "Defensa");
		// TODO Auto-generated constructor stub
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
		return "TenebrisScutum";
	}

}
