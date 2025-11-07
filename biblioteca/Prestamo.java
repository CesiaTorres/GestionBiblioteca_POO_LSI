 
import java.util.Calendar;
import java.text.SimpleDateFormat;
/**
 * La clase Prestamo representa el préstamo de un libro realizado por un socio.
 * Contiene la fecha de retiro, la fecha de devolución (si se devuelve),
 * y las referencias al socio y al libro involucrados.
 *
 * Un préstamo se considera vencido cuando la fecha actual supera
 * la fecha límite compuesta por: fechaRetiro + días de préstamo asignados al socio.
 *
 * @author Fernandez Alejandro Facundo
 * @version 1.0
 */

public class Prestamo {
    private Calendar fechaRetiro; //Fecha en que se retiró el libro
    private Calendar fechaDevolucion; //Fecha en que se devolvió el libro (null si no fue devuelto aún)
    private Socio socio; //Socio que realiza el préstamo
    private Libro libro; //Libro prestado

    /**
     * Constructor de la clase Prestamo.
     * Inicializa fecha de retiro y asigna el libro y socio.
     * La fecha de devolución inicia en null.
     *
     * @param p_fechaRetiro Fecha en que se retira el libro
     * @param p_socio       Socio que realiza el préstamo
     * @param p_libro       Libro prestado
     */
    public Prestamo(Calendar p_fechaRetiro, Socio p_socio, Libro p_libro) {
        this.setFechaRetiro(p_fechaRetiro);
        this.setSocio(p_socio);
        this.setLibro(p_libro);
        this.setFechaDevolucion(null); //null??
    }

    //Setters y getters
    private void setFechaRetiro(Calendar p_fecha) {
        this.fechaRetiro = p_fecha;
    }
    private void setFechaDevolucion(Calendar p_fecha) {
        this.fechaDevolucion = p_fecha;
    }
    private void setSocio(Socio p_socio) {
        this.socio = p_socio;
    }
    private void setLibro(Libro p_libro) {
        this.libro = p_libro;
    }
    public Calendar getFechaRetiro() {
        return this.fechaRetiro;
    }
    public Calendar getFechaDevolucion() {
        return this.fechaDevolucion;
    }
    public Socio getSocio() {
        return this.socio;
    }
    public Libro getLibro() {
        return this.libro;
    }
    
    /**
     * Registra la fecha de devolución del préstamo.
     *
     * @param p_fecha Fecha en la que se devuelve el libro
     */
    public void registrarFechaDevolucion(Calendar p_fecha) {
        this.setFechaDevolucion(p_fecha);
    }

    /**
     * Indica si el préstamo se encuentra vencido al comparar la fecha límite
     * con la fecha pasada como parámetro.
     *
     * @param p_fecha Fecha a evaluar
     * @return true si el préstamo está vencido, false en caso contrario
     */
    public boolean vencido(Calendar p_fecha) {
        int diasPrestamo = this.getSocio().getDiasPrestamo();
        Calendar fechaLimite = Calendar.getInstance();
        //Calendar fechaLimite = (Calendar) this.getFechaRetiro().clone(); //clone() devuelve un Object 
        fechaLimite.setTime(this.getFechaRetiro().getTime()); //setTime exige un Date y getTime devuelve ese Date
        fechaLimite.add(Calendar.DAY_OF_YEAR, diasPrestamo);

        //Está vencido si p_fecha es posterior a la fecha límite
        return p_fecha.after(fechaLimite);
    }

    /**
     * Devuelve la información detallada del préstamo.
     * @return Cadena con la información del préstamo
     */
    public String toString() {
        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        String retiro = fecha.format(this.getFechaRetiro().getTime());     
        String devolucion = " ";

        if (this.getFechaDevolucion() == null) {
            devolucion = "No devuelto";
        } else {
            devolucion = fecha.format(this.getFechaDevolucion().getTime());
        }

        return  "Retiro: " + retiro +
                " - Devolución: " + devolucion + "\n" +
                "Libro: " + this.getLibro().getTituloLibro() + "\n" +
                "Socio: " + this.getSocio().getNombre() +"\n";
    }
}
