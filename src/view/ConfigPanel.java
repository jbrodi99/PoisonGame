package view;

import controller.GameController;

import javax.swing.*;


public class ConfigPanel extends JFrame implements IView {
    private final JFrame frame;
    private final GameController controller;
    private JTextArea txtOutput;

    public ConfigPanel(GameController controller) {
        this.controller = controller;
        frame = new JFrame("Veneno");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 768);
        frame.setResizable(false);
    }

    @Override
    public void displayMessage(String message) {
        var windowsMessage = new Message(message);
        windowsMessage.setVisible(true);
    }

    @Override
    public void init() {
        frame.setVisible(true);
    }
}
