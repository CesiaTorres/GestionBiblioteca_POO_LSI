
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaLibros extends JFrame {

    private Biblioteca biblioteca;

    private JTextField txtTitulo, txtEdicion, txtEditorial, txtAnio;

    public VentanaLibros(Biblioteca p_biblioteca) {
        this.biblioteca = p_biblioteca;

        setTitle("Gestion de Libros");
        setSize(350, 300);
        setLayout(new GridLayout(6, 2));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Campos para agregar libro
        add(new JLabel("Titulo:"));
        txtTitulo = new JTextField();
        add(txtTitulo);

        add(new JLabel("Edicion:"));
        txtEdicion = new JTextField();
        add(txtEdicion);

        add(new JLabel("Editorial:"));
        txtEditorial = new JTextField();
        add(txtEditorial);

        add(new JLabel("Año:"));
        txtAnio = new JTextField();
        add(txtAnio);

        JButton btnAgregar = new JButton("Agregar");
        JButton btnQuitar = new JButton("Quitar Libro");
        JButton btnListar = new JButton("Listar Libros");
        JButton btnTitulos = new JButton("Listar Titulos");

        add(btnAgregar);
        add(btnQuitar);
        add(btnListar);
        add(btnTitulos);

        // Eventos

        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String titulo = txtTitulo.getText();
                    int edicion = Integer.parseInt(txtEdicion.getText());
                    String editorial = txtEditorial.getText();
                    int anio = Integer.parseInt(txtAnio.getText());

                    Libro l = new Libro(titulo, edicion, editorial, anio);
                    biblioteca.agregarLibro(l);

                    JOptionPane.showMessageDialog(null, "Libro agregado!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: Datos invalidos");
                }
            }
        });

        btnQuitar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String titulo = JOptionPane.showInputDialog("Titulo del libro:");
                Libro l = buscarLibro(titulo);

                if (l != null) {
                    biblioteca.quitarLibro(l);
                    JOptionPane.showMessageDialog(null, "Libro eliminado");
                } else {
                    JOptionPane.showMessageDialog(null, "No existe ese libro");
                }
            }
        });

        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, biblioteca.listaDeLibros());
            }
        });

        btnTitulos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, biblioteca.listaDeTitulos());
            }
        });

    }

    private Libro buscarLibro(String titulo) {
        for (Libro l : biblioteca.getLibros()) {
            if (l.getTituloLibro().equalsIgnoreCase(titulo)) {
                return l;
            }
        }
        return null;
    }
}
