import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAOImpl {

    private static final String NOMBRE_ARCHIVO = "libros.txt";
    private static final String DELIMITADOR = ";";
    private static final int CAMPOS_ESPERADOS = 4; // Título, Edición, Editorial, Año

    // --- Métodos de LECTURA y ESCRITURA BÁSICOS ---

    /**
     * Carga todos los libros desde el archivo TXT, realizando el parseo (de String a Objeto).
     */
    private List<Libro> cargarLibros() {
        List<Libro> libros = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                
                String[] partes = linea.split(DELIMITADOR);
                
                if (partes.length == CAMPOS_ESPERADOS) {
                    try {
                        String titulo = partes[0];
                        int edicion = Integer.parseInt(partes[1]);
                        String editorial = partes[2];
                        int anio = Integer.parseInt(partes[3]);
                        
                        // Recrear el objeto Libro usando el constructor que acepta 4 argumentos
                        // El 'true' es solo para diferenciar el constructor, si lo usas.
                        Libro libro = new Libro(titulo, edicion, editorial, anio); 
                        libros.add(libro);
                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato numérico en línea: " + linea);
                    }
                } else {
                    System.err.println("Línea de libro mal formateada, ignorada: " + linea);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo " + NOMBRE_ARCHIVO + " no encontrado, iniciando con lista vacía.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return libros;
    }

    /**
     * Guarda la lista completa de libros en el archivo, sobrescribiendo el contenido anterior.
     */
    private void guardarLibros(List<Libro> libros) {
        // 'false' en FileWriter indica que sobrescribimos el archivo (no append)
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO, false))) { 
            
            for (Libro libro : libros) {
                bw.write(libro.toCSV()); // Usa el método de la clase Libro para obtener el formato TXT
                bw.newLine();
            }
            System.out.println("Libros guardados exitosamente en " + NOMBRE_ARCHIVO);
            
        } catch (IOException e) {
            System.out.println("Error al guardar los libros en el archivo.");
            e.printStackTrace();
        }
    }

    // --- Implementación de los Métodos DAO (CRUD) ---
    
   
    public void guardarLibro(Libro libro) {
        List<Libro> libros = cargarLibros(); // Carga la lista existente
        // Opcional: Revisar si el libro ya existe (por título)
        if (libros.stream().noneMatch(l -> l.getTituloLibro().equals(libro.getTituloLibro()))) {
             libros.add(libro); // Agrega el nuevo libro
             guardarLibros(libros); // Persiste la lista completa
        } else {
            System.out.println("El libro con título '" + libro.getTituloLibro() + "' ya existe.");
        }
    }

    public Libro obtenerPorTitulo(String titulo) {
        return cargarLibros().stream()
                .filter(l -> l.getTituloLibro().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null); // Retorna el primer libro encontrado o null
    }

    public List<Libro> obtenerTodos() {
        return cargarLibros();
    }

    public void actualizarLibro(Libro libroActualizado) {
        List<Libro> libros = cargarLibros();
        boolean encontrado = false;
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getTituloLibro().equals(libroActualizado.getTituloLibro())) {
                libros.set(i, libroActualizado); // Reemplaza el objeto en la lista
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            guardarLibros(libros); // Guarda la lista actualizada
        } else {
            System.out.println("No se encontró el libro para actualizar: " + libroActualizado.getTituloLibro());
        }
    }

    public void eliminarLibro(Libro libro) {
        List<Libro> libros = cargarLibros();
        // removeIf elimina el libro basándose en una condición (título)
        if (libros.removeIf(l -> l.getTituloLibro().equals(libro.getTituloLibro()))) {
            guardarLibros(libros);
        } else {
            System.out.println("No se encontró el libro para eliminar: " + libro.getTituloLibro());
        }
    }
    public String getNombreArchivo() { return NOMBRE_ARCHIVO; }

    public void guardarTodos(List<Libro> libros) {
        // Esta es la misma lógica que tenías en 'guardarLibros(List<Libro> libros)'
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO, false))) { 
            for (Libro libro : libros) {
                bw.write(libro.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar TODOS los libros.");
            e.printStackTrace();
        }
    }
}