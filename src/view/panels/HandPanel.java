package view.panels;

import model.interfaces.ICard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class HandPanel extends JLayeredPane {

    private final Dimension dimensionHand = new Dimension(450,215);
    private final List<CardPanel> cards = new ArrayList<>();

    public HandPanel(){
        super();
        cards.add(new CardPanel(-0.5));
        cards.add(new CardPanel(-0.25));
        cards.add(new CardPanel(0.25));
        cards.add(new CardPanel(0.5));
        initComponents();
    }

    private void initComponents(){
        setPreferredSize(new Dimension(dimensionHand.width,dimensionHand.height));
        setMinimumSize(new Dimension(dimensionHand.width,dimensionHand.height));
        setMaximumSize(new Dimension(dimensionHand.width,dimensionHand.height));

        AtomicInteger x = new AtomicInteger(0);
        cards.forEach(cardPanel -> {
            cardPanel.setBounds(x.get(),0,160,215);
            cardPanel.setBackground(Color.BLACK);
            add(cardPanel);
            x.addAndGet(100);
        });
    }

    public void updateHand(List<ICard> cards){

    }

}
