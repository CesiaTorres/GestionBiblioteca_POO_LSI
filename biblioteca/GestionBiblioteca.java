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
         Scanner lector = new Scanner(System.in); //exception??

         int opcion = 1;
         while (opcion != 0) {
            System.out.println(biblioteca.getNombre());
            System.out.println("Menu Principal");
            System.out.println("1. Gestionar Libros\n2. Gestionar Socios\n3. Gestionar Prestamos\n");
            //Cuanto la opcion es 1: Gestion de libros
                             
            
         }
            //• ¿Qué cantidad de socios de tipo Estudiante hay?
            //• ¿Cuál es la lista de docentes que nunca han adeudado ni adeudan libros?
            //• ¿Cuál es la lista de libros? ¿Y la de socios?
            //• ¿Qué socio tiene prestado el libro “Programando con JAVA”? 
        
    }
    
    private static void menuGestionarLibros(Biblioteca p_biblioteca, Scanner p_lector) {
        int opcion = 1;

        while(opcion != 0){
            System.out.println("Menu Gestion de Libros");
            System.out.println("1. Agregar Libro\n2. Quitar Libro\n3. Listar Libros\n4. Listar Titulos\n0. Volver a menu principal");
            opcion = Integer.parseInt(p_lector.nextLine());
            //IDEA: Verificar que no ingrese una letra
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
                case 5:
                    //quien tiene el libro?
                    break;
                case 6:
                    //anotar si alguien devuelve un libro??
                    break;
                case 7:
                    //prestar libro??
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Ingrese una opcion valida.");
            }
        }
    }
    
    private static void agregarLibro(Biblioteca p_biblioteca, Scanner p_lector) {
        System.out.print("Ingrese el titulo del libro: ");
        String titulo = p_lector.nextLine();
        System.out.print("Ingrese la edicion (numeros): ");
        int edicion = Integer.parseInt(p_lector.nextLine());
        System.out.print("Ingrese el editorial: ");
        String editorial = p_lector.nextLine();
        System.out.print("Ingrese el año de lanzamiento: ");
        int anio = Integer.parseInt(p_lector.nextLine());

        Libro libro = new Libro(titulo, edicion, editorial, anio);
        p_biblioteca.agregarLibro(libro);
    }

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
    
    private static void menuGestionarSocios(Biblioteca p_biblioteca, Scanner p_lector) {
        int opcion = 1;

        while(opcion != 0){
            System.out.println("Menu Gestion de Socios");
            System.out.println("1. Registrar Socio\n2. Mostrar datos de Socio\n3. Quitar Socio\n4. Listar socios\n5. Lista de docentes responsables\n0. Volver a menu principal");
            opcion = Integer.parseInt(p_lector.nextLine());
            //IDEA: Verificar que no ingrese una letra
            switch (opcion) {
                case 1:
                    //
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

        while(opcion != 0){
            System.out.println("Menu Gestion de Prestamos");
            System.out.println("1. Registrar Prestamo\n2. Registrar Devolucion\n3. Listar Prestamos\n0. Volver a menu principal");
            opcion = Integer.parseInt(p_lector.nextLine());
            //IDEA: Verificar que no ingrese una letra
            switch (opcion) {
                case 1:
                    Calendar hoy = Calendar.getInstance();
                    Socio socio = buscarSocio(p_biblioteca, p_lector);
                    Libro libro = buscarLibro(p_biblioteca, p_lector);
                    
                    if(socio == null || libro == null) {
                        System.out.println("No se pudo registrar el prestamo. Verifique los datos ingresados.");
                        break;
                    }
                    
                    p_biblioteca.prestarLibro(hoy, socio, libro);
                    System.out.println("Prestamo registrado exitosamente!");
                    break;
                case 2:
                    registrarDevolucion(p_biblioteca, p_lector);
                    System.out.println("Devolucion registrada exitosamente!");
                    break;
                case 3:
                    System.out.println("Lista de prestamos:\n" + p_biblioteca.listaDePrestamos());
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Ingrese una opcion valida.");
            }
        }
    }

    /**
     * Registra un nuevo socio.
     * @param p_biblioteca Biblioteca en la que se registrará el socio.
     * @param p_lector Lector de la entrada estándar.
     */
    public static void agregarSocio(Biblioteca p_biblioteca, Scanner p_lector) {
        System.out.print("Ingrese el DNI del socio: ");
        int dni = Integer.parseInt(p_lector.nextLine());
        System.out.print("Ingrese el nombre del socio: ");
        String nombre = p_lector.nextLine();
        System.out.print("Ingresar dias a prestar el libro: ");
        int dias = Integer.parseInt(p_lector.nextLine());
        
        int tipo = 0;
        while(tipo != 1 && tipo != 2) {
            System.out.print("1. Es estudiante, 2. Es docente: ");
            tipo = Integer.parseInt(p_lector.nextLine());
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

    public static Socio buscarSocio(Biblioteca p_biblioteca, Scanner p_lector) {
        System.out.print("Ingrese el DNI del socio: ");
        int dni = Integer.parseInt(p_lector.nextLine());
        Socio socio1 = p_biblioteca.buscarSocio(dni);
        if(socio1 != null) {
            return socio1;
        } else {
            System.out.println("El socio no se encuentra en la biblioteca.");
            return null;
        }
    }
    private static void menuGestionarPrestamos(Biblioteca p_biblioteca, Scanner p_lector) {
        
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
