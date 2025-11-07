import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaBiblioteca extends JFrame {

    private Biblioteca biblioteca;

    public VentanaBiblioteca(Biblioteca p_biblioteca) {
        this.setBiblioteca(p_biblioteca);

        this.setTitle("Biblioteca - UNNE");
        this.setSize(500, 450);
        this.setPreferredSize(getSize());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Fondo fondo = new Fondo("/img/fondo.jpg");
        fondo.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));
        this.setContentPane(fondo);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.toFront();
        this.setAlwaysOnTop(true);
        this.setAlwaysOnTop(false);

        JButton btnLibros = new JButton("Gestionar Libros");
        btnLibros.setPreferredSize(new Dimension(250, 50));
        btnLibros.setBorderPainted(btnLibros.isBorderPainted());
        JButton btnSocios = new JButton("Gestionar Socios");
        btnSocios.setPreferredSize(new Dimension(250, 50));
        btnSocios.setBorderPainted(btnLibros.isBorderPainted());
        JButton btnPrestamos = new JButton("Gestionar Prestamos");
        btnPrestamos.setPreferredSize(new Dimension(250, 50));
        btnPrestamos.setBorderPainted(btnLibros.isBorderPainted());
        JButton btnSalir = new JButton("Guardar y Salir");
        btnSalir.setPreferredSize(new Dimension(250, 50));
        btnSalir.setBackground(new Color(255, 31, 31));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setBorderPainted(btnLibros.isBorderPainted());

        this.add(btnLibros);
        this.add(btnSocios);
        this.add(btnPrestamos);
        this.add(btnSalir);

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
    
    //setters y getters de biblioteca
    public Biblioteca getBiblioteca() {
        return this.biblioteca;
    }
    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
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
                    gestor.guardarDatos(this.getBiblioteca().getLibros(),this.getBiblioteca().getSocios());
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
