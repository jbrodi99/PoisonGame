package view.panels;

import view.components.MyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JoinOrCreatePanel extends JPanel {
    private JPanel mainPanel;
    private CardLayout panels;

    public JoinOrCreatePanel(JPanel parent, CardLayout panels){
        super();
        this.mainPanel = parent;
        this.panels = panels;

        initComponents();
    }

    private void initComponents() {
        setVisible(false);
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(true);
        setBackground(Color.BLACK);

        MyButton btnCreate = new MyButton("CREATE GAME",200,60);
        MyButton btnSearch = new MyButton("SEARCH GAME",200,60);

        btnCreate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnCreate.setTexture("src/resources/img/botonHV1.png");
//                setTexture("src/resources/img/menuGameV2.png");
            }
        });

        btnCreate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnCreate.setTexture("src/resources/img/botonV2.png");
//                setTexture("src/resources/img/menuGameFondoV1.png");
            }
        });

        btnCreate.addActionListener(e -> {
            this.setVisible(false);
            panels.show(mainPanel,"create");
        });

        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnSearch.setTexture("src/resources/img/botonHV1.png");
//                setTexture("src/resources/img/menuGameBV2.png");
            }
        });

        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnSearch.setTexture("src/resources/img/botonV2.png");
                //setTexture("src/resources/img/menuGameFondoV1.png");
            }
        });

        btnSearch.addActionListener(e -> {
            this.setVisible(false);
            panels.show(mainPanel,"join");
        });


        this.add(Box.createVerticalGlue());
        this.add(btnCreate);
        this.add(Box.createVerticalGlue());
        this.add(btnSearch);
        this.add(Box.createVerticalGlue());
    }
}
