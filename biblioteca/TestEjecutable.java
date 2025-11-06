import java.util.ArrayList;
import java.util.Calendar;

public class TestEjecutable {

    public static void main(String[] args) {
        
        GestorPersistencia gestor = new GestorPersistencia();

        System.out.println("--- 🚀 INICIANDO APLICACIÓN: Cargando datos (.dat)... ---");
        GestorPersistencia.CargaDatos datosCargados = gestor.cargarDatos();
        
        Biblioteca biblioteca = new Biblioteca(
            "Biblioteca Central (Modo Persistente)", 
            datosCargados.libros, 
            datosCargados.socios
        );
        System.out.println("--- ✅ Biblioteca '" + biblioteca.getNombre() + "' cargada con datos. ---");

        System.out.println("\n--- Simulando uso de la Biblioteca ---");
        
        System.out.println("\n--- Estado Actual de Socios (Cargados) ---");
        System.out.println(biblioteca.listaDeSocios());
        System.out.println("\n--- Estado Actual de Libros (Cargados) ---");
        System.out.println(biblioteca.listaDeLibros());

        System.out.println("\n--- Simulando Préstamo (en memoria) ---");
        Socio socioPrueba = biblioteca.buscarSocio(111111);
        final String TITULO_PRUEBA = "Cien años de soledad";
        Libro libroPrueba = biblioteca.getLibros().stream()
            .filter(l -> l.getTituloLibro().equals(TITULO_PRUEBA))
            .findFirst().orElse(null);

        if (socioPrueba == null) {
            System.out.println("Socio de prueba (111111) no encontrado. Creando...");
            biblioteca.nuevoSocioEstudiante(111111, "Ana Garcia", "Sistemas");
            socioPrueba = biblioteca.buscarSocio(111111);
        }
         if (libroPrueba == null) {
            System.out.println("Libro de prueba ('" + TITULO_PRUEBA + "') no encontrado. Creando...");
            biblioteca.nuevoLibro(TITULO_PRUEBA, 1, "Sudamericana", 1967);
            libroPrueba = biblioteca.getLibros().stream().filter(l -> l.getTituloLibro().equals(TITULO_PRUEBA)).findFirst().orElse(null);
        }

        if (socioPrueba != null && libroPrueba != null) {
            System.out.println("Realizando préstamo de '" + libroPrueba.getTituloLibro() + "' a '" + socioPrueba.getNombre() + "'.");
            biblioteca.prestarLibro(Calendar.getInstance(), socioPrueba, libroPrueba);
        } else {
            System.out.println("No se pudo encontrar el socio o el libro para el préstamo de prueba.");
        }
        
        System.out.println("\n--- Estado de Libros (Después del Préstamo) ---");
        System.out.println(biblioteca.listaDeLibros());

        System.out.println("\n--- 💾 CERRANDO APLICACIÓN: Guardando datos (.dat)... ---");
        try {
            gestor.guardarDatos(biblioteca.getLibros(), biblioteca.getSocios());
        } catch (Exception e) {
            System.err.println("¡Error fatal al guardar los datos! " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("\n--- 🟢 Aplicación finalizada. ---");
        System.out.println("Vuelve a ejecutar para verificar que el préstamo se cargó desde 'prestamos.dat'.");
    }
}