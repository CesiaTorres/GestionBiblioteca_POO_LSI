import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SocioDAOImpl { 

    private static final String NOMBRE_ARCHIVO = "socios.txt";
    private static final String DELIMITADOR = ";";

    /**
     * Carga y parsea (analiza) todos los registros de socios desde el archivo TXT.
     * Esta lógica fue explicada anteriormente.
     */
    private List<Socio> cargarSocios() {
        // --- Lógica de cargarSocios() omitida por brevedad. 
        // --- Debe leer socios.txt, parsear el DNI, TIPO (Docente/Estudiante) y crear el objeto.
        // --- Se asume que este método funciona correctamente y devuelve la lista completa.
        List<Socio> socios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split(DELIMITADOR);
                
                // Formato asumido: DNI;Nombre;DiasPrestamo;TIPO_CLASE;Campo_Extra
                if (partes.length >= 4) { 
                    try {
                        int dni = Integer.parseInt(partes[0]);
                        String nombre = partes[1];
                        int diasPrestamo = Integer.parseInt(partes[2]);
                        String tipo = partes[3];
                        String campoExtra = partes.length > 4 ? partes[4] : ""; 

                        Socio socio = null;
                        if (tipo.equalsIgnoreCase("Estudiante")) {
                            // Asumimos el constructor Estudiante(int dni, String nombre, String carrera)
                            socio = new Estudiante(dni, nombre, campoExtra); 
                        } else if (tipo.equalsIgnoreCase("Docente")) {
                             // Asumimos el constructor Docente(int dni, String nombre, String area)
                            socio = new Docente(dni, nombre, campoExtra);
                        }
                        
                        if (socio != null) {
                            socio.setDiasPrestamo(diasPrestamo); // Necesario si las subclases tienen días fijos en el constructor.
                            socios.add(socio);
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato numérico en línea: " + linea);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // No hay problema, devolvemos la lista vacía
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socios;
    }

    private void guardarSocios(List<Socio> socios) {
        // --- Lógica de guardarSocios() omitida por brevedad. 
        // --- Debe escribir la lista completa de socios en el archivo socios.txt usando toCSV().
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO, false))) {
            for (Socio socio : socios) {
                // Se requiere que las subclases Docente y Estudiante tengan un método toCSV()
                // que incluya el tipo (soyDeLaClase()).
                if (socio instanceof Docente) {
                    bw.write(((Docente)socio).toCSV()); 
                } else if (socio instanceof Estudiante) {
                    bw.write(((Estudiante)socio).toCSV());
                } else {
                    // Caso si la clase Socio no es abstracta
                    bw.write(socio.toCSVBase() + DELIMITADOR + socio.soyDeLaClase());
                }
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // --- Implementación del método FALTANTE ---

    /**
     * Busca un socio por su DNI.
     * @param dni El DNI del socio a buscar (clave primaria).
     * @return El objeto Socio encontrado o null si no existe.
     */
    public Socio obtenerPorDni(int dni) {
        // Carga todos los socios del archivo y busca el que coincida con el DNI
        return cargarSocios().stream()
                .filter(s -> s.getDniSocio() == dni)
                .findFirst() // Toma el primer resultado
                .orElse(null); // Retorna null si no lo encuentra
    }

    // --- Implementación de otros métodos CRUD ---
    
    
    public void guardarSocio(Socio socio) {
        List<Socio> socios = cargarSocios();
        // Verificar que no exista antes de agregar
        if (socios.stream().noneMatch(s -> s.getDniSocio() == socio.getDniSocio())) {
            socios.add(socio);
            guardarSocios(socios);
        } else {
            System.out.println("Error: Ya existe un socio con DNI " + socio.getDniSocio());
        }
    }

    
    public List<Socio> obtenerTodos() {
        return cargarSocios();
    }
    

    public void actualizarSocio(Socio socioActualizado) {
        List<Socio> socios = cargarSocios();
        boolean encontrado = false;
        for (int i = 0; i < socios.size(); i++) {
            if (socios.get(i).getDniSocio() == socioActualizado.getDniSocio()) {
                socios.set(i, socioActualizado); // Reemplazamos el objeto
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            guardarSocios(socios);
        } else {
            System.out.println("No se encontró el socio para actualizar: DNI " + socioActualizado.getDniSocio());
        }
    }
    

    public void eliminarSocio(Socio socio) {
        List<Socio> socios = cargarSocios();
        if (socios.removeIf(s -> s.getDniSocio() == socio.getDniSocio())) {
            guardarSocios(socios);
        } else {
            System.out.println("No se encontró el socio para eliminar: DNI " + socio.getDniSocio());
        }
    }
    public String getNombreArchivo() { return NOMBRE_ARCHIVO; }

    public void guardarTodos(List<Socio> socios) {
        guardarSocios(socios);
    }
}