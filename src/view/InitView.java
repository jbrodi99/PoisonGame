package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

public class InitView implements IView{
    private final GameController controller;
    private final JFrame frame;
    private JPanel mainPanel; // Agregamos este atributo para referenciar al panel principal.

    public InitView(GameController controller) {
        this.frame = new JFrame("VENENO");
        this.controller = controller;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 640);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        initComponents();
    }

    public InitView(GameController controller, JFrame frame) {
        this.frame = frame;
        this.controller = controller;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 640);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        // Instanciamos el panel principal y lo asignamos al atributo.
        mainPanel = new ImagePanel("src/resources/img/veneno.jpg");

        JLabel txt = new JLabel("Bienvenidos al juego del Veneno!.");
        txt.setForeground(Color.white);
        txt.setFont(new Font("Arial", Font.BOLD, 24));
        txt.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton playBtn = new JButton("Play");
        setBtn(playBtn, Color.white, Color.black);
        JButton rankingBtn = new JButton("Ranking");
        setBtn(rankingBtn, Color.white, Color.black);
        JButton howPlayBtn = new JButton("¿How Play?");
        setBtn(howPlayBtn, Color.white, Color.black);

        // Configuración de los listeners de los botones
        playBtn.addActionListener(e -> menu());
        rankingBtn.addActionListener(e -> displayRanking());
        howPlayBtn.addActionListener(e -> showInstructions());

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(false);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 100)));
        mainPanel.add(txt);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(playBtn);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(rankingBtn);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(howPlayBtn);

        frame.getContentPane().add(mainPanel);
    }

    public JPanel getPanel() {
        // Devuelve el panel principal ya configurado
        return mainPanel;
    }

    private void menu() {
        frame.getContentPane().removeAll(); // Limpia el contenido actual del frame.
        JPanel menuPanel = new MenuView(controller, frame).getPanel(); // Obtén el nuevo panel.
        frame.getContentPane().add(menuPanel); // Agrega el panel del menú.
        frame.revalidate(); // Refresca el frame.
        frame.repaint();
    }

    private void displayRanking() {
        // Implementación para mostrar el ranking.
    }

    private void showInstructions() {
        // Implementación para mostrar las instrucciones.
    }

    private void setBtn(JButton btn, Color bgBtn, Color fgBtn) {
        btn.setBackground(bgBtn);
        btn.setForeground(fgBtn);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    // Clase personalizada para el fondo del panel
    private static class ImagePanel extends JPanel {
        private final Image backgroundImage;

        public ImagePanel(String imagePath) {
            this.backgroundImage = new ImageIcon(imagePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
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
}
