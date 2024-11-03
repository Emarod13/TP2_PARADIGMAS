package Personajes;

import org.jpl7.Query;
import org.jpl7.Term;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class PrologHelper {
    private static final String FILE_PATH = "batalla.pl";

    // Método para crear o actualizar el archivo Prolog con hechos y reglas
    public void generarArchivoProlog() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            // Ejemplo de hechos y reglas iniciales
            writer.write("hechizo(expecto_patronum).\n");
            writer.write("energia_suficiente(harry_potter).\n");
            writer.write("varita_sauco_disponible.\n");
            writer.write("puede_usar_hechizo(harry_potter, expecto_patronum) :- "
                    + "energia_suficiente(harry_potter), "
                    + "\\+ esta_herido(harry_potter), "
                    + "varita_sauco_disponible.\n");
            // Agrega otros hechos y reglas según la lógica de la batalla
            writer.flush();
            System.out.println("Archivo Prolog creado o actualizado en: " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo Prolog: " + e.getMessage());
        }
    }

    // Método para consultar si un personaje puede usar un hechizo
    public boolean puedeUsarHechizo(String personaje, String hechizo) {
        // Asegura que el archivo .pl esté cargado en Prolog
        cargarArchivoProlog();

        // Consulta en formato de cadena
        String consulta = "puede_usar_hechizo(" + personaje + ", " + hechizo + ").";
        Query query = new Query(consulta);

        // Ejecuta la consulta y devuelve el resultado
        boolean resultado = query.hasSolution();
        query.close(); // Cierra la consulta después de ejecutarla

        return resultado;
    }

    // Método auxiliar para cargar el archivo .pl en el motor de Prolog
    private void cargarArchivoProlog() {
        String consultaCargar = "consult('" + FILE_PATH + "').";
        Query query = new Query(consultaCargar);
        if (!query.hasSolution()) {
            throw new IllegalStateException("No se pudo cargar el archivo Prolog.");
        }
        query.close();
    }

    // Ejemplo de otro método de consulta: Obtener una estrategia de ataque
    public Map<String, Term>[] obtenerEstrategiaDeAtaque(String personaje) {
        // Asegura que el archivo .pl esté cargado en Prolog
        cargarArchivoProlog();

        // Define la consulta a Prolog (ajusta según la lógica)
        String consulta = "estrategia_ataque(" + personaje + ", Enemigo, Hechizo).";
        Query query = new Query(consulta);

        // Ejecuta la consulta y obtiene los resultados
        Map<String, Term>[] soluciones = query.allSolutions();
        query.close(); // Cierra la consulta después de ejecutarla

        return soluciones;
    }
}
