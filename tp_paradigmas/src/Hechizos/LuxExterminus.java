package Hechizos;

import Personajes.Personaje;

public class LuxExterminus extends HechizoStrategy {
	
	private static double PORCENTAJE = 0.3;
	private static int COSTO = 50;
	public LuxExterminus() {
		super(COSTO, "Ataque");
		// TODO Auto-generated constructor stub
	}
	@Override
	public void ejecutar(Personaje p) {
		if (p.isProtegido())

		{
			System.out.println(p.getNombre() + " esta protegido, repele el ataque");
		} else {
			if (p.getPuntos_de_vida() / p.getVida_inicial() <= PORCENTAJE) {
				p.morir();
			}
		}
	}
	@Override
	public String getTipo() {
		// TODO Auto-generated method stub
		return this.tipo;
	}
	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return "LuxExterminus";
	}

}