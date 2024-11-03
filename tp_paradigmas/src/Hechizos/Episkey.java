package Hechizos;

import Personajes.Personaje;

public class Episkey extends HechizoStrategy {
	private static int CURACION = 100;
	@Override
	public void ejecutar(Personaje p) {
		p.setPuntos_de_vida(p.getPuntos_de_vida()+CURACION);
		
	}
	@Override
	public Object getTipo() {
		// TODO Auto-generated method stub
		return "Defensa";
	}
	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return "Episkey";
	}
	
	
}
