package Personajes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import Hechizos.ExpectoPatronum;

class TestPersonajes {
	
	public void pruebaMorir() {
		
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
}
