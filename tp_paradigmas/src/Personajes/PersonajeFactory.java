package Personajes;

import java.util.Random;

public class PersonajeFactory {
	public Estudiante crearEstudiante() {
		return new Estudiante();
	}

	public Auror crearAuror() {
		return new Auror();
	}

	public Comandante crearComandante() {
		return new Comandante();
	}

	public Profesor crearProfesor() {
		return new Profesor();
	}

	public Seguidor crearSeguidor() {
		return new Seguidor();
	}

	public Mago crearMagoAleatorio() {
		Random rand = new Random();
		int numeroAleatorio = rand.nextInt(2);

		switch (numeroAleatorio) {
		case 0:
			return crearProfesor();
		case 1:
			return crearAuror();

		}
		return crearEstudiante(); // case 2:
		
	}
	
	public Mortifago crearMortifagoAleatorio() {
		Random rand = new Random();
		int numeroAleatorio = rand.nextInt(1);

		if(numeroAleatorio == 0) {
			return crearComandante();
		}
		return crearSeguidor();
		
	}
}
