package Personajes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import Hechizos.*;

class TestHechizos {
	private Estudiante objetivo = new Estudiante("Mago estudiante", new HashMap<>());
	private Comandante usuario = new Comandante("Mortifago Comandante", new HashMap<>());

	/// HECHIZOS DE MORTIFAGO
	/// ------------------------------------------------------
	@Test
	public void AvadaKedavraFuncionalidades() {
		AvadaKedavra test = new AvadaKedavra();
		usuario.getLista_de_hechizos().put("AvadaKedavra", test);

		int energia_inicial = usuario.getEnergia();
		int costo = test.getCostoEnergia();

		usuario.atacar(objetivo, test.getNombre());

		assertEquals(true, objetivo.isVivo()); // no deberia poder matarlo
		assertEquals(energia_inicial - costo, usuario.getEnergia()); // energia reducida

		objetivo.recibirDaño(200); // lo bajamos para que pueda morir
		usuario.atacar(objetivo, test.getNombre());

		assertFalse(objetivo.isVivo()); // deberia poder matarlo

	}

	@Test
	public void SectumSempraFuncionalidades() {
		SectumSempra test = new SectumSempra();
		usuario.getLista_de_hechizos().put(test.getNombre(), test);

		usuario.atacar(objetivo, test.getNombre());

		assertEquals(usuario.energia_inicial - test.getCostoEnergia(), usuario.getEnergia());
		assertEquals(objetivo.vida_inicial - test.getDaño(), objetivo.getPuntos_de_vida());

		assertTrue(objetivo.efectos_aplicados.containsKey("Sangrado"));

	}

	@Test
	public void EpiskeyFuncionalidades() {
		Episkey test = new Episkey();
		usuario.getLista_de_hechizos().put(test.getNombre(), test);

		usuario.defender(test.getNombre());

		assertEquals(usuario.energia_inicial - test.getCostoEnergia(), usuario.getEnergia());
		assertEquals(usuario.vida_inicial + test.getCuracion(), usuario.getPuntos_de_vida());

	}

	/// HECHIZOS DE MAGO
	// ----------------------------------------
	@Test
	public void ExpectoPatronumFuncionalidades() {
		ExpectoPatronum test = new ExpectoPatronum();
		objetivo.getLista_de_hechizos().put(test.getNombre(), test);

		objetivo.atacar(usuario, test.getNombre());

		assertEquals(objetivo.energia_inicial - test.getCostoEnergia(), objetivo.getEnergia());
		assertEquals(usuario.vida_inicial - test.getDaño(), usuario.getPuntos_de_vida());

	}
	@Test
	public void ProtegoFuncionalidades() {
		Protego test = new Protego();
		SectumSempra hechizoAtaque = new SectumSempra();
		objetivo.getLista_de_hechizos().put(test.getNombre(), test);
		usuario.getLista_de_hechizos().put(hechizoAtaque.getNombre(),hechizoAtaque);

		objetivo.defender(test.getNombre());
		
		assertEquals(objetivo.energia_inicial - test.getCostoEnergia(), objetivo.getEnergia());
		assertTrue(objetivo.efectos_aplicados.containsKey("Protegido"));
		assertTrue(objetivo.isProtegido());
		
		usuario.atacar(objetivo, hechizoAtaque.getNombre());
		assertEquals(objetivo.getPuntos_de_vida(),objetivo.getVida_inicial());

	}
	@Test
	public void ExpelliarmusFuncionalidades() {
		Expelliarmus test = new Expelliarmus();
		SectumSempra hechizoAtaque = new SectumSempra();
		objetivo.getLista_de_hechizos().put(test.getNombre(), test);
		usuario.getLista_de_hechizos().put(hechizoAtaque.getNombre(),hechizoAtaque);
		
		objetivo.atacar(usuario, test.getNombre());

		assertEquals(objetivo.energia_inicial - test.getCostoEnergia(), objetivo.getEnergia());
		
		assertTrue(usuario.isDesarmado());
		
		usuario.atacar(objetivo, hechizoAtaque.getNombre());
		
		assertEquals(objetivo.getPuntos_de_vida(),objetivo.getVida_inicial());
		
		
		
	}

}