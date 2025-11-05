import java.util.*;

/**
 * La clase Biblioteca representa una biblioteca con libros y socios.
 * Permite registrar nuevos libros, socios, realizar préstamos de libros,
 * y listar información relevante de la biblioteca.
 * 
 * @author
 * @version
 */
public class Biblioteca {
    // Atributos
    private String nombre;
    private ArrayList<Libro> libros;
    private ArrayList<Socio> socios;

    /**
     * Constructor de la clase Biblioteca.
     * 
     * @param p_nombre El nombre de la biblioteca.
     */
    public Biblioteca(String p_nombre) {
        this.setNombre(p_nombre);
        this.setLibro(new ArrayList<Libro>());
        this.setSocio(new ArrayList<Socio>());
    }
    
    //Acessors
    public String getNombre(){
        return this.nombre;
    }
    public ArrayList<Libro> getLibros(){
        return this.libros;
    }
    public ArrayList<Socio> getSocios(){
        return this.socios;
    }
    private void setNombre(String p_nombre){
        this.nombre = p_nombre;
    }
    private void setLibro (ArrayList <Libro> p_Libros){
        this.libros = p_Libros;
    }
    private void setSocio (ArrayList <Socio> p_socios){
        this.socios = p_socios;
    }
    public void agregarSocio (Socio p_socio){
        this.getSocios().add(p_socio);
    }
    public void agregarLibro (Libro p_libro){
        this.getLibros().add(p_libro);
    }    
    
    /**
     * Crea un nuevo libro y lo añade a la lista de libros de la biblioteca.
     * 
     * @param p_titulo   El título del libro.
     * @param p_edicion  La edición del libro.
     * @param p_editorial La editorial del libro.
     * @param p_anio     El año de publicación del libro.
     */
    public void nuevoLibro(String p_titulo, int p_edicion, String p_editorial, int p_anio) {
        Libro unLibro = new Libro(p_titulo, p_edicion, p_editorial, p_anio);
        this.agregarLibro(unLibro);
    }

    /**
     * Crea un nuevo socio estudiante y lo añade a la lista de socios.
     * 
     * @param p_dniSocio DNI del socio estudiante.
     * @param p_nombre   Nombre del socio estudiante.
     * @param p_carrera  Carrera del socio estudiante.
     */
    public void nuevoSocioEstudiante(int p_dniSocio, String p_nombre, String p_carrera) {
        Socio unEstudiante = new Estudiante(p_dniSocio, p_nombre, p_carrera);
        this.agregarSocio(unEstudiante);
    }

    /**
     * Crea un nuevo socio docente y lo añade a la lista de socios.
     * 
     * @param p_dniSocio DNI del socio docente.
     * @param p_nombre   Nombre del socio docente.
     * @param p_area     Área de especialización del socio docente.
     */
    public void nuevoSocioDocente(int p_dniSocio, String p_nombre, String p_area) {
        Socio unDocente = new Docente(p_dniSocio, p_nombre, p_area);
        this.agregarSocio(unDocente);
    }
