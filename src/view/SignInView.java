package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

public class SignInView implements IView{
    private final GameController controller;
    private final JFrame frame;
    private JPanel signInPanel;

    public SignInView(GameController controller, JFrame frame) {
        this.controller = controller;
        this.frame = frame;
        this.signInPanel = new JPanel();
        initSignIn();
    }

    private void initSignIn(){
        signInPanel.setLayout(new BoxLayout(signInPanel,BoxLayout.Y_AXIS));

        JLabel signInLabel = new JLabel("Sign In:");
        signInLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
        signInLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField nameField = new JTextField(20);
        nameField.setMaximumSize(new Dimension(200, 30));
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton submitBtn = new JButton("Sign in");
        submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        submitBtn.addActionListener(e -> {
            String playerName = nameField.getText().trim();
            if (!playerName.isEmpty()) {
                controller.signIn(playerName);
                //openLoginPanel();
            } else {
                displayMessage("Please, input a valid name.");
            }
        });

        signInPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        signInPanel.add(signInLabel);
        signInPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        signInPanel.add(nameField);
        signInPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        signInPanel.add(submitBtn);
    }

    private void openLoginPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new LoginView(controller, frame).getPanel());
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
        //
    }

    @Override
    public JPanel getPanel() {
        return signInPanel;
    }
}
