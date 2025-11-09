package GUI;
import biblioteca.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VentanaSocios extends JFrame {

    private Biblioteca biblioteca;

    public VentanaSocios(Biblioteca p_biblioteca) {
        this.setBiblioteca(p_biblioteca);

        this.setTitle("Gestion de Socios");
        this.setSize(300, 300);
        this.setPreferredSize(getSize());
        this.setLayout(new GridLayout(6, 1, 5, 5));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JButton btnRegistrar = new JButton("Registrar Socio");
        btnRegistrar.setBackground(new Color(0, 224, 45));
        btnRegistrar.setBorderPainted(btnRegistrar.isBorderPainted());
        JButton btnBuscar = new JButton("Buscar Socio");
        btnBuscar.setBorderPainted(btnBuscar.isBorderPainted());
        JButton btnQuitar = new JButton("Eliminar Socio");
        btnQuitar.setBorderPainted(btnQuitar.isBorderPainted());
        JButton btnListar = new JButton("Listar Socios");
        btnListar.setBorderPainted(btnListar.isBorderPainted());
        JButton btnDocentesResp = new JButton("Docentes Responsables");
        btnDocentesResp.setBorderPainted(btnDocentesResp.isBorderPainted());
        JButton btnSalir = new JButton("Guardar y Salir");
        btnSalir.setBackground(new Color(255, 31, 31));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setBorderPainted(btnSalir.isBorderPainted());

        this.add(btnRegistrar);
        this.add(btnBuscar);
        this.add(btnQuitar);
        this.add(btnListar);
        this.add(btnDocentesResp);
        this.add(btnSalir);

        // EVENTOS ======================================

        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int dni = Integer.parseInt(JOptionPane.showInputDialog(null, "DNI:", "Gestión de Socios",
                            JOptionPane.QUESTION_MESSAGE));

                    if (getBiblioteca().buscarSocio(dni) != null) {
                        JOptionPane.showMessageDialog(null, "Error: Ya existe un socio con ese DNI.",
                                "Gestion de Socios", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String nombre = JOptionPane.showInputDialog(null, "Nombre y apellido:", "Gestión de Socios",
                            JOptionPane.QUESTION_MESSAGE);
                    int opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "1. Estudiante\n2. Docente",
                            "Gestión de Socios", JOptionPane.QUESTION_MESSAGE));

                    if (opcion == 1) {
                        String carrera = JOptionPane.showInputDialog(null, "Carrera:", "Gestión de Socios",
                                JOptionPane.QUESTION_MESSAGE);
                        getBiblioteca().nuevoSocioEstudiante(dni, nombre, carrera);
                    } else if (opcion == 2) {
                        String area = JOptionPane.showInputDialog(null, "Area:", "Gestión de Socios",
                                JOptionPane.QUESTION_MESSAGE);
                        getBiblioteca().nuevoSocioDocente(dni, nombre, area);
                    }

                    JOptionPane.showMessageDialog(null, "¡Socio registrado!", "Biblioteca UNNE",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ".Datos invalidos.", "Biblioteca UNNE",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int dni = Integer.parseInt(JOptionPane.showInputDialog(null, "DNI:", "Gestion de Socios",
                            JOptionPane.INFORMATION_MESSAGE));
                    Socio s = getBiblioteca().buscarSocio(dni);

                    if (s != null) {
                        JOptionPane.showMessageDialog(null, s.toString(), "Gestion de Socios",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No existe ese socio.", "Biblioteca UNNE",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error en el DNI", "Biblioteca UNNE",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnQuitar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int dni = Integer.parseInt(JOptionPane.showInputDialog(null, "DNI:", "Gestion de Socios",
                            JOptionPane.INFORMATION_MESSAGE));
                    Socio s = getBiblioteca().buscarSocio(dni);

                    if (s != null) {
                        getBiblioteca().quitarSocio(s);
                        JOptionPane.showMessageDialog(null, "Socio eliminado.", "Biblioteca UNNE",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No existe ese socio.", "Biblioteca UNNE",
                                JOptionPane.WARNING_MESSAGE);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error en el DNI", "Biblioteca UNNE",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, getBiblioteca().listaDeSocios(), "Lista de Socios",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });

        btnDocentesResp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, getBiblioteca().listaDeDocentesResponsables(),
                        "Lista de Docentes Responsables", JOptionPane.PLAIN_MESSAGE);
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
}
