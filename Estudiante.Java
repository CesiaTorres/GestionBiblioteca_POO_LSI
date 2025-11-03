// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
public class Estudiante extends Socio {
   private String carrera;

   public Estudiante(int p_dniSocio, String p_nombre, String p_carrera) {
      super(p_dniSocio, p_nombre, 20);
      this.setCarrera(p_carrera);
   }

   private void setCarrera(String p_carrera) {
      this.carrera = p_carrera;
   }

   public String getCarrera() {
      return this.carrera;
   }
   @Override
   public boolean puedePedir() {
      return super.puedePedir() && super.cantLibrosPrestados() < 3;
   }

   public String soyDeLaClase() {
      return "Estudiante";
   }
}