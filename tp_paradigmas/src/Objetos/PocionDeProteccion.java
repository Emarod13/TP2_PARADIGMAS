package Objetos;

import Personajes.Personaje;

public class PocionDeProteccion implements Consumible  {
	public static int DURACION = 3;
	@Override
	public void consumir(Personaje p) {
		p.aplicarEfecto("Protegido", DURACION);
		
	}

}
