package Hechizos;

import Personajes.Personaje;

public abstract class HechizoStrategy {
	protected int costo;
	protected String tipo;
	public abstract void  ejecutar(Personaje p);
	public int getCostoEnergia() {
		// TODO Auto-generated method stub
		return costo;
	}
	public abstract Object getTipo();
	public abstract String getNombre();

}