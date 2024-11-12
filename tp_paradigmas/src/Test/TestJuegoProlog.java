package Test;

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
import Hechizos.HechizoStrategy;
import JuegoClases.Juego;
import Personajes.Auror;
import Personajes.Batallon;
import Personajes.Comandante;
import Personajes.Personaje;
import Personajes.PersonajeFactory;
import Personajes.Seguidor;


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
	public void ejecutarAccionUnitaria() {
		Batallon test = new Batallon(new ArrayList<>(PersonajeFactory.generarMagos()));
		Personaje prueba = PersonajeFactory.generarMortifagos().get(0);
		System.out.println(prueba.getNombre());
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
		System.out.println(prueba.getNombre());
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
	
	@Test
	public void ataqueBatallon() {
		Batallon test = new Batallon(new ArrayList<>(PersonajeFactory.generarMagos()));
		Batallon objetivo = new Batallon(new ArrayList<>(PersonajeFactory.generarMortifagos()));
		juego.cargarOActualizarPersonajeEnProlog(test.getMiembros());
		juego.cargarOActualizarPersonajeEnProlog(objetivo.getMiembros());
		System.out.println("PERSONAJES Y HECHIZOS CARGADOS EN PROLOG, COMIENZA EL JUEGO!!!");
		System.out.println("--------------------------------------------------------------");
		juego.cargarHechizosEnProlog(test.getMiembros());
		juego.cargarHechizosEnProlog(objetivo.getMiembros());
		
		test.atacar(objetivo, juego);
	}

}
