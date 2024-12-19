package view.panels;

import utils.ITextureFactory;
import utils.TextureFactory;

import javax.swing.*;
import java.awt.*;


public class StatusPanel extends JPanel{

    protected ITextureFactory textureFactory;
    protected Image imageScaled = null;

    public StatusPanel() {
        super();
        this.textureFactory = TextureFactory.getInstance();
        initComponents();
    }

    private void initComponents(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(false);
        setAlignmentX(CENTER_ALIGNMENT);
        setAlignmentY(CENTER_ALIGNMENT);
    }

    public void setTexture(String path, int width, int height){
        SwingWorker<Image, Void> worker = new SwingWorker<>() {
            @Override
            protected Image doInBackground() {
                return textureFactory.createTexture(path,width,height);
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
