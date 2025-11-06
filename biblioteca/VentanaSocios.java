import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VentanaSocios extends JFrame {

    private Biblioteca biblioteca;

    public VentanaSocios(Biblioteca p_biblioteca) {
        this.biblioteca = p_biblioteca;

        setTitle("Gestion de Socios");
        setSize(300, 300);
        setLayout(new GridLayout(5, 1));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton btnRegistrar = new JButton("Registrar Socio");
        JButton btnBuscar = new JButton("Buscar Socio");
        JButton btnQuitar = new JButton("Eliminar Socio");
        JButton btnListar = new JButton("Listar Socios");
        JButton btnDocentesResp = new JButton("Docentes Responsables");

        add(btnRegistrar);
        add(btnBuscar);
        add(btnQuitar);
        add(btnListar);
        add(btnDocentesResp);

        // EVENTOS ======================================

        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int dni = Integer.parseInt(JOptionPane.showInputDialog("DNI:"));
                    String nombre = JOptionPane.showInputDialog("Nombre y apellido:");
                    int opcion = Integer.parseInt(JOptionPane.showInputDialog("1. Estudiante\n2. Docente"));

                    if (opcion == 1) {
                        String carrera = JOptionPane.showInputDialog("Carrera:");
                        biblioteca.nuevoSocioEstudiante(dni, nombre, carrera);
                    } else if (opcion == 2) {
                        String area = JOptionPane.showInputDialog("Area:");
                        biblioteca.nuevoSocioDocente(dni, nombre, area);
                    }

                    JOptionPane.showMessageDialog(null, "¡Socio registrado!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ".Datos invalidos.");
                }
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int dni = Integer.parseInt(JOptionPane.showInputDialog("Ingrese DNI del socio:"));
                    Socio s = biblioteca.buscarSocio(dni);

                    if (s != null) {
                        JOptionPane.showMessageDialog(null, s.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "No existe ese socio.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error en el DNI");
                }
            }
        });

        btnQuitar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int dni = Integer.parseInt(JOptionPane.showInputDialog("Ingrese DNI del socio:"));
                    Socio s = biblioteca.buscarSocio(dni);

                    if (s != null) {
                        biblioteca.quitarSocio(s);
                        JOptionPane.showMessageDialog(null, "Socio eliminado.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No existe ese socio.");
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error en el DNI");
                }
            }
        });

        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, biblioteca.listaDeSocios());
            }
        });

        btnDocentesResp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, biblioteca.listaDeDocentesResponsables());
            }
        });
    }
}
