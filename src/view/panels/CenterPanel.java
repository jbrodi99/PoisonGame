package view.panels;

import model.interfaces.ICenterStack;
import view.components.MyLabel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CenterPanel extends JPanel {
    private final Dimension dimensionCenter = new Dimension(190,150);
    private final List<JPanel> centers = new ArrayList<>();
    private final List<MyLabel> textures = new ArrayList<>();

    public CenterPanel(){
        super();
        for (int i = 0; i < 3; i++) {
            centers.add(new JPanel());
        }
        initComponents();
    }

    private void initComponents(){
        setLayout(new FlowLayout());
        setBackground(Color.MAGENTA);
//        setOpaque(false);     //comentar fondo cuando termine testeo
        setBorder(BorderFactory.createEmptyBorder(0,0,60,0));
        centers.forEach(center -> {
            center.setBackground(Color.YELLOW);
            center.setLayout(new BorderLayout());
            center.setPreferredSize(dimensionCenter);
            center.setMinimumSize(dimensionCenter);
            MyLabel lbl = new MyLabel(dimensionCenter.width, dimensionCenter.height);
            lbl.setBorder(BorderFactory.createLineBorder(Color.CYAN));
            textures.add(lbl);
            center.add(lbl);
            add(center);
        });

        textures.get(0).setText("Espada");
        textures.get(1).setText("Basto");
        textures.get(2).setText("Oro");

        setVisible(true);
    }

    public void updateTextures(List<ICenterStack> newCenters){

    }
}
