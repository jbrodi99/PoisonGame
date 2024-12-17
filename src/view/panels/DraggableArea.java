package view.panels;

import model.enums.TYPECARD;
import model.interfaces.ICard;
import model.interfaces.ICenterStack;
import view.components.MyLabel;
import view.main.MainView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DraggableArea extends JLayeredPane {
    private MainView context;
    private final Dimension dimensionCenter = new Dimension(190,150);
    private final Dimension dimensionCard = new Dimension(150,205);

    private final List<JPanel> centerZone = new ArrayList<>();
    private final List<JPanel> handZone = new ArrayList<>();
    private final List<MyLabel> centers = new ArrayList<>();
//    private final List<Draggable> handDrag = new ArrayList<>();
    private final List<Draggable> hand = new ArrayList<>();

    public DraggableArea(MainView context){
        super();
        this.context = context;
        initComponents();
    }

    private void initComponents(){
        setLayout(null);
        setOpaque(false);

        for (int i = 0; i < 3; i++) {
            JPanel panel = new JPanel();
            centerZone.add(panel);
            add(panel,JLayeredPane.DEFAULT_LAYER);
        }
        for (int i = 0; i < 4; i++) {
            JPanel panel = new JPanel();
            handZone.add(panel);
            add(panel, JLayeredPane.DEFAULT_LAYER);
        }

        AtomicInteger x = new AtomicInteger(117);
        centerZone.forEach(centerZone -> {
            centerZone.setOpaque(false);
            centerZone.setLayout(new BorderLayout());
            centerZone.setBackground(Color.BLACK);
            centerZone.setBounds(x.get(), 170,dimensionCenter.width,dimensionCenter.height);
            x.addAndGet(190);
        });

        x.set(175);

        handZone.forEach( cardZone -> {
            cardZone.setLayout(null);
            cardZone.setOpaque(false);
//            cardZone.setBackground(Color.RED);
//            cardZone.setOpaque(false);
            cardZone.setBounds(x.get(),400,160,210);
            x.addAndGet(100);
        });

    }

    public void updateHand(List<ICard> newHand){

        //quito elemento utilizado y dejo los demas labels
//        while (hand.size() > newHand.size()){
//            MyLabel toRemove = hand.remove(hand.size() - 1);
//            remove(toRemove);
//        }

        int index = 0;
        for (ICard card : newHand){
//            System.out.println("src/resources/img/cards/texture_" + card.getTypeCard().getName() + "_" + card.getValue().getName() + ".png");
//            if (index > hand.size()){
//                MyLabel lbl = new MyLabel(dimensionCard.width, dimensionCard.height);
//                lbl.setTexture(card.getTypeCard().getName(),card.getValue().getName());
//                JPanel container = handZone.get(index);
//                Draggable draggable = new Draggable(lbl,container.getX(),container.getY(),context, centerZone,handZone);
//                hand.add(lbl);
//                add(draggable, DRAG_LAYER);
//            } else {
//                //actualizar textura sobre lbl existente
//                hand.get(index).setTexture(card.getTypeCard().getName(),card.getValue().getName());
//            }
            MyLabel lbl = new MyLabel(dimensionCard.width, dimensionCard.height);
            lbl.setTexture(card.getTypeCard().getName(),card.getValue().getName());
//            lbl.setText("Soy una carta");
//            lbl.setBorder(BorderFactory.createLineBorder(Color.CYAN));
            JPanel container = handZone.get(index);
            Draggable draggable = new Draggable(lbl,container.getX(),container.getY(),context, centerZone,handZone);
            hand.add(draggable);
            add(draggable, DRAG_LAYER);
            index++;
        }
        revalidate();
        repaint();
    }

    public void updateCenter(List<ICenterStack> newCenter){
        //TODO: implementar

        int index = 0;

        for (ICenterStack centerStack : newCenter) {
            if (!centerStack.isEmpty()){
                ICard card = centerStack.getTopCard();   //TODO: implementar mapeo con textura para cargarla en el centro
//                MyLabel lbl = new MyLabel(dimensionCenter.width, dimensionCenter.height,"src/resources/img/cards/texture_" + card.getTypeCard().getName() + "_" + card.getValue().getName() + "_perspective.png");
                MyLabel lbl = new MyLabel(dimensionCenter.width, dimensionCenter.height);
                lbl.setTexture(card.getTypeCard().getName(), card.getValue().getName(), index);
//                if (card.getTypeCard() == TYPECARD.CUP) {
//                    System.out.println("src/resources/img/cards/texture_" + card.getTypeCard().getName() + "_" + card.getValue().getName() + "_" + index + "_perspective.png");
//                    lbl = new MyLabel(dimensionCenter.width, dimensionCenter.height,"src/resources/img/cards/texture_" + card.getTypeCard().getName() + "_" + card.getValue().getName() + "_" + index + "_perspective.png");
//                } else {
//                    System.out.println("src/resources/img/cards/texture_" + card.getTypeCard().getName() + "_" + card.getValue().getName() + "_perspective.png");
//                    lbl = new MyLabel(dimensionCenter.width, dimensionCenter.height,"src/resources/img/cards/texture_" + card.getTypeCard().getName() + "_" + card.getValue().getName() + "_perspective.png");
//                }
//                lbl.setBorder(BorderFactory.createLineBorder(Color.CYAN));
                centerZone.get(index).add(lbl);
                centers.add(lbl);
            }
            index++;
        }
    }

    public void clear(){

        // Eliminar los Draggables existentes de la capa y limpiar la lista
        for (Draggable draggable : hand) {
            draggable.getParent().remove(draggable); // Elimina el componente de su contenedor
            remove(draggable); // Elimina el componente del JLayeredPane
        }

        hand.clear();

        for (MyLabel center : centers) {
            center.getParent().remove(center);
            remove(center);
        }

        centers.clear();
    }

    public void drag(boolean action){
        hand.forEach(card -> {
            card.setAction(action);
        });
    }
}
