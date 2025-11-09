package persistencia;
import biblioteca.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SocioDAOImpl {

    private static final String NOMBRE_ARCHIVO = "socios.dat";
    private static final String TIPO_ESTUDIANTE = "EST";
    private static final String TIPO_DOCENTE = "DOC";

    public void guardarTodos(List<Socio> socios) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(NOMBRE_ARCHIVO))) {
            for (Socio socio : socios) {
                if (socio instanceof Estudiante) {
                    dos.writeUTF(TIPO_ESTUDIANTE);
                    dos.writeInt(socio.getDniSocio());
                    dos.writeUTF(socio.getNombre());
                    dos.writeInt(socio.getDiasPrestamo());
                    dos.writeUTF(((Estudiante) socio).getCarrera());
                } else if (socio instanceof Docente) {
                    dos.writeUTF(TIPO_DOCENTE); 
                    dos.writeInt(socio.getDniSocio());
                    dos.writeUTF(socio.getNombre());
                    dos.writeInt(socio.getDiasPrestamo());
                    dos.writeUTF(((Docente) socio).getArea()); 
                }
            }
        } catch (IOException e) {
            System.err.println("Error al guardar socios: " + e.getMessage());
        }
    }

    public List<Socio> obtenerTodos() {
        List<Socio> socios = new ArrayList<>();
        try (DataInputStream dis = new DataInputStream(new FileInputStream(NOMBRE_ARCHIVO))) {
            while (dis.available() > 0) {
                String tipoSocio = dis.readUTF();
                int dni = dis.readInt();
                String nombre = dis.readUTF();
                int diasPrestamo = dis.readInt();
                
                Socio socio = null;
                if (tipoSocio.equals(TIPO_ESTUDIANTE)) {
                    String carrera = dis.readUTF();
                    socio = new Estudiante(dni, nombre, carrera);
                } else if (tipoSocio.equals(TIPO_DOCENTE)) {
                    String area = dis.readUTF();
                    socio = new Docente(dni, nombre, area);
                }
                
                if (socio != null) {
                    socio.setDiasPrestamo(diasPrestamo); 
                    socios.add(socio);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo " + NOMBRE_ARCHIVO + " no encontrado.");
        } catch (IOException e) {
            System.err.println("Error al leer socios: " + e.getMessage());
        }
        return socios;
    }
 
    public String getNombreArchivo() { return NOMBRE_ARCHIVO; }

    public Socio obtenerPorDni(int dni) {
        return obtenerTodos().stream()
                .filter(s -> s.getDniSocio() == dni)
                .findFirst()
                .orElse(null);
    }
    
}
