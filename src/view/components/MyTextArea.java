package view.components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyTextArea extends JTextField {

    private BufferedImage texture;
    private int width;
    private int heigth;

    public MyTextArea(String txt,int width, int height){
        super(txt);

        this.width = width;
        this.heigth = height;

        try {
            texture = ImageIO.read(new File("src/resources/img/txtareaV1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setOpaque(false);
        setEditable(false);
        Dimension areaDim = new Dimension(this.width,this.heigth);
        setPreferredSize(areaDim);
        setMaximumSize(areaDim);
        setMaximumSize(areaDim);

        setFont(new Font(Font.SANS_SERIF,Font.ITALIC,25));
        setForeground(new Color(237,140,255));
        setHorizontalAlignment(JTextField.CENTER);

        setAlignmentX(CENTER_ALIGNMENT);
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    public void setTexture(String path){
        try {
            texture = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        if (texture != null){
            Image imgEsc = texture.getScaledInstance(this.width,this.heigth,Image.SCALE_SMOOTH);
            g2d.drawImage(imgEsc,0,0,getWidth(),getHeight(),this);
        }
        super.paintComponent(g);
        g2d.dispose();
    }
}
