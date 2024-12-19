package view.panels;

import view.main.MainView;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.border.Border;
import javax.swing.event.MouseInputAdapter;


public class Draggable extends JComponent {
    private MainView context;
    private List<JPanel> centerZone;
    private List<JPanel> handZone;
    private Point pointPressed;
    private final Point originalLocation;
    private final JComponent draggable;
    private boolean action = false;
    private boolean isDragging = false;

    public Draggable(final JComponent component, final int x, final int y,MainView context, List<JPanel> centerZone, List<JPanel> handZone) {
        this.context = context;
        this.centerZone = centerZone;
        this.handZone = handZone;
        draggable = component;
        originalLocation = new Point(x,y);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setLocation(x, y);
        setSize(component.getPreferredSize());
        setLayout(new BorderLayout());
        add(component);
        MouseInputAdapter mouseAdapter = new MouseHandler();
        addMouseMotionListener(mouseAdapter);
        addMouseListener(mouseAdapter);
    }

    @Override
    public void setBorder(final Border border) {
        super.setBorder(border);
        if (border != null) {
            Dimension size = draggable.getPreferredSize();
            Insets insets = border.getBorderInsets(this);
            size.width += (insets.left + insets.right + 5);
            size.height += (insets.top + insets.bottom);
            setSize(size);
        }
    }

    public void setAction(boolean action){
        this.action = action;
    }

    private class MouseHandler extends MouseInputAdapter {
        @Override
        public void mouseDragged(final MouseEvent e) {
            if (action && !isDragging) {
                isDragging = true;
                SwingUtilities.invokeLater(() -> {
                    Point pointDragged = e.getPoint();
                    Point loc = getLocation();
                    loc.translate(pointDragged.x - pointPressed.x, pointDragged.y - pointPressed.y);
                    setLocation(loc);
                    isDragging = false;
                    repaint();
                });
            }
        }
        @Override
        public void mousePressed(final MouseEvent e) {
            pointPressed = e.getPoint();
            disableRepaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            enableRepaint();
            Point pointReleased = SwingUtilities.convertPoint(Draggable.this, e.getPoint(), getParent());
            setLocation(originalLocation);
            int indexCenter = 0;
            int indexCard = 0;
            if (action){
                for (JPanel center : centerZone){
                    if (center.getBounds().contains(pointReleased)){
                        for (JPanel card : handZone){
                            Point realPos = originalLocation.getLocation();
                            realPos.translate(60,0);
                            if (card.getBounds().contains(realPos)){
                                context.getController().playTurn(indexCard,indexCenter);
                            }
                            indexCard++;
                        }
                    }
                    indexCenter++;
                }
            }
        }
    }

    public void disableRepaint(){
        for (Component component : context.getComponents()){
            component.setIgnoreRepaint(true);
        }
    }

    public void enableRepaint(){
        for (Component component : context.getComponents()){
            component.setIgnoreRepaint(false);
        }
    }
}