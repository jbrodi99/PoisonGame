package view.panels;

import model.interfaces.IGameMatchStatusPublic;
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

public class JoinPanel extends JPanel {
    private JPanel mainPanel;
    private CardLayout panels;
    private MainView context;
    private JTable table;
    private Timer timer;

    public JoinPanel(JPanel parent, CardLayout panels, MainView context){
        super();
        this.mainPanel = parent;
        this.panels = panels;
        this.context = context;
        this.table = new JTable(new MyTblModel(context.getController().getMatches()));
        initComponents();
    }

    private void initComponents() {
        setVisible(false);
        setLayout(new BorderLayout());
        setOpaque(true);
        setBackground(Color.BLACK);

        MyTextArea txtArea = new MyTextArea("Search a game by name or id", 200,128);

//        JTable table = new JTable(new MyTblModel(context.getController().getMatches()));

        MyButton btnSearch = new MyButton("SEARCH",200,64);

        btnSearch.addActionListener(e -> refreshTable());

        MyButton btnJoin = new MyButton("JOIN",200,64);

        btnJoin.addActionListener(e -> joinSelectedGame());

        JPanel btnPane = new JPanel();

        add(txtArea, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
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
//            if (context.getController().connectPlayer(selectedMatch.getId(), context.getUsername())) {
//                panels.show(mainPanel, context.getViewSelected());
//            }
            context.getController().connectPlayer(selectedMatch.getId(), context.getUsername());
        } else {
            context.displayMessage("Please select a game");
        }
    }

    private void startTimer(){
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(JoinPanel.this::refreshTable);
            }
        }, 0, 10000); // Actualiza cada 5 segundos
    }

    private void stopTimer(){
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
