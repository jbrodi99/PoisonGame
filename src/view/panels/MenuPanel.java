package view.panels;

import view.components.MyButton;
import view.components.MyTextArea;
import view.main.MainView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuPanel extends JPanel {
    private BufferedImage texture;
    private JPanel mainPanel;
    private CardLayout panels;
    private MainView context;
    MyTextArea txtArea;

    public MenuPanel(JPanel parent, CardLayout panels, MainView context, String resource){
        super();

        this.mainPanel = parent;
        this.panels = panels;
        this.context = context;

        try {
            texture = ImageIO.read(new File(resource));
        } catch (IOException e) {
            e.printStackTrace();
        }
        initComponents();
    }

    private void initComponents() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(false);

        txtArea = new MyTextArea("Welcome " + context.getUsername() + "!",500,130); //se setea con null el saludo
        MyButton btnPlay = new MyButton("PLAY",200,60);
        MyButton btnRanking = new MyButton("RANKING",200,60);
        MyButton btnHowPlay = new MyButton("HOW PLAY",200,60);

        btnPlay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnPlay.setTexture("src/resources/img/botonHV1.png");
                setTexture("src/resources/img/menuGameV2.png");
            }
        });

        btnPlay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnPlay.setTexture("src/resources/img/botonV2.png");
                setTexture("src/resources/img/menuGameFondoV1.png");
            }
        });

        btnPlay.addActionListener(e -> {
            this.setVisible(false);
            panels.show(mainPanel,"selection");
        });

        btnRanking.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnRanking.setTexture("src/resources/img/botonHV1.png");
                setTexture("src/resources/img/menuGameBV2.png");
            }
        });

        btnRanking.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnRanking.setTexture("src/resources/img/botonV2.png");
                setTexture("src/resources/img/menuGameFondoV1.png");
            }
        });

        btnRanking.addActionListener(e -> {
//            this.setVisible(false);
//            panels.show(mainPanel,"reg");
        });

        btnHowPlay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnHowPlay.setTexture("src/resources/img/botonHV1.png");
                setTexture("src/resources/img/menuGameBBV2.png");
            }
        });

        btnHowPlay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnHowPlay.setTexture("src/resources/img/botonV2.png");
                setTexture("src/resources/img/menuGameFondoV1.png");
            }
        });

        btnHowPlay.addActionListener(e -> {
//            this.setVisible(false);
//            panels.show(mainPanel,"login");
        });

        this.add(Box.createVerticalGlue());
        this.add(txtArea);
        this.add(Box.createVerticalGlue());
        this.add(btnPlay);
        this.add(Box.createVerticalGlue());
        this.add(btnRanking);
        this.add(Box.createVerticalGlue());
        this.add(btnHowPlay);
        this.add(Box.createVerticalGlue());

        setVisible(false);
    }

    public void setTexture(String path){
        try {
            texture = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (texture != null){
            Image imgEsc = texture.getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH);
            g2d.drawImage(imgEsc,0,0,getWidth(),getHeight(),this);
        }

        txtArea.setText("Welcome " + context.getUsername() + "!");  //Solución provisoria para actualizar el nombre

        super.paintComponent(g);
    }
}
