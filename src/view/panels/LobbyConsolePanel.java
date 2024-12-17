package view.panels;

import view.components.MyTextArea;
import view.main.MainView;

import javax.swing.*;
import java.awt.*;

public class LobbyConsolePanel extends JPanel {
    private JPanel mainPanel;
    private CardLayout panels;
    private MainView context;
    private JTextArea txtOutput;

    public LobbyConsolePanel(JPanel parent, CardLayout panels, MainView context){
        super();
        this.mainPanel = parent;
        this.panels = panels;
        this.context = context;
        initComponents();
    }

    private void initComponents() {
        setVisible(false);
        setOpaque(false);
        setLayout(new BorderLayout());

        MyTextArea logo = new MyTextArea("LOBBY", 500,130);

        JPanel center = new JPanel();
        center.setLayout(new GridLayout());
        center.add(new JLabel("jugador 1"));    //TODO: Cambiar por laberls con imagenes que representen jugadores
        center.add(new JLabel("jugador 2"));
        center.add(new JLabel("jugador 3"));
        center.add(new JLabel("jugador 4"));

        JLabel progressBar = new JLabel("barra de progreso...");

        add(logo,BorderLayout.NORTH);
        add(center,BorderLayout.CENTER);
        add(progressBar, BorderLayout.SOUTH);
    }

}
