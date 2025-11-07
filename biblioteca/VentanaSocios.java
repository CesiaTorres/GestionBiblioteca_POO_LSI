import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VentanaSocios extends JFrame {

    private Biblioteca biblioteca;

    public VentanaSocios(Biblioteca p_biblioteca) {
        this.biblioteca = p_biblioteca;

        setTitle("Gestion de Socios");
        setSize(300, 300);
        setLayout(new GridLayout(6, 1));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnRegistrar = new JButton("Registrar Socio");
        JButton btnBuscar = new JButton("Buscar Socio");
        JButton btnQuitar = new JButton("Eliminar Socio");
        JButton btnListar = new JButton("Listar Socios");
        JButton btnDocentesResp = new JButton("Docentes Responsables");
        JButton btnSalir = new JButton("Guardar y Salir");

        add(btnRegistrar);
        add(btnBuscar);
        add(btnQuitar);
        add(btnListar);
        add(btnDocentesResp);
        add(btnSalir);

        // EVENTOS ======================================

        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int dni = Integer.parseInt(JOptionPane.showInputDialog(null,"DNI:", "Gestión de Socios", JOptionPane.QUESTION_MESSAGE));

                    if (biblioteca.buscarSocio(dni) != null) {
                    JOptionPane.showMessageDialog(null, "Error: Ya existe un socio con ese DNI.", "Gestion de Socios", JOptionPane.ERROR_MESSAGE);
                    return; 
            }

                    String nombre = JOptionPane.showInputDialog(null,"Nombre y apellido:","Gestión de Socios", JOptionPane.QUESTION_MESSAGE);
                    int opcion = Integer.parseInt(JOptionPane.showInputDialog(null,"1. Estudiante\n2. Docente","Gestión de Socios", JOptionPane.QUESTION_MESSAGE));

                    if (opcion == 1) {
                        String carrera = JOptionPane.showInputDialog(null,"Carrera:","Gestión de Socios", JOptionPane.QUESTION_MESSAGE);
                        biblioteca.nuevoSocioEstudiante(dni, nombre, carrera);
                    } else if (opcion == 2) {
                        String area = JOptionPane.showInputDialog(null,"Area:","Gestión de Socios", JOptionPane.QUESTION_MESSAGE);
                        biblioteca.nuevoSocioDocente(dni, nombre, area);
                    }

                    JOptionPane.showMessageDialog(null, "¡Socio registrado!","Biblioteca UNNE",JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ".Datos invalidos.","Biblioteca UNNE",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int dni = Integer.parseInt(JOptionPane.showInputDialog(null,"DNI:","Gestion de Socios",JOptionPane.INFORMATION_MESSAGE));
                    Socio s = biblioteca.buscarSocio(dni);

                    if (s != null) {
                        JOptionPane.showMessageDialog(null, s.toString(),"Gestion de Socios",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No existe ese socio.","Biblioteca UNNE",JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error en el DNI","Biblioteca UNNE",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnQuitar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int dni = Integer.parseInt(JOptionPane.showInputDialog(null,"DNI:","Gestion de Socios",JOptionPane.INFORMATION_MESSAGE));
                    Socio s = biblioteca.buscarSocio(dni);

                    if (s != null) {
                        biblioteca.quitarSocio(s);
                        JOptionPane.showMessageDialog(null, "Socio eliminado.","Biblioteca UNNE",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No existe ese socio.","Biblioteca UNNE",JOptionPane.WARNING_MESSAGE);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error en el DNI","Biblioteca UNNE",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, biblioteca.listaDeSocios(),"Lista de Socios",JOptionPane.PLAIN_MESSAGE);
            }
        });

        btnDocentesResp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, biblioteca.listaDeDocentesResponsables(),"Lista de Docentes Responsables",JOptionPane.PLAIN_MESSAGE);
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
            }
        });
    }
}
