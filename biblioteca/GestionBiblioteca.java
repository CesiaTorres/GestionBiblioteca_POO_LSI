 
import java.util.Calendar;

/**
 * La clase GestionBiblioteca es la encargada de gestionar la información de la biblioteca.
 * 
 * @author Torres Jemina Cesia
 * @author Reyes Kevin Josue
 * @version 1.0
 */
public class GestionBiblioteca {
    public static void main(String[] args) {
         Biblioteca biblioteca = new Biblioteca("Biblioteca Central");
         Scanner lector = new Scanner(System.in);
         crearSocios(biblioteca);
    }

    private static void crearLibros(Biblioteca p_biblioteca) {
        p_biblioteca.nuevoLibro("Programando con JAVA", 1, "Editorial X", 2018);
        p_biblioteca.nuevoLibro("JAVA. Como Programar", 3, "Editorial Y", 2014);
        p_biblioteca.nuevoLibro("Vivir para contarla", 1, "Editorial Z", 2002);
    }
    private static void crearSocios(Biblioteca p_biblioteca) {
        p_biblioteca.agragarSocio(new Docente(27556445, "Obregon Adrian", 25));
        p_biblioteca.agragarSocio(new Docente(17982110, "Reyes Kevin Josue", 12));
        p_biblioteca.agragarSocio(new Estudiante(23556445, "Torres Jemina Cesia", 17));
        p_biblioteca.agragarSocio(new Docente(26556445, "Romero Ingrid Luana", 1));
        p_biblioteca.agragarSocio(new Estudiante(36556445, "Gomez Angela Rebeca", 6));
        p_biblioteca.agragarSocio(new Estudiante(14524782, "Fernandez Alejandro Facundo", 30));
    }
}
