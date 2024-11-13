package JuegoClases;

import java.util.Random;

import Personajes.Batallon;
import Personajes.PersonajeFactory;


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
		System.out.println("\n-------------------------------------------\n");
		System.out.println("TODO CARGADO EN PROLOG, COMIENZA EL JUEGO!!!!");
		System.out.println("\n-------------------------------------------\n");
		

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
				System.out.println("\n-------------------------------------------\n");
				System.out.println("TODO LISTO, SIGUIENTE RONDA!!!!");
				System.out.println("\n-------------------------------------------\n");
			} else {
				mortifagos.atacar(magos, juego);
				magos.atacar(mortifagos, juego);
				mortifagos.procesarEfectos();
				magos.procesarEfectos();
				mortifagos.recuperarEnergia();
				magos.recuperarEnergia();
				juego.cargarOActualizarPersonajeEnProlog(magos.getMiembros());
				juego.cargarOActualizarPersonajeEnProlog(mortifagos.getMiembros());
				System.out.println("\n-------------------------------------------\n");
				System.out.println("TODO LISTO, SIGUIENTE RONDA!!!!");
				System.out.println("\n-------------------------------------------\n");
			

			}

		} while (magos.tienePersonajesVivos() && mortifagos.tienePersonajesVivos());
		
		if(magos.tienePersonajesVivos()) {
			System.out.println("-------------------------------------------");
			System.out.println("Magos ganadores!!");
			System.out.println("-------------------------------------------");
		}
		else {
			System.out.println("-------------------------------------------");
			System.out.println("Mortifagos ganadores!!");
			System.out.println("-------------------------------------------");
		}

	}
}
