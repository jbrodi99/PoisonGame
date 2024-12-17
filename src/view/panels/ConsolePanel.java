package view.panels;

import model.enums.TYPECARD;
import model.interfaces.ICard;
import model.interfaces.ICenterStack;
import model.interfaces.IPlayer;
import model.interfaces.IPlayerPublic;
import view.components.Message;
import view.interfaces.IGameView;
import view.main.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsolePanel extends JPanel implements IGameView {
    private JPanel mainPanel;
    private CardLayout panels;
    private MainView context;
    private JTextArea txtOutput;
    private JTextField txtInput;
    private JButton btnEnter;
    private LobbyConsolePanel lobby;
    private static final Map<String, Integer> SUIT_MAP = new HashMap<>();

    static {
        SUIT_MAP.put("sword", 1);
        SUIT_MAP.put("goblet", 2);
        SUIT_MAP.put("coin", 3);
    }

    public ConsolePanel(JPanel parent, CardLayout panels, MainView context, LobbyConsolePanel lobby){
        super();
        this.mainPanel = parent;
        this.panels = panels;
        this.context = context;
        this.lobby = lobby;
        initComponents();
    }

    private void initComponents() {
        mainPanel.add(lobby,"lobbyC");
        setLayout(new BorderLayout());

        // Configuración del área de salida.
        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        txtOutput.setFont(new Font("Monospaced", Font.PLAIN, 16));

        // Configuración del campo de entrada y botón.
        txtInput = new JTextField();
        btnEnter = new JButton("Enter");

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(txtInput, BorderLayout.CENTER);
        inputPanel.add(btnEnter, BorderLayout.EAST);

        // Agregar componentes al panel principal.
        add(txtOutput, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        // Configuración de eventos.
        btnEnter.addActionListener(e -> processCommand());
        txtInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    processCommand();
                }
            }
        });

        // Si necesitas manejar el cierre de la ventana.
        context.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                context.getController().disconnectPlayer();
                System.exit(0);
            }
        });
    }

    private void processCommand() {
        String command = txtInput.getText();
        if (!command.isEmpty()) {
            commandHandler(command);
            txtInput.setText("");
        }
    }

    private void commandHandler(String command){
        command = command.toLowerCase();
        if (command.startsWith("card:")){
            playCard(command);
        } else if (command.startsWith("mode:")) {   //TODO: cambiar comando
            chmod();
        } else if (command.startsWith("bg:")) {
            //cambiar fondo de color
        } else {
            displayMessage("Comando invalido.");
        }
    }

    private int convertSuit(String token) {
        return SUIT_MAP.getOrDefault(token, 0);
    }


    private void playCard(String command){
//        String[] tokens = command.split("\\s+"); // express.regular para separar por caracteres en blanco.
//        if (tokens.length == 3){
//            try{
//                int indexCard = Integer.parseInt(tokens[1]);
//                int indexCenter = convertSuit(tokens[2]);
//                try{
//                    context.getController().playTurn(indexCard - 1, indexCenter - 1);
//                } catch (IndexOutOfBoundsException e) {
//                    displayMessage("The center valid is \"sword\", \"goblet\", \"coin\".");
//                }
//            }catch (NumberFormatException ex){
//                println("The index of card is only number.");
//            }
//        } else {
//            println("The command \"card:\" is of de form card: \"index\" \"suit\".");
//        }
        // Expresión regular para "card: <número> <palo>"
        Pattern pattern = Pattern.compile("^card:\\s+(\\d+)\\s+(sword|goblet|coin)$");
        Matcher matcher = pattern.matcher(command);

        if (matcher.matches()) {
            try {
                int indexCard = Integer.parseInt(matcher.group(1));
                int indexCenter = convertSuit(matcher.group(2));
                context.getController().playTurn(indexCard - 1, indexCenter - 1);
            } catch (IndexOutOfBoundsException e) {
                displayMessage("The center valid is \"sword\", \"goblet\", \"coin\".");
            }
        } else {
            println("The command \"card:\" is of the form \"card: <index> <suit>\".");
        }
    }

    private void print(String string) {
        txtOutput.append(string);
    }

    private void println(String string) {
        print(string + "\n");
    }

    private void displaySeparator() {
        // Obtener el ancho de la ventana
        FontMetrics metrics = txtOutput.getFontMetrics(txtOutput.getFont());
        int charWidth = metrics.charWidth('-'); // Ancho promedio de un carácter "-"
        int textAreaWidth = txtOutput.getWidth(); // Ancho total del JTextArea

        // Calcular cuántos caracteres "-" caben en el JTextArea
        int numDashes = textAreaWidth / charWidth;

        // Generar la cadena de separación con la cantidad calculada
        String separator = "-".repeat(numDashes);

        // Agregar la separación al JTextArea
        txtOutput.append(separator + "\n");
    }

    private String centerText(String text, int width) {
        int padding = (width - text.length()) / 2; // Espacios a la izquierda
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(Math.max(0, padding))); // Espacios iniciales
        sb.append(text);
        sb.append(" ".repeat(Math.max(0, width - padding - text.length()))); // Espacios finales
        return sb.toString();
    }

    @Override
    public void displayActions() {
        btnEnter.setEnabled(true);
        txtInput.setEditable(true);
    }

    @Override
    public void hiddenActions() {
        btnEnter.setEnabled(false);
        txtInput.setEditable(false);
        txtInput.setText("");
    }

    @Override
    public void displayHand(List<ICard> cards) {

        println("Hand.");

        // Preparar las filas del formato
        StringBuilder topRow = new StringBuilder();
        StringBuilder middleRow1 = new StringBuilder();
        StringBuilder middle = new StringBuilder();
        StringBuilder middleRow2 = new StringBuilder();
        StringBuilder bottomRow = new StringBuilder();
        StringBuilder indexCard = new StringBuilder();
        int currentIndex = 1;

        // Construir cada carta
        for (ICard card : cards) {
            String number = card.getValue().getName();
            String suit = card.getTypeCard().getName();

            topRow.append("+----------------+ ");
            middleRow1.append("| " + centerText(number + " " + suit, 14) + " | ");
            middle.append("|                | ");
            middleRow2.append("| " + centerText(suit + " " + number, 14) + " | ");
            bottomRow.append("+----------------+ ");
            if(card.getTypeCard() == TYPECARD.CUP){
                indexCard.append(centerText("" + currentIndex++ + " any " ,18));

            } else {
                indexCard.append(centerText("" + currentIndex++  + " " + card.getTypeCard().getName() + " " ,18));
            }

        }

        // Agregar las filas al área de texto
        StringBuilder hand = new StringBuilder();
        hand.append(topRow).append("\n");
        hand.append(middleRow1).append("\n");
        hand.append(middle).append("\n");
        hand.append(middleRow2).append("\n");
        hand.append(bottomRow).append("\n");
        hand.append(indexCard).append("\n");

        // Mostramos la mano en el área de texto
        println(hand.toString());
    }

    @Override
    public void displayGraveyard(List<ICard> cards) {
        println("Graveyard: " + cards.size() + " cards");
    }

    @Override
    public void displayBoard(List<ICenterStack> centers, List<IPlayerPublic> players) {
        println("Board\n");

        for(IPlayerPublic player : players){
            print(player.getUserName() + " " + player.getHealth());
            if(player.isYourTurn()){
                println(" (playing)");
            } else {
                println(" (waiting)");
            }
        }

        displaySeparator();

        // Preparar las filas del formato
        StringBuilder typeCenter = new StringBuilder();
        StringBuilder topRow = new StringBuilder();
        StringBuilder middleRow1 = new StringBuilder();
        StringBuilder middle = new StringBuilder();
        StringBuilder middleRow2 = new StringBuilder();
        StringBuilder bottomRow = new StringBuilder();
        StringBuilder totalPoints = new StringBuilder();

        // Construir cada centro
        for (ICenterStack center : centers) {
            if (!center.isEmpty()) {
                ICard topCard = center.getTopCard();
                //double points = center.countPoints();

                // Construir la representación de una carta
                typeCenter.append(centerText(center.getTypecard().getName() + " ",18));
                topRow.append("+----------------+ ");
                middleRow1.append("| " + centerText(topCard.getValue().getName() + " " + topCard.getTypeCard().getName(), 14) + " | ");
                middle.append("|                | ");
                middleRow2.append("| " + centerText(topCard.getTypeCard().getName() + " " + topCard.getValue().getName(), 14) + " | ");
                bottomRow.append("+----------------+ ");
                totalPoints.append(centerText("" + center.countPoints() ,18));
            } else {
                // Recuadro vacío
                typeCenter.append(centerText(center.getTypecard().getName() + " ",18));
                topRow.append("+----------------+ ");
                middleRow1.append("|                | ");
                middle.append("|                | ");
                middleRow2.append("|                | ");
                bottomRow.append("+----------------+ ");
                totalPoints.append(centerText("0", 18)); // Sin puntos si está vacío
            }
        }

        // Agregar las filas al área de texto
        txtOutput.append(typeCenter.toString() + "\n");
        txtOutput.append(topRow.toString() + "\n");
        txtOutput.append(middleRow1.toString() + "\n");
        txtOutput.append(middle.toString() + "\n");
        txtOutput.append(middleRow2.toString() + "\n");
        txtOutput.append(bottomRow.toString() + "\n");
        txtOutput.append(totalPoints.toString() + "\n");

        displaySeparator();
    }

    @Override
    public void displayTablePoints(Object o, int points) {

    }

    @Override
    public void cleanBoard() {
        txtOutput.setText("");
    }

    @Override
    public void finishGame(String message) {
        displayMessage(message);
        panels.show(context,"menu");
    }

    @Override
    public void disconnect() {

    }

    @Override
    public void waitPlayer(List<IPlayerPublic> players) {
//        if (players <= 0) {
//            println("No hay jugadores esperando.");
//            return;
//        }
//        StringBuilder stickmen = new StringBuilder();
//
//        // Dibujar la cantidad de stickmans solicitados
//        for (int i = 0; i < players; i++) {
//            stickmen.append("      O      ");  // Cabeza
//        }
//        stickmen.append("\n");
//
//        for (int i = 0; i < players; i++) {
//            stickmen.append("     /|\\     "); // Brazos y torso
//        }
//        stickmen.append("\n");
//
//        for (int i = 0; i < players; i++) {
//            stickmen.append("     / \\     "); // Piernas
//        }
//        stickmen.append("\n");
//
//        for (int i = 0; i < players; i++) {
//            stickmen.append("Jugador ").append(i + 1).append("  "); // Etiquetas de jugadores
//        }
//        stickmen.append("\n");
//
//        stickmen.append("\nEsperando a que el(los) ").append("jugador(es) estén listos...\n");
//
//        // Mostrar los stickmans en el área de texto
//        println(stickmen.toString());
        this.setVisible(false);
        panels.show(mainPanel, "lobbyC");
    }

    public void play() {
        lobby.setVisible(false);
        panels.show(mainPanel, context.getViewSelected());
    }

    @Override
    public void chmod() {
        context.setViewSelected("graphics");
        context.setView();
        panels.show(mainPanel, context.getViewSelected());
    }

    @Override
    public void backToMenu() {
        this.setVisible(false);
        panels.show(mainPanel,"menu");
    }

    @Override
    public void displayMessage(String message) {
        var msg = new Message(message);
        msg.setVisible(true);
    }

    @Override
    public void init() {
        //chequear si se quita
    }
}
