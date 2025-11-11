package persistencia;
import biblioteca.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Implementa la interfaz LibroDAO que ya tenías
public class LibroDAOImpl {

    private static final String NOMBRE_ARCHIVO = "libros.dat";

    /**
     * Guarda la lista COMPLETA de libros en el archivo, sobrescribiéndolo.
     * Sigue el patrón de "Baja o eliminación" del PDF[cite: 173]: 
     * se reescribe el archivo solo con los datos válidos (la lista actual).
     */

    public void guardarTodos(List<Libro> p_libros) {
        try (FileOutputStream fos = new FileOutputStream(NOMBRE_ARCHIVO, false);
             DataOutputStream dos = new DataOutputStream(fos)) { 

            for (Libro libro : p_libros) {
                dos.writeUTF(libro.getTituloLibro());   
                dos.writeInt(libro.getEdicionLibro());   
                dos.writeUTF(libro.getEditorialLibro()); 
                dos.writeInt(libro.getAnioLibro());     
            }

        } catch (IOException e) {
            System.err.println("Error al guardar libros: " + e.getMessage());
        }
    }

    /**
     * Carga todos los libros leyendo el archivo secuencial.
     */
 
    public List<Libro> obtenerTodos() {
        List<Libro> libros = new ArrayList<>();
        
        try (FileInputStream fis = new FileInputStream(NOMBRE_ARCHIVO);
             DataInputStream dis = new DataInputStream(fis)) { 
            while (dis.available() > 0) { 
                String titulo = dis.readUTF();     
                int edicion = dis.readInt();       
                String editorial = dis.readUTF();  
                int anio = dis.readInt();          
                
                libros.add(new Libro(titulo, edicion, editorial, anio));
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("Archivo " + NOMBRE_ARCHIVO + " no encontrado, se creará al guardar."); 
        } catch (IOException e) {
            System.err.println("Error al leer libros: " + e.getMessage()); 
        }
        return libros;
    }
    
    public String getNombreArchivo() { return NOMBRE_ARCHIVO; }

 
    public void guardarLibro(Libro p_libro) {
        List<Libro> libros = obtenerTodos();
        libros.add(p_libro);
        guardarTodos(libros);
    }
    

    public Libro obtenerPorTitulo(String titulo) {
        return obtenerTodos().stream()
                .filter(libro -> libro.getTituloLibro().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);
    }
}
