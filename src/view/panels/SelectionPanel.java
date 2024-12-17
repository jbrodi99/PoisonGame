package view.panels;


import view.components.MyButton;
import view.components.MyTextArea;
import view.main.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectionPanel extends JPanel {
    private JPanel mainPanel;
    private CardLayout panels;
    private MainView context;

    public SelectionPanel(JPanel parent, CardLayout panels, MainView context){
        super();
        this.mainPanel = parent;
        this.panels = panels;
        this.context = context;
        initComponents();
    }

    private void initComponents() {
        setVisible(false);
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(true);
        setBackground(Color.BLACK);

        MyTextArea txtArea = new MyTextArea("Select the view mode",500,130);
        MyButton btnConsole = new MyButton("CONSOLE",200,60);
        MyButton btnGraphics = new MyButton("GRAPHICS",200,60);

        btnConsole.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnConsole.setTexture("src/resources/img/botonHV1.png");
//                setTexture("src/resources/img/menuGameV2.png");
            }
        });

        btnConsole.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnConsole.setTexture("src/resources/img/botonV2.png");
//                setTexture("src/resources/img/menuGameFondoV1.png");
            }
        });

        btnConsole.addActionListener(e -> {
            this.setVisible(false);
            context.setViewSelected("console");
            context.setView();
            panels.show(mainPanel,"joc");
        });

        btnGraphics.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnGraphics.setTexture("src/resources/img/botonHV1.png");
//                setTexture("src/resources/img/menuGameBV2.png");
            }
        });

        btnGraphics.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnGraphics.setTexture("src/resources/img/botonV2.png");
                //setTexture("src/resources/img/menuGameFondoV1.png");
            }
        });

        btnGraphics.addActionListener(e -> {
            this.setVisible(false);
            context.setViewSelected("graphics");
            context.setView();
            panels.show(mainPanel,"joc");
        });

        this.add(Box.createVerticalGlue());
        this.add(txtArea);
        this.add(Box.createVerticalGlue());
        this.add(btnConsole);
        this.add(Box.createVerticalGlue());
        this.add(btnGraphics);
        this.add(Box.createVerticalGlue());
    }
}
