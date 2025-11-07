import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaBiblioteca extends JFrame {

    private Biblioteca biblioteca;

    public VentanaBiblioteca(Biblioteca p_biblioteca) {
        this.biblioteca = p_biblioteca;
        

        setTitle("Biblioteca - UNNE");
        setSize(300, 250);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 1));
        setLocationRelativeTo(null);
        setVisible(true);
        toFront();
        setAlwaysOnTop(true);
        setAlwaysOnTop(false);


        JButton btnLibros = new JButton("Gestionar Libros");
        JButton btnSocios = new JButton("Gestionar Socios");
        JButton btnPrestamos = new JButton("Gestionar Prestamos");
        JButton btnSalir = new JButton("Guardar y Salir");

        add(btnLibros);
        add(btnSocios);
        add(btnPrestamos);
        add(btnSalir);

            // Eventos
            btnLibros.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new VentanaLibros(biblioteca).setVisible(true);
                }
            });

            btnSocios.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new VentanaSocios(biblioteca).setVisible(true);
                }
            });

            btnPrestamos.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new VentanaPrestamos(biblioteca).setVisible(true);
                }
            });

            // El botón Salir realiza el procedimiento centralizado de salida (guardar y salir)
            btnSalir.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    exitProcedure();
                }
            });

        
    }

    /**
     * Procedimiento centralizado para guardar (si aplica) y salir de la aplicación.
     * Actualmente finaliza la JVM; si necesitás persistir `biblioteca` antes de salir,
     * agregá esa lógica aquí.
     */
    private void exitProcedure() {
        GestorPersistencia gestor = new GestorPersistencia();
        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Desea guardar y salir?",
                "Guardar y salir",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (opcion == JOptionPane.YES_OPTION) {
            // Aquí se puede agregar la lógica de guardado (serializar `biblioteca`, escribir en archivo, etc.)
            // Ejemplo: guardarBibliotecaEnArchivo(this.biblioteca);
            System.out.println("Guardando datos y saliendo...");
            try {
                    gestor.guardarDatos(biblioteca.getLibros(), biblioteca.getSocios());
            } catch (Exception e) {
                    System.err.println("¡Error fatal al guardar los datos! " + e.getMessage());
                    e.printStackTrace();
            }
            dispose();
            System.exit(0);
        } else if (opcion == JOptionPane.NO_OPTION) {
            // Solo cerrar la interfaz sin salir de la aplicación (o cancelar, según prefieras)
            dispose();
        }
    }
}
