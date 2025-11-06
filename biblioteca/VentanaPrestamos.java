import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class VentanaPrestamos extends JFrame {

    private Biblioteca biblioteca;

    public VentanaPrestamos(Biblioteca p_biblioteca) {
        this.biblioteca = p_biblioteca;

        setTitle("Gestion de Prestamos");
        setSize(300, 300);
        setLayout(new GridLayout(4, 1));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton btnPrestar = new JButton("Registrar Prestamo");
        JButton btnDevolver = new JButton("Registrar Devolucion");
        JButton btnVencidos = new JButton("Listar Vencidos");
        JButton btnQuien = new JButton("Quien tiene el libro?");

        add(btnPrestar);
        add(btnDevolver);
        add(btnVencidos);
        add(btnQuien);

        // === EVENTOS ===

        btnPrestar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int dni = Integer.parseInt(JOptionPane.showInputDialog("DNI del socio:"));
                    Socio s = biblioteca.buscarSocio(dni);

                    if (s == null) {
                        JOptionPane.showMessageDialog(null, "Socio no encontrado");
                        return;
                    }

                    String titulo = JOptionPane.showInputDialog("Titulo del libro:");
                    Libro l = buscarLibro(titulo);

                    if (l == null) {
                        JOptionPane.showMessageDialog(null, "Libro no existe");
                        return;
                    }

                    Calendar hoy = Calendar.getInstance();
                    if (biblioteca.prestarLibro(hoy, s, l)) {
                        JOptionPane.showMessageDialog(null, "Prestamo registrado!");
                    }else {
                        JOptionPane.showMessageDialog(null, "No se puede realizar el préstamo. Verifique la disponibilidad del libro o la capacidad del socio.");
                    }


                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error en los datos");
                }
            }
        });

        btnDevolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String titulo = JOptionPane.showInputDialog("Titulo del libro:");
                    Libro l = buscarLibro(titulo);

                    if (l == null) {
                        JOptionPane.showMessageDialog(null, "Libro no encontrado");
                        return;
                    }

                    biblioteca.devolverLibro(l);
                    JOptionPane.showMessageDialog(null, "Devolucion registrada!");

                } catch (LibroNoPrestadoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error en datos");
                }
            }
        });

        btnVencidos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, biblioteca.prestamosVencidos());
            }
        });

        btnQuien.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String titulo = JOptionPane.showInputDialog("Titulo del libro:");
                    Libro l = buscarLibro(titulo);

                    if (l == null) {
                        JOptionPane.showMessageDialog(null, "El libro no existe");
                        return;
                    }

                    String mensaje = biblioteca.quienTieneElLibro(l);
                    JOptionPane.showMessageDialog(null, mensaje);

                } catch (LibroNoPrestadoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
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
