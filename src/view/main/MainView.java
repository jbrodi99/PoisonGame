package view.main;

import controller.GameController;
import view.components.Message;
import view.interfaces.IGameView;
import view.interfaces.IView;
import view.panels.*;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame implements IView {
    private GameController controller;
    private String username;
    private String viewSelected;
    private final CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout); // Agregamos este atributo para referenciar al panel principal.
    private JPanel consolePanel;
    private JPanel graphicPanel;

    public MainView(GameController controller) {
        super();
        this.controller = controller;
        initComponents();
    }

//    //contructor para testeo
//    public MainView() {
//        super();
//        initComponents();
//    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 640);
        setResizable(false);
        setLocationRelativeTo(null);

        mainPanel.add(new LoginAndRegisterPanel(mainPanel,cardLayout,"src/resources/img/fondoMainV2.png"), "lar");
        mainPanel.add(new LoginPanel(mainPanel,cardLayout,this), "login");
        mainPanel.add(new RegisterPanel(mainPanel,cardLayout,this),"reg");
        mainPanel.add(new MenuPanel(mainPanel,cardLayout,this,"src/resources/img/menuGameFondoV1.png"), "menu");
        mainPanel.add(new SelectionPanel(mainPanel,cardLayout, this), "selection");
        mainPanel.add(new JoinOrCreatePanel(mainPanel,cardLayout), "joc");
        mainPanel.add(new JoinPanel(mainPanel,cardLayout,this), "join");
        mainPanel.add(new CreateGamePanel(mainPanel,cardLayout,this), "create");
        this.consolePanel = new ConsolePanel(mainPanel,cardLayout,this, new LobbyConsolePanel(mainPanel, cardLayout,this));
        this.graphicPanel = new GraphicPanel(mainPanel,cardLayout,this, new LobbyGrapichsPanel(mainPanel, cardLayout,this));
        mainPanel.add(consolePanel, "console");
        mainPanel.add(graphicPanel, "graphics");

        add(mainPanel);
    }

    public void setViewSelected(String viewSelected){
        this.viewSelected = viewSelected;
    }

    public String getViewSelected(){
        return viewSelected;
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

    public void setView(){
        if (viewSelected.equals("console")){
            controller.setView((IGameView) consolePanel);
            System.out.println("vista consola set");
        } else if (viewSelected.equals("graphics")) {
            controller.setView((IGameView) graphicPanel);
            System.out.println("Vista Grafica set");
        }
    }

    @Override
    public void displayMessage(String message) {
        var msg = new Message(message);
        msg.setVisible(true);
    }

    @Override
    public void init() {
        setVisible(true);
    }

//    public static void main(String[] args) {
//
//        SwingUtilities.invokeLater(() -> {
//            MainView mainp = new MainView();
//            mainp.init();
//        });
//    }

    public String getVisiblePanelName() {
        for (Component comp : mainPanel.getComponents()) {
            if (comp.isVisible()) {
                return ((JPanel) comp).getName();
            }
        }
        return null;
    }
}
