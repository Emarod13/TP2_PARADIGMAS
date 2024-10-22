package Personajes;

public class Mago extends Personaje {

	public Mago(String nombre, int nivel_de_magia, int puntos_de_vida) {
		super(nombre, nivel_de_magia, puntos_de_vida);
		// TODO Auto-generated constructor stub
	}

	public void atacar(Mortifago m) {
		if(this.desarmado == false) {
			// funcionalidad
		}
	}
	public void asistir(Mago m) {
		if(this.desarmado == false) {
			//funcionalidad
		}
		
	}
}
