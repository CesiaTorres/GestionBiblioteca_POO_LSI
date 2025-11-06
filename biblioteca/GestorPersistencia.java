import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestorPersistencia {

    private LibroDAOImpl libroDAO;
    private SocioDAOImpl socioDAO;
    private PrestamoDAOImpl prestamoDAO; // <- Agregado

    public GestorPersistencia() {
        // Inicializamos los DAOs que saben leer/escribir en .txt
        // Asumiendo que tus Impl implementan la Interfaz (ej: LibroDAOImpl implements LibroDAO)
        this.libroDAO = new LibroDAOImpl(); 
        this.socioDAO = new SocioDAOImpl();
        
        // ¡Crucial! PrestamoDAOImpl necesita los otros DAOs para funcionar
        this.prestamoDAO = new PrestamoDAOImpl(this.libroDAO, this.socioDAO);
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
        // El DAO se encarga de resolver las referencias (Socio y Libro)
        List<Prestamo> prestamos = this.prestamoDAO.obtenerTodos();
        System.out.println("[Gestor] " + prestamos.size() + " préstamos cargados.");

        // Paso 4: Vincular los préstamos cargados a los Socios y Libros en memoria
        vincularPrestamos(libros, socios, prestamos);

        return new CargaDatos(libros, socios);
    }
    
    /**
     * Asigna los préstamos cargados a las listas internas de los objetos Socio y Libro.
     */
    private void vincularPrestamos(ArrayList<Libro> libros, ArrayList<Socio> socios, List<Prestamo> prestamos) {
        System.out.println("[Gestor] Vinculando " + prestamos.size() + " préstamos a Socios y Libros...");
        
        // Limpiamos las listas internas por si acaso
        socios.forEach(s -> s.getPrestamos().clear());
        libros.forEach(l -> l.getArrayPrestamos().clear());
        
        for (Prestamo p : prestamos) {
            // Buscamos el Socio correspondiente EN LA LISTA DE MEMORIA (no en el DAO)
            Socio socioEnMemoria = socios.stream()
                .filter(s -> s.getDniSocio() == p.getSocio().getDniSocio())
                .findFirst().orElse(null);
            
            // Buscamos el Libro correspondiente EN LA LISTA DE MEMORIA
            Libro libroEnMemoria = libros.stream()
                .filter(l -> l.getTituloLibro().equals(p.getLibro().getTituloLibro()))
                .findFirst().orElse(null);
            
            // Si encontramos ambos, los vinculamos
            if (socioEnMemoria != null) {
                socioEnMemoria.agregarPrestamo(p);
            }
             if (libroEnMemoria != null) {
                 // Asumiendo que tu clase Libro tiene 'agregarPrestamo'
                 libroEnMemoria.agregarPrestamo(p);
             }
        }
        System.out.println("[Gestor] Vinculación completada.");
    }


    /**
     * Guarda el estado actual de la biblioteca (Libros, Socios y Préstamos).
     */
    public void guardarDatos(List<Libro> libros, List<Socio> socios) {
        // 1. Guardar Libros
        System.out.println("\n[Gestor] Guardando " + libros.size() + " libros...");
        this.libroDAO.guardarTodos(libros);

        // 2. Guardar Socios
        System.out.println("[Gestor] Guardando " + socios.size() + " socios...");
        this.socioDAO.guardarTodos(socios);
        
        // 3. Extraer y Guardar Préstamos
        // Obtenemos todos los préstamos de todos los socios
        List<Prestamo> todosLosPrestamos = socios.stream()
            .flatMap(socio -> socio.getPrestamos().stream()) // Combina todas las listas de préstamos
            .distinct() // Evita duplicados
            .collect(Collectors.toList());

        System.out.println("[Gestor] Guardando " + todosLosPrestamos.size() + " préstamos...");
        this.prestamoDAO.guardarTodos(todosLosPrestamos);
        
        System.out.println("[Gestor] ¡Datos guardados exitosamente!");
    }
    
    // Clase interna CargaDatos (sin cambios)
    public static class CargaDatos {
        public final ArrayList<Libro> libros;
        public final ArrayList<Socio> socios;
        public CargaDatos(ArrayList<Libro> l, ArrayList<Socio> s) { this.libros = l; this.socios = s; }
    }
}