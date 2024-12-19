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
    private Image imgEsc = null;
    private final int width;
    private final int height;
    private final ITextureFactory textureFactory = TextureFactory.getInstance();

    public MyLabel(int width, int height){
        super();
        this.width = width;
        this.height = height;
        initComponents();
    }

    public MyLabel(int width, int height,String path){
        super();
        this.width = width;
        this.height = height;
        setTexture(path);
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

    public void setTexture(String path){
        SwingWorker<Image, Void> worker = new SwingWorker<>() {
            @Override
            protected Image doInBackground() {
                return textureFactory.createTexture(path,width,height);
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
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (imgEsc != null){
            g2d.drawImage(imgEsc,0,0,getWidth(),getHeight(),this);
        }
        super.paintComponent(g);
    }
}
