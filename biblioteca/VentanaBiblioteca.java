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

        JButton btnLibros = new JButton("Gestionar Libros");
        JButton btnSocios = new JButton("Gestionar Socios");
        JButton btnPrestamos = new JButton("Gestionar Prestamos");
        JButton btnSalir = new JButton("Cerrar Ventana");

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

        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
            }
        });
    }
}
