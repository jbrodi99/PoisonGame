package view.panels;

import utils.TextureFactory;
import view.components.MyButton;
import view.components.MyTextArea;
import view.components.MyTextField;
import view.main.MainView;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends CustomPanel {

    public LoginPanel(JPanel parent,CardLayout panels,MainView context){
        super(parent,panels,context);
        initComponents();
    }

    private void initComponents(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBackground(Color.BLACK);

        MyTextArea txtArea = new MyTextArea("Enter with your username",500,130);
        txtArea.setTexture("src/resources/img/components/txtareaV1.png");
        MyTextField txtInput = new MyTextField(200,64);
        txtInput.setTexture("src/resources/img/components/txtFieldV6.png");
        MyButton btnSignIn = new MyButton(200,60, TextureFactory.getInstance());
        MyButton btnBack = new MyButton(200,60, TextureFactory.getInstance());

        btnSignIn.setText("SIGN IN");
        btnSignIn.setTexture("src/resources/img/components/botonV2.png");
        btnBack.setText("BACK");
        btnBack.setTexture("src/resources/img/components/botonV2.png");

        btnSignIn.addActionListener(e -> {
            context.setUsername(txtInput.getText().trim());
            if (context.getController().signIn(context.getUsername())){
                setNextPanel(PANELS.MENU);
                CustomPanelFactory.createCustomPaneltexture(mainPanel,panels,context,TextureFactory.getInstance(),PANELS.MENU);
                CustomPanelFactory.removePanel(PANELS.LOGIN_AND_REGISTER);
                CustomPanelFactory.removePanel(PANELS.REGISTER);
                nextPanel();
            }
        });

        btnBack.addActionListener(e -> {
            setNextPanel(PANELS.LOGIN_AND_REGISTER);
            CustomPanelFactory.createCustomPaneltexture(mainPanel,panels,context, TextureFactory.getInstance(),PANELS.LOGIN_AND_REGISTER);
            nextPanel();
        });
        
        add(Box.createVerticalGlue());
        add(txtArea);
        add(Box.createVerticalGlue());
        add(txtInput);
        add(Box.createVerticalGlue());
        add(btnSignIn);
        add(Box.createVerticalGlue());
        add(btnBack);
        add(Box.createVerticalGlue());
    }
}
