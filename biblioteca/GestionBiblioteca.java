 import java.util.*;
/**
 * La clase GestionBiblioteca es la encargada de gestionar la información de la biblioteca.
 * 
 * @author Torres Jemina Cesia
 * @author Reyes Kevin Josue
 * @author Romero Ingrid Luana
 * @author Gomez Angela Rebeca
 * @author Fernandez Alejandro Facundo
 * @version 1.0
 */
public class GestionBiblioteca {
    public static void main(String[] args) {
         Biblioteca biblioteca = new Biblioteca("Biblioteca Central");
         Scanner lector = new Scanner(System.in);

         int opcion = 1;
         while (opcion != 0) {
            System.out.println(biblioteca.getNombre());
            System.out.println("*** Menu Principal ***");
            System.out.println("1. Gestionar Libros\n2. Gestionar Socios\n3. Gestionar Prestamos\n4. Abrir Interfaz Grafica\n0. Salir");
            
            opcion = leerEnteroValido(lector);
                
                switch (opcion) {
                case 1:
                    menuGestionarLibros(biblioteca, lector);
                    break;
                case 2:
                    menuGestionarSocios(biblioteca, lector);
                    break;
                case 3:
                    menuGestionPrestamos(biblioteca, lector);
                    break; 
                case 4:
                    VentanaBiblioteca ventana = new VentanaBiblioteca(biblioteca);
                    ventana.setVisible(true);
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    return;
                default:
                    System.out.println("Opción no válida");
                }    
            
        }
            //• ¿Qué cantidad de socios de tipo Estudiante hay?✅
            //• ¿Cuál es la lista de docentes que nunca han adeudado ni adeudan libros?✅
            //• ¿Cuál es la lista de libros? ¿Y la de socios? ✅
            //• ¿Qué socio tiene prestado el libro “Programando con JAVA”?✅ 
        
    }
    
    private static void menuGestionarLibros(Biblioteca p_biblioteca, Scanner p_lector) {
        int opcion = 1;

        while(opcion != 0){
            System.out.println("Menu Gestion de Libros");
            System.out.println("1. Agregar Libro\n2. Quitar Libro\n3. Listar Libros\n4. Listar Titulos\n0. Volver a menu principal");
            opcion = leerEnteroValido(p_lector);

            switch (opcion) {
                case 1:
                    agregarLibro(p_biblioteca, p_lector);
                    System.out.println("Libro agregado exitosamente a la biblioteca!");
                    break;
                case 2:
                    Libro libro = buscarLibro(p_biblioteca, p_lector);
                    if(libro != null) {
                        p_biblioteca.quitarLibro(libro);
                        System.out.println("Libro quitado exitosamente de la biblioteca.");
                    }
                    break;
                case 3:
                    System.out.println("Lista de libros:\n" + p_biblioteca.listaDeLibros());
                    break;
                case 4:
                    System.out.println("Lista de titulos:\n" + p_biblioteca.listaDeTitulos());
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Ingrese una opcion valida.");
            }
        }
    }
    
    
    private static void menuGestionarSocios(Biblioteca p_biblioteca, Scanner p_lector) {
        int opcion = 1;

        while(opcion != 0){
            System.out.println("Menu Gestion de Socios");
            System.out.println("1. Registrar Socio\n2. Mostrar datos de Socio\n3. Quitar Socio\n4. Cantidad de socios (estudiantes/docentes)\n5. Lista de docentes responsables\n0. Volver a menu principal");
            opcion = leerEnteroValido(p_lector);

            switch (opcion) {
                case 1:
                    agregarSocio(p_biblioteca, p_lector);
                    System.out.println("Socio registrado exitosamente!");
                    break;
                case 2:
                    if(buscarSocio(p_biblioteca, p_lector) == null) {
                        System.out.println("El socio no se encuentra en la biblioteca.");
                        break;
                    }
                    System.out.println(buscarSocio(p_biblioteca, p_lector).toString());
                    break;
                case 3:
                    Socio socio = buscarSocio(p_biblioteca, p_lector);
                    if(socio == null) {
                        System.out.println("El socio no se encuentra en la biblioteca.");
                        break;
                    }
                    p_biblioteca.quitarSocio(socio);
                    System.out.println("Socio quitado exitosamente de la biblioteca.");
                    break;
                case 4:
                    System.out.println("Lista de socios:\n" + p_biblioteca.listaDeSocios());
                    break;
                case 5:
                    System.out.println("Lista de docentes responsables:\n" + p_biblioteca.listaDeDocentesResponsables());
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Ingrese una opcion valida.");
            }
        }
    }

    private static void menuGestionPrestamos(Biblioteca p_biblioteca, Scanner p_lector) {
        int opcion = 1;
        Libro libro = null;
        Socio socio = null;

        while(opcion != 0){
            System.out.println("Menu Gestion de Prestamos");
            System.out.println("1. Registrar Prestamo\n2. Registrar Devolucion\n3. Listar Prestamos Vencidos\n4. Quien tiene el libro?\n0. Volver a menu principal");
            opcion = leerEnteroValido(p_lector);

            switch (opcion) {
                case 1:
                    Calendar hoy = Calendar.getInstance();
                    socio = buscarSocio(p_biblioteca, p_lector);
                    libro = buscarLibro(p_biblioteca, p_lector);
                    
                    if(socio == null || libro == null) {
                        System.out.println("No se pudo registrar el prestamo. Verifique los datos ingresados.");
                        break;
                    }
                    
                    p_biblioteca.prestarLibro(hoy, socio, libro);
                    System.out.println("Prestamo registrado exitosamente!");
                    break;
                case 2:
                    libro = buscarLibro(p_biblioteca, p_lector);
                    try {
                        p_biblioteca.devolverLibro(libro); 
                        System.out.println("Devolucion registrada exitosamente!");
                        break;
                    } catch (LibroNoPrestadoException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                case 3:
                    System.out.println("Lista de prestamos vencidos:\n" + p_biblioteca.prestamosVencidos());
                    break;
                case 4:
                    libro = buscarLibro(p_biblioteca, p_lector);
                    try {
                        System.out.println(p_biblioteca.quienTieneElLibro(libro));
                        break;
                    } catch (LibroNoPrestadoException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Ingrese una opcion valida.");
            }
        }
    }

    /**
     * Lee un entero desde el lector, repitiendo la solicitud si el formato es inválido.
     * @param p_lector Lector de la entrada estándar.
     * @return Valor entero
     */
    private static int leerEnteroValido(Scanner p_lector) {
        int valor = 0;
        boolean entradaValida = false;
        
        while (!entradaValida) {
            //System.out.print("Ingrese una opción: ");
            try {           
                valor = Integer.parseInt(p_lector.nextLine()); //Intenta convertir la línea a un entero
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Ingrese solo números. Intente nuevamente.");
            }
        }
        return valor;
    }
    
    /**
     * Agrega un libro a la biblioteca.
     * @param p_biblioteca Biblioteca en la que se agregará el libro.
     * @param p_lector Lector de la entrada estándar.
     */
    private static void agregarLibro(Biblioteca p_biblioteca, Scanner p_lector) {
        System.out.print("Ingrese el titulo del libro: ");
        String titulo = p_lector.nextLine();
        System.out.print("Ingrese la edicion (números): ");
        int edicion = leerEnteroValido(p_lector);
        System.out.print("Ingrese el editorial: ");
        String editorial = p_lector.nextLine();
        System.out.print("Ingrese el año de lanzamiento: ");
        int anio = leerEnteroValido(p_lector);

        Libro libro = new Libro(titulo, edicion, editorial, anio);
        p_biblioteca.agregarLibro(libro);
    }
    
    /**
     * Busca un libro en la biblioteca.
     * @param p_biblioteca Biblioteca en la que se buscará el libro.
     * @param p_lector Lector de la entrada estándar.
     * @return Libro encontrado o null si no se encuentra.
     */
    private static Libro buscarLibro(Biblioteca p_biblioteca, Scanner p_lector) {
        System.out.print("Ingrese el titulo del libro: ");
        String titulo = p_lector.nextLine();
    
        for(Libro libro : p_biblioteca.getLibros()) {
            if(libro.getTituloLibro().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        
        System.out.println("El libro no se encuentra en la biblioteca.");
        return null;
    }
    
    /**
     * Registra un nuevo socio.
     * @param p_biblioteca Biblioteca en la que se registrará el socio.
     * @param p_lector Lector de la entrada estándar.
     */
    public static void agregarSocio(Biblioteca p_biblioteca, Scanner p_lector) {
        System.out.print("Ingrese el DNI del socio: ");
        int dni = leerEnteroValido(p_lector);
        System.out.print("Ingrese el nombre del socio: ");
        String nombre = p_lector.nextLine();
        System.out.print("Ingresar dias a prestar el libro: ");
        int dias = leerEnteroValido(p_lector);
        
        int tipo = 0;
        while(tipo != 1 && tipo != 2) {
            System.out.print("1. Es estudiante, 2. Es docente: ");
            tipo = leerEnteroValido(p_lector);
            switch (tipo) {
                case 1:
                    System.out.print("Ingrese la carrera: ");
                    String carrera = p_lector.nextLine();
                    p_biblioteca.nuevoSocioEstudiante(dni, nombre, carrera);
                    break;
                   
                case 2:
                    System.out.print("Ingrese el area de especialización: ");
                    String area = p_lector.nextLine();
                    p_biblioteca.nuevoSocioDocente(dni, nombre, area);
                    break;
                default:
                    System.out.println("Ingrese una opcion valida.");
            }
        }    
    }

    /**
     * Busca un socio en la biblioteca.
     * @param p_biblioteca Biblioteca en la que se buscará el socio.
     * @param p_lector Lector de la entrada estándar.
     * @return Socio encontrado o null si no se encuentra.
     */
    public static Socio buscarSocio(Biblioteca p_biblioteca, Scanner p_lector) {
        System.out.print("Ingrese el DNI del socio: ");
        int dni = leerEnteroValido(p_lector);
        Socio socio1 = p_biblioteca.buscarSocio(dni);
        if(socio1 != null) {
            return socio1;
        } else {
            System.out.println("El socio no se encuentra en la biblioteca.");
            return null;
        }
    }



    /*
    private static void crearLibros(Biblioteca p_biblioteca) {
        p_biblioteca.nuevoLibro("Programando con JAVA", 1, "Editorial X", 2018);
        p_biblioteca.nuevoLibro("JAVA. Como Programar", 3, "Editorial Y", 2014);
        p_biblioteca.nuevoLibro("Vivir para contarla", 1, "Editorial Z", 2002);
    }
    private static void crearSocios(Biblioteca p_biblioteca) {
        p_biblioteca.agragarSocio(new Docente(27556445, "Obregon Adrian", 25));
        p_biblioteca.agragarSocio(new Docente(17982110, "Reyes Kevin Josue", 12));
        p_biblioteca.agragarSocio(new Estudiante(23556445, "Torres Jemina Cesia", 17));
        p_biblioteca.agragarSocio(new Docente(26556445, "Romero Ingrid Luana", 1));
        p_biblioteca.agragarSocio(new Estudiante(36556445, "Gomez Angela Rebeca", 6));
        p_biblioteca.agragarSocio(new Estudiante(14524782, "Fernandez Alejandro Facundo", 30));
    }*/
}
