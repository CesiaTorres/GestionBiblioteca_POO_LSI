 
import java.util.*;
/**
 * Implementacion de la Clase Libro.
 * 
 * @author Adrian Obregon
 * @version 1.0
 *  
 */
public class Libro
{
    private String titulo;
    private int edicion;
    private String editorial;
    private int anio;
    private ArrayList <Prestamo> prestamos;
    
    /**
     * Constructor para Libro. No recibe coleccion e inicializa la misma.
     * @param p_titulo corresponde al titulo del Libro.
     * @param p_edicion corresponde a la edicion del Libro.
     * @param p_editorial corresponde a la editorial del Libro.
     * @param p_anio corresponde al año de lanzamiento del Libro.
     */
    public Libro(String p_titulo, int p_edicion, String p_editorial, int p_anio){
        this.setTituloLibro(p_titulo);
        this.setEdicionLibro(p_edicion);
        this.setEditorialLibro(p_editorial);
        this.setAnioLibro(p_anio);
        this.setArrayPrestamos(new ArrayList <Prestamo> ());
    }
    
    /**
     * Constructor para Libro. Recibe una coleccion y la asigna al Setter del atributo prestamos.
     * @param p_titulo corresponde al titulo del Libro.
     * @param p_edicion corresponde a la edicion del Libro.
     * @param p_editorial corresponde a la editorial del Libro.
     * @param p_anio corresponde al año de lanzamiento del Libro.
     * @param p_prestamos corresponde a la coleccion de prestamos recibida como parametro.
     */
    public Libro(String p_titulo, int p_edicion, String p_editorial, int p_anio, ArrayList <Prestamo> p_prestamos){
        this.setTituloLibro(p_titulo);
        this.setEdicionLibro(p_edicion);
        this.setEditorialLibro(p_editorial);
        this.setAnioLibro(p_anio);
        this.setArrayPrestamos(p_prestamos);
    }
    
    //Setters y Getters
    /**
     * @param p_titulo asigna un titulo al Libro.
     */
    private void setTituloLibro(String p_titulo){
        this.titulo = p_titulo;
    }
    
    /**
     * @param p_edicion asigna una edicion al Libro.
     */
    private void setEdicionLibro(int p_edicion){
        this.edicion = p_edicion;
    }
    
    /**
     * @param p_editorial asigna una editorial al Libro.
     */
    private void setEditorialLibro(String p_editorial){
        this.editorial = p_editorial;
    }
    
    /**
     * @param p_anio asigna el año de lanzamiento al Libro.
     */
    private void setAnioLibro(int p_anio){
        this.anio = p_anio;
    }
    
    /**
     * @param p_prestamos asigna una Coleccion de Prestamos al atributo prestamos.
     */
    private void setArrayPrestamos(ArrayList <Prestamo> p_prestamos){
        this.prestamos = p_prestamos;
    }
    
    /**
     * Obtiene el titulo del Libro.
     * @return retorna el titulo del Libro.
     */
    public String getTituloLibro(){
        return this.titulo;
    }
    
    /**
     * Obtiene la edicion del Libro.
     * @return retorna la edicion del Libro.
     */    
    public int getEdicionLibro(){
        return this.edicion;
    }
    
    /**
     * Obtiene la editorial del Libro.
     * @return retorna la editorial del Libro.
     */
    public String getEditorialLibro(){
        return this.editorial;
    }
    
    /**
     * Obtiene el año de lanzamiento del Libro.
     * @return retorna el año de lanzamiento del Libro.
     */
    public int getAnioLibro(){
        return this.anio;
    }
    
    /**
     * Obtiene la Coleccion de Prestamos.
     * @return retorna la Coleccion de Prestamos.
     */
    public ArrayList <Prestamo> getArrayPrestamos(){
        return this.prestamos;
    }
    
    /**
     * Metodos para Agregar un Prestamo.
     * @param p_prestamo recibe un Objeto Prestamo proximo a agregar a la Coleccion.
     * @return devuelve true si se agrego el Prestamo, false si no se pudo agregar.
     */
    public boolean agregarPrestamo(Prestamo p_prestamo){
        return this.getArrayPrestamos().add(p_prestamo);
    }
    
    /**
     * Metodos para Remover un Prestamo.
     * @param p_prestamo recibe un Objeto Prestamo proximo a quitar a la Coleccion.
     * @return devuelve true si se quito el Prestamo, false si no se pudo quitar.
     */
    public boolean quitarPrestamo(Prestamo p_prestamo){
        return this.getArrayPrestamos().remove(p_prestamo);
    }
    
    /**
     * Metodo para saber cual fue el ultimo prestamo comparando fechas
     * @return retorna el ultimo Prestamo si existe.
     */
    public Prestamo ultimoPrestamo(){
        if(this.getArrayPrestamos().isEmpty()){
            return null;
        }else{
            Prestamo ultimo = this.getArrayPrestamos().get(0);
            for(Prestamo p : this.getArrayPrestamos()){
                if(p.getFechaRetiro().after(ultimo.getFechaRetiro())){ //lo que hace after es comparar fechas. ¿La fecha de retiro de este préstamo (p) es después de la del préstamo anterior (ultimo)?
                    ultimo = p;
                }
            }
            return ultimo;
        }
    }
    
    /**
     * Metodo para saber si un Libro fue prestado 
     * @return true en caso de estar prestado y false en caso de no encontrarse en prestamo.
     */
    public boolean prestado(){
        Prestamo ultimo = this.ultimoPrestamo();
        if(ultimo != null && ultimo.getFechaDevolucion() == null){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Metodo redefinido para mostrar el Titulo del Libro
     * @return un String que muestra el Titulo del Libro.
     */
    public String toString() {
        return "Titulo: " + this.getTituloLibro();
    }
    
    public String toCSV() {
        return this.getTituloLibro() + ";" + 
               this.getEdicionLibro() + ";" + 
               this.getEditorialLibro() + ";" + 
               this.getAnioLibro();
    }
}
