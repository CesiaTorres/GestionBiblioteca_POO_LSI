// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Socio {
    /**
     * Atributos de clase
     */
   private int dniSocio;
   private String nombre;
   private int diasPrestamo;
   private ArrayList<Prestamo> prestamos;

   /**
    * Constructor de clase 
    * @param p_dniSocio
    * @param p_nombre
    * @param p_diasPrestamo
    */
   public Socio(int p_dniSocio, String p_nombre, int p_diasPrestamo) {
      this.setDniSocio(p_dniSocio);
      this.setNombre(p_nombre);
      this.setDiasPrestamo(p_diasPrestamo);
      this.setPrestamos(new ArrayList<Prestamo>());
   }

   /**
    * Constructor de clase
    * @param p_dniSocio
    * @param p_nombre
    * @param p_diasPrestamo
    * @param p_prestamos
    */
   public Socio(int p_dniSocio, String p_nombre, int p_diasPrestamo, ArrayList<Prestamo> p_prestamos){
      this.setDniSocio(p_dniSocio);
      this.setNombre(p_nombre);
      this.setDiasPrestamo(p_diasPrestamo);
      this.setPrestamos(p_prestamos);
   }

   /**
    * Setter que recibe un dato de tipo entero y permite modificar el atributo dniSocio
    * @param p_dniSocio
    */
   private void setDniSocio(int p_dniSocio) {
      this.dniSocio = p_dniSocio;
   }

   /**
    * Setter que recibe un dato de tipo String y permite modificar el atributo nombre
    * @param p_nombre
    */
   private void setNombre(String p_nombre) {
      this.nombre = p_nombre;
   }

   /**
    * Setter que recibe un dato entero y permite modificar el atributo diasPrestamo
    * @param p_diasPrestamo
    */
   public void setDiasPrestamo(int p_diasPrestamo) {
      this.diasPrestamo = p_diasPrestamo;
   }

   /**
    * Setter que recibe un dato de tipo ArrayList y permite modificar el atributo prestamos
    * @param p_prestamos
    */
   private void setPrestamos(ArrayList<Prestamo> p_prestamos) {
      this.prestamos = p_prestamos;
   }

   /**
    * Getter que permite obtener el estado del atributo dniSocio
    * @return dato de tipo entero
    */
   public int getDniSocio() {
      return this.dniSocio;
   }

   /**
    * Getter que permite obtener el estado del atributo nombre
    * @return un dato de tipo String
    */
   public String getNombre() {
      return this.nombre;
   }

   /**
    * Getter que permite obtener el estado del atributo diasPrestamo
    * @return un tipo de dato entero
    */
   public int getDiasPrestamo() {
      return this.diasPrestamo;
   }

   /**
    * Getter que permite obtener el estado del atributo prestamos
    * @return un tipo de dato ArrayList
    */
   public ArrayList<Prestamo> getPrestamos() {
      return this.prestamos;
   }

   /**
    * Metodo publico que permite agregar un dato de tipo Prestamo al atributo prestamos de tipo ArrayList 
    * @param p_prestamo
    */
   public void agregarPrestamo(Prestamo p_prestamo) {
      this.getPrestamos().add(p_prestamo);
   }

   /**
    * Metodo publico que permite remover un dato de tipo Prestamo al atributo prestamos de tipo ArrayList
    * @param p_prestamo
    */
   public void removerPrestamo(Prestamo p_prestamo) {
    if(this.getPrestamos().size() == 0){
        System.out.println("No tiene archivos para ser borrado");
    }else{
        this.getPrestamos().remove(p_prestamo);
    }
   }

   /**
    * Metodo publico que devuelve la cantidad de libros prestados que tiene el socio
    * @return un tipo de dato entero
    */
   public int cantLibrosPrestados() {
      int libros = 0;

      for(int i = 0; i < this.getPrestamos().size(); ++i) {
         if (((Prestamo)this.getPrestamos().get(i)).getFechaDevolucion() != null) {
            ++libros;
         }
      }

      return libros;
   }

   /**
    * Metodo publico que devuelve una cadena de texto 
    */
   public String toString() {
      int nroDni = this.getDniSocio();
      return "D.N.I.: " + nroDni + " || " + this.getNombre() + "(" + this.soyDeLaClase() + ") || Libros Prestados: " + this.cantLibrosPrestados();
   }

   /**
    * Metodo publico que devuelve un valor booleano verdadero en caso de que el socio pueda pedir prestado un libro
    * Puede pedir prestado en caso de que no tenga libros vencidos
    * En caso contratio devuelve un valor booleano falso
    * @return un tipo de dato booleano
    */
   public boolean puedePedir() {
      Calendar hoy = new GregorianCalendar();
      boolean puede = true;

      for(int i = 0; i < this.getPrestamos().size(); ++i) {
         puede = puede && ((Prestamo)this.getPrestamos().get(i)).vencido(hoy);
      }

      return puede;
   }

   /**
    * Metodo abstracto que devuelve una cadena de texto indicando la clase a la que pertenece el objeto
    * @return un tipo de dato String
    */
   public abstract String soyDeLaClase();
   public String toCSVBase() {
      // Usamos el DNI como ID, Nombre, y los Días de Préstamo por defecto
      return this.getDniSocio() + ";" + this.getNombre() + ";" + this.getDiasPrestamo();
   }
}
