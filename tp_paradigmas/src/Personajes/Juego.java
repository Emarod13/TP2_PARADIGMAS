package Personajes;

import org.jpl7.Query;
import org.jpl7.Term;

import Hechizos.HechizoStrategy;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Juego {
	private Random random = new Random();

	public Juego() {
		// Cargar las reglas de Prolog al iniciar el juego
		Query cargarReglas = new Query("consult('batalla.pl')");
		if (!cargarReglas.hasSolution()) {
			System.err.println("No se pudieron cargar las reglas de Prolog");
		}
	}

	public void cargarOActualizarPersonajeEnProlog(Personaje personaje) {
		String nombre = personaje.getNombre();

	    // Verificar si el personaje está vivo; si no, eliminar sus hechos en Prolog y salir
	    if (!personaje.isVivo()) {
	        // Eliminar cualquier hecho `personaje` y `esta_debilitado` existente para este personaje
	        String retractPersonaje = String.format("retractall(personaje('%s', _, _, _)).", nombre);
	        Query retractPersonajeQuery = new Query(retractPersonaje);
	        retractPersonajeQuery.hasSolution();

	        String retractDebilitado = String.format("retractall(esta_debilitado('%s')).", nombre);
	        Query retractDebilitadoQuery = new Query(retractDebilitado);
	        retractDebilitadoQuery.hasSolution();

	        System.out.println("Personaje " + nombre + " ha sido eliminado en Prolog (está muerto).");
	        return;
	    }

	    // Si el personaje está vivo, actualizar o cargar el personaje en Prolog
	    int vida = personaje.getPuntos_de_vida();
	    int energia = personaje.getEnergia();

	    // Eliminar cualquier hecho `personaje` existente para este personaje en Prolog
	    String retractPersonaje = String.format("retract(personaje('%s', _, _, _)).", nombre);
	    Query retractQuery = new Query(retractPersonaje);
	    retractQuery.hasSolution();

	    // Insertar el hecho `personaje` (esto carga o actualiza el personaje)
	    String nuevoHechoPersonaje = String.format("assert(personaje('%s', '%s', %d, %d)).", nombre,
	            personaje.getTipo(), vida, energia);
	    Query assertQuery = new Query(nuevoHechoPersonaje);
	    assertQuery.hasSolution();

	    // Actualizar el estado de `esta_debilitado`
	    String retractDebilitado = String.format("retract(esta_debilitado('%s')).", nombre);
	    Query retractDebilitadoQuery = new Query(retractDebilitado);
	    retractDebilitadoQuery.hasSolution(); // Elimina `esta_debilitado` si ya existe

	    if (personaje.estaDebilitado()) {
	        String nuevoHechoDebilitado = String.format("assert(esta_debilitado('%s')).", nombre);
	        Query assertDebilitadoQuery = new Query(nuevoHechoDebilitado);
	        assertDebilitadoQuery.hasSolution();
	    }

	   // System.out.println("Personaje " + nombre + " cargado o actualizado en Prolog.");
	}

	public void cargarOActualizarPersonajeEnProlog(List<Personaje> personaje) {
		for (Personaje p : personaje) {
			cargarOActualizarPersonajeEnProlog(p);
		}
	}
	public void actuar(Personaje personaje, List<Personaje> enemigos) {
		
		if(enemigos.isEmpty()) {
			System.out.println("No quedan enemigos!!!!");
			return;
		}
		String decision = tomarDecision(personaje,enemigos);
		System.out.println(personaje.getNombre() + " decide " + decision);
		ejecutarAccion(decision, personaje, enemigos);
		
	}
	/**
	 * Toma una decisión de acción (atacar, defender o consumir) para un personaje
	 */
	public String tomarDecision(Personaje personaje, List<Personaje> enemigos) {
		// Actualizar el estado debilitado de este personaje y de sus enemigos en Prolog
		//cargarOActualizarPersonajeEnProlog(personaje);
		//enemigos.forEach(this::cargarOActualizarPersonajeEnProlog);
		try {
	        Thread.sleep(200); // 100 ms de pausa
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt(); // Restablece el estado de interrupción si se produce una excepción
	        System.out.println("Interrupción en la pausa del hilo");
	    }
		
		// Configurar la consulta para decidir la acción
		Query consulta = new Query(String.format("decidir_accion(%d, %d, Accion)", personaje.getPuntos_de_vida(),
				(int) enemigos.stream().filter(Personaje::estaDebilitado).count() // Contar enemigos debilitados
		));

		// Obtener la acción decidida por Prolog y eliminar las comillas simples
		String accion = null;
		if (consulta.hasSolution()) {
			accion = consulta.oneSolution().get("Accion").toString().replace("'", "");
		} else {
			accion = "Accion no reconocida";
		}

		// Imprimir la acción decidida para depuración
	//	System.out.println("Acción decidida: " + accion);
		
		return accion;

	}

	/**
	 * Ejecuta la acción decidida en Prolog para el personaje
	 */
	public void ejecutarAccion(String accion, Personaje personaje, List<Personaje> enemigos) {
		switch (accion) {
		case "Atacar":
			Personaje objetivo = obtenerObjetivo(personaje, enemigos);
		
			if (objetivo != null) {
				// Elegir hechizo de ataque
				String hechizoAtaque = elegirHechizo(personaje, "Ataque");
				
				if (hechizoAtaque != null) {
					// Reemplazar las comillas simples por dobles si es necesario
					
					hechizoAtaque = hechizoAtaque.replace("'", "");

					personaje.atacar(objetivo, hechizoAtaque);
					cargarOActualizarPersonajeEnProlog(objetivo);
					if(!objetivo.isVivo()) {
						enemigos.removeIf(per->per.getNombre().equals(objetivo.getNombre())); // lo saco de la lista
					}
					cargarOActualizarPersonajeEnProlog(personaje);
				
		
				} else {
					System.out.println(personaje.getNombre() + " no tiene hechizos de ataque disponibles.");
				}
			}
			break;
		case "Defender":
			// Elegir hechizo de defensa
			String hechizoDefensa = elegirHechizo(personaje, "Defensa");
			if (hechizoDefensa != null) {
				// Reemplazar las comillas simples por dobles si es necesario
				hechizoDefensa = hechizoDefensa.replace("'", "");

				personaje.defender(hechizoDefensa);
				cargarOActualizarPersonajeEnProlog(personaje);
			
			} else {
				System.out.println(personaje.getNombre() + " no tiene hechizos de defensa disponibles.");
			}
			break;
		/*
		 * case "consumir": personaje.consumirObjeto();
		 * System.out.println(personaje.getNombre() +
		 * " consume un objeto para recuperar energía"); break;
		 */
		default:
			System.out.println("Acción no reconocida");
			break;
		}
	}

	public String elegirHechizo(Personaje personaje, String tipo) {
		int energiaActual = personaje.getEnergia();
		String nombrePersonaje = personaje.getNombre();

		// Imprimir los valores de depuración
	//	System.out.println("Energía actual de " + nombrePersonaje + ": " + energiaActual);
	//	System.out.println("Vida actual de " + nombrePersonaje + ": " + personaje.getPuntos_de_vida());
	//	System.out.println("Tipo de hechizo solicitado: " + tipo);

		// Crear la consulta para Prolog
		String consulta = "hechizos_disponibles('" + nombrePersonaje + "', " + energiaActual + ", '" + tipo
				+ "', HechizosDisponibles).";

		// Imprimir la consulta Prolog generada para depuración
	//	System.out.println("Consulta Prolog: " + consulta);

		// Ejecutar la consulta
		Query q = new Query(consulta);

		// Procesar los resultados de Prolog
		if (q.hasSolution()) {
			Map<String, Term> resultado = q.oneSolution();
			Term hechizos = resultado.get("HechizosDisponibles");

			// Imprimir los hechizos disponibles que devuelve Prolog
			System.out.println("Hechizos disponibles: " + hechizos);

			// Convertir el término de lista a un array
			if (hechizos.isList() && hechizos.arity() > 0) {
				Term[] hechizosArray = Term.listToTermArray(hechizos);

				// Elegir un hechizo al azar de la lista
				Term hechizoElegido = hechizosArray[random.nextInt(hechizosArray.length)];
				System.out.println("Hechizo elegido: " + hechizoElegido.toString());
				return hechizoElegido.toString();
			} else {
				System.out.println("No se encontraron hechizos disponibles.");
			}
		} else {
			System.out.println("No se encontraron resultados en Prolog.");
		}

		return null; // No hay hechizos disponibles
	}

	/**
	 * Obtiene el objetivo recomendado de ataque para el personaje en base a Prolog
	 */
	public Personaje obtenerObjetivo(Personaje personaje, List<Personaje> enemigos) {
		Query consultaObjetivos = new Query(
				String.format("objetivos_recomendados(Objetivo, '%s')", enemigos.getFirst().getTipo()));

		if (consultaObjetivos.hasSolution()) {
			String nombreObjetivo = consultaObjetivos.oneSolution().get("Objetivo").toString().replace("'", "");
			System.out.println(nombreObjetivo);
			for (Personaje enemigo : enemigos) {
	            if (enemigo.getNombre().equals(nombreObjetivo)) {
	                return enemigo;
	            }
	        }
		}

		// Si no hay objetivo recomendado, se elige uno aleatorio
		return enemigos.get(new Random().nextInt(enemigos.size()));
	}

	public void cargarHechizosEnProlog(Personaje personaje) {
		String nombrePersonaje = personaje.getNombre();

		// Primero eliminamos los hechos de hechizos existentes para este personaje en
		// Prolog
		String retractHechizos = String.format("retractall(hechizo(_, '%s', _, _)).", nombrePersonaje);
		Query retractQuery = new Query(retractHechizos);
		retractQuery.hasSolution();

		// Cargar cada hechizo del personaje en Prolog
		Map<String, HechizoStrategy> hechizos = personaje.getLista_de_hechizos();
		for (Map.Entry<String, HechizoStrategy> entry : hechizos.entrySet()) {
			String nombreHechizo = entry.getKey();
			HechizoStrategy hechizo = entry.getValue();
			int costo = hechizo.getCostoEnergia();
			String tipo = hechizo.getTipo(); // Puede ser "ataque" o "defensa"

			// Crear el hecho en Prolog
			String assertHechizo = String.format("assert(hechizo('%s', '%s', %d, '%s')).", nombreHechizo,
					nombrePersonaje, costo, tipo);
			Query assertQuery = new Query(assertHechizo);
			assertQuery.hasSolution();

			// Mensaje de depuración
			System.out.println("Hechizo cargado en Prolog: " + nombreHechizo + " para " + nombrePersonaje
					+ " con costo " + costo + " y tipo " + tipo);
		}

	//	System.out.println("Hechizos del personaje " + nombrePersonaje + " cargados en Prolog.");
	}

	public void cargarHechizosEnProlog(List<Personaje> personajes) {
		for (Personaje p : personajes) {
			cargarHechizosEnProlog(p);
		}

	}
}
