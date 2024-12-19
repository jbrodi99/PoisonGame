package view.panels;

import utils.TextureFactory;
import view.components.MyButton;
import view.components.MyTextArea;
import view.components.MyTextField;
import view.main.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterPanel extends CustomPanel {

    public RegisterPanel(JPanel parent, CardLayout panels, MainView context){
        super(parent,panels,context);
        setNextPanel(PANELS.LOGIN_AND_REGISTER);
        CustomPanelFactory.createCustomPaneltexture(mainPanel,panels,context, TextureFactory.getInstance(),PANELS.LOGIN_AND_REGISTER);
        initComponents();
    }

    private void initComponents(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBackground(Color.BLACK);

        MyTextArea txtArea = new MyTextArea("Register with your username",500,130);
        txtArea.setTexture("src/resources/img/components/txtareaV1.png");
        MyTextField txtInput = new MyTextField(200,64);
        txtInput.setTexture("src/resources/img/components/txtFieldV6.png");
        MyButton btnSignUp = new MyButton(200,60, TextureFactory.getInstance());

        btnSignUp.setText("SIGN UP");
        btnSignUp.setTexture("src/resources/img/components/botonV2.png");

        btnSignUp.addActionListener(e -> {
            String name = txtInput.getText().trim();
            if (name != null){
                context.getController().signUp(txtInput.getText().trim());
                nextPanel();
            }
        });

        MyButton btnBack = new MyButton(200,60, TextureFactory.getInstance());

        btnBack.setText("BACK");
        btnBack.setTexture("src/resources/img/components/botonV2.png");

        btnBack.addActionListener(e -> {
            nextPanel();
        });

        this.add(Box.createVerticalGlue());
        this.add(txtArea);
        this.add(Box.createVerticalGlue());
        this.add(txtInput);
        this.add(Box.createVerticalGlue());
        this.add(btnSignUp);
        this.add(Box.createVerticalGlue());
        this.add(btnBack);
        this.add(Box.createVerticalGlue());
    }
}
