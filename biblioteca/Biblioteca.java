import java.util.*;

/**
 * La clase Biblioteca representa una biblioteca con libros y socios.
 * Permite registrar nuevos libros, socios, realizar préstamos de libros,
 * y listar información relevante de la biblioteca.
 * 
 * @author Reyes Kevin Josue
 * @version 1.0
 */
public class Biblioteca{
    //Atributos
    private String nombre;
    private ArrayList<Libro> libros;
    private ArrayList<Socio> socios;
    
    /**
     * Constructor de la clase Biblioteca.
     * 
     * @param p_nombre El nombre de la biblioteca.
     */
    public Biblioteca(String p_nombre){
        this.setNombre(p_nombre);
        this.setLibros(new ArrayList<Libro>());
        this.setSocios(new ArrayList<Socio>());
    }

    /**
     * Constructor de la clase Biblioteca. Recibe la coleccion de libros y socios.
     * 
     * @param p_nombre El nombre de la biblioteca.
     */
    public Biblioteca(String p_nombre, ArrayList<Libro> p_libros, ArrayList<Socio> p_socios){
        this.setNombre(p_nombre);
        this.setLibros(p_libros);
        this.setSocios(p_socios);
    }

    //Acessors
    private void setNombre(String p_nombre){
        this.nombre = p_nombre;
    }
    private void setLibros(ArrayList<Libro> p_libros){
        this.libros = p_libros;
    }
    private void setSocios(ArrayList<Socio> p_socios){
        this.socios = p_socios;
    }
    public String getNombre(){
        return this.nombre;
    }
    public ArrayList<Libro> getLibros(){
        return this.libros;
    }
    public ArrayList<Socio> getSocios(){
        return this.socios;
    }


    //Metodos para el manejo de Libros y Socios
    public boolean agregarLibro(Libro p_libro) {
        return this.getLibros().add(p_libro);
    }
    public boolean quitarLibro(Libro p_libro){
        return this.getLibros().remove(p_libro);
    }
    public boolean agregarSocio(Socio p_socio) {
        return this.getSocios().add(p_socio);
    }
    public boolean quitarSocio(Socio p_socio) {
        return this.getSocios().remove(p_socio);
    }
    public Socio buscarSocio(int p_dni) {
        for (Socio unSocio : this.getSocios()) {     
            if (unSocio.getDniSocio() == p_dni) {
                return unSocio;
            }
        }
        return null;
    }
    
    //Metodos 
    /**
     * Crea un nuevo libro y lo añade a la lista de libros de la biblioteca.
     * 
     * @param p_titulo   El título del libro.
     * @param p_edicion  La edición del libro.
     * @param p_editorial La editorial del libro.
     * @param p_anio     El año de publicación del libro.
     */
    public void nuevoLibro(String p_titulo, int p_edicion, String p_editorial, int p_anio) {
        Libro libro = new Libro(p_titulo, p_edicion, p_editorial, p_anio);
   
        if (this.agregarLibro(libro)) {
            System.out.println("Libro agregado exitosamente.");
        } else {
            System.out.println("Error al agregar el libro.");
        }
    }

    /**
     * Crea un nuevo socio estudiante y lo añade a la lista de socios.
     * 
     * @param p_dniSocio DNI del socio estudiante.
     * @param p_nombre   Nombre del socio estudiante.
     * @param p_carrera  Carrera del socio estudiante.
     */
    public void nuevoSocioEstudiante(int p_dniSocio, String p_nombre, String p_carrera) {
        Socio socio = new Estudiante(p_dniSocio, p_nombre, p_carrera);
   
        if (this.agregarSocio(socio)) {
            System.out.println("Socio estudiante agregado exitosamente.");
        } else {
            System.out.println("Error al agregar el socio estudiante.");
        }
    }

    /**
     * Crea un nuevo socio docente y lo añade a la lista de socios.
     * 
     * @param p_dniSocio DNI del socio docente.
     * @param p_nombre   Nombre del socio docente.
     * @param p_area     Área de especialización del socio docente.
     */
    public void nuevoSocioDocente(int p_dniSocio, String p_nombre, String p_area) {
        Socio socio = new Docente(p_dniSocio, p_nombre, p_area);
  
        if (this.agregarSocio(socio)) {
            System.out.println("Socio docente agregado exitosamente.");
        } else {
            System.out.println("Error al agregar el socio docente.");
        }
    }

    /**
     * Realiza un préstamo de libro a un socio si cumple las condiciones:
     * - El socio tiene capacidad para pedir otro libro.
     * - El libro no se encuentra actualmente prestado.
     *
     * @param p_fechaRetiro Fecha en la que se realiza el préstamo.
     * @param p_socio Socio que solicita el préstamo.
     * @param p_libro Libro que se desea prestar.
     * @return true si el préstamo fue realizado, false si no fue posible.
     */
    public boolean prestarLibro(Calendar p_fechaRetiro, Socio p_socio, Libro p_libro) {
        if (p_socio.puedePedir() && !p_libro.prestado()) {
            Prestamo prestamo = new Prestamo(p_fechaRetiro, p_socio, p_libro);
            p_socio.agregarPrestamo(prestamo);
            p_libro.agregarPrestamo(prestamo);
            System.out.println("Préstamo realizado exitosamente.");
            return true;
        } else {
            System.out.println(
                "No se puede realizar el préstamo. Verifique la disponibilidad del libro o la capacidad del socio.");
            return false;
        }
    }

    /**
     * Registra la devolución de un libro si se encontraba prestado.
     *
     * @param p_libro Libro a devolver.
     * @throws LibroNoPrestadoException si el libro no se encuentra prestado.
     */
    public void devolverLibro(Libro p_libro) throws LibroNoPrestadoException {
        if (!p_libro.prestado()) {
            throw new LibroNoPrestadoException(
                    "El libro " + p_libro.getTituloLibro() + " no se puede devolver ya que se encuentra en la biblioteca");
        } else {
            Prestamo prestamo = p_libro.ultimoPrestamo();
            Calendar fechaDevolucion = Calendar.getInstance();
            prestamo.registrarFechaDevolucion(fechaDevolucion);
            System.out.println("Libro devuelto exitosamente.");
        }
    }
    
    /**
     * Cuenta cuántos socios pertenecen a una clase específica.
     *
     * @param p_objeto Nombre de la clase del socio a buscar ("Docente", "Estudiante").
     * @return Cantidad de socios del tipo especificado.
     */
    public int cantidadSociosPorTipo(String p_objeto) {
        int contador = 0;
        for (Socio unSocio : this.getSocios()) {
            if (unSocio.soyDeLaClase().equalsIgnoreCase(p_objeto)) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Obtiene una lista con todos los préstamos que se encuentran vencidos
     * y aún no fueron devueltos.
     *
     * @return Colección de préstamos vencidos.
     */
    public ArrayList<Prestamo> prestamosVencidos() {
        Calendar fecha = Calendar.getInstance();
        ArrayList<Prestamo> prestamosVencidos = new ArrayList<Prestamo>();
        for (Socio unSocio : this.getSocios()) {
            for (Prestamo prestamo : unSocio.getPrestamos()) {
                if (prestamo.getFechaDevolucion() == null && prestamo.vencido(fecha)) {
                    prestamosVencidos.add(prestamo);
                }
            }
        }
        return prestamosVencidos;
    }

    /**
     * Obtiene una lista de docentes que cumplen con la condición
     * de "responsables", es decir, que no tienen préstamos vencidos.
     *
     * @return Lista de docentes responsables.
     */
    public ArrayList<Docente> docentesResponsables() {
        ArrayList<Docente> docentesResponsables = new ArrayList<Docente>(); // new ArrayList<Docente>()????
        for (Socio socio : this.getSocios()) {
            if (socio.soyDeLaClase().equalsIgnoreCase("Docente")) {
                Docente docente = (Docente) socio;
                if (docente.esResponsable()) {
                    docentesResponsables.add(docente);
                }
            }
        }
        return docentesResponsables;
    }

    /**
     * Indica qué socio tiene actualmente un libro prestado.
     *
     * @param p_libro Libro a consultar.
     * @return Cadena de texto con los datos del socio.
     * @throws LibroNoPrestadoException si el libro se encuentra en la biblioteca.
     */
    public String quienTieneElLibro(Libro p_libro) throws LibroNoPrestadoException {
        if (!p_libro.prestado()) {
            throw new LibroNoPrestadoException(
                "El libro se encuentra en la biblioteca.");
        }
        Prestamo prestamo = p_libro.ultimoPrestamo();
        Socio socio = prestamo.getSocio();
        return socio.getNombre() + " tiene el libro " + p_libro.getTituloLibro();

    }



    /**
     * Genera una lista en formato texto con todos los socios registrados
     * y la cantidad total por cada tipo de socio.
     *
     * @return Cadena con información de socios.
     */
    public String listaDeSocios() {
        String resultado = "";
        int contador = 1;
        for (Socio socio : this.getSocios()) {
            resultado += contador + ") "+ socio.toString() + "\n";
            contador++;
        }
        resultado += "****************************************\nCantidad de Socios del tipo Estudiante: " + this.cantidadSociosPorTipo("Estudiante") + "\n";
        resultado += "Cantidad de Socios del tipo Docente: " + this.cantidadSociosPorTipo("Docente") + "\n ****************************************";
        return resultado;
    }

    /**
     * Lista todos los libros indicando si están prestados o no.
     *
     * @return Cadena con listado de libros.
     */
    public String listaDeLibros() {
        String resultado = "";
        int contador = 1;
        for (Libro libro : this.getLibros()) {
            resultado += contador + ") "+ libro.toString() + "|| Prestado: " + (libro.prestado() ? "(Si)" : "(No)") + "\n";
            contador++;
        }
        return resultado;
    }
    
    /**
     * Lista únicamente los títulos de los libros registrados.
     *
     * @return Cadena con el título de todos los libros.
     */
    public String listaDeTitulos() {
        String resultado = "";
        int contador = 1;
        for (Libro libro : this.getLibros()) {
            resultado += libro.toString() + "\n";
            resultado += contador + ") " + libro.toString() + "\n";
            contador++;
        }
        return resultado;
    }

    /**
     * Lista los docentes marcados como responsables.
     *
     * @return Cadena con información de docentes responsables.
     */
    public String listaDeDocentesResponsables() {
        String resultado = "";
        for (Docente unDocente : this.docentesResponsables()) { //Docente <-> Socio
            resultado += unDocente.toString() + "\n";
        }
        return resultado;
    }
}
