package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

public class SelectionView implements IView{
    private final GameController controller;
    private final JFrame frame;
    private JPanel selectionPanel;

    public SelectionView(GameController controller, JFrame frame){
        this.controller = controller;
        this.frame = frame;
        this.selectionPanel = new JPanel();
        initConfig();
    }

    private void initConfig(){
        selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Selection of View");
        titleLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Selección de vista para unirse a partida existente
        JLabel selectionViewLbl = new JLabel("Select View:");
        selectionViewLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton graphicViewBtn = new JButton("Graphic View");
        graphicViewBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton ConsoleViewBtn = new JButton("Console View");
        ConsoleViewBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Listeners para las opciones de vista
        graphicViewBtn.addActionListener(e -> {
            //controller.setView(new GraphicView(controller));
            //displayMessage("Graphic View Selected.");
            displayMessage("Coming soon");
            //openLoginPanel();
            //returnToMenu(); // Regresar al menú
        });

        ConsoleViewBtn.addActionListener(e -> {
            controller.setView(new ConsoleView(controller, frame));
            displayMessage("Console View Selected.");
            openLoginPanel();
            //returnToMenu(); // Regresar al menú
        });

        // Agregar los componentes al panel
        selectionPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        selectionPanel.add(titleLabel);
        selectionPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        selectionPanel.add(selectionViewLbl);
        selectionPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        selectionPanel.add(graphicViewBtn);
        selectionPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        selectionPanel.add(ConsoleViewBtn);
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
        frame.setVisible(true);
    }

    @Override
    public JPanel getPanel() {
        return selectionPanel;
    }
}
