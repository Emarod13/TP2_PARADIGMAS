package Hechizos;

import Personajes.Personaje;

public class Episkey extends HechizoStrategy {
	private static int CURACION = 100;
	private static int COSTO=75;
	public Episkey() {
		super(COSTO,"Defensa");
	}
	@Override
	public void ejecutar(Personaje p) {
		p.setPuntos_de_vida(p.getPuntos_de_vida()+CURACION);
		
	}
	@Override
	public String getTipo() {

		return this.tipo;
	}
	@Override
	public String getNombre() {
	
		return "Episkey";
	}
	public int getCuracion() {
		return Episkey.CURACION;
	}
	
	
}
