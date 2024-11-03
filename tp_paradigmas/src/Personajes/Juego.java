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
        Query cargarReglas = new Query("consult('reglas.pl')");
        if (!cargarReglas.hasSolution()) {
            System.err.println("No se pudieron cargar las reglas de Prolog");
        }
    }
    
    /**
     * Actualiza en Prolog si el personaje está debilitado o no
     */
    public void actualizarEstadoDebilitadoEnProlog(Personaje personaje) {
        String consultaProlog;

        // Verificar si el personaje está debilitado en Java
        if (personaje.estaDebilitado()) {
            // Agregar el hecho de que el personaje está debilitado
            consultaProlog = String.format("agregar_debilitado('%s')", personaje.getNombre());
        } else {
            // Eliminar el hecho de que el personaje está debilitado
            consultaProlog = String.format("eliminar_debilitado('%s')", personaje.getNombre());
        }

        // Ejecutar la consulta para actualizar el estado en Prolog
        Query actualizarEstado = new Query(consultaProlog);
        actualizarEstado.hasSolution();
    }

    /**
     * Toma una decisión de acción (atacar, defender o consumir) para un personaje
     */
    public void tomarDecision(Personaje personaje, List<Personaje> enemigos) {
        // Actualizar el estado debilitado de este personaje y de sus enemigos en Prolog
        actualizarEstadoDebilitadoEnProlog(personaje);
        enemigos.forEach(this::actualizarEstadoDebilitadoEnProlog);

        // Configurar la consulta para decidir la acción
        Query consulta = new Query(
            String.format("decidir_accion(%d, %d, Accion)",
                personaje.getPuntos_de_vida(),
                (int) enemigos.stream().filter(Personaje::estaDebilitado).count()  // Contar enemigos debilitados
            )
        );

        // Obtener la acción decidida por Prolog
        String accion = consulta.oneSolution().get("Accion").toString();
        ejecutarAccion(accion, personaje, enemigos);
    }

    /**
     * Ejecuta la acción decidida en Prolog para el personaje
     */
    private void ejecutarAccion(String accion, Personaje personaje, List<Personaje> enemigos) {
        switch (accion) {
            case "atacar":
                Personaje objetivo = obtenerObjetivo(personaje, enemigos);
                if (objetivo != null) {
                    String hechizoAtaque = elegirHechizo(personaje, "Ataque");
                    if (hechizoAtaque != null) {
                        personaje.atacar(objetivo, hechizoAtaque);
                        System.out.println(personaje.getNombre() + " ataca a " + objetivo.getNombre() + " con " + hechizoAtaque);
                    } else {
                        System.out.println(personaje.getNombre() + " no tiene hechizos de ataque disponibles.");
                    }
                }
                break;
            case "defender":
                String hechizoDefensa = elegirHechizo(personaje, "Defensa");
                if (hechizoDefensa != null) {
                    personaje.defender(hechizoDefensa);
                    System.out.println(personaje.getNombre() + " se defiende con " + hechizoDefensa);
                } else {
                    System.out.println(personaje.getNombre() + " no tiene hechizos de defensa disponibles.");
                }
                break;
            /*case "consumir":
                personaje.consumirObjeto();
                System.out.println(personaje.getNombre() + " consume un objeto para recuperar energía");
                break;*/
            default:
                System.out.println("Acción no reconocida");
                break;
        }
    }

    private String elegirHechizo(Personaje personaje, String tipo) {
        int energiaActual = personaje.getEnergia();
        String nombrePersonaje = personaje.getNombre();

        // Crear la consulta para Prolog
        String consulta = "hechizos_disponibles('" + nombrePersonaje + "', " + energiaActual + ", '" + tipo + "', HechizosDisponibles).";
        
        // Ejecutar la consulta
        Query q = new Query(consulta);

        // Procesar los resultados de Prolog
        if (q.hasSolution()) {
            Map<String, Term> resultado = q.oneSolution();
            Term hechizos = resultado.get("HechizosDisponibles");

            // Convertir el término de lista a un array
            if (hechizos.isList() && hechizos.arity() > 0) {
                Term[] hechizosArray = Term.listToTermArray(hechizos);
                Term hechizoElegido = hechizosArray[random.nextInt(hechizosArray.length)];
                return hechizoElegido.toString();
            }
        }
        return null; // No hay hechizos disponibles
    }



    /**
     * Obtiene el objetivo recomendado de ataque para el personaje en base a Prolog
     */
    private Personaje obtenerObjetivo(Personaje personaje, List<Personaje> enemigos) {
        Query consultaObjetivos = new Query(
            String.format("objetivos_recomendados(Objetivo, '%s', Objetivo)",  personaje.getTipo())
        );

        if (consultaObjetivos.hasSolution()) {
            String nombreObjetivo = consultaObjetivos.oneSolution().get("Objetivo").toString();
            return enemigos.stream()
                           .filter(enemigo -> enemigo.getNombre().equals(nombreObjetivo))
                           .findFirst()
                           .orElse(null);
        }

        // Si no hay objetivo recomendado, se elige uno aleatorio
        return enemigos.get(new Random().nextInt(enemigos.size()));
    }
    public void cargarPersonajesEnProlog(List<Personaje> personajes) {
        try (FileWriter fw = new FileWriter("batalla.pl", true); // `true` para agregar en lugar de sobrescribir
             PrintWriter pw = new PrintWriter(fw)) {
            
            for (Personaje personaje : personajes) {
                String hechoPersonaje = String.format("personaje('%s', '%s', %d, %d).",
                        personaje.getNombre(),
                        personaje.getTipo(),
                        personaje.getPuntos_de_vida(),
                        personaje.getEnergia());
                pw.println(hechoPersonaje);
            }

            System.out.println("Personajes cargados en Prolog con éxito.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo Prolog: " + e.getMessage());
        }
    }
    
    public void actualizarPersonajeEnProlog(Personaje personaje) {
        String nombre = personaje.getNombre();
        int vida = personaje.getPuntos_de_vida();
        int energia = personaje.getEnergia();

        // Eliminar cualquier hecho `personaje` existente para este personaje en Prolog
        String retractPersonaje = String.format("retract(personaje('%s', _, _, _)).", nombre);
        Query retractQuery = new Query(retractPersonaje);
        retractQuery.hasSolution();

        // Insertar el hecho `personaje` actualizado en Prolog
        String nuevoHechoPersonaje = String.format("assert(personaje('%s', '%s', %d, %d)).",
                nombre, personaje.getTipo(), vida, energia);
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

        System.out.println("Personaje " + nombre + " actualizado en Prolog.");
    }
}
