import java.util.*;

/**
 * Write a description of class Biblioteca here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Biblioteca
{
    // instance variables - replace the example below with your own
    private String nombre;
    private ArrayList<Libro> libros;
    private ArrayList<Socio> socios;
    
    /**
     * Constructors for objects of class Biblioteca
     */
    public Biblioteca(String p_nombre)
    {
        // initialise instance variables
        this.setNombre(p_nombre);
        this.setLibros(new ArrayList<Libro>());
        this.setSocios(new ArrayList<Socio>());
    }

    /**
     * @param p_nombre
     * @param p_libros
     * @param p_socios
     */
    public Biblioteca(String p_nombre, ArrayList<Libro> p_libros, ArrayList<Socio> p_socios)
    {
        // initialise instance variables
        this.setNombre(p_nombre);
        this.setLibros(p_libros);
        this.setSocios(p_socios);
    }

    // accessor methods
    /**
     * @param p_nombre
     */
    private void setNombre(String p_nombre)
    {
        this.nombre = p_nombre;
    }
    /**
     * @param p_libros
     */
    private void setLibros(ArrayList<Libro> p_libros)
    {
        this.libros = p_libros;
    }
    /**
     * @param p_socios
     */
    private void setSocios(ArrayList<Socio> p_socios)
    {
        this.socios = p_socios;
    }
    /**
     * @return
     */
    public String getNombre()
    {
        return this.nombre;
    }
    /**
     * @return
     */
    public ArrayList<Libro> getLibros()
    {
        return this.libros;
    }
    /**
     * @return
     */
    public ArrayList<Socio> getSocios()
    {
        return this.socios;
    }
    // arraylist methods
    /**
     * @param p_libro
     * @return
     */
    public boolean agregarLibro(Libro p_libro)
    {
        return this.getLibros().add(p_libro);
    }
    /**
     * @param p_libro
     * @return
     */
    public boolean quitarLibro(Libro p_libro)
    {
        return this.getLibros().remove(p_libro);
    }

    /**
     * @param p_socio
     * @return
     */
    public boolean agragarSocio(Socio p_socio) {
        return this.getSocios().add(p_socio);
    }

    /**
     * @param p_socio
     * @return
     */
    public boolean quitarSocio(Socio p_socio) {
        return this.getSocios().remove(p_socio);
    }
    
    // other methods

    /**
     * @param p_titulo
     * @param p_edicion
     * @param p_editorial
     * @param p_anio
     */
    public void nuevoLibro(String p_titulo, int p_edicion, String p_editorial, int p_anio) {
        Libro libro = new Libro(p_titulo, p_edicion, p_editorial, p_anio);
        boolean a = this.agregarLibro(libro);
        if (a) {
            System.out.println("Libro agregado exitosamente.");
        } else {
            System.out.println("Error al agregar el libro.");
        }
    }
    //+nuevoSocioEstudiante(p_dniSocio: int, p_nombre: String, p_carrera: String): void
    /**
     * @param p_dniSocio
     * @param p_nombre
     * @param p_carrera
     */
    public void nuevoSocioEstudiante(int p_dniSocio, String p_nombre, String p_carrera) {
        Socio socio = new Estudiante(p_dniSocio, p_nombre, p_carrera);
        boolean a = this.agragarSocio(socio);
        if (a) {
            System.out.println("Socio estudiante agregado exitosamente.");
        } else {
            System.out.println("Error al agregar el socio estudiante.");
        }
    }

    //+nuevoSocioDocente(p_dniSocio: int, p_nombre: String, p_area: String): void
    /**
     * @param p_dniSocio
     * @param p_nombre
     * @param p_area
     */
    public void nuevoSocioDocente(int p_dniSocio, String p_nombre, String p_area) {
        Socio socio = new Docente(p_dniSocio, p_nombre, p_area);
        boolean a = this.agragarSocio(socio);
        if (a) {
            System.out.println("Socio docente agregado exitosamente.");
        } else {
            System.out.println("Error al agregar el socio docente.");
        }
    }
    //+prestarLibro(p_fechaRetiro: Calendar, p_socio: Socio, p_libro: Libro): boolean
    public boolean prestarLibro(Calendar p_fechaRetiro, Socio p_socio, Libro p_libro) {
        if (p_socio.puedePedir() && !p_libro.prestado()) {
            Prestamo prestamo = new Prestamo(p_fechaRetiro, p_socio, p_libro);
            p_socio.addPrestamo(prestamo);
            System.out.println("Préstamo realizado exitosamente.");
            return true;
        } else {
            System.out.println("No se puede realizar el préstamo. Verifique la disponibilidad del libro o la capacidad del socio.");
            return false;
        }
    }
}