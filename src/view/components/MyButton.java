package view.components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyButton extends JButton {

    private BufferedImage texture;
    private int width;
    private int heigth;

    public MyButton(String txt, int width, int height){
        super(txt);

        this.width = width;
        this.heigth = height;

        try {
            texture = ImageIO.read(new File("src/resources/img/botonV2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setFont(new Font(Font.SANS_SERIF,Font.ITALIC,20));
        setContentAreaFilled(false); // Para personalizar la pintura
        setFocusPainted(false); // Eliminar borde de foco
        setBorderPainted(false); // Opcional: Ocultar borde
        setOpaque(false);
        setAlignmentX(CENTER_ALIGNMENT);

        Dimension buttonDim = new Dimension(this.width,this.heigth);
        //setForeground(new Color(237,140,255));
        setPreferredSize(buttonDim);
        setMinimumSize(buttonDim);
        setMaximumSize(buttonDim);
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    public void setTexture(String path){
        try {
            texture = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        if (texture != null){
            Image imgEsc = texture.getScaledInstance(this.width,this.heigth,Image.SCALE_SMOOTH);
            g2d.drawImage(imgEsc,0,0,getWidth(),getHeight(),this);
        }

        // Dibujar el texto del botón
        FontMetrics fm = g2d.getFontMetrics();
        String text = getText();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();

        g2d.setColor(new Color(237,140,255)); // Usar el color de texto definido
        g2d.drawString(text, (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - 3);

        super.paintComponent(g2d);
        g2d.dispose();
    }
}
