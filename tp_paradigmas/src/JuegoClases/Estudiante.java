package JuegoClases;

import java.util.Map;

public class Estudiante extends Mago {


	private static int puntos_de_vida = 250;
	private static int energia = 150;
	
	public Estudiante(String nombre, Map<String,HechizoStrategy> hechizos ) {
		super(nombre,  puntos_de_vida, energia, hechizos);
		// TODO Auto-generated constructor stub
	}

}
