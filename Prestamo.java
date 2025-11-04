// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
import java.util.Calendar;

public class Prestamo {
   private Calendar fechaRetiro;
   private Calendar fechaDevolucion;
   private Socio socio;
   private Libro libro;

   public Prestamo(Calendar p_fechaRetiro, Socio p_socio, Libro p_libro) {
      this.setFechaRetiro(p_fechaRetiro);
      this.setSocio(p_socio);
      this.setLibro(p_libro);
      this.setFechaDevolucion((Calendar)null);
   }

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

   public void registrarFechaDevolucion(Calendar p_fecha) {
      this.fechaDevolucion = p_fecha;
   }

   public boolean vencido(Calendar p_fecha) {
      int dias = this.getSocio().getDiasPrestamo();
      Calendar retiro = this.getFechaRetiro();
      retiro.add(5, dias);
      return p_fecha.before(retiro) || p_fecha.equals(retiro);
   }

//    Retiro: <<fecha de retiro>> - Devolución: <<fecha de devolución>>
//  Libro: <<título del libro>>
//  Socio: <<nombre del socio>>
   public String toString() {
      String fechaDevolucionStr = (this.getFechaDevolucion() == null) ? "No devuelto" :
               this.getFechaDevolucion().get(Calendar.DAY_OF_MONTH) + "/" +
                     (this.getFechaDevolucion().get(Calendar.MONTH) + 1) + "/" +
                     this.getFechaDevolucion().get(Calendar.YEAR);
   
      return "Retiro: " + this.getFechaRetiro().get(Calendar.DAY_OF_MONTH) + "/" +
               (this.getFechaRetiro().get(Calendar.MONTH) + 1) + "/" +
               this.getFechaRetiro().get(Calendar.YEAR) +
               " - Devolución: " + fechaDevolucionStr + "\n" +
               "Libro: " + this.getLibro().getTitulo() + "\n" +
               "Socio: " + this.getSocio().getNombre();  
   }
}