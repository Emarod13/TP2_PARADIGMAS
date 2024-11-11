package JuegoClases;

import java.util.Map;

public class Comandante extends Mortifago {
	
	
	private static int puntos_de_vida = 500;
	private static int energia = 300;
	public Comandante(String nombre, Map<String,HechizoStrategy> hechizos ) {
		super(nombre, puntos_de_vida, energia, hechizos);
		// TODO Auto-generated constructor stub
	}

}
