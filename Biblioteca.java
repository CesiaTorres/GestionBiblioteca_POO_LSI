import java.lang.reflect.Array;
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

    /**
     * @param p_fechaRetiro
     * @param p_socio
     * @param p_libro
     * @return boolean
     */
    public boolean prestarLibro(Calendar p_fechaRetiro, Socio p_socio, Libro p_libro) {
        if (p_socio.puedePedir() && !p_libro.prestado()) {
            Prestamo prestamo = new Prestamo(p_fechaRetiro, p_socio, p_libro);
            p_socio.agregarPrestamo(prestamo);
            System.out.println("Préstamo realizado exitosamente.");
            return true;
        } else {
            System.out.println(
                    "No se puede realizar el préstamo. Verifique la disponibilidad del libro o la capacidad del socio.");
            return false;
        }
    }

    /**
     * @param p_libro
     * @throws LibroNoPrestadoException
     */
    public void devolverLibro(Libro p_libro) throws LibroNoPrestadoException {
        if (!p_libro.prestado()) {
            throw new LibroNoPrestadoException(
                    "El libro " + p_libro.getTitulo() + " no se puede devolver ya que se encuentra en la biblioteca");
        } else {
            Prestamo prestamo = p_libro.ultimoPrestamo();
            Calendar fechaDevolucion = Calendar.getInstance();
            prestamo.registrarFechaDevolucion(fechaDevolucion);
            System.out.println("Libro devuelto exitosamente.");
        }
    }
    
    /**
     * @param p_objeto
     * @return 2
     */
    public int cantidadSociosPorTipo(String p_objeto) {
        int contador = 0;
        for (Socio socio : this.getSocios()) {
            if (socio.soyDeLaClase().equals(p_objeto)) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * @return
     */
    public ArrayList<Prestamo> prestamosVencidos() {
        Calendar fecha = Calendar.getInstance();
        ArrayList<Prestamo> prestamosVencidos = new ArrayList<Prestamo>();
        for (Socio socio : this.getSocios()) {
            for (Prestamo prestamo : socio.getPrestamos()) {
                if (prestamo.getFechaDevolucion() == null && prestamo.vencido(fecha)) {
                    prestamosVencidos.add(prestamo);
                }
            }
        }
        return prestamosVencidos;
    }

    /**
     * @return
     */
    public ArrayList<Socio> docentesResponsables() {
        ArrayList<Socio> docentesResponsables = new ArrayList<Socio>();
        for (Socio socio : this.getSocios()) {
            if (socio.soyDeLaClase().equals("Docente")) {
                Docente docente = (Docente) socio;
                if (docente.esResponsable()) {
                    docentesResponsables.add(docente);
                }
            }
        }
        return docentesResponsables;
    }

    /**
     * @param p_libro
     * @return
     * @throws LibroNoPrestadoException
     */
    public String quienTieneElLibro(Libro p_libro) throws LibroNoPrestadoException {
        if (!p_libro.prestado()) {
            throw new LibroNoPrestadoException(
                    "El libro se encuentra en la biblioteca.");
        } else {
            Prestamo prestamo = p_libro.ultimoPrestamo();
            Socio socio = prestamo.getSocio();
            return socio.toString();
        }
    }

    /**
     * @param p_dni
     * @return
     */
    public Socio buscarSocio(int p_dni) {
        for (Socio socio : this.getSocios()) {
            if (socio.getDniSocio() == p_dni) {
                return socio;
            }
        }
        return null;
    }

    /**
     * @return
     */
    public String listaDeSocios() {
        String resultado = "";
        int contador = 1;
        for (Socio socio : this.getSocios()) {
            resultado += contador + ") " + socio.toString() + "\n";
            contador++;
        }
        return resultado;
    }

    /**
     * @return
     */
    public String listaDeLibros() {
        String resultado = "";
        int contador = 1;
        for (Libro libro : this.getLibros()) {
            resultado += contador + ") " + libro.toString() + "||" + (libro.prestado() ? "Si" : "No") + "\n";
            contador++;
        }
        return resultado;
    }
    
    /**
     * @return
     */
    public String listaDeTitulos() {
        String resultado = "";
        int contador = 1;
        for (Libro libro : this.getLibros()) {
            resultado += contador + ") " + libro.toString() + "\n";
            contador++;
        }
        return resultado;
    }

    /**
     * @return
     */
    public String istaDeDocentesResponsables() {
        String resultado = "";
        for (Socio socio : this.docentesResponsables()) {
            resultado +=   socio.toString() + "\n";
        }
        return resultado;
    }
}