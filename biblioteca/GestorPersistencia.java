import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestorPersistencia {

    private LibroDAOImpl libroDAO;
    private SocioDAOImpl socioDAO;
    private PrestamoDAOImpl prestamoDAO; 

    public GestorPersistencia() {
        this.libroDAO = new LibroDAOImpl();
        this.socioDAO = new SocioDAOImpl();
        this.prestamoDAO = new PrestamoDAOImpl();
    }

    /**
     * Carga todos los datos (Libros, Socios y Préstamos) y los vincula.
     */
    public CargaDatos cargarDatos() {
        System.out.println("[Gestor] Cargando libros desde " + libroDAO.getNombreArchivo() + "...");
        ArrayList<Libro> libros = new ArrayList<>(this.libroDAO.obtenerTodos());
        System.out.println("[Gestor] " + libros.size() + " libros cargados.");

        System.out.println("[Gestor] Cargando socios desde " + socioDAO.getNombreArchivo() + "...");
        ArrayList<Socio> socios = new ArrayList<>(this.socioDAO.obtenerTodos());
        System.out.println("[Gestor] " + socios.size() + " socios cargados.");
        
        System.out.println("[Gestor] Cargando préstamos desde " + prestamoDAO.getNombreArchivo() + "...");
        List<Prestamo> prestamos = this.prestamoDAO.obtenerTodos(libros, socios);
        System.out.println("[Gestor] " + prestamos.size() + " préstamos cargados.");

        vincularPrestamos(libros, socios, prestamos);

        return new CargaDatos(libros, socios);
    }
    
    /**
     * Asigna los préstamos cargados (que ya tienen las referencias correctas)
     * a las listas internas de los objetos Socio y Libro.
     */
    private void vincularPrestamos(ArrayList<Libro> libros, ArrayList<Socio> socios, List<Prestamo> prestamos) {
        System.out.println("[Gestor] Vinculando " + prestamos.size() + " préstamos a Socios y Libros...");
        
        socios.forEach(s -> s.getPrestamos().clear());
        libros.forEach(l -> l.getArrayPrestamos().clear());
        
        for (Prestamo p : prestamos) {
            p.getSocio().agregarPrestamo(p);
            p.getLibro().agregarPrestamo(p);
        }
        System.out.println("[Gestor] Vinculación completada.");
    }


    /**
     * Guarda el estado actual de la biblioteca (Libros, Socios y Préstamos).
     * Esto implementa la "baja lógica" del PDF [cite: 173] 
     * al sobrescribir los archivos con el estado actual de la memoria.
     */
    public void guardarDatos(List<Libro> libros, List<Socio> socios) {

        System.out.println("\n[Gestor] Guardando " + libros.size() + " libros...");
        this.libroDAO.guardarTodos(libros);

 
        System.out.println("[Gestor] Guardando " + socios.size() + " socios...");
        this.socioDAO.guardarTodos(socios);
  
        List<Prestamo> todosLosPrestamos = socios.stream()
            .flatMap(socio -> socio.getPrestamos().stream())
            .distinct()
            .collect(Collectors.toList());

        System.out.println("[Gestor] Guardando " + todosLosPrestamos.size() + " préstamos...");
        this.prestamoDAO.guardarTodos(todosLosPrestamos);
        
        System.out.println("[Gestor] ¡Datos guardados exitosamente!");
    }
    
    public static class CargaDatos {
        public final ArrayList<Libro> libros;
        public final ArrayList<Socio> socios;
        public CargaDatos(ArrayList<Libro> l, ArrayList<Socio> s) { this.libros = l; this.socios = s; }
    }
}