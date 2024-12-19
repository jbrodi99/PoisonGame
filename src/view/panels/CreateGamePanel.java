package view.panels;

import utils.TextureFactory;
import view.components.MyButton;
import view.components.MyLabel;
import view.components.MyTextField;
import view.main.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreateGamePanel extends CustomPanel {

    public CreateGamePanel(JPanel parent, CardLayout panels, MainView context){
        super(parent,panels,context);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(true);
        setBackground(Color.BLACK);

        MyLabel lblNum = new MyLabel(300,40);
        lblNum.setText("Input the num of players:");

        MyTextField txtNum = new MyTextField(200,64);
        txtNum.setTexture("src/resources/img/components/txtFieldV6.png");

        MyLabel lblPoints = new MyLabel(300,40);
        lblPoints.setText("Input the limit of points:");

        MyTextField txtPoints = new MyTextField(200,64);
        txtPoints.setTexture("src/resources/img/components/txtFieldV6.png");

        MyLabel lblGameName = new MyLabel(400,40);
        lblGameName.setText("Input the name of the game-match:");

        MyTextField txtGameName = new MyTextField(200,64);
        txtGameName.setTexture("src/resources/img/components/txtFieldV6.png");


        MyButton btnOK = new MyButton(200,60, TextureFactory.getInstance());
        btnOK.setText("Ok");
        btnOK.setTexture("src/resources/img/components/botonV2.png");

        btnOK.addActionListener(e -> {
            int numOfPlayers = 0;
            int limitPoints = 0;
            try {
                numOfPlayers = Integer.parseInt(txtNum.getText().trim());
                limitPoints = Integer.parseInt(txtPoints.getText().trim());
            } catch (NumberFormatException ex) {
                context.displayMessage("Only use number for \"number of player\" and \"limit points\".");
            } catch (NullPointerException en){
                context.displayMessage("Please complete the form.");
            }
            String gameName = txtGameName.getText().trim();
            if (gameName != null && numOfPlayers > 0 && limitPoints > 0){
                this.setVisible(false);
                context.getController().initGame(limitPoints,numOfPlayers, gameName, context.getUsername());
                context.getController().connectPlayer(context.getUsername());
            }
        });

        add(Box.createVerticalStrut(25));
        add(lblNum);
        add(Box.createVerticalStrut(5));
        add(txtNum);
        add(Box.createVerticalStrut(30));
        add(lblPoints);
        add(Box.createVerticalStrut(5));
        add(txtPoints);
        add(Box.createVerticalStrut(30));
        add(lblGameName);
        add(Box.createVerticalStrut(5));
        add(txtGameName);
        add(Box.createVerticalGlue());
        add(btnOK);
        add(Box.createVerticalGlue());
    }

}
