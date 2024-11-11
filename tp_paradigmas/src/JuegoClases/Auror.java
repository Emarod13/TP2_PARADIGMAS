package JuegoClases;

import java.util.Map;

public class Auror extends Mago {

	
	private static int puntos_de_vida = 500;
	private static int energia = 300;
	public Auror(String nombre, Map<String, HechizoStrategy> map ) {
		super(nombre, puntos_de_vida, energia, map);
		// TODO Auto-generated constructor stub
	}
}
