package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

public class InitView implements IView{
    private final GameController controller;
    private final JFrame frame;

    public InitView(GameController controller){
        this.frame = new JFrame("VENENO");
        this.controller = controller;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 640);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JPanel jPanel = new ImagePanel("src/resources/img/veneno.jpg");

        // seteo los atributos de los labels.
        JLabel txt = new JLabel("Bienvenidos al juego del Veneno!.");
        txt.setForeground(Color.WHITE);
        txt.setFont(new Font("Arial", Font.BOLD, 24));
        txt.setAlignmentX(Component.CENTER_ALIGNMENT); // alinear el texto al centro.

        // Atributos de los botones.
        JButton playBtn = new JButton("Play");
        setBtn(playBtn, Color.white, Color.black);
        JButton rankingBtn = new JButton("Ranking");
        setBtn(rankingBtn, Color.white, Color.black);
        JButton howPlayBtn = new JButton("¿How Play?");
        setBtn(howPlayBtn, Color.white, Color.black);
        // se agregan los componenetes al panel.
        setComponents(jPanel, txt, playBtn, rankingBtn, howPlayBtn);

        // agrego el panel al frame.
        frame.getContentPane().add(jPanel, BorderLayout.CENTER);

        // Funcionalidad del jugarBtn.
        playBtn.addActionListener(e -> menu());

        // funcionalidad del rankingBtn
        rankingBtn.addActionListener(e -> displayRanking());

        // funcionalidad del comoJugarBtn
        howPlayBtn.addActionListener(e -> showInstructions());
    }

    private void menu() {

    }

    private void displayRanking() {

    }

    private void showInstructions() {

    }

    private void setBtn(JButton btn, Color bgBtn, Color fgBtn) {
        btn.setBackground(bgBtn);
        btn.setForeground(fgBtn);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void setComponents(JPanel panel, JLabel txt, JButton playBtn, JButton rankingBtn, JButton howPlayBtn){
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        // agregos los componentes al panel.
        panel.add(Box.createRigidArea(new Dimension(0, 100)));
        panel.add(txt);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(playBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(rankingBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(howPlayBtn);
    }

    // Clase personalizada para dibujar una imagen de fondo en un panel
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

    }

    @Override
    public void init() {
        frame.setVisible(true);
    }
}
