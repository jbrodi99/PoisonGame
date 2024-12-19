package view.components;

import utils.ITextureFactory;
import utils.TextureFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyTextField extends JTextField {
    private Image imgEsc = null;
    private int width;
    private int heigth;
    private final ITextureFactory textureFactory = TextureFactory.getInstance();

    public MyTextField(int width, int height){
        super();
        this.width = width;
        this.heigth = height;
        initComponent();
    }

    private void initComponent(){
        setOpaque(false);

        Dimension txtDim = new Dimension(this.width,this.heigth);
        setPreferredSize(txtDim);
        setMinimumSize(txtDim);
        setMaximumSize(txtDim);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        setFont(new Font(Font.SANS_SERIF,Font.ITALIC,20));
        setForeground(new Color(237,140,255));
        setHorizontalAlignment(JTextField.CENTER);
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
                    imgEsc = get();
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

        if (imgEsc != null){
            g2d.drawImage(imgEsc,0,0,this.width,this.heigth,this);
        }

        super.paintComponent(g2d);
    }
}
