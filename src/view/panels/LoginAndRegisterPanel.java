package view.panels;

import view.components.MyButton;
import view.components.MyTextArea;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginAndRegisterPanel extends JPanel {
    private BufferedImage texture;
    private JPanel mainPanel;
    private CardLayout panels;

    public LoginAndRegisterPanel(JPanel parent,CardLayout panels,String resource){
        super();

        this.mainPanel = parent;
        this.panels = panels;

        try {
            texture = ImageIO.read(new File(resource));
        } catch (IOException e) {
            e.printStackTrace();
        }
        initComponents();
    }

    private void initComponents(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(false);

        MyTextArea txtArea = new MyTextArea("Welcome user",500,130);
        MyButton btnSignIn = new MyButton("SIGN IN",200,60);
        MyButton btnSignUp = new MyButton("SIGN UP",200,60);

        btnSignIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnSignIn.setTexture("src/resources/img/botonHV1.png");
            }
        });

        btnSignIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnSignIn.setTexture("src/resources/img/botonV2.png");
            }
        });

        btnSignIn.addActionListener(e -> {
            this.setVisible(false);
            panels.show(mainPanel,"login");
        });

        btnSignUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnSignUp.setTexture("src/resources/img/botonHV1.png");
            }
        });

        btnSignUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnSignUp.setTexture("src/resources/img/botonV2.png");
            }
        });

        btnSignUp.addActionListener(e -> {
            this.setVisible(false);
            panels.show(mainPanel,"reg");
        });

        this.add(Box.createVerticalGlue());
        this.add(txtArea);
        this.add(Box.createVerticalGlue());
        this.add(btnSignIn);
        this.add(Box.createVerticalGlue());
        this.add(btnSignUp);
        this.add(Box.createVerticalGlue());

        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (texture != null){
            Image imgEsc = texture.getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH);
            g2d.drawImage(imgEsc,0,0,getWidth(),getHeight(),this);
        }
        super.paintComponent(g);
    }
}
