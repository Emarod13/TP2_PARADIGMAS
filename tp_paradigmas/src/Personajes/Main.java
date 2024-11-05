package Personajes;

import java.util.Random;

import org.jpl7.*;

public class Main {
	public static void main(String[] args) {
		Random rand = new Random();
		Batallon magos = new Batallon();
		Batallon mortifagos = new Batallon();
		Juego juego = new Juego();

		juego.cargarPersonajesEnProlog(magos.getMiembros());
		juego.cargarPersonajesEnProlog(mortifagos.getMiembros());
		juego.cargarHechizosEnProlog(magos.getMiembros());
		juego.cargarHechizosEnProlog(mortifagos.getMiembros());

		do {
			if (rand.nextBoolean()) {
				magos.atacar(mortifagos, juego);
				mortifagos.atacar(magos, juego);
				magos.procesarEfectos();
				mortifagos.procesarEfectos();
			} else {
				mortifagos.atacar(magos, juego);
				magos.atacar(mortifagos, juego);
				mortifagos.procesarEfectos();
				magos.procesarEfectos();

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
