import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PrestamoDAOImpl  {

    private static final String NOMBRE_ARCHIVO = "prestamos.dat";

    /**
     * Guarda los préstamos. Solo guarda las CLAVES (IDs) de las relaciones.
     */
  
    public void guardarTodos(List<Prestamo> prestamos) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(NOMBRE_ARCHIVO))) {
            for (Prestamo p : prestamos) {
                dos.writeInt(p.getSocio().getDniSocio()); 
                dos.writeUTF(p.getLibro().getTituloLibro());

                dos.writeLong(p.getFechaRetiro().getTimeInMillis());
                
                long fechaDevLong = -1L; 
                if (p.getFechaDevolucion() != null) {
                    fechaDevLong = p.getFechaDevolucion().getTimeInMillis();
                }
                dos.writeLong(fechaDevLong);
            }
        } catch (IOException e) {
            System.err.println("Error al guardar préstamos: " + e.getMessage());
        }
    }

    /**
     * Carga los préstamos. Necesita las listas de libros y socios
     * para "re-conectar" las referencias de objetos.
     * * NOTA: Este método difiere de la interfaz, es específico
     * para la carga en el GestorPersistencia.
     */
    public List<Prestamo> obtenerTodos(List<Libro> librosCargados, List<Socio> sociosCargados) {
        List<Prestamo> prestamos = new ArrayList<>();
        Map<Integer, Socio> mapaSocios = sociosCargados.stream()
            .collect(Collectors.toMap(Socio::getDniSocio, Function.identity()));
        Map<String, Libro> mapaLibros = librosCargados.stream()
            .collect(Collectors.toMap(Libro::getTituloLibro, Function.identity()));

        try (DataInputStream dis = new DataInputStream(new FileInputStream(NOMBRE_ARCHIVO))) {
            while (dis.available() > 0) {
                int dniSocio = dis.readInt();
                String tituloLibro = dis.readUTF();
                long fechaRetiroLong = dis.readLong();
                long fechaDevLong = dis.readLong();

                Socio socioRef = mapaSocios.get(dniSocio);
                Libro libroRef = mapaLibros.get(tituloLibro);

                if (socioRef != null && libroRef != null) {
                    Calendar fechaRetiro = Calendar.getInstance();
                    fechaRetiro.setTimeInMillis(fechaRetiroLong);
                    
                    Prestamo p = new Prestamo(fechaRetiro, socioRef, libroRef);
                    
                    if (fechaDevLong != -1L) {
                        Calendar fechaDev = Calendar.getInstance();
                        fechaDev.setTimeInMillis(fechaDevLong);
                        p.registrarFechaDevolucion(fechaDev);
                    }
                    prestamos.add(p);
                } else {
                    System.err.println("Advertencia: No se encontró Socio (DNI " + dniSocio + ") o Libro ('" + tituloLibro + "') para un préstamo. Se ignora.");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo " + NOMBRE_ARCHIVO + " no encontrado.");
        } catch (IOException e) {
            System.err.println("Error al leer préstamos: " + e.getMessage());
        }
        return prestamos;
    }
    
    public String getNombreArchivo() { return NOMBRE_ARCHIVO; }
    
   
}