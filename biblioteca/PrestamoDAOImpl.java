import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PrestamoDAOImpl {

    private static final String NOMBRE_ARCHIVO = "prestamos.txt";
    private static final String DELIMITADOR = ";";
    private static final int CAMPOS_ESPERADOS = 4;

    // Dependencias OBLIGATORIAS para resolver las claves foráneas
    private LibroDAOImpl libroDAO;
    private SocioDAOImpl socioDAO;

    /**
     * Constructor que recibe los DAOs necesarios para resolver las relaciones.
     */
    public PrestamoDAOImpl(LibroDAOImpl p_libroDAO, SocioDAOImpl p_socioDAO) {
        // ¡Importante! Asegúrate que las clases LibroDAOImpl y SocioDAOImpl implementen estas interfaces.
        this.libroDAO = p_libroDAO;
        this.socioDAO = p_socioDAO;
    }

    public String getNombreArchivo() { return NOMBRE_ARCHIVO; }

    public List<Prestamo> obtenerTodos() {
        List<Prestamo> prestamos = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                
                String[] partes = linea.split(DELIMITADOR);
                
                if (partes.length == CAMPOS_ESPERADOS) {
                    try {
                        int dniSocio = Integer.parseInt(partes[0]);
                        String tituloLibro = partes[1];
                        Calendar fechaRetiro = Prestamo.parseCalendar(partes[2]);
                        
                        // --- RESOLUCIÓN DE RELACIONES ---
                        // Buscamos los objetos usando los otros DAOs
                        Socio socio = socioDAO.obtenerPorDni(dniSocio);
                        Libro libro = libroDAO.obtenerPorTitulo(tituloLibro);
                        
                        if (socio != null && libro != null) {
                            // Recreamos el objeto Prestamo
                            Prestamo prestamo = new Prestamo(fechaRetiro, socio, libro);
                            
                            // Verificamos si tiene fecha de devolución
                            if (!partes[3].equalsIgnoreCase("NULL")) {
                                prestamo.registrarFechaDevolucion(Prestamo.parseCalendar(partes[3]));
                            }
                            prestamos.add(prestamo);
                        } else {
                            System.err.println("[PrestamoDAO] Advertencia: No se encontró Socio (DNI " + dniSocio + ") o Libro (Título '" + tituloLibro + "') para el préstamo. Se ignora la línea.");
                        }
                    } catch (Exception e) {
                        System.err.println("Error al parsear línea de préstamo: " + linea + " | Error: " + e.getMessage());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("[PrestamoDAO] Archivo " + NOMBRE_ARCHIVO + " no encontrado, iniciando vacío.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prestamos;
    }

    public void guardarTodos(List<Prestamo> prestamos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO, false))) {
            for (Prestamo prestamo : prestamos) {
                bw.write(prestamo.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar TODOS los préstamos.");
            e.printStackTrace();
        }
    }
}