package view.panels;

import utils.ITextureFactory;
import utils.TextureFactory;
import view.components.MyButton;
import view.components.MyTextArea;
import view.main.MainView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuPanel extends CustomPanelTexture {

    MyTextArea txtArea;

    public MenuPanel(JPanel parent, CardLayout panels, MainView context, ITextureFactory textureFactory){
        super(parent,panels,context,textureFactory);
        setTexture("src/resources/img/components/menuGameFondoV1.png",context.getWidth(), context.getHeight());
        initComponents();
    }

    private void initComponents() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(false);

        txtArea = new MyTextArea("Welcome " + context.getUsername() + "!",500,130); //se setea con null el saludo
        txtArea.setTexture("src/resources/img/components/txtareaV1.png");
        MyButton btnPlay = new MyButton(200,60, TextureFactory.getInstance());
        MyButton btnRanking = new MyButton(200,60, TextureFactory.getInstance());
        MyButton btnHowPlay = new MyButton(200,60, TextureFactory.getInstance());

        btnPlay.setText("PLAY");
        btnRanking.setText("RANKING");
        btnHowPlay.setText("HOW PLAY");
        btnPlay.setTexture("src/resources/img/components/botonV2.png");
        btnRanking.setTexture("src/resources/img/components/botonV2.png");
        btnHowPlay.setTexture("src/resources/img/components/botonV2.png");

        btnPlay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnPlay.setTexture("src/resources/img/components/botonHV1.png");
                setTexture("src/resources/img/components/menuGameV2.png", context.getWidth(), context.getHeight());
            }
        });

        btnPlay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnPlay.setTexture("src/resources/img/components/botonV2.png");
                setTexture("src/resources/img/components/menuGameFondoV1.png", context.getWidth(), context.getHeight());
            }
        });

        btnPlay.addActionListener(e -> {
            setNextPanel(PANELS.SELECTION);
            CustomPanelFactory.createCustomPanel(mainPanel,panels,context,PANELS.SELECTION);
            nextPanel();
        });

        btnRanking.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnRanking.setTexture("src/resources/img/components/botonHV1.png");
                setTexture("src/resources/img/components/menuGameBV2.png", context.getWidth(), context.getHeight());
            }
        });

        btnRanking.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnRanking.setTexture("src/resources/img/components/botonV2.png");
                setTexture("src/resources/img/components/menuGameFondoV1.png", context.getWidth(), context.getHeight());
            }
        });

        btnRanking.addActionListener(e -> {
            setNextPanel(PANELS.RANKING);
            RankingPanel ranking = (RankingPanel) CustomPanelFactory.createCustomPaneltexture(mainPanel,panels,context,TextureFactory.getInstance(),PANELS.RANKING);
//            CustomPanelFactory.createCustomPaneltexture(mainPanel,panels,context,TextureFactory.getInstance(),PANELS.RANKING);
            ranking.updateTable();
            nextPanel();
        });

        btnHowPlay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnHowPlay.setTexture("src/resources/img/components/botonHV1.png");
                setTexture("src/resources/img/components/menuGameBBV2.png", context.getWidth(), context.getHeight());
            }
        });

        btnHowPlay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnHowPlay.setTexture("src/resources/img/components/botonV2.png");
                setTexture("src/resources/img/components/menuGameFondoV1.png", context.getWidth(), context.getHeight());
            }
        });

        btnHowPlay.addActionListener(e -> {
//            setNextPanel(PANELS.HOW_PLAY);
//            CustomPanelFactory.createCustomPanel(mainPanel,panels,context,PANELS.HOW_PLAY);
//            nextPanel();
        });

        this.add(Box.createVerticalGlue());
        this.add(txtArea);
        this.add(Box.createVerticalGlue());
        this.add(btnPlay);
        this.add(Box.createVerticalGlue());
        this.add(btnRanking);
        this.add(Box.createVerticalGlue());
        this.add(btnHowPlay);
        this.add(Box.createVerticalGlue());

        setVisible(false);
    }
}
