package view;

import controller.GameController;
import model.ICard;
import model.IPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ConsoleView extends JFrame implements IGameView {

    private final GameController controller;
    private JTextArea txtOutput;
    private JFrame mainView;
    private JTextField txtInput;
    private JButton btnEnter;
    private ConsoleViewStatus statusConsole;
    private final String name;
    private static boolean playing = false;
//    private int points;
//    private int numOfPlayers;


    public ConsoleView(String name, GameController controller) {
        this.controller = controller;
        this.name = name;
        setTitle("Veneno Consola");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setContentPane(mainView);

        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        Font font = new Font("Arial",Font.PLAIN,16);
        txtOutput.setFont(font);

        txtInput = new JTextField();
        btnEnter = new JButton("Enter");

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(txtInput,BorderLayout.CENTER);
        inputPanel.add(btnEnter,BorderLayout.EAST);

        mainView.setLayout(new BorderLayout());
        mainView.add(new JScrollPane(txtOutput),BorderLayout.CENTER);
        mainView.add(txtInput,BorderLayout.SOUTH);

        btnEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandHandler(txtInput.getText());
                txtInput.setText("");
            }
        });

        mainView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.disconnectPlayer();
                System.exit(0);
            }
        });

    }

    private void print(String string) {
        txtOutput.append(string);
    }

    private void println(String string) {
        print(string + "\n");
    }

    private void commandHandler(String command){
        command = command.toLowerCase();
        //ejecutar comando correspondiente...
    }

    @Override
    public void displayMessage(String message) {
        println(message);
    }

    @Override
    public void init() {
        setVisible(true);
    }

    @Override
    public void displayButton() {

    }

    @Override
    public void hiddenButton() {

    }

    @Override
    public void displayCard(ICard card) {

    }

    @Override
    public void displayHand(IPlayer player) {

    }

    @Override
    public void displayTable(Object o) {

    }

    @Override
    public void displayTablePoints(Object o, int points) {

    }

    @Override
    public void cleanTable() {

    }

    @Override
    public void finishGame(String message) {

    }

    @Override
    public void disconnect() {

    }
}
