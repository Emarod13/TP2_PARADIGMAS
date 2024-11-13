package Test;


import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import Hechizos.AvadaKedavra;
import Hechizos.Episkey;
import Hechizos.ExpectoPatronum;
import Hechizos.SectumSempra;
import Personajes.Auror;
import Personajes.Batallon;
import Personajes.Comandante;
import Personajes.Estudiante;
import Personajes.Personaje;
import Personajes.PersonajeFactory;
import Personajes.Profesor;
import Personajes.Seguidor;

public class TestPersonajes {
	private Personaje prueba = new Comandante("Bellatrix Lestrange",new HashMap<>());;
	
	@Test
	public void atacarSinEnergia () {
		System.out.println("************************************************************");
		int energiaInicial = 175;
		Personaje atacante = new Seguidor("Atacante de prueba", new HashMap<>());
		Personaje objetivo = new Estudiante("Objetivo de Prueba", new HashMap<>());
		AvadaKedavra hechizo = new AvadaKedavra();
		atacante.getLista_de_hechizos().put(hechizo.getNombre(), hechizo);
		
		//Energia inferior al hechizo que usara
		
		atacante.atacar(objetivo, hechizo.getNombre());
		
		assertEquals(atacante.getEnergia_inicial(), energiaInicial);
		System.out.println("************************************************************");
	}
	@Test
	public void recuperarEnergia() {
		int energia=20;
		prueba.recuperarEnergia(20);
		assertEquals(prueba.getEnergia(), prueba.getEnergia_inicial()+energia);
	}
	@Test
	public void estaDebilitado() {
		prueba.setPuntos_de_vida((int) (prueba.getVida_inicial()*0.4));
		assertTrue(prueba.estaDebilitado());
	}
	@Test
	public void defenderse() {
		Episkey hechizoDefensa = new Episkey();
		prueba.getLista_de_hechizos().put(hechizoDefensa.getNombre(), hechizoDefensa);
		
		prueba.defender(hechizoDefensa.getNombre());
		
		assertEquals(prueba.getVida_inicial()+hechizoDefensa.getCuracion(),prueba.getPuntos_de_vida());
		assertEquals(prueba.getEnergia(),prueba.getEnergia_inicial()-hechizoDefensa.getCostoEnergia());
		
	}
	@Test
	public void atacar() {
		Personaje objetivo = new Auror("Harry Potter",new HashMap<>());
		SectumSempra hechizoAtaque = new SectumSempra();
		prueba.getLista_de_hechizos().put(hechizoAtaque.getNombre(), hechizoAtaque);
		prueba.atacar(objetivo, hechizoAtaque.getNombre());
		
		assertEquals(prueba.getEnergia_inicial()-hechizoAtaque.getCostoEnergia(),prueba.getEnergia());
		assertEquals(objetivo.getVida_inicial()-hechizoAtaque.getDaño(),objetivo.getPuntos_de_vida());
	}
	@Test
	public void pruebaMorir() {
		prueba.morir();;
		
		assertFalse(prueba.isVivo());
	
	}
	@Test
	public void recibirDaño() {
		int daño=30;
		prueba.recibirDaño(daño);
		
		assertEquals(prueba.getPuntos_de_vida(), prueba.getVida_inicial()-daño);
		
		prueba.recibirDaño(prueba.getPuntos_de_vida());
		//deberia morir
		
		assertFalse(prueba.isVivo());
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
		
		assertTrue(test.isDesarmado());
		assertTrue(objetivo.isProtegido());

		test.atacar(objetivo, "ExpectoPatronum"); // al estar desarmado, no deberia poder atacar
		assertEquals(objetivo.getPuntos_de_vida(), objetivo.getVida_inicial());

		test.procesarEfectos(); 
		assertEquals(test.getPuntos_de_vida(), test.getVida_inicial() - 25); // con daño del sangrado

		test.atacar(objetivo, "ExpectoPatronum"); // al estar el objetivo protegido, no deberia poder atacar
		assertEquals(objetivo.getPuntos_de_vida(), objetivo.getVida_inicial());

		objetivo.procesarEfectos();
		test.atacar(objetivo, "ExpectoPatronum"); // ahora si deberia poder
		assertEquals(objetivo.getPuntos_de_vida(), objetivo.getVida_inicial() - hechizo.getDaño());

		test.procesarEfectos(); // en este turno si
		assertEquals(test.getPuntos_de_vida(), test.getVida_inicial() - 50); // con daño del sangrado

	}
	@Test
	public void BatallonesFuncionalidades() {
		Batallon magos = new Batallon(new ArrayList<>(PersonajeFactory.generarMagos()));
		Batallon mortifagos = new Batallon(new ArrayList<>(PersonajeFactory.generarMortifagos()));

		magos.getMiembros().forEach(personaje -> personaje.aplicarEfecto("Sangrado", 1));
		mortifagos.getMiembros().forEach(personaje -> personaje.aplicarEfecto("Sangrado", 1));

		magos.procesarEfectos();
		magos.getMiembros()
				.forEach(personaje -> assertEquals(personaje.getPuntos_de_vida(), personaje.getVida_inicial() - 25));
		mortifagos.procesarEfectos();
		mortifagos.getMiembros()
				.forEach(personaje -> assertEquals(personaje.getPuntos_de_vida(), personaje.getVida_inicial() - 25));

		magos.getMiembros().forEach(personaje -> personaje.morir());

		mortifagos.getMiembros().forEach(personaje -> personaje.morir());

		assertFalse(magos.tienePersonajesVivos());
		assertFalse(mortifagos.tienePersonajesVivos());
	}
}
