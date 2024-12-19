package view.panels;

import view.main.MainView;

import javax.swing.*;
import java.awt.*;

public abstract class CustomPanel extends JPanel {

    protected JPanel mainPanel;
    protected CardLayout panels;
    protected MainView context;
    protected PANELS nextPanel;

    public CustomPanel(JPanel parent, CardLayout panels, MainView context){
        super();
        this.mainPanel = parent;
        this.panels = panels;
        this.context = context;
    }

    public void setNextPanel(PANELS panel){
        this.nextPanel = panel;
    }

    public void nextPanel(){
        this.setVisible(false);
        panels.show(mainPanel,nextPanel.getName());
    }


}
