package view.panels;

import model.interfaces.IGameMatchStatusPublic;
import utils.TextureFactory;
import view.components.MyButton;
import view.components.MyTblModel;
import view.components.MyTextArea;
import view.main.MainView;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class JoinPanel extends CustomPanel {
    private final JTable table;
    private Timer timer;

    public JoinPanel(JPanel parent, CardLayout panels, MainView context){
        super(parent,panels,context);
        this.table = new JTable(new MyTblModel(context.getController().getMatches()));
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setOpaque(true);
        setBackground(Color.BLACK);

        MyTextArea txtArea = new MyTextArea("Search a game by name or id", 200,128);
        txtArea.setTexture("src/resources/img/components/txtareaV1.png");
        MyButton btnSearch = new MyButton(200,64, TextureFactory.getInstance());
        btnSearch.setText("SEARCH");
        btnSearch.setTexture("src/resources/img/components/botonV2.png");
        btnSearch.addActionListener(e -> refreshTable());
        MyButton btnJoin = new MyButton(200,64, TextureFactory.getInstance());
        btnJoin.setText("JOIN");
        btnJoin.setTexture("src/resources/img/components/botonV2.png");
        btnJoin.addActionListener(e -> joinSelectedGame());
        JPanel btnPane = new JPanel();
        btnPane.setBackground(Color.BLACK);

        add(txtArea, BorderLayout.NORTH);
        table.setBackground(Color.BLACK);
        table.setForeground(new Color(237,140,255));
        table.setBorder(BorderFactory.createLineBorder(new Color(237,140,255)));
        add(table,BorderLayout.CENTER);
        btnPane.add(btnSearch);
        btnPane.add(btnJoin);
        add(btnPane, BorderLayout.SOUTH);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                startTimer();
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                stopTimer();
            }
        });
    }

    private void refreshTable() {
        List<IGameMatchStatusPublic> matches = context.getController().getMatches();
        if (!matches.isEmpty()) {
            table.setModel(new MyTblModel(matches));
            table.repaint();
        }
    }

    private void joinSelectedGame() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            IGameMatchStatusPublic selectedMatch = ((MyTblModel) table.getModel()).getData().get(selectedRow);
            context.getController().connectPlayer(selectedMatch.getId(), context.getUsername());
        } else {
            context.displayMessage("Please select a game");
        }
    }

    private void startTimer(){
        if (getParent() != null){
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    SwingUtilities.invokeLater(JoinPanel.this::refreshTable);
                }
            }, 0, 10000); // Actualiza cada 5 segundos
        }
    }

    private void stopTimer(){
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
