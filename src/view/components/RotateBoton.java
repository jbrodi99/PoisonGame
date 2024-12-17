package view.components;


import javax.swing.*;
import java.awt.*;


public class RotateBoton extends JButton {
    private final double angle; // Ángulo de rotación en grados

    public RotateBoton(String text, double angle) {
        super(text);
        this.angle = angle;
        setOpaque(false); // No usar un fondo opaco
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Rotar el gráfico alrededor del centro del componente
        int w = getWidth();
        int h = getHeight();
        g2d.translate(w / 2, h / 2);
        g2d.rotate(Math.toRadians(angle));
        g2d.translate(-w / 2, -h / 2);

        // Dibujar el botón rotado
        super.paintComponent(g2d);

        g2d.dispose();
    }
}
