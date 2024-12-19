package view.panels;


import utils.TextureFactory;
import view.components.MyButton;
import view.components.MyTextArea;
import view.interfaces.IGameView;
import view.main.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectionPanel extends CustomPanel {

    public SelectionPanel(JPanel parent, CardLayout panels, MainView context){
        super(parent, panels, context);
        nextPanel = PANELS.JOIN_OR_CREATE;
        CustomPanelFactory.createCustomPanel(parent,panels,context,PANELS.JOIN_OR_CREATE);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(true);
        setBackground(Color.BLACK);

        MyTextArea txtArea = new MyTextArea("Select the view mode",500,130);
        txtArea.setTexture("src/resources/img/components/txtareaV1.png");
        MyButton btnConsole = new MyButton(200,60, TextureFactory.getInstance());
        MyButton btnGraphics = new MyButton(200,60, TextureFactory.getInstance());


        btnConsole.setText("CONSOLE");
        btnConsole.setTexture("src/resources/img/components/botonV2.png");
        btnGraphics.setText("GRAPHICS");
        btnGraphics.setTexture("src/resources/img/components/botonV2.png");

        btnConsole.addActionListener(e -> {
            context.getController().setView((IGameView) CustomPanelFactory.createCustomPanel(mainPanel,panels,context,PANELS.CONSOLE));
            nextPanel();
        });


        btnGraphics.addActionListener(e -> {
            context.getController().setView((IGameView) CustomPanelFactory.createCustomPaneltexture(mainPanel,panels,context, TextureFactory.getInstance(),PANELS.GRAPHICS));
            nextPanel();
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
