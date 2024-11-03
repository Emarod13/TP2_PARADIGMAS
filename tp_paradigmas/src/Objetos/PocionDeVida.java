package Objetos;

import Personajes.Personaje;

public class PocionDeVida implements Consumible  {
	protected int efecto;
	
	public PocionDeVida(int puntos_de_vida){
		this.efecto = puntos_de_vida;
	}
	@Override
	public void consumir(Personaje p) {
		p.setPuntos_de_vida(p.getPuntos_de_vida()+efecto); 
		
	}
	

}
