package view.panels;

import model.logic.Ranking;
import utils.ITextureFactory;
import utils.TextureFactory;
import view.components.MyButton;
import view.components.MyLabel;
import view.components.MyTextArea;
import view.main.MainView;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class RankingPanel extends CustomPanelTexture{

    JPanel tablePanel;

    public RankingPanel(JPanel parent, CardLayout panels, MainView context, ITextureFactory textureFactory) {
        super(parent, panels, context, textureFactory);
        initComponents();
    }

    private void initComponents(){
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        MyTextArea txtArea = new MyTextArea("RANKING", 200,128);
        txtArea.setTexture("src/resources/img/components/txtareaV1.png");

        tablePanel = new JPanel();
        tablePanel.setOpaque(false);
        tablePanel.setLayout(new BoxLayout(tablePanel,BoxLayout.Y_AXIS));


        MyButton btnBack = new MyButton(200,60, TextureFactory.getInstance());
        btnBack.setText("BACK");
        btnBack.setTexture("src/resources/img/components/botonV2.png");

        btnBack.addActionListener(e -> {
            setNextPanel(PANELS.MENU);
            CustomPanelFactory.createCustomPaneltexture(mainPanel,panels,context, TextureFactory.getInstance(),PANELS.MENU);
            nextPanel();
        });

        JPanel containerBtn = new JPanel();
        containerBtn.setOpaque(false);

        containerBtn.add(btnBack);

        add(txtArea,BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(containerBtn,BorderLayout.SOUTH);
    }

    public void updateTable(){
        List<Ranking.SerializableEntry> ranking = context.getController().getTopFive();

        tablePanel.removeAll();

        for (Ranking.SerializableEntry serializableEntry : ranking) {
//            StringBuilder txtLbl = new StringBuilder();
            MyLabel lbl = new MyLabel(300,100,"src/resources/img/components/labelRanking.png");
            String txtLbl = "Player: " + serializableEntry.getKey() + " - Score: " + serializableEntry.getValue();
            lbl.setText(txtLbl);
            tablePanel.add(Box.createVerticalGlue());
            tablePanel.add(lbl);
        }
        tablePanel.add(Box.createVerticalGlue());
    }
}
