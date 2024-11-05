package Hechizos;

import Personajes.Personaje;

public abstract class HechizoStrategy {
	protected int costo;
	protected String tipo;
	public HechizoStrategy(int costo, String tipo) {
		this.costo = costo;
		this.tipo = tipo;
	}
	public abstract void  ejecutar(Personaje p);
	public int getCostoEnergia() {
		// TODO Auto-generated method stub
		return costo;
	}
	public abstract String getTipo();
	public abstract String getNombre();

}