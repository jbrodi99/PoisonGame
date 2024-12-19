package view.components;

import utils.ITextureFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyButton extends JButton {

    private Image imgEsc = null;
    private final int width;
    private final int height;
    private final ITextureFactory textureFactory;

    public MyButton(int width, int height, ITextureFactory textureFactory){
        super();
        this.width = width;
        this.height = height;
        this.textureFactory = textureFactory;
        initComponent();
    }

    private void initComponent() {

        setContentAreaFilled(false); // Para personalizar la pintura
        setFocusPainted(false); // Eliminar borde de foco
        setBorderPainted(false); // Opcional: Ocultar borde

        setFont(new Font(Font.SANS_SERIF,Font.ITALIC,20));
        setForeground(new Color(237,140,255));
        setOpaque(false);
        setAlignmentX(CENTER_ALIGNMENT);

        Dimension buttonDim = new Dimension(this.width,this.height);
        setPreferredSize(buttonDim);
        setMinimumSize(buttonDim);
        setMaximumSize(buttonDim);
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setTexture("src/resources/img/components/botonHV1.png");
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                setTexture("src/resources/img/components/botonV2.png");
            }
        });
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

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        if (imgEsc != null){
            g2d.drawImage(imgEsc,0,0,getWidth(),getHeight(),this);
        }

        super.paintComponent(g2d);
    }
}
