package view.panels;

import utils.TextureFactory;
import view.components.MyButton;
import view.main.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JoinOrCreatePanel extends CustomPanel {

    public JoinOrCreatePanel(JPanel parent, CardLayout panels, MainView context){
        super(parent,panels,context);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(true);
        setBackground(Color.BLACK);

        MyButton btnCreate = new MyButton(200,60, TextureFactory.getInstance());
        MyButton btnSearch = new MyButton(200,60, TextureFactory.getInstance());
        MyButton btnLoad = new MyButton(200,60, TextureFactory.getInstance());

        btnCreate.setText("CREATE GAME");
        btnSearch.setText("SEARCH GAME");
        btnLoad.setText("LOAD GAME");
        btnCreate.setTexture("src/resources/img/components/botonV2.png");
        btnSearch.setTexture("src/resources/img/components/botonV2.png");
        btnLoad.setTexture("src/resources/img/components/botonV2.png");

        btnCreate.addActionListener(e -> {
            setNextPanel(PANELS.CREATE);
            CustomPanelFactory.createCustomPanel(mainPanel,panels,context,PANELS.CREATE);
            nextPanel();
        });

        btnSearch.addActionListener(e -> {
            setNextPanel(PANELS.JOIN);
            CustomPanelFactory.createCustomPanel(mainPanel,panels,context,PANELS.JOIN);
            nextPanel();
        });

        btnLoad.addActionListener(e -> {
            setNextPanel(PANELS.LOAD);
            CustomPanelFactory.createCustomPanel(mainPanel,panels,context,PANELS.LOAD);
            nextPanel();
        });

        this.add(Box.createVerticalGlue());
        this.add(btnCreate);
        this.add(Box.createVerticalGlue());
        this.add(btnSearch);
        this.add(Box.createVerticalGlue());
        this.add(btnLoad);
        this.add(Box.createVerticalGlue());
    }

}
