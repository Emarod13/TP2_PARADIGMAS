package Personajes;

import java.util.Map;
import java.util.Set;

import Hechizos.HechizoStrategy;

public class Auror extends Mago {

	private static int nivel_de_magia = 2;
	private static int puntos_de_vida = 500;
	private static int energia = 300;
	public Auror(String nombre, Map<String, HechizoStrategy> map ) {
		super(nombre,nivel_de_magia, puntos_de_vida, energia, map);
		// TODO Auto-generated constructor stub
	}
}
