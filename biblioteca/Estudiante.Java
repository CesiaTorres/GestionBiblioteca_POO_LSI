package biblioteca;
/**
 * La clase Estudiante representa a un socio que es estudiante dentro del sistema de gestión de biblioteca.
 * Hereda de la clase abstracta Socio y agrega información específica como la carrera que cursa.
 *
 * @author Romero Ingrid Luana
 * @version 1.0
 */
public class Estudiante extends Socio {
    //La carrera universitaria que cursa el estudiante
    private String carrera;

    /**
     * Constructor para crear un nuevo estudiante.
     * Se le pasa una coleccion inicializada por parametros.
     *
     * @param p_dniSocio       DNI del estudiante (heredado de Socio)
     * @param p_nombre         Nombre del estudiante (heredado de Socio)
     * @param p_diasPrestamo   Cantidad de días permitidos de préstamo (heredado de Socio)
     * @param p_carrera        Carrera que el estudiante está cursando
     */
    public Estudiante(int p_dniSocio, String p_nombre, ArrayList<Prestamo> p_listaPrestamos, String p_carrera){
        super(p_dniSocio, p_nombre, 20, p_listaPrestamos);
        this.setCarrera(p_carrera);
    }
    /**
     * Constructor para crear un nuevo estudiante.
     * No se le pasa una coleccion inicializada. Socio se encarga de inicializar.
     *
     * @param p_dniSocio       DNI del estudiante (heredado de Socio)
     * @param p_nombre         Nombre del estudiante (heredado de Socio)
     * @param p_diasPrestamo   Cantidad de días permitidos de préstamo (heredado de Socio)
     * @param p_carrera        Carrera que el estudiante está cursando
     */
    public Estudiante(int p_dniSocio, String p_nombre, String p_carrera){
        super(p_dniSocio, p_nombre, 20);
        this.setCarrera(p_carrera);
    }

    //setters y getters
    private void setCarrera(String p_carrera){
        this.carrera = p_carrera;
    }
    public String getCarrera(){
        return this.carrera;
    }

     /**
     * Determina si el estudiante puede pedir un nuevo préstamo de libro.
     * 
     * Un estudiante puede pedir un libro si cumple con: +no tiene ningún préstamo vencido(20 días después de la fecha de retiro) 
     * + si no tiene más de 3 libros prestados en su poder.
     *
     * @return  true si puede pedir un libro, false en caso contrario
     */
    @Override
    public boolean puedePedir(){
        if(super.puedePedir() && super.cantLibrosPrestados() <= 3 ){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Devuelve el nombre de la clase actual.
     *
     * @return La cadena "Estudiante".
     */
    @Override
    public String soyDeLaClase(){
        return "Estudiante";
    }
}