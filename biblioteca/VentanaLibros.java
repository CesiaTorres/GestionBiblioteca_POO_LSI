
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
        setLayout(new GridLayout(7, 2));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Campos para agregar libro
        add(new JLabel("Titulo:"));
        txtTitulo = new JTextField();
        add(txtTitulo);

        add(new JLabel("Edicion (int):"));
        txtEdicion = new JTextField();
        add(txtEdicion);

        add(new JLabel("Editorial:"));
        txtEditorial = new JTextField();
        add(txtEditorial);

        add(new JLabel("Año (int):"));
        txtAnio = new JTextField();
        add(txtAnio);

        JButton btnAgregar = new JButton("Agregar");
        JButton btnQuitar = new JButton("Quitar Libro");
        JButton btnListar = new JButton("Listar Libros");
        JButton btnTitulos = new JButton("Listar Titulos");
        JButton btnSalir = new JButton("Guardar y Salir");

        add(btnAgregar);
        add(btnQuitar);
        add(btnListar);
        add(btnTitulos);
        add(btnSalir);

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
                    JOptionPane.showMessageDialog(null, "Libro agregado!", "Biblioteca UNNE",JOptionPane.INFORMATION_MESSAGE);
                    
                    txtTitulo.setText("");
                    txtEdicion.setText("");
                    txtEditorial.setText("");
                    txtAnio.setText("");

                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(),"Biblioteca UNNE",JOptionPane.ERROR_MESSAGE);
                }
                 catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: Datos invalidos","Biblioteca UNNE",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnQuitar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String titulo = JOptionPane.showInputDialog("Titulo del libro:");
                Libro l = buscarLibro(titulo);

                if (l != null) {
                    biblioteca.quitarLibro(l);
                    JOptionPane.showMessageDialog(null, "Libro eliminado. ","Biblioteca UNNE",JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No existe ese libro","Biblioteca UNNE",JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, biblioteca.listaDeLibros(), "Lista de Libros", JOptionPane.PLAIN_MESSAGE);
            }
        });

        btnTitulos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, biblioteca.listaDeTitulos(), "Lista de Titulos", JOptionPane.PLAIN_MESSAGE);
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
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
