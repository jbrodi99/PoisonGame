package view.panels;

import model.interfaces.ICard;
import model.interfaces.ICenterStack;
import model.interfaces.IPlayerPublic;
import utils.TextureFactory;
import view.components.MyButton;
import view.components.MyLabel;
import view.main.MainView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DraggableArea extends JLayeredPane {
    private final MainView context;
    private final Dimension dimensionCenter = new Dimension(190, 150);
    private final Dimension dimensionCard = new Dimension(150, 205);
    private final Dimension dimensionStatus = new Dimension(190, 120);

    private StatusPanel statusPanel;
    private StatusPanel graveyardPanel;
    private final List<JPanel> centerZone = new ArrayList<>();
    private final List<JPanel> pointCenterZone = new ArrayList<>();
    private final List<JPanel> handZone = new ArrayList<>();
    private List<MyLabel> players = new ArrayList<>();
    private MyLabel graveyard;
    private final List<MyLabel> centers = new ArrayList<>();
    private final List<MyLabel> pointCenters = new ArrayList<>();
    private final List<Draggable> hand = new ArrayList<>();

    public DraggableArea(MainView context) {
        super();
        this.context = context;
        initComponents();
    }

    private void initComponents() {
        setLayout(null);
        setOpaque(false);

        for (int i = 0; i < 3; i++) {
            JPanel panel = new JPanel();
            centerZone.add(panel);
            add(panel, JLayeredPane.DEFAULT_LAYER);
            JPanel panel1 = new JPanel();
            pointCenterZone.add(panel1);
            add(panel1,JLayeredPane.DEFAULT_LAYER);
        }
        for (int i = 0; i < 4; i++) {
            JPanel panel = new JPanel();
            handZone.add(panel);
            add(panel, JLayeredPane.DEFAULT_LAYER);
        }

        statusPanel = new StatusPanel();
        statusPanel.setBounds(10,10,dimensionStatus.width,dimensionStatus.height);
        statusPanel.setTexture("src/resources/img/components/textureLabelStatus.png",statusPanel.getWidth(), statusPanel.getHeight());
        add(statusPanel,JLayeredPane.DEFAULT_LAYER);

        graveyardPanel = new StatusPanel();
        graveyardPanel.setBounds(600,10,dimensionStatus.width,dimensionStatus.height);
        graveyardPanel.setTexture("src/resources/img/components/textureLabelStatus90.png",graveyardPanel.getWidth(), graveyardPanel.getHeight());

        add(graveyardPanel,JLayeredPane.DEFAULT_LAYER);


        AtomicInteger x = new AtomicInteger(117);
        centerZone.forEach(centerZone -> {
            centerZone.setOpaque(false);
            centerZone.setLayout(new BorderLayout());
            centerZone.setBackground(Color.BLACK);
            centerZone.setBounds(x.get(), 170, dimensionCenter.width, dimensionCenter.height);
            x.addAndGet(190);
        });

        x.set(150);
        pointCenterZone.forEach(pointCenters -> {
            pointCenters.setOpaque(false);
            pointCenters.setLayout(new BorderLayout());
            pointCenters.setBounds(x.get(), 330, 40, 40);

            x.addAndGet(230);
        });

        x.set(175);

        handZone.forEach(cardZone -> {
            cardZone.setLayout(null);
            cardZone.setOpaque(false);
            cardZone.setBounds(x.get(), 400, 160, 210);
            x.addAndGet(100);
        });

    }

    public void updateStatus(List<IPlayerPublic> players){
        statusPanel.add(Box.createVerticalStrut(45));
        for (IPlayerPublic player : players) {
            MyLabel lbl = new MyLabel(dimensionStatus.width,15);
            lbl.setFont(new Font(Font.SANS_SERIF,Font.BOLD,10));
//            lbl.setTexture("src/resources/img/components/textureLabelStatus.png");
            StringBuilder statusText = new StringBuilder();
            statusText.append(player.getUserName())
                    .append(" ")
                    .append("Health: ")
                    .append(player.getHealth())
                    .append(" ");
            if(player.isYourTurn()){
                statusText.append("(playing)");
            } else {
                statusText.append(" (waiting)");
            }
            statusText.append("\n");
            lbl.setText(statusText.toString());
            statusPanel.add(lbl);
            this.players.add(lbl);
            statusPanel.add(Box.createVerticalStrut(5));
        }
    }

    public void updateHand(List<ICard> newHand) {

        int index = 0;
        for (ICard card : newHand) {
            MyLabel lbl = new MyLabel(dimensionCard.width, dimensionCard.height);
            lbl.setTexture(card.getTypeCard().getName(), card.getValue().getName());
            JPanel container = handZone.get(index);
            Draggable draggable = new Draggable(lbl, container.getX(), container.getY(), context, centerZone, handZone);
            hand.add(draggable);
            add(draggable, DRAG_LAYER);
            index++;
        }
        revalidate();
        repaint();
    }

    public void updateCenter(List<ICenterStack> newCenter) {

        int index = 0;

        for (ICenterStack centerStack : newCenter) {
            if (!centerStack.isEmpty()) {
                ICard card = centerStack.getTopCard();
                MyLabel lbl = new MyLabel(dimensionCenter.width, dimensionCenter.height);
                lbl.setTexture(card.getTypeCard().getName(), card.getValue().getName(), index);
                centerZone.get(index).add(lbl);
                centers.add(lbl);
                MyLabel lblPoints = new MyLabel(40, 40);
                lblPoints.setText("" + centerStack.countPoints());
                pointCenterZone.get(index).add(lblPoints);
                pointCenters.add(lblPoints);
            } else {
                MyLabel lblPoints = new MyLabel(40, 40);
                lblPoints.setText("0");
                pointCenterZone.get(index).add(lblPoints);
                pointCenters.add(lblPoints);
            }

            index++;
        }
    }

    public void updateGraveyard(List<ICard> cards) {
        graveyard= new MyLabel(dimensionStatus.width,15);
        graveyard.setFont(new Font(Font.SANS_SERIF,Font.BOLD,10));
        graveyard.setText("Graveyard: " + cards.size() + " cards");
        graveyardPanel.add(Box.createVerticalGlue());
        graveyardPanel.add(graveyard);
        graveyardPanel.setAlignmentX(0.3f);
        graveyardPanel.add(Box.createVerticalGlue());
    }

    public void clear() {

        graveyardPanel.removeAll();

        for (MyLabel pointCenter : pointCenters) {
            pointCenter.getParent().remove(pointCenter);
        }
        pointCenters.clear();

        for (MyLabel player : players){
            player.getParent().remove(player);
        }
        players.clear();
        statusPanel.removeAll();

        for (Draggable draggable : hand) {
            draggable.getParent().remove(draggable);
            remove(draggable);
        }

        hand.clear();

        for (MyLabel center : centers) {
            center.getParent().remove(center);
            remove(center);
        }

        centers.clear();
    }

    public void drag(boolean action) {
        hand.forEach(card -> {
            card.setAction(action);
        });
    }


}
