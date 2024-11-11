package JuegoClases;

import java.util.Map;

public abstract class  Mago extends Personaje {
	public Mago(String nombre,  int puntos_de_vida, int energia, Map<String,HechizoStrategy> hechizos) {
		super(nombre,  puntos_de_vida, energia, hechizos);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getTipo() {
		// TODO Auto-generated method stub
		return "Mago";
	}

	
}
