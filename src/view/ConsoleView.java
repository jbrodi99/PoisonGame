package view;

import controller.GameController;
import model.interfaces.ICard;
import model.interfaces.ICenterStack;
import model.interfaces.IPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.util.List;

public class ConsoleView extends JFrame implements IGameView {

    private final GameController controller;
    private JTextArea txtOutput;
    private JTextField txtInput;
    private JButton btnEnter;
    private ConsoleViewStatus statusConsole;
    private final String name;
    public static boolean playing = false;
//    private int points;
//    private int numOfPlayers;

    public ConsoleView(String name, GameController controller) {
        this.controller = controller;
        this.name = name;
        setTitle("Veneno Consola");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
    }

    private void initComponents() {

        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        Font font = new Font("Monospaced",Font.PLAIN,16);
        txtOutput.setFont(font);

        txtInput = new JTextField();
        btnEnter = new JButton("Enter");

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(txtInput,BorderLayout.CENTER);
        inputPanel.add(btnEnter,BorderLayout.EAST);

        setLayout(new BorderLayout());
        add(new JScrollPane(txtOutput),BorderLayout.CENTER);
        add(inputPanel,BorderLayout.SOUTH);

        btnEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandHandler(txtInput.getText());
                txtInput.setText("");
            }
        });

        btnEnter.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    commandHandler(txtInput.getText());
                    txtInput.setText("");
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.disconnectPlayer();
                System.exit(0);
            }
        });
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

    private void print(String string) {
        txtOutput.append(string);
    }

    private void println(String string) {
        print(string + "\n");
    }

    private void commandHandler(String command){
        command = command.toLowerCase();
        //ejecutar comando correspondiente...
        if (command.equals("jugar")){
            play(5,2);
        } else if (command.startsWith("carta:")) {
            playCard(command);
        }   else if (command.startsWith("jugador:")) {
            setPlayer(command);
        } else if (command.equals("listo")) {
            controller.startGame();
        } else {
            displayMessage("Comando invalido.");
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

//    @Override
//    public void displayCard(int number, String type) {
//        // Ancho fijo de la carta (sin contar los bordes)
//        int cardWidth = 10;
//
//        // Construir la carta
//        StringBuilder card = new StringBuilder();
//        card.append("+------------+\n");
//        card.append("| " + centerText(number + " " + type, cardWidth) + " |\n");
//        card.append("| " + " ".repeat(cardWidth) + " |\n"); // Espacio vacío
//        card.append("| " + centerText(type + " " + number, cardWidth) + " |\n");
//        card.append("+------------+\n");
//
//        // Mostrar la carta en el área de texto
//        println(card.toString());
//    }

    /**
     * Centra un texto dentro de un espacio con ancho fijo.
     *
     * @param text El texto a centrar.
     * @param width El ancho total disponible para el texto.
     * @return Una cadena con el texto centrado.
     */
    private String centerText(String text, int width) {
        int padding = (width - text.length()) / 2; // Espacios a la izquierda
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(Math.max(0, padding))); // Espacios iniciales
        sb.append(text);
        sb.append(" ".repeat(Math.max(0, width - padding - text.length()))); // Espacios finales
        return sb.toString();
    }

    @Override
    public void displayHand(List<ICard> cards) {

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
            indexCard.append(centerText("" + currentIndex++,18));
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
    public void displayBoard(List<ICenterStack> centers, List<IPlayer> players) {
        println("Board\n");

        for(IPlayer player : players){
            println(player.getUserName() + player.getHealth());
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
                double points = center.countPoints();

                // Construir la representación de una carta
                typeCenter.append(centerText(center.getTypecard().getName(),18));
                topRow.append("+----------------+ ");
                middleRow1.append("| " + centerText(topCard.getValue().getName() + " " + topCard.getTypeCard().getName(), 14) + " | ");
                middle.append("|                | ");
                middleRow2.append("| " + centerText(topCard.getTypeCard().getName() + " " + topCard.getValue().getName(), 14) + " | ");
                bottomRow.append("+----------------+ ");
                totalPoints.append(centerText("" + center.countPoints() ,17));
            } else {
                // Recuadro vacío
                typeCenter.append(centerText(center.getTypecard().getName(),18));
                topRow.append("+----------------+ ");
                middleRow1.append("|                | ");
                middle.append("|                | ");
                middleRow2.append("|                | ");
                bottomRow.append("+----------------+ ");
                totalPoints.append(centerText("0", 17)); // Sin puntos si está vacío
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
        //mostrar table de puntaje de jugadores
    }

    @Override
    public void cleanBoard() {
        txtOutput.setText("");
    }

    @Override
    public void finishGame(String message) {
        println(message);
    }

    @Override
    public void disconnect() {
        println("Un jugador se ah desconectado, se guardara la partida para jugar luego.");
        SwingUtilities.invokeLater(this::dispose);
        SwingUtilities.invokeLater(() -> System.exit(0));
    }

    private void play(int limitPoints, int numOfPlayers){
        controller.initGame(limitPoints,numOfPlayers);
    }

    private void play(String command){
        if(!ConsoleView.playing){
            ConsoleView.playing = true;
            controller.startGame();
        }
    }

    private void playCard(String command){
        String[] partes = command.split("\\s+"); // express.regular para separar por caracteres en blanco.
        if (partes.length == 3){
           try{
               int indexCard = Integer.parseInt(partes[1]);
               int indexCenter = Integer.parseInt(partes[2]);
               try{
                   controller.playTurn(indexCard - 1, indexCenter - 1);
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }catch (NumberFormatException ex){
               println("algo raro paso...");
           }
        } else {
            println("Solo debe ingresar el numero de carta a jugar...");
        }
    }

    private void setPlayer(String command){
        String[] partes = command.split("\\s+"); // express.regular para separar por caracteres en blanco.
        if(partes.length == 3){
            try{
                int id = Integer.parseInt(partes[1]);
                controller.setPlayerID(id);
                controller.connectPlayer(partes[2],id);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
