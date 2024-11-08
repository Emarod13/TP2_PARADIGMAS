package Personajes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.jpl7.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import Hechizos.AvadaKedavra;
import Hechizos.Episkey;
import Hechizos.ExpectoPatronum;
import Hechizos.HechizoStrategy;

public class TestJuegoProlog {

	private Juego juego = new Juego();

	@Before
	public void setUp() {
		// Inicializar el objeto Juego y limpiar hechos en Prolog
		juego = new Juego();
		new Query("retractall(personaje(_, _, _, _))").hasSolution();
		new Query("retractall(hechizo(_, _, _, _))").hasSolution();
		new Query("retractall(objetivos_recomendados(_, _))").hasSolution();
	}

	@After
	public void tearDown() {
		// Limpiar hechos en Prolog después de cada prueba
		new Query("retractall(personaje(_, _, _, _))").hasSolution();
		new Query("retractall(hechizo(_, _, _, _))").hasSolution();
		new Query("retractall(objetivos_recomendados(_, _))").hasSolution();
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

		Batallon test = new Batallon(new ArrayList<>(PersonajeFactory.generarMagos()));
		Batallon objetivo = new Batallon(new ArrayList<>(PersonajeFactory.generarMortifagos()));
		juego.cargarOActualizarPersonajeEnProlog(test.getMiembros());
		juego.cargarOActualizarPersonajeEnProlog(objetivo.getMiembros());
		test.getMiembros().forEach(personaje -> juego.cargarHechizosEnProlog(personaje));
		objetivo.getMiembros().forEach(personaje -> juego.cargarHechizosEnProlog(personaje));

		// objetivo.atacar(test, juego);

		Personaje prueba = objetivo.getMiembros().getFirst();

		prueba.setPuntos_de_vida(10);
		juego.actuar(prueba, test.getMiembros());
		prueba.setPuntos_de_vida(500);
		juego.actuar(prueba, test.getMiembros()); // deberia atacar, ya que tiene toda la vida.

		// prueba.setPuntos_de_vida(10); // deberia defenderse.

		// prueba.setPuntos_de_vida(10); // deberia defenderse.
		/*
		 * Personaje objetivoDebil1 = test.getMiembros().get(3);
		 * objetivoDebil1.setPuntos_de_vida(20);
		 * juego.cargarOActualizarPersonajeEnProlog(objetivoDebil1);
		 * System.out.println(objetivoDebil1.getNombre());
		 */
		/*
		 * Personaje objetivoDebil2 = test.getMiembros().get(4);
		 * objetivoDebil2.setPuntos_de_vida(20);
		 * juego.cargarOActualizarPersonajeEnProlog(objetivoDebil2);
		 * System.out.println(objetivoDebil2.getNombre());
		 */

	}

	@Test
	public void ejecutarAccionUnitaria() {
		Batallon test = new Batallon(new ArrayList<>(PersonajeFactory.generarMagos()));
		Personaje prueba = PersonajeFactory.generarMortifagos().get(0);
		System.out.println(prueba.nombre);
		System.out.println("Energia: " + prueba.getEnergia());
		System.out.println("Vida: " + prueba.getPuntos_de_vida());
		juego.cargarOActualizarPersonajeEnProlog(prueba);
		juego.cargarHechizosEnProlog(prueba);
		juego.cargarOActualizarPersonajeEnProlog(test.getMiembros());

		juego.ejecutarAccion("Atacar", prueba, test.getMiembros()); // deberia atacar
		juego.ejecutarAccion("Defender", prueba, test.getMiembros()); // deberia defenderse
		juego.ejecutarAccion("defenderseee", prueba, test.getMiembros()); // no deberia hacer nada, accion incorrecta

	}

	@Test
	public void tomarDecisionUnitaria() {
		Batallon test = new Batallon(new ArrayList<>(PersonajeFactory.generarMagos()));
		Personaje prueba = PersonajeFactory.generarMortifagos().get(0);
		System.out.println(prueba.nombre);
		System.out.println("Energia: " + prueba.getEnergia());
		System.out.println("Vida: " + prueba.getPuntos_de_vida());
		juego.cargarOActualizarPersonajeEnProlog(prueba);
		juego.cargarHechizosEnProlog(prueba);
		juego.cargarOActualizarPersonajeEnProlog(test.getMiembros());

		String decision;

		decision = juego.tomarDecision(prueba, test.getMiembros()); // al estar con vida al maximo, deberia decidir
																	// atacar

		assertEquals(decision, "Atacar");

		prueba.setPuntos_de_vida(10);
		System.out.println("Vida: " + prueba.getPuntos_de_vida());
		decision = juego.tomarDecision(prueba, test.getMiembros());
		assertEquals(decision, "Defender"); // al estar con poca vida, decide defenderse
	}

	@Test
	public void eleccionHechizoUnitaria() {
		// Crear un personaje con un hechizo específico en su lista de hechizos
		Personaje personaje = new Comandante("Bellatrix Lestrange", new HashMap<>());
		HechizoStrategy hechizoAtaque = new AvadaKedavra();
		HechizoStrategy hechizoDefensa = new Episkey();

		juego.cargarHechizosEnProlog(personaje);

		String hechizoSeleccionado = juego.elegirHechizo(personaje, "Ata");
		assertEquals(null, hechizoSeleccionado);

		personaje.getLista_de_hechizos().put("AvadaKedavra", hechizoAtaque);
		personaje.getLista_de_hechizos().put("Episkey", hechizoDefensa);

		// Cargar el hechizo en Prolog y luego llamar a elegirHechizo para ver si
		// devuelve el hechizo correcto
		juego.cargarHechizosEnProlog(personaje);
		hechizoSeleccionado = juego.elegirHechizo(personaje, "Ataque").replace("'", "");

		assertEquals("AvadaKedavra", hechizoSeleccionado);

		hechizoSeleccionado = juego.elegirHechizo(personaje, "Defensa").replace("'", "");
		assertEquals("Episkey", hechizoSeleccionado);
	}

	@Test
	public void testCargarOActualizarPersonajeUnitaria() {
		// Crear un personaje y asignarle puntos de vida y energía
		Personaje personaje = new Comandante("Bellatrix Lestrange", null);

		// Ejecutar el método que carga o actualiza el personaje en Prolog
		juego.cargarOActualizarPersonajeEnProlog(personaje);

		// Verificar si el personaje ha sido cargado o actualizado (este caso depende de
		// que el código de Prolog esté funcionando)
		Query consulta = new Query("personaje('Bellatrix Lestrange', 'Mortifago', 500, 300)");
		assertTrue(consulta.hasSolution());
	}

	@Test
	public void testCargarHechizosEnPrologUnitaria() {
		// Crear un personaje con varios hechizos
		Personaje personaje = new Comandante("Bellatrix Lestrange", new HashMap<>());
		HechizoStrategy hechizoAtaque = new AvadaKedavra();
		HechizoStrategy hechizoDefensa = new Episkey();
		personaje.getLista_de_hechizos().put("AvadaKedavra", hechizoAtaque);
		personaje.getLista_de_hechizos().put("Episkey", hechizoDefensa);
		// Cargar los hechizos en Prolog
		juego.cargarHechizosEnProlog(personaje);

		// Comprobar si ambos hechizos fueron cargados en Prolog
		Query consultaAtaque = new Query(String.format("hechizo('AvadaKedavra', 'Bellatrix Lestrange',%d, '%s')",
				hechizoAtaque.getCostoEnergia(), hechizoAtaque.getTipo()));
		Query consultaDefensa = new Query(String.format("hechizo('Episkey', 'Bellatrix Lestrange', %d, '%s')",
				hechizoDefensa.getCostoEnergia(), hechizoDefensa.getTipo()));

		assertTrue(consultaAtaque.hasSolution());
		assertTrue(consultaDefensa.hasSolution());
	}

	@Test
	public void testObtenerObjetivoUnitaria() {
		// Crear personajes y enemigos
		Personaje personaje = new Auror("Harry Potter", new HashMap<>());

		Personaje enemigo1 = new Seguidor("Lucius Malfoy", new HashMap<>());
		Personaje enemigo2 = new Comandante("Severus Snape", new HashMap<>());

		List<Personaje> enemigos = Arrays.asList(enemigo1, enemigo2);
		enemigo1.setPuntos_de_vida(10);
		juego.cargarOActualizarPersonajeEnProlog(enemigos);

		// Ejecutar el método
		Personaje objetivo = juego.obtenerObjetivo(personaje, enemigos);

		// Verificar que me devuelve el objetivo "debilitado"
		assertEquals("Lucius Malfoy", objetivo.getNombre());
	}

}
