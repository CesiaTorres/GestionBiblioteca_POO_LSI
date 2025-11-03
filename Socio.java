// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Socio {
   private int dniSocio;
   private String nombre;
   private int diasPrestamo;
   private ArrayList<Prestamo> prestamos;

   public Socio(int p_dniSocio, String p_nombre, int p_diasPrestamo) {
      this.setDniSocio(p_dniSocio);
      this.setNombre(p_nombre);
      this.setDiasPrestamo(p_diasPrestamo);
      this.setPrestamos(new ArrayList<Prestamo>());
   }

   private void setDniSocio(int p_dniSocio) {
      this.dniSocio = p_dniSocio;
   }

   private void setNombre(String p_nombre) {
      this.nombre = p_nombre;
   }

   public void setDiasPrestamo(int p_diasPrestamo) {
      this.diasPrestamo = p_diasPrestamo;
   }

   private void setPrestamos(ArrayList<Prestamo> p_prestamos) {
      this.prestamos = p_prestamos;
   }

   public int getDniSocio() {
      return this.dniSocio;
   }

   public String getNombre() {
      return this.nombre;
   }

   public int getDiasPrestamo() {
      return this.diasPrestamo;
   }

   public ArrayList<Prestamo> getPrestamos() {
      return this.prestamos;
   }

   public void addPrestamo(Prestamo p_prestamo) {
      this.getPrestamos().add(p_prestamo);
   }

   public void removePrestamo(Prestamo p_prestamo) {
      this.getPrestamos().remove(p_prestamo);
   }

   public int cantLibrosPrestados() {
      int libros = 0;

      for(int i = 0; i < this.getPrestamos().size(); ++i) {
         if (((Prestamo)this.getPrestamos().get(i)).getFechaDevolucion() == null) {
            ++libros;
         }
      }

      return libros;
   }

   public String toString() {
      int var10000 = this.getDniSocio();
      return "D.N.I.: " + var10000 + " || " + this.getNombre() + "(" + this.soyDeLaClase() + ") || Libros Prestados: " + this.cantLibrosPrestados();
   }

   public boolean puedePedir() {
      Calendar hoy = new GregorianCalendar();
      boolean puede = true;

      for(int i = 0; i < this.getPrestamos().size(); ++i) {
         puede = puede && ((Prestamo)this.getPrestamos().get(i)).vencido(hoy);
      }

      return puede;
   }

   public abstract String soyDeLaClase();
}
