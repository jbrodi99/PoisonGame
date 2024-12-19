package view.panels;

import utils.ITextureFactory;
import view.main.MainView;

import javax.swing.*;
import java.awt.*;

public abstract class CustomPanelTexture extends CustomPanel {

    protected ITextureFactory textureFactory;
    protected Image imageScaled = null;

    public CustomPanelTexture(JPanel parent, CardLayout panels, MainView context, ITextureFactory textureFactory) {
        super(parent, panels, context);
        this.textureFactory = textureFactory;
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
//        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (imageScaled != null){
            g2d.drawImage(imageScaled,0,0,getWidth(),getHeight(),this);
        }
        super.paintComponent(g);
//        g2d.dispose();
    }
}
