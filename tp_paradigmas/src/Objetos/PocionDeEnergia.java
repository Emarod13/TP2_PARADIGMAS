package Objetos;

import Personajes.Personaje;

public class PocionDeEnergia extends Pocion {
	protected int energia;
	
	public PocionDeEnergia(int energia) {
		this.energia = energia;
	}
	@Override
	public void consumir(Personaje p) {
		p.setEnergia(p.getEnergia()+energia);
		
	}
	

}
