public class Docente extends Socio{
    private String area;

    public Docente(int p_dniSocio, String p_nombre, int p_diasPrestamo, String p_area){
        super(p_dniSocio, p_nombre, p_diasPrestamo);
        this.setArea(p_area);
    }

    private void setArea(String p_area){
        this.area = p_area;
    }
    public String getArea(){
        return this.area;
    }

    public boolean esResponsable(){

    }

    public void cambiarDiasDePrestamo(int p_dias){

    } 

    public boolean puedePedir(){

    }

    public String soyDeLaClase(){
        return "Docente";
    }

}