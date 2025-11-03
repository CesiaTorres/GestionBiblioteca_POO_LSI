// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
public class Docente extends Socio {
   private String area;

   public Docente(int p_dniSocio, String p_nombre, String p_area) {
      super(p_dniSocio, p_nombre, 5);
      this.setArea(p_area);
   }

   private void setArea(String p_area) {
      this.area = p_area;
   }

   public String getArea() {
      return this.area;
   }

   public boolean esResponsable() {
      boolean resultado = super.puedePedir();

      for(int i = 0; i < this.getPrestamos().size(); ++i) {
         if (((Prestamo)super.getPrestamos().get(i)).getFechaDevolucion() != null) {
            resultado = resultado && !((Prestamo)super.getPrestamos().get(i)).vencido(((Prestamo)super.getPrestamos().get(i)).getFechaDevolucion());
         }
      }

      return resultado;
   }

   public void agregarDiasDePrestamo(int p_dias) {
      if (this.esResponsable()) {
         super.setDiasPrestamo(super.getDiasPrestamo() + p_dias);
      }

   }

   public String soyDeLaClase() {
      return "Docente";
   }
}
