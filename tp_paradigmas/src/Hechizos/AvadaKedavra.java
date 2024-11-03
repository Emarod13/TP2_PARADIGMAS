package Hechizos;

import Personajes.Personaje;

public class AvadaKedavra extends HechizoStrategy { // COMANDANTE
	private static double PORCENTAJE = 0.3;
	@Override
	public void ejecutar(Personaje p) {
		if(p.getPuntos_de_vida()/p.getVida_inicial() <= PORCENTAJE) {
			p.morir();
		}
		
	}
	@Override
	public Object getTipo() {
		// TODO Auto-generated method stub
		return "Ataque";
	}
	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return "AvadaKedavra";
	}
	
	
}
