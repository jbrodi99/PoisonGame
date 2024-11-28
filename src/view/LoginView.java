package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

public class LoginView implements IView {
    private final GameController controller;
    private final JFrame frame;
    private JPanel loginPanel;

    public LoginView(GameController controller, JFrame frame) {
        this.controller = controller;
        this.frame = frame;
        this.loginPanel = new JPanel();
        initLogin();
    }

    private void initLogin() {
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

        JButton signInBtn = new JButton("Sign In");
        signInBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton signUpBtn = new JButton("Sign Up");
        signUpBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton lobbyBtn = new JButton("Play");
        lobbyBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
//        if(!controller.isPlayerConnect()){
//            lobbyBtn.setEnabled(false);
//            lobbyBtn.setVisible(false);
//        } else {
//            lobbyBtn.setEnabled(true);
//            lobbyBtn.setVisible(true);
//        }

        signInBtn.addActionListener(e -> {
            openSignInPanel();
        });

        signUpBtn.addActionListener(e -> {
            openSignUpPanel();
        });

        lobbyBtn.addActionListener(e -> {
            play();
        });

        loginPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        loginPanel.add(signInBtn);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        loginPanel.add(signUpBtn);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        loginPanel.add(lobbyBtn);
    }

    private void openSignInPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new SignInView(controller, frame).getPanel());
        frame.revalidate();
        frame.repaint();
    }

    private void openSignUpPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new SignUpView(controller, frame).getPanel());
        frame.revalidate();
        frame.repaint();
    }

    private void play() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(controller.getView().getPanel());
        frame.revalidate();
        frame.repaint();
    }

    private void returnToMenu() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new MenuView(controller, frame).getPanel());
        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void displayMessage(String message) {
        var msg = new Message(message);
        msg.setVisible(true);
    }

    @Override
    public void init() {
        frame.setVisible(true);
    }

    public JPanel getPanel() {
        return loginPanel;
    }
}
