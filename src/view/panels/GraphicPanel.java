package view.panels;

import model.enums.NUMBER;
import model.enums.TYPECARD;
import model.interfaces.ICard;
import model.interfaces.ICenterStack;
import model.interfaces.IPlayerPublic;
import model.logic.Card;
import model.logic.CenterStack;
import model.logic.Player;
import view.components.Message;
import view.components.MyLabel;
import view.interfaces.IGameView;
import view.main.MainView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class GraphicPanel extends JPanel implements IGameView {

    private BufferedImage texture;
    private JPanel mainPanel;
    private CardLayout panels;
    private MainView context;
    private LobbyGrapichsPanel lobby;
    private DraggableArea board;
    private boolean action = false;

    public GraphicPanel(JPanel parent, CardLayout panels, MainView context, LobbyGrapichsPanel lobby){
        super();
        this.mainPanel = parent;
        this.panels = panels;
        this.context = context;
        this.lobby = lobby;
        mainPanel.add(lobby, "lobby");
        try {
            texture = ImageIO.read(new File("src/resources/img/backgrounds/boardV6.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        initComponents();
    }

    public GraphicPanel(){
        super();
        try {
            texture = ImageIO.read(new File("src/resources/img/backgrounds/boardV6.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setOpaque(false);
        setBackground(Color.BLACK);

        board = new DraggableArea(context);

        add(board, BorderLayout.CENTER);

    }

    @Override
    public void displayActions() {
        action = true;
    }

    @Override
    public void hiddenActions() {
        action = false;
    }

    @Override
    public void displayHand(List<ICard> cards) {
        board.updateHand(cards);
        board.drag(action);
    }

    @Override
    public void displayGraveyard(List<ICard> cards) {
        //TODO: analizar donde colocar cementerio, en status Player o creo un label con las cartas ?
    }

    @Override
    public void displayBoard(List<ICenterStack> centers, List<IPlayerPublic> players) {
//        statusPlayer.updateStatus(List.of(
//                "data player"
//                //TODO: enviar los datos a actualizar del jugador
//        ));
//        statusGame.updateStatus(List.of(
//                "data"
//                //TODO: enviar los datos a actualizar de la partida
//        ));
        board.updateCenter(centers); //TODO:agregar funcionalidad
    }

    @Override
    public void displayTablePoints(Object o, int points) {

    }

    @Override
    public void cleanBoard() {
        board.clear();
    }

    @Override
    public void finishGame(String message) {

    }

    @Override
    public void disconnect() {

    }

    @Override
    public void waitPlayer(List<IPlayerPublic> players) {
        lobby.updateLobby(players);
        this.setVisible(false);
        panels.show(mainPanel,"lobby");
    }

    @Override
    public void play() {
        lobby.setVisible(false);
        panels.show(mainPanel, context.getViewSelected());
    }

    @Override
    public void backToMenu() {
        this.setVisible(false);
        panels.show(mainPanel, "menu");
    }

    @Override
    public void chmod() {
        context.setViewSelected("console");
        context.setView();
        panels.show(mainPanel, context.getViewSelected());
    }

    @Override
    public void displayMessage(String message) {
        var msg = new Message(message);
        msg.setVisible(true);
    }

    @Override
    public void init() {
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

//        if (texture != null){
//            Image imgEsc = texture.getScaledInstance(context.getWidth(), context.getHeight(),Image.SCALE_SMOOTH);
//            g2d.drawImage(imgEsc,0,0,context.getWidth(),context.getHeight(),this);
//        }

        if (texture != null){
            Image imgEsc = texture.getScaledInstance(getParent().getWidth(), getParent().getHeight(),Image.SCALE_SMOOTH);
            g2d.drawImage(imgEsc,0,0,getParent().getWidth(),getParent().getHeight(),this);
        }

        super.paintComponent(g);
    }

}
