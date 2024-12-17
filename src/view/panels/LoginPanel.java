package view.panels;

import view.components.MyButton;
import view.components.MyTextArea;
import view.components.MyTextField;
import view.main.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPanel extends JPanel {
    private JPanel mainPanel;
    private CardLayout panels;
    private MainView context;

    public LoginPanel(JPanel parent,CardLayout panels,MainView context){
        super();

        this.mainPanel = parent;
        this.panels = panels;
        this.context = context;

        initComponents();
    }

    private void initComponents(){

        setVisible(false);
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBackground(Color.BLACK);

        MyTextArea txtArea = new MyTextArea("Enter with your username",500,130);

        MyTextField txtInput = new MyTextField(200,64);

        MyButton btnSignIn = new MyButton("SIGN IN",200,60);

        MyButton btnBack = new MyButton("BACK",200,60);

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
            context.setUsername(txtInput.getText().trim());
            if (context.getController().signIn(context.getUsername())){
                this.setVisible(false);
                panels.show(mainPanel,"menu");
            }
        });

        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnBack.setTexture("src/resources/img/botonHV1.png");
            }
        });

        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnBack.setTexture("src/resources/img/botonV2.png");
            }
        });

        btnBack.addActionListener(e -> {
            this.setVisible(false);
            panels.show(mainPanel,"lar");
        });
        
        this.add(Box.createVerticalGlue());

        this.add(txtArea);

        this.add(Box.createVerticalGlue());

        this.add(txtInput);

        this.add(Box.createVerticalGlue());

        this.add(btnSignIn);

        this.add(Box.createVerticalGlue());

        this.add(btnBack);

        this.add(Box.createVerticalGlue());

    }

}
