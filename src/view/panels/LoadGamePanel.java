package view.panels;

import model.interfaces.IGameMatchStatusPublic;
import utils.TextureFactory;
import view.components.MyButton;
import view.components.MyTblModel;
import view.components.MyTextArea;
import view.main.MainView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LoadGamePanel extends CustomPanel{

    private final JTable table;

    public LoadGamePanel(JPanel parent, CardLayout panels, MainView context) {
        super(parent, panels, context);
        this.table = new JTable(new MyTblModel(context.getController().getMatchesSaved(context.getUsername())));
        initComponents();
    }

    private void initComponents(){
        setLayout(new BorderLayout());
        setOpaque(true);
        setBackground(Color.BLACK);

        MyTextArea txtArea = new MyTextArea("Search a game saved by name or id", 200,128);
        txtArea.setTexture("src/resources/img/components/txtareaV1.png");
        MyButton btnSearch = new MyButton(200,64, TextureFactory.getInstance());
        btnSearch.setText("SEARCH");
        btnSearch.setTexture("src/resources/img/components/botonV2.png");
        btnSearch.addActionListener(e -> refreshTable());
        MyButton btnJoin = new MyButton(200,64, TextureFactory.getInstance());
        btnJoin.setText("LOAD");
        btnJoin.setTexture("src/resources/img/components/botonV2.png");
        btnJoin.addActionListener(e -> joinSelectedGame());
        MyButton btnBack = new MyButton(200,64, TextureFactory.getInstance());
        btnBack.setText("BACK");
        btnBack.setTexture("src/resources/img/components/botonV2.png");
        btnBack.addActionListener(e -> backToMenu());
        JPanel btnPane = new JPanel();
        btnPane.setBackground(Color.BLACK);

        add(txtArea, BorderLayout.NORTH);
        table.setBackground(Color.BLACK);
        table.setForeground(new Color(237,140,255));
        table.setBorder(BorderFactory.createLineBorder(new Color(237,140,255)));
        add(table,BorderLayout.CENTER);
        btnPane.add(btnBack);
        btnPane.add(btnSearch);
        btnPane.add(btnJoin);
        add(btnPane, BorderLayout.SOUTH);
    }

    private void refreshTable() {
        List<IGameMatchStatusPublic> matches = context.getController().getMatchesSaved(context.getUsername());
        if (!matches.isEmpty()) {
            table.setModel(new MyTblModel(matches));
            table.repaint();
        } else {
            context.displayMessage("You don´t have games saved");
        }
    }

    private void joinSelectedGame() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            IGameMatchStatusPublic selectedMatch = ((MyTblModel) table.getModel()).getData().get(selectedRow);
            context.getController().loadGame(selectedMatch.getId());
        } else {
            context.displayMessage("Please select a game");
        }
    }

    private void backToMenu(){
        nextPanel = PANELS.JOIN_OR_CREATE;
        nextPanel();
    }

}
