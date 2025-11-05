 /**
 * La clase Docente representa a un socio del tipo docente dentro del sistema de biblioteca.
 * Extiende la clase abstracta Socio y añade el atributo correspondiente al área de especialización.
 * 
 * Un docente puede solicitar préstamos de libros y también puede tener días adicionales de préstamo 
 * si se lo considera responsable (es decir, si no posee préstamos vencidos).
 * 
 * @author Romero Ingrid Luana
 * @version 1.0
**/
 
import java.util.ArrayList;
import java.util.Calendar;
public class Docente extends Socio{
    /** Área de especialización del docente. */
    private String area;

     /**  
     * Constructor de la clase  Docente. Inicializa sus atributos y los heredados de Socio. 
     * Con uns lista de prestamos vacia (0...n)
     *
     * @param p_dniSocio        DNI del docente
     * @param p_nombre          Nombre del docente
     * @param p_area            Área de especialización del docente
     */
    public Docente(int p_dniSocio, String p_nombre,  String p_area){
        super(p_dniSocio, p_nombre, 5 );
        this.setArea(p_area);
    }

    /**  
     * Constructor de la clase  Docente. Inicializa sus atributos y los heredados de Socio. 
     * Con uns lista de prestamos. (*...n)
     *
     * @param p_dniSocio        DNI del docente
     * @param p_nombre          Nombre del docente
     * @param p_diasPrestamo    Cantidad de días de préstamo permitidos
     * @param p_listaPrestamos  Lista de préstamos que el docente posee
     * @param p_area            Área de especialización del docente
     */
    public Docente(int p_dniSocio, String p_nombre, ArrayList p_listaPrestamos, String p_area){
        super(p_dniSocio, p_nombre, 5 , p_listaPrestamos);
        this.setArea(p_area);
    }


    /stters y getters
    private void setArea(String p_area){
        this.area = p_area;
    }
    public String getArea(){
        return this.area;
    }

     /**
     * Determina si el docente es responsable, es decir,
     * si no tiene préstamos vencidos a la fecha actual.
     *
     * @return true si no tiene préstamos vencidos, false en caso contrario
     */
    public boolean esResponsable(){
        for(Prestamo unPrestamo: super.getListaPrestamos()){ 
        Calendar fechaChequeo;

        if (unPrestamo.getFechaDevolucion() != null) { 
            fechaChequeo = unPrestamo.getFechaDevolucion();
        } else {
            fechaChequeo = Calendar.getInstance(); 
        }

        if (unPrestamo.vencido(fechaChequeo) ){ 
            return false;
        }
     }
        return true;
    }

    /**
     * Permite modificar los días de préstamo concedidos al docente, 
     * si este es considerado responsable (sin préstamos vencidos).
     *
     * @param p_dias Cantidad de días adicionales a sumar a los ya otorgados
     */
    public void cambiarDiasDePrestamo(int p_dias){
        
        if(this.esResponsable()){
            int diasAdicionales = this.getDiasPrestamo() + p_dias ;
            this.setDiasPrestamo(diasAdicionales); 
        }
    } 

     /**
     * Indica si el docente puede solicitar un préstamo.
     * Este método delega en el comportamiento de la clase Socio.
     *
     * @return true si puede pedir un préstamo, false si no
     */
    @Override
    public boolean puedePedir(){
        return super.puedePedir();
    }

    /**
     * Devuelve el nombre de la clase actual (útil para identificar el tipo de socio).
     *
     * @return La cadena "Docente"
     */
    @Override
    public String soyDeLaClase(){
        return "Docente";
    }

}