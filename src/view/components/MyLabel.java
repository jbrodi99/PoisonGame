package view.components;

import utils.ITextureFactory;
import utils.TextureFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyLabel extends JLabel {
    private BufferedImage texture = null;
    private Image imgEsc = null;
    private int width;
    private int height;
    private double degree = 0; // Ángulo de rotación en grados
    private ITextureFactory textureFactory = new TextureFactory();

    public MyLabel(int width, int height){
        super();
        this.width = width;
        this.height = height;
        initComponents();
    }

    public MyLabel(int width, int height, double degree){
        super();
        this.width = width;
        this.height = height;
//        this.degree = degree;
        initComponents();
    }

    public MyLabel(int width, int height,String path){
        super();
        this.width = width;
        this.height = height;
        try {
            texture = ImageIO.read(new File(path));
            imgEsc = texture.getScaledInstance(this.width,this.height,Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        initComponents();
    }

    private void initComponents(){
        setFont(new Font(Font.SANS_SERIF,Font.ITALIC,20));
        setForeground(new Color(237,140,255));
        setOpaque(false);
        setAlignmentX(CENTER_ALIGNMENT);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);

        Dimension buttonDim = new Dimension(this.width,this.height);
        setPreferredSize(buttonDim);
        setMinimumSize(buttonDim);
        setMaximumSize(buttonDim);
    }

//    private void rotate(double degree){
//        this.degree = degree;
//    }

    public void setTexture(String path){
        try {
            texture = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        revalidate();
        repaint();
    }

    public void setTexture(String type, String number){
        SwingWorker<Image, Void> worker = new SwingWorker<>() {
            @Override
            protected Image doInBackground() {
                return textureFactory.createTexture(type,number,width,height);
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
//        imgEsc = textureFactory.createTexture(type,number,this.width,this.height);
//        revalidate();
//        repaint();
    }

    public void setTexture(String type, String number, int index){
        SwingWorker<Image, Void> worker = new SwingWorker<>() {
            @Override
            protected Image doInBackground() {
                return textureFactory.createTexture(type,number,index,width, height);
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
//        imgEsc = textureFactory.createTexture(type,number,index,this.width, this.height);
//        revalidate();
//        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (imgEsc != null){
//            Image imgEsc = texture.getScaledInstance(this.width,this.height,Image.SCALE_SMOOTH);
            g2d.drawImage(imgEsc,0,0,getWidth(),getHeight(),this);
        }

//        if (degree != 0) {
//            int w = getWidth();
//            int h = getHeight();
//            g2d.translate(w / 2, h / 2);
//            g2d.rotate(Math.toRadians(degree));
//            g2d.translate(-w / 2, -h / 2);
//        }

        super.paintComponent(g);
    }
}
