package JuegoClases;

import java.util.Random;


public class Main {
	public static void main(String[] args) {
		Random rand = new Random();
		Batallon magos = new Batallon(PersonajeFactory.generarMagos());
		Batallon mortifagos = new Batallon(PersonajeFactory.generarMortifagos());
		Juego juego = new Juego();

		juego.cargarOActualizarPersonajeEnProlog(magos.getMiembros());
		juego.cargarOActualizarPersonajeEnProlog(mortifagos.getMiembros());
		juego.cargarHechizosEnProlog(magos.getMiembros());
		juego.cargarHechizosEnProlog(mortifagos.getMiembros());
		System.out.println("TODO CARGADO EN PROLOG, COMIENZA EL JUEGO!!!!");
		System.out.println("-------------------------------------------");
		System.out.println("-------------------------------------------");
		

		do {
			if (rand.nextBoolean()) {
				magos.atacar(mortifagos, juego);
				mortifagos.atacar(magos, juego);
				magos.procesarEfectos();
				mortifagos.procesarEfectos();
				magos.recuperarEnergia();
				mortifagos.recuperarEnergia();
				juego.cargarOActualizarPersonajeEnProlog(magos.getMiembros());
				juego.cargarOActualizarPersonajeEnProlog(mortifagos.getMiembros());
				System.out.println("SIGUIENTE RONDA!!!!");
				System.out.println("-------------------------------------------");
				System.out.println("-------------------------------------------");
			} else {
				mortifagos.atacar(magos, juego);
				magos.atacar(mortifagos, juego);
				mortifagos.procesarEfectos();
				magos.procesarEfectos();
				mortifagos.recuperarEnergia();
				magos.recuperarEnergia();
				juego.cargarOActualizarPersonajeEnProlog(magos.getMiembros());
				juego.cargarOActualizarPersonajeEnProlog(mortifagos.getMiembros());
				System.out.println("SIGUIENTE RONDA!!!!");
				System.out.println("-------------------------------------------");
				System.out.println("-------------------------------------------");

			}

		} while (magos.tienePersonajesVivos() && mortifagos.tienePersonajesVivos());
		
		if(magos.tienePersonajesVivos()) {
			System.out.println("magos ganadores!!");
		}
		else {
			System.out.println("mortifagos ganadores!!");
		}

	}
}
