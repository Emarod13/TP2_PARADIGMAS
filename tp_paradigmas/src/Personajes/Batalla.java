package Personajes;

import java.util.Random;

public class Batalla {

	private Batallon magos;
	private Batallon mortifagos;

	public Batalla() {
		magos = new Batallon();
		mortifagos = new Batallon();
	}

	public void iniciar() {
		Random rand = new Random();

		if (rand.nextBoolean() == true) { // COMIENZAN LOS MAGOS
			while (magos.tienePersonajesVivos() && mortifagos.tienePersonajesVivos()) {
				magos.atacar(mortifagos);
				mortifagos.atacar(magos);
			}
		} else { // COMIENZAN LOS MORTIFAGOS
			while (magos.tienePersonajesVivos() && mortifagos.tienePersonajesVivos()) {
				mortifagos.atacar(mortifagos);
				magos.atacar(magos);
			}

		}

	}
	
}
