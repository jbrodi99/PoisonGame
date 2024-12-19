package view.panels;


import model.interfaces.ICard;
import model.interfaces.ICenterStack;
import model.interfaces.IPlayerPublic;
import utils.ITextureFactory;
import utils.TextureFactory;
import view.components.Message;
import view.components.MyButton;
import view.interfaces.IGameView;
import view.main.MainView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GraphicPanel extends CustomPanelTexture implements IGameView {

    private final LobbyGrapichsPanel lobby;
    private DraggableArea board;
    private boolean action = false;

    public GraphicPanel(JPanel parent, CardLayout panels, MainView context, ITextureFactory textureFactory){
        super(parent, panels, context, textureFactory);
        lobby = (LobbyGrapichsPanel) CustomPanelFactory.createCustomPanel(parent,panels,context, PANELS.LOBBY_GRAPHICS);
        setTexture("src/resources/img/backgrounds/boardV6.png",context.getWidth(),context.getHeight());
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setOpaque(false);
        setBackground(Color.BLACK);

        board = new DraggableArea(context);

        MyButton mode = new MyButton(100,30, TextureFactory.getInstance());
        mode.setTexture("src/resources/img/components/botonHV1.png");
        mode.setText("MODE");
        mode.setFont(new Font(Font.SANS_SERIF,Font.BOLD,10));
        mode.setBounds(680,580,100,30);

        mode.addActionListener(e -> {
            chmod();
        });

        board.add(mode, JLayeredPane.DEFAULT_LAYER);

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
        board.updateGraveyard(cards);
    }

    @Override
    public void displayBoard(List<ICenterStack> centers, List<IPlayerPublic> players) {
        board.updateStatus(players);
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
        displayMessage(message);
        backToMenu();
    }

    @Override
    public void disconnect() {
    }

    @Override
    public void waitPlayer(List<IPlayerPublic> players) {
        lobby.updateLobby(players);
        nextPanel = PANELS.LOBBY_GRAPHICS;
        this.setVisible(false);
        nextPanel();
    }

    @Override
    public void play() {
        CustomPanelFactory.removePanel(PANELS.SELECTION);
        CustomPanelFactory.removePanel(PANELS.JOIN_OR_CREATE);
        CustomPanelFactory.removePanel(PANELS.CREATE);
        lobby.setVisible(false);
        nextPanel = PANELS.GRAPHICS;
        nextPanel();
    }

    @Override
    public void backToMenu() {
        nextPanel = PANELS.MENU;
        nextPanel();
    }

    @Override
    public void chmod() {
        context.getController().setView((IGameView) CustomPanelFactory.createCustomPanel(mainPanel,panels,context,PANELS.CONSOLE));
        nextPanel = PANELS.CONSOLE;
        nextPanel();
        context.getController().refresh();
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
        this.setVisible(true);
    }

}
