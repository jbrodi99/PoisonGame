package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

public class MenuView {
    private final GameController controller;
    private final JFrame frame;
    private final JPanel menuPanel;

    public MenuView(GameController controller, JFrame frame) {
        this.controller = controller;
        this.frame = frame;
        this.menuPanel = new JPanel();
        initMenu();
    }

    private void initMenu() {
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Configuración de la Partida");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton newGameBtn = new JButton("New Game");
        newGameBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGameBtn.addActionListener(e -> openConfigPanel());

        JButton backBtn = new JButton("Back To Main Menu");
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        backBtn.addActionListener(e -> returnToInitView());

        // Agregar los botones al panel.
        menuPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        menuPanel.add(titleLabel);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        menuPanel.add(newGameBtn);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        menuPanel.add(backBtn);
    }

    private void openConfigPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new ConfigView(controller, frame).getPanel());
        frame.revalidate();
        frame.repaint();
    }

    private void returnToInitView() {
        frame.getContentPane().removeAll();
        InitView newInitView = new InitView(controller, frame);
        frame.getContentPane().add(newInitView.getPanel());
        frame.revalidate();
        frame.repaint();
    }

    public JPanel getPanel() {
        return menuPanel;
    }
}
