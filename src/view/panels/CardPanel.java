package view.panels;

import view.components.MyLabel;

import javax.swing.*;
import java.awt.*;

public class CardPanel extends JPanel {

    private final Dimension dimensionCard = new Dimension(150,205);
    private final MyLabel card;

    public CardPanel(Double degrees){
        super();
        card = new MyLabel(dimensionCard.width,dimensionCard.height,degrees);
        initComponents();
    }

    private void initComponents(){
        card.setBorder(BorderFactory.createLineBorder(Color.RED));
        add(card);
    }
}
