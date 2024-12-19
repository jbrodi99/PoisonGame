package view.panels;

import utils.ITextureFactory;
import utils.TextureFactory;
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

public class LoginAndRegisterPanel extends CustomPanelTexture{

    public LoginAndRegisterPanel(JPanel parent, CardLayout panels, MainView context, ITextureFactory textureFactory){
        super(parent,panels,context,textureFactory);
        setTexture("src/resources/img/components/fondoMainV2.png",context.getWidth(),context.getHeight());
        initComponents();
    }

    private void initComponents(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(false);

        MyTextArea txtArea = new MyTextArea("Welcome user",500,130);
        txtArea.setTexture("src/resources/img/components/txtareaV1.png");
        MyButton btnSignIn = new MyButton(200,60, TextureFactory.getInstance());
        MyButton btnSignUp = new MyButton(200,60,TextureFactory.getInstance());

        btnSignIn.setText("SIGN IN");
        btnSignUp.setText("SIGN UP");
        
        btnSignIn.setTexture("src/resources/img/components/botonV2.png");
        btnSignUp.setTexture("src/resources/img/components/botonV2.png");

        btnSignIn.addActionListener(e -> {
            setNextPanel(PANELS.LOGIN);
            CustomPanelFactory.createCustomPanel(mainPanel, panels,context, PANELS.LOGIN);
            nextPanel();
        });

        btnSignUp.addActionListener(e -> {
            setNextPanel(PANELS.REGISTER);
            CustomPanelFactory.createCustomPanel(mainPanel,panels,context,PANELS.REGISTER);
            nextPanel();
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
}