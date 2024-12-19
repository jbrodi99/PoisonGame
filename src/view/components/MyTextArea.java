package view.components;

import utils.ITextureFactory;
import utils.TextureFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyTextArea extends JTextField {

    private Image imageScaled = null;
    private final int width;
    private final int heigth;
    private final ITextureFactory textureFactory;

    public MyTextArea(String txt,int width, int height){
        super(txt);
        this.width = width;
        this.heigth = height;
        textureFactory = TextureFactory.getInstance();
        initComponent();
    }

    private void initComponent(){
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
        SwingWorker<Image, Void> worker = new SwingWorker<>() {
            @Override
            protected Image doInBackground() {
                return textureFactory.createTexture(path,width,heigth);
            }

            @Override
            protected void done() {
                try {
                    imageScaled = get();
                    revalidate();
                    repaint();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        if (imageScaled != null){
            g2d.drawImage(imageScaled,0,0,getWidth(),getHeight(),this);
        }
        super.paintComponent(g);
    }
}
