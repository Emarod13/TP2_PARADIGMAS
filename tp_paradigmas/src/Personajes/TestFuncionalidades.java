package Personajes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jpl7.Query;
import org.junit.jupiter.api.Test;

import Hechizos.AvadaKedavra;
import Hechizos.ExpectoPatronum;

public class TestFuncionalidades {

	@Test
	public void AvadaKedavraFuncionalidades() {
		AvadaKedavra test = new AvadaKedavra();
		Estudiante objetivo = new Estudiante("Objetivo", null);
		Comandante usuario = new Comandante("Lanzador", null);
		usuario.setLista_de_hechizos(new HashMap<>());
		usuario.getLista_de_hechizos().put("AvadaKedavra", test);

		int energia_inicial = usuario.getEnergia();
		int costo = test.getCostoEnergia();

		usuario.atacar(objetivo, "AvadaKedavra");

		assertEquals(true, objetivo.isVivo()); // no deberia poder matarlo
		assertEquals(energia_inicial - costo, usuario.getEnergia()); // energia reducida

		objetivo.recibirDaño(200); // lo bajamos para que pueda morir
		usuario.atacar(objetivo, "AvadaKedavra");

		assertEquals(false, objetivo.isVivo()); // deberia poder matarlo

	}

	@Test
	public void pruebaEfectos() {
		Profesor test = new Profesor("Objeto de prueba", null);
		Seguidor objetivo = new Seguidor("Objetivo", null);
		test.setLista_de_hechizos(new HashMap<>());
		ExpectoPatronum hechizo = new ExpectoPatronum();
		test.getLista_de_hechizos().put("ExpectoPatronum", hechizo);
		test.aplicarEfecto("Sangrado", 2);
		objetivo.aplicarEfecto("Protegido", 1);
		test.aplicarEfecto("Desarmado", 1);

		test.atacar(objetivo, "ExpectoPatronum"); // al estar desarmado, no deberia poder atacar
		assertEquals(objetivo.getPuntos_de_vida(), objetivo.getVida_inicial());

		test.procesarEfectos(); // en este turno, el sangrado no tendria que haber hecho daño
		assertEquals(test.getPuntos_de_vida(), test.getVida_inicial() - 10); // con daño del sangrado

		test.atacar(objetivo, "ExpectoPatronum"); // al estar el objetivo protegido, no deberia poder atacar
		assertEquals(objetivo.getPuntos_de_vida(), objetivo.getVida_inicial());

		objetivo.procesarEfectos();
		test.atacar(objetivo, "ExpectoPatronum"); // ahora si deberia poder
		assertEquals(objetivo.getPuntos_de_vida(), objetivo.getVida_inicial() - hechizo.getDaño());

		test.procesarEfectos(); // en este turno si
		assertEquals(test.getPuntos_de_vida(), test.getVida_inicial() - 20); // con daño del sangrado

	}

	@Test
	public void BatallonesFuncionalidades() {
		Batallon magos = new Batallon(new ArrayList<>(PersonajeFactory.generarMagos()));
		Batallon mortifagos = new Batallon(new ArrayList<>(PersonajeFactory.generarMortifagos()));

		magos.getMiembros().forEach(personaje -> personaje.aplicarEfecto("Sangrado", 1));
		mortifagos.getMiembros().forEach(personaje -> personaje.aplicarEfecto("Sangrado", 1));

		magos.procesarEfectos();
		magos.getMiembros()
				.forEach(personaje -> assertEquals(personaje.getPuntos_de_vida(), personaje.getVida_inicial() - 10));
		mortifagos.procesarEfectos();
		mortifagos.getMiembros()
				.forEach(personaje -> assertEquals(personaje.getPuntos_de_vida(), personaje.getVida_inicial() - 10));

		magos.getMiembros().forEach(personaje -> personaje.morir());

		mortifagos.getMiembros().forEach(personaje -> personaje.morir());

		assertEquals(magos.tienePersonajesVivos(), false);
		assertEquals(mortifagos.tienePersonajesVivos(), false);
	}

	@Test
	public void PrologJuego() {
		Juego juego = new Juego();

		Batallon test = new Batallon(new ArrayList<>(PersonajeFactory.generarMagos()));
		Batallon objetivo = new Batallon(new ArrayList<>(PersonajeFactory.generarMortifagos()));
		juego.cargarOActualizarPersonajeEnProlog(test.getMiembros());
		juego.cargarOActualizarPersonajeEnProlog(objetivo.getMiembros());
		test.getMiembros().forEach(personaje->juego.cargarHechizosEnProlog(personaje));
		objetivo.getMiembros().forEach(personaje->juego.cargarHechizosEnProlog(personaje));
		
		objetivo.atacar(test, juego);
		
		
		
		
	}
}
