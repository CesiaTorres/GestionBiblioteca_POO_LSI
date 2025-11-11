package GUI;
import biblioteca.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaLibros extends JFrame {

    private Biblioteca biblioteca;

    private JTextField txtTitulo, txtEdicion, txtEditorial, txtAnio;

    public VentanaLibros(Biblioteca p_biblioteca) {
        this.setBiblioteca(p_biblioteca);

        this.setTitle("Gestion de Libros");
        this.setSize(350, 300);
        this.setPreferredSize(getSize());
        this.setLayout(new GridLayout(7, 2, 5, 5));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // Campos para agregar libro
        this.add(new JLabel("Titulo:"));
        txtTitulo = new JTextField();
        this.add(txtTitulo);

        this.add(new JLabel("Edicion (int):"));
        txtEdicion = new JTextField();
        this.add(txtEdicion);

        this.add(new JLabel("Editorial:"));
        txtEditorial = new JTextField();
        this.add(txtEditorial);

        this.add(new JLabel("Año (int):"));
        txtAnio = new JTextField();
        this.add(txtAnio);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBackground(new Color(0, 224, 45));
        btnAgregar.setBorderPainted(btnAgregar.isBorderPainted());
        JButton btnQuitar = new JButton("Quitar Libro");
        btnQuitar.setBorderPainted(btnQuitar.isBorderPainted());
        JButton btnListar = new JButton("Listar Libros");
        btnListar.setBorderPainted(btnListar.isBorderPainted());
        JButton btnTitulos = new JButton("Listar Titulos");
        btnTitulos.setBorderPainted(btnTitulos.isBorderPainted());
        JButton btnSalir = new JButton("Guardar y Salir");
        btnSalir.setBackground(new Color(255, 31, 31));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setBorderPainted(false);

        this.add(btnAgregar);
        this.add(btnQuitar);
        this.add(btnListar);
        this.add(btnTitulos);
        this.add(btnSalir);

        // Eventos

        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String titulo = txtTitulo.getText();
                    int edicion = Integer.parseInt(txtEdicion.getText());
                    String editorial = txtEditorial.getText();
                    int anio = Integer.parseInt(txtAnio.getText());

                    Libro l = new Libro(titulo, edicion, editorial, anio);
                    getBiblioteca().agregarLibro(l);
                    JOptionPane.showMessageDialog(null, "Libro agregado!", "Biblioteca UNNE",
                            JOptionPane.INFORMATION_MESSAGE);

                    txtTitulo.setText("");
                    txtEdicion.setText("");
                    txtEditorial.setText("");
                    txtAnio.setText("");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Biblioteca UNNE", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: Datos invalidos", "Biblioteca UNNE",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnQuitar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String titulo = JOptionPane.showInputDialog("Titulo del libro:");
                Libro l = buscarLibro(titulo);

                if (l != null) {
                    if (getBiblioteca().quitarLibro(l)) {
                        JOptionPane.showMessageDialog(null, "Libro eliminado. ", "Biblioteca UNNE",
                            JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo eliminar el Libro. ", "Biblioteca UNNE",
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No existe ese libro", "Biblioteca UNNE",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, getBiblioteca().listaDeLibros(), "Lista de Libros",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });

        btnTitulos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, getBiblioteca().listaDeTitulos(), "Lista de Titulos",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
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
    private Libro buscarLibro(String titulo) {
        for (Libro l : this.getBiblioteca().getLibros()) {
            if (l.getTituloLibro().equalsIgnoreCase(titulo)) {
                return l;
            }
        }
        return null;
    }
}
