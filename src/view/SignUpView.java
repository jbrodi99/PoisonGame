package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

public class SignUpView implements IView{
    private final GameController controller;
    private final JFrame frame;
    private JPanel signUpPanel;

    public SignUpView(GameController controller, JFrame frame) {
        this.controller = controller;
        this.frame = frame;
        this.signUpPanel = new JPanel();
        initSignUp();
    }

    private void initSignUp(){
        signUpPanel.setLayout(new BoxLayout(signUpPanel,BoxLayout.Y_AXIS));

        JLabel signUpLabel = new JLabel("Sign Up:");
        signUpLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
        signUpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField nameField = new JTextField(20);
        nameField.setMaximumSize(new Dimension(200, 30));
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton submitBtn = new JButton("Sign Up");
        submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        submitBtn.addActionListener(e -> {
            String playerName = nameField.getText().trim();
            if (!playerName.isEmpty()) {
                controller.signUp(playerName);
                openLoginPanel();
            } else {
                displayMessage("Please, input a valid name.");
            }
        });

        signUpPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        signUpPanel.add(signUpLabel);
        signUpPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        signUpPanel.add(nameField);
        signUpPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        signUpPanel.add(submitBtn);
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

    }

    @Override
    public JPanel getPanel() {
        return signUpPanel;
    }
}
