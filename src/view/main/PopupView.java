package view.main;

import view.components.Message;
import view.interfaces.IView;

import javax.swing.*;

public class PopupView extends JFrame implements IView {

    public PopupView(){
        super("Message!!!");
        initComponents();
    }

    private void initComponents(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    @Override
    public void displayMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            var msg = new Message(message);
            msg.setVisible(true);
        });
    }

    @Override
    public void init() {
        setVisible(true);
    }
}
