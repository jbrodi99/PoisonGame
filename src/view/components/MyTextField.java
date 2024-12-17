package view.components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyTextField extends JTextField {
    private BufferedImage texture;
    private int width;
    private int heigth;

    public MyTextField(int width, int height){
        super();

        this.width = width;
        this.heigth = height;

        try {
            texture = ImageIO.read(new File("src/resources/img/txtFieldV6.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setOpaque(false);

        Dimension txtDim = new Dimension(this.width,this.heigth);
        setPreferredSize(txtDim);
        setMinimumSize(txtDim);
        setMaximumSize(txtDim);

        setFont(new Font(Font.SANS_SERIF,Font.ITALIC,20));
        setForeground(new Color(237,140,255));
        setHorizontalAlignment(JTextField.CENTER);
        //setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

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
