package view.main;

import controller.GameController;
import utils.TextureFactory;
import view.components.Message;
import view.interfaces.IGameView;
import view.interfaces.IView;
import view.panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static view.panels.PANELS.LOGIN_AND_REGISTER;

public class MainView extends JFrame implements IView {
    private GameController controller;
    private String username;
    private final CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout); // Agregamos este atributo para referenciar al panel principal.


    public MainView(GameController controller) {
        super();
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 640);
        setResizable(false);
        setLocationRelativeTo(null);

        CustomPanelFactory.createCustomPaneltexture(mainPanel, cardLayout, this, TextureFactory.getInstance(), LOGIN_AND_REGISTER);
        add(mainPanel);


        // Si necesitas manejar el cierre de la ventana.
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.disconnectPlayer();
                System.exit(0);
            }
        });
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public GameController getController() {
        return controller;
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
