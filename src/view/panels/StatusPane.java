package view.panels;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StatusPane extends JPanel {
    private Dimension dimLbl = new Dimension(200,120);

    public StatusPane(){
        super();
        initComponents();
    }

    private void initComponents(){
        setBackground(Color.BLACK);
        setMinimumSize(new Dimension(210, 185));
        setPreferredSize(new Dimension(210, 185));
        JLabel lblEstado = new JLabel("Estado");
//        Dimension dimLbl = new Dimension(200,120);
        lblEstado.setPreferredSize(dimLbl);
        lblEstado.setMinimumSize(dimLbl);
        lblEstado.setBorder(BorderFactory.createLineBorder(Color.RED));
        add(lblEstado);
    }

    public void updateStatus(List<String> data){

    }
}
