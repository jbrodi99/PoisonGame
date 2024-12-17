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

public class LobbyGrapichsPanel extends JPanel {
    private JPanel mainPanel;
    private CardLayout panels;
    private MainView context;
    private JPanel center;
//    MyLabel progressBar;
//    private List<String> resourceProgressBar = List.of(
//            "src/resources/img/components/progressBar25percentV2.png",
//            "src/resources/img/components/progressBar50percentV2.png",
//            "src/resources/img/components/progressBar75percentV2.png",
//            "src/resources/img/components/progressBar100percentV2.png"
//    );
    private final Map<Integer, JLabel> playersIcon = new HashMap<>();

    public LobbyGrapichsPanel(JPanel parent, CardLayout panels, MainView context){
        super();
        this.mainPanel = parent;
        this.panels = panels;
        this.context = context;
        initComponents();
    }

    private void initComponents() {
        setOpaque(true);
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        MyTextArea logo = new MyTextArea("LOBBY", 500,130);

        //TODO: instanciar o configurar cuando es visible y tengo los datos?
        center = new JPanel();
        center.setBackground(Color.BLACK);
        center.setOpaque(false);
//        center.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

//        JLabel progressBar = new MyLabel(100,20,"rutaDeImagen");
//        progressBar = new MyLabel(context.getWidth(),40,"src/resources/img/components/progressBar25percentV2.png");
//        progressBar.setForeground(Color.WHITE);

        add(logo,BorderLayout.NORTH);
        add(center,BorderLayout.CENTER);
//        add(progressBar, BorderLayout.SOUTH);

    }

    public void updateLobby(List<IPlayerPublic> players){
        //TODO: traer lista de jugadores y actualizar labels
//        players = context.getController().getAllPlayers();
        players.stream()
                .filter(player -> !playersIcon.containsKey(player.getId()))
                .forEach(player -> {
                    MyLabel playerLabel = new MyLabel(180, 300, "src/resources/img/cards/texture_cup_one.png");
                    playersIcon.put(player.getId(), playerLabel);
                    center.add((playerLabel));
//                    progressBar.setTexture(resourceProgressBar.get(players.size() - 1));
                });
        revalidate();
        repaint();
    }

}
