package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import Hechizos.AvadaKedavra;
import Hechizos.Episkey;
import Hechizos.ExpectoPatronum;
import Hechizos.Expelliarmus;
import Hechizos.HechizoStrategy;
import Hechizos.LaceroMortis;
import Hechizos.LuxExterminus;
import Hechizos.Protego;
import Hechizos.SectumSempra;
import Hechizos.SomnumEternum;
import Hechizos.TenebrisScutum;
import Personajes.Comandante;
import Personajes.Estudiante;
import Personajes.Profesor;



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

		objetivo.recibirDaño(400); // lo bajamos para que pueda morir
		usuario.atacar(objetivo, test.getNombre());

		assertFalse(objetivo.isVivo()); // deberia poder matarlo

	}

	@Test
	public void SectumSempraFuncionalidades() {
		SectumSempra test = new SectumSempra();
		usuario.getLista_de_hechizos().put(test.getNombre(), test);

		usuario.atacar(objetivo, test.getNombre());

		assertEquals(usuario.getEnergia_inicial() - test.getCostoEnergia(), usuario.getEnergia());
		assertEquals(objetivo.getVida_inicial() - test.getDaño(), objetivo.getPuntos_de_vida());

		assertTrue(objetivo.getEfectos_aplicados().containsKey("Sangrado"));

	}

	@Test
	public void EpiskeyFuncionalidades() {
		Episkey test = new Episkey();
		usuario.getLista_de_hechizos().put(test.getNombre(), test);

		usuario.defender(test.getNombre());

		assertEquals(usuario.getEnergia_inicial() - test.getCostoEnergia(), usuario.getEnergia());
		assertEquals(usuario.getVida_inicial() + test.getCuracion(), usuario.getPuntos_de_vida());

	}
	@Test
	public void TenebrisScutum() {
		TenebrisScutum test = new TenebrisScutum();
		ExpectoPatronum hechizoAtaque = new ExpectoPatronum();
		usuario.getLista_de_hechizos().put(test.getNombre(), test);
		objetivo.getLista_de_hechizos().put(hechizoAtaque.getNombre(), hechizoAtaque);
		usuario.defender(test.getNombre());
		
		assertEquals(usuario.getEnergia_inicial()-test.getCostoEnergia(),usuario.getEnergia());
		assertTrue(usuario.getEfectos_aplicados().containsKey("Protegido"));
		
		objetivo.atacar(usuario,hechizoAtaque.getNombre());
		
		assertEquals(usuario.getVida_inicial(),usuario.getPuntos_de_vida());
	}
	@Test
	public void LaceroMortis() {
		LaceroMortis test = new LaceroMortis();
		ExpectoPatronum hechizoAtaque = new ExpectoPatronum()
				;
		usuario.getLista_de_hechizos().put(test.getNombre(), test);
		objetivo.getLista_de_hechizos().put(hechizoAtaque.getNombre(), hechizoAtaque);
		usuario.atacar(objetivo,test.getNombre());
		
		assertEquals(usuario.getEnergia_inicial()-test.getCostoEnergia(),usuario.getEnergia());
		assertTrue(objetivo.getEfectos_aplicados().containsKey("Desarmado"));
		
		objetivo.atacar(usuario,hechizoAtaque.getNombre());
		
		assertEquals(usuario.getVida_inicial(),usuario.getPuntos_de_vida());
	}

	/// HECHIZOS DE MAGO
	// ----------------------------------------
	@Test
	public void ExpectoPatronumFuncionalidades() {
		ExpectoPatronum test = new ExpectoPatronum();
		objetivo.getLista_de_hechizos().put(test.getNombre(), test);

		objetivo.atacar(usuario, test.getNombre());

		assertEquals(objetivo.getEnergia_inicial() - test.getCostoEnergia(), objetivo.getEnergia());
		assertEquals(usuario.getVida_inicial() - test.getDaño(), usuario.getPuntos_de_vida());

	}
	@Test
	public void ProtegoFuncionalidades() {
		Protego test = new Protego();
		SectumSempra hechizoAtaque = new SectumSempra();
		objetivo.getLista_de_hechizos().put(test.getNombre(), test);
		usuario.getLista_de_hechizos().put(hechizoAtaque.getNombre(),hechizoAtaque);

		objetivo.defender(test.getNombre());
		
		assertEquals(objetivo.getEnergia_inicial() - test.getCostoEnergia(), objetivo.getEnergia());
		assertTrue(objetivo.getEfectos_aplicados().containsKey("Protegido"));
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

		assertEquals(objetivo.getEnergia_inicial() - test.getCostoEnergia(), objetivo.getEnergia());
		
		assertTrue(usuario.isDesarmado());
		
		usuario.atacar(objetivo, hechizoAtaque.getNombre());
		
		assertEquals(objetivo.getPuntos_de_vida(),objetivo.getVida_inicial());
		
		
		
	}
	@Test
	public void LuxExterminus() {
		Profesor lanzador = new Profesor("Lanzador",new HashMap<String, HechizoStrategy>());
		LuxExterminus test = new LuxExterminus();
		lanzador.getLista_de_hechizos().put("LuxExterminus", test);
		
		int energia_inicial = lanzador.getEnergia();
		int costo = test.getCostoEnergia();
		
		lanzador.atacar(usuario, test.getNombre());

		assertEquals(true, usuario.isVivo()); // no deberia poder matarlo
		assertEquals(energia_inicial - costo, lanzador.getEnergia()); // energia reducida
		
		lanzador.setEnergia(energia_inicial);
		usuario.recibirDaño(450); // lo bajamos para que pueda morir
		lanzador.atacar(usuario, test.getNombre());

		assertFalse(usuario.isVivo()); // deberia poder matarlo
	}
	@Test
	public void SomnumEternum() {
		SomnumEternum test = new SomnumEternum();
		objetivo.getLista_de_hechizos().put("SomnumEternum", test);
		int costo = test.getCostoEnergia();
		
		objetivo.atacar(usuario, test.getNombre());
		assertEquals(objetivo.getEnergia_inicial()-costo,objetivo.getEnergia());
		
		assertEquals(usuario.getVida_inicial()-test.getDaño(),usuario.getPuntos_de_vida());
		
		assertTrue(usuario.getEfectos_aplicados().containsKey("Agotado"));
		
	}

}
