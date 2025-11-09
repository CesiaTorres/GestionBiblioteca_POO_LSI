package GUI;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class Fondo extends JPanel {
    private Image imagen;

    public Fondo(String nombreImagen) {
        if (nombreImagen != null) {
            try {
                imagen = new ImageIcon(getClass().getResource(nombreImagen)).getImage();
            } catch (Exception e) {
                System.err.println("Error al cargar la imagen: " + nombreImagen);
                e.printStackTrace();
            }
        }
        this.setOpaque(false); 
    }

    // Sobrescribimos el método para dibujar el fondo
    @Override
    protected void paintComponent(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        }
        super.paintComponent(g); 
    }
}
