package persistencia;
import biblioteca.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestorPersistencia {

    private LibroDAOImpl libroDAO;
    private SocioDAOImpl socioDAO;
    private PrestamoDAOImpl prestamoDAO; 

    public GestorPersistencia() {
        setLibroDAO(new LibroDAOImpl());
        setSocioDAO(new SocioDAOImpl());
        setPrestamoDAO(new PrestamoDAOImpl());
    }
    //setters y getters de los DAO si es necesario
    public LibroDAOImpl getLibroDAO() {
        return libroDAO;
    }

    public SocioDAOImpl getSocioDAO() {
        return socioDAO;
    }
    
    public PrestamoDAOImpl getPrestamoDAO() {
        return prestamoDAO;
    }

    private void setLibroDAO(LibroDAOImpl libroDAO) {
        this.libroDAO = libroDAO;
    }

    private void setSocioDAO(SocioDAOImpl socioDAO) {
        this.socioDAO = socioDAO;
    }

    private void setPrestamoDAO(PrestamoDAOImpl prestamoDAO) {
        this.prestamoDAO = prestamoDAO;
    }

    /**
     * Carga todos los datos (Libros, Socios y Préstamos) y los vincula.
     */
    public CargaDatos cargarDatos() {
        System.out.println("[Gestor] Cargando libros desde " + getLibroDAO().getNombreArchivo() + "...");
        ArrayList<Libro> libros = new ArrayList<>(this.getLibroDAO().obtenerTodos());
        System.out.println("[Gestor] " + libros.size() + " libros cargados.");

        System.out.println("[Gestor] Cargando socios desde " + getSocioDAO().getNombreArchivo() + "...");
        ArrayList<Socio> socios = new ArrayList<>(this.getSocioDAO().obtenerTodos());
        System.out.println("[Gestor] " + socios.size() + " socios cargados.");
        
        System.out.println("[Gestor] Cargando préstamos desde " + getPrestamoDAO().getNombreArchivo() + "...");
        List<Prestamo> prestamos = this.getPrestamoDAO().obtenerTodos(libros, socios);
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
        this.getLibroDAO().guardarTodos(libros);

 
        System.out.println("[Gestor] Guardando " + socios.size() + " socios...");
        this.getSocioDAO().guardarTodos(socios);
  
        List<Prestamo> todosLosPrestamos = socios.stream()
            .flatMap(socio -> socio.getPrestamos().stream())
            .distinct()
            .collect(Collectors.toList());

        System.out.println("[Gestor] Guardando " + todosLosPrestamos.size() + " préstamos...");
        this.getPrestamoDAO().guardarTodos(todosLosPrestamos);
        
        System.out.println("[Gestor] ¡Datos guardados exitosamente!");
    }
    
    public static class CargaDatos {
        public final ArrayList<Libro> libros;
        public final ArrayList<Socio> socios;
        public CargaDatos(ArrayList<Libro> l, ArrayList<Socio> s) { this.libros = l; this.socios = s; }
    }
}
