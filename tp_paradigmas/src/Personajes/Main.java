package Personajes;

import org.jpl7.*;


public class Main {
    public static void main(String[] args) {
    	Batalla batalla = new Batalla();
        // Inicializar JPL
    	
        String filePath = "ruta/a/batalla.pl";  // Asegúrate de que la ruta sea correcta
        Query cargarArchivo = new Query("consult", new Term[] {new Atom(filePath)});
        
        // Comprobar si se cargó el archivo
        if (cargarArchivo.hasSolution()) {
            System.out.println("Archivo Prolog cargado correctamente.");
        } else {
            System.out.println("Error al cargar el archivo Prolog.");
            return;
        }

        // Ejemplo de consulta: ¿Puede Harry Potter usar "expelliarmus"?
        Query consulta = new Query("puede_usar_hechizo(harry_potter, expelliarmus)");
        if (consulta.hasSolution()) {
            System.out.println("Harry Potter puede usar Expelliarmus.");
        } else {
            System.out.println("Harry Potter no puede usar Expelliarmus.");
        }
        
        batalla.iniciar();
    }
}

