package view.components;

import javax.swing.*;
import java.awt.*;

public class Message extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel lblMessage;

    public Message(String message) {
        initComponent(null,message);
    }

    public Message(JFrame parent,String message) {
        initComponent(parent,message);
    }

    private void initComponent(JFrame parent,String message){
        contentPane = new JPanel(new BorderLayout());
        lblMessage = new JLabel(message,SwingConstants.CENTER);
        buttonOK = new JButton("Ok");
        Font font = new Font(Font.MONOSPACED, Font.BOLD,20);
        lblMessage.setFont(font);

        contentPane.add(lblMessage,BorderLayout.CENTER);
        contentPane.add((buttonOK), BorderLayout.SOUTH);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        lblMessage.setText(message);
        setLocationRelativeTo(parent);
        pack();

        buttonOK.addActionListener(e -> onOK());
    }

    private void onOK() {
        dispose();
    }

}
