package view.panels;

import model.interfaces.IPlayerPublic;
import view.components.MyLabel;
import view.components.MyTextArea;
import view.main.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LobbyGrapichsPanel extends CustomPanel {

    private JPanel center;
    private final Map<Integer, JLabel> playersIcon = new HashMap<>();

    public LobbyGrapichsPanel(JPanel parent, CardLayout panels, MainView context){
        super(parent, panels, context);
        initComponents();
    }

    private void initComponents() {
        setOpaque(true);
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        MyTextArea logo = new MyTextArea("LOBBY", 500,130);
        logo.setTexture("src/resources/img/components/txtareaV1.png");

        center = new JPanel();
        center.setBackground(Color.BLACK);
        center.setOpaque(false);
        center.setBorder(BorderFactory.createEmptyBorder(60,0,0,0));

        add(logo,BorderLayout.NORTH);
        add(center,BorderLayout.CENTER);

    }

    public void updateLobby(List<IPlayerPublic> players){
        //TODO: traer lista de jugadores y actualizar labels
//        players = context.getController().getAllPlayers();
        players.stream()
                .filter(player -> !playersIcon.containsKey(player.getId()))
                .forEach(player -> {
                    MyLabel playerLabel = new MyLabel(200, 255, "src/resources/img/components/cartaLobby.png");
                    playersIcon.put(player.getId(), playerLabel);
                    center.add((playerLabel));
                });
        revalidate();
        repaint();
    }

}
