package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

public class ConfigView implements IView{
    private final GameController controller;
    private final JFrame frame;
    private JPanel configPanel;

    public ConfigView(GameController controller, JFrame frame) {
        this.controller = controller;
        this.frame = frame;
        this.configPanel = new JPanel();
        initConfig();
    }

    private void initConfig() {
        configPanel.setLayout(new BoxLayout(configPanel, BoxLayout.Y_AXIS));

        // Título
        JLabel titleLabel = new JLabel("Configuración de la Partida");
        titleLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Preguntar al controlador si la partida existe
        boolean gameExists = controller.gameExists();

        if (!gameExists) {
            // Configuración de nueva partida
            JLabel numPlayersLbl = new JLabel("Num of players:");
            numPlayersLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

            JTextField numPlayersField = new JTextField(20);
            numPlayersField.setMaximumSize(new Dimension(200, 30));
            numPlayersField.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel limitPointsLbl = new JLabel("Limit points:");
            limitPointsLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

            JTextField limitePuntosField = new JTextField(20);
            limitePuntosField.setMaximumSize(new Dimension(200, 30));
            limitePuntosField.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton newGameBtn = new JButton("New Game");
            newGameBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Listener para crear partida
            newGameBtn.addActionListener(e -> {
                try {
                    int numPlayers = Integer.parseInt(numPlayersField.getText().trim());
                    int limitPoints = Integer.parseInt(limitePuntosField.getText().trim());
                    controller.initGame(limitPoints, numPlayers);
                    displayMessage("Game successfully created.");
                    openSelectionPanel();
                } catch (NumberFormatException ex) {
                    displayMessage("Please input valid values.");
                }
            });

            // Agregar los componentes al panel
            configPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            configPanel.add(titleLabel);
            configPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            configPanel.add(numPlayersLbl);
            configPanel.add(numPlayersField);
            configPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            configPanel.add(limitPointsLbl);
            configPanel.add(limitePuntosField);
            configPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            configPanel.add(newGameBtn);

        } else {
            openSelectionPanel();
        }

        // Botón "Volver" para regresar a la vista anterior
        JButton volverBtn = new JButton("Go Back");
        volverBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        volverBtn.addActionListener(e -> returnToMenu());

        configPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        configPanel.add(volverBtn);
    }

    private void openSelectionPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new SelectionView(controller, frame).getPanel());
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
        return configPanel;
    }
}
