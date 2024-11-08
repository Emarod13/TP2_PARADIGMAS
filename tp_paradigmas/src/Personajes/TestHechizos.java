package Personajes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import Hechizos.AvadaKedavra;

class TestHechizos {

	@Test
	public void AvadaKedavraFuncionalidades() {
		AvadaKedavra test = new AvadaKedavra();
		Estudiante objetivo = new Estudiante("Objetivo", null);
		Comandante usuario = new Comandante("Lanzador", null);
		usuario.setLista_de_hechizos(new HashMap<>());
		usuario.getLista_de_hechizos().put("AvadaKedavra", test);

		int energia_inicial = usuario.getEnergia();
		int costo = test.getCostoEnergia();

		usuario.atacar(objetivo, test.getNombre());

		assertEquals(true, objetivo.isVivo()); // no deberia poder matarlo
		assertEquals(energia_inicial - costo, usuario.getEnergia()); // energia reducida

		objetivo.recibirDaño(200); // lo bajamos para que pueda morir
		usuario.atacar(objetivo, test.getNombre());

		assertEquals(false, objetivo.isVivo()); // deberia poder matarlo

	}

}
