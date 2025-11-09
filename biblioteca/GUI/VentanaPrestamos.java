package GUI;
import biblioteca.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class VentanaPrestamos extends JFrame {

    private Biblioteca biblioteca;

    public VentanaPrestamos(Biblioteca p_biblioteca) {
        this.setBiblioteca(p_biblioteca);

        this.setTitle("Gestion de Prestamos");
        this.setSize(300, 300);
        this.setPreferredSize(getSize());
        this.setLayout(new GridLayout(5, 1, 15, 5));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JButton btnPrestar = new JButton("Registrar Prestamo");
        btnPrestar.setBorderPainted(btnPrestar.isBorderPainted());
        JButton btnDevolver = new JButton("Registrar Devolucion");
        btnDevolver.setBorderPainted(btnDevolver.isBorderPainted());
        JButton btnVencidos = new JButton("Listar Vencidos");
        btnVencidos.setBorderPainted(btnVencidos.isBorderPainted());
        JButton btnQuien = new JButton("Quien tiene el libro?");
        btnQuien.setBorderPainted(btnQuien.isBorderPainted());
        JButton btnSalir = new JButton("Guardar y Salir");
        btnSalir.setBackground(new Color(255, 31, 31));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setBorderPainted(btnSalir.isBorderPainted());

        this.add(btnPrestar);
        this.add(btnDevolver);
        this.add(btnVencidos);
        this.add(btnQuien);
        this.add(btnSalir);

        // === EVENTOS ===

        btnPrestar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int dni = Integer.parseInt(JOptionPane.showInputDialog(null, "DNI:", "Gestion de Prestamos",
                            JOptionPane.INFORMATION_MESSAGE));
                    Socio s = getBiblioteca().buscarSocio(dni);

                    if (s == null) {
                        JOptionPane.showMessageDialog(null, "Socio no encontrado", "Gestion de Prestamos",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    String titulo = JOptionPane.showInputDialog(null, "Titulo del libro:", "Gestion de Prestamos",
                            JOptionPane.INFORMATION_MESSAGE);
                    Libro l = buscarLibro(titulo);

                    if (l == null) {
                        JOptionPane.showMessageDialog(null, "Libro no existe", "Biblioteca UNNE",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    Calendar hoy = Calendar.getInstance();
                    if (getBiblioteca().prestarLibro(hoy, s, l)) {
                        JOptionPane.showMessageDialog(null, "Prestamo registrado!", "Gestion de Prestamos",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "No se puede realizar el préstamo. Verifique la disponibilidad del libro o la capacidad del socio.",
                                "Gestion de Prestamos", JOptionPane.WARNING_MESSAGE);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error en los datos", "Biblioteca UNNE", JOptionPane.ERROR);
                }
            }
        });

        btnDevolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String titulo = JOptionPane.showInputDialog(null, "Titulo del libro:", "Gestion de Prestamos",
                            JOptionPane.INFORMATION_MESSAGE);
                    Libro l = buscarLibro(titulo);

                    if (l == null) {
                        JOptionPane.showMessageDialog(null, "Libro no encontrado", "Gestion de Prestamos",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    getBiblioteca().devolverLibro(l);
                    JOptionPane.showMessageDialog(null, "Devolucion registrada!", "Gestion de Prestamos",
                            JOptionPane.INFORMATION_MESSAGE);

                } catch (LibroNoPrestadoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Biblioteca UNNE",
                            JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error en datos", "Biblioteca UNNE",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        btnVencidos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (getBiblioteca().prestamosVencidos().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay prestamos vencidos", "Gestion de Prestamos",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, getBiblioteca().prestamosVencidos(), "Gestion de Prestamos",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        btnQuien.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String titulo = JOptionPane.showInputDialog(null, "Titulo del libro:", "Gestion de Prestamos",
                            JOptionPane.INFORMATION_MESSAGE);
                    Libro l = buscarLibro(titulo);

                    if (l == null) {
                        JOptionPane.showMessageDialog(null, "El libro no existe", "Biblioteca UNNE",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    String mensaje = getBiblioteca().quienTieneElLibro(l);
                    JOptionPane.showMessageDialog(null, mensaje, "Biblioteca UNNE", JOptionPane.INFORMATION_MESSAGE);

                } catch (LibroNoPrestadoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Biblioteca UNNE",
                            JOptionPane.INFORMATION_MESSAGE);
                }
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
