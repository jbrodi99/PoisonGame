package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Message extends JDialog {
    private final JPanel contentPane;
    private final JButton buttonOK;
    private final JLabel lblMessage;

    public Message(String message) {
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
        setLocationRelativeTo(null);
        pack();

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

}
