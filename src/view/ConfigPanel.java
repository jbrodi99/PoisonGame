package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;


public class ConfigPanel {
//    private final JFrame frame;
//    private final GameController controller;
//    private JComboBox<String> viewOptionComboBox;
//    private JTextField playerNameField;
//    private JTextField playerPointsField;
//    private JTextField numberOfPlayersField;
//    private JButton startGameButton;
//
//    public ConfigPanel(GameController controller) throws RemoteException {
//        this.frame = new JFrame("Configuration");
//        this.controller = controller;
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 300);
//        frame.setResizable(false);
//        frame.setLocationRelativeTo(null);
//
//        initComponents();
//    }
//
//    private void initComponents() {
//        // Panel principal
//        JPanel mainPanel = new JPanel(new BorderLayout());
//        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//        // Panel de configuración
//        JPanel configPanel = new JPanel(new GridLayout(4, 2, 10, 10));
//        configPanel.setBorder(BorderFactory.createTitledBorder("Game Settings"));
//
//        // Componentes de configuración
//        configPanel.add(new JLabel("Select View:"));
//        viewOptionComboBox = new JComboBox<>(new String[]{"Graphical Interface", "Console"});
//        configPanel.add(viewOptionComboBox);
//
//        configPanel.add(new JLabel("Player Name:"));
//        playerNameField = new JTextField(15);
//        configPanel.add(playerNameField);
//
//        configPanel.add(new JLabel("Initial Points:"));
//        playerPointsField = new JTextField(5);
//        configPanel.add(playerPointsField);
//
//        configPanel.add(new JLabel("Number of Players:"));
//        numberOfPlayersField = new JTextField(5);
//        configPanel.add(numberOfPlayersField);
//
//        mainPanel.add(configPanel, BorderLayout.CENTER);
//
//        // Panel de botón
//        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        startGameButton = new JButton("Start Game");
//        buttonPanel.add(startGameButton);
//        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
//
//        // Añadir funcionalidad al botón
//        startGameButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                handleStartGame();
//            }
//        });
//
//        frame.add(mainPanel);
//    }
//
//    private void handleStartGame() {
//        // Obtener datos ingresados
//        String selectedView = (String) viewOptionComboBox.getSelectedItem();
//        String playerName = playerNameField.getText().trim();
//        String initialPoints = playerPointsField.getText().trim();
//        String numOfPlayers = numberOfPlayersField.getText().trim();
//
//        // Validaciones básicas
//        if (playerName.isEmpty() || initialPoints.isEmpty() || numOfPlayers.isEmpty()) {
//            JOptionPane.showMessageDialog(frame, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        try {
//            int points = Integer.parseInt(initialPoints);
//            int players = Integer.parseInt(numOfPlayers);
//
//            // Notificar al controlador para configurar el juego
//            controller.configureGame(selectedView, playerName, points, players);
//
//            // Transición hacia la ejecución del juego
//            if (selectedView.equals("Graphical Interface")) {
//                controller.startGraphicalGame();
//            } else {
//                controller.startConsoleGame();
//            }
//
//            // Cerrar la configuración
//            frame.dispose();
//
//        } catch (NumberFormatException ex) {
//            JOptionPane.showMessageDialog(frame, "Points and number of players must be valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
//        } catch (RemoteException ex) {
//            JOptionPane.showMessageDialog(frame, "An error occurred while starting the game.", "Error", JOptionPane.ERROR_MESSAGE);
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void init(){
//        frame.setVisible(true);
//    }
}
