package view;

import javax.swing.*;

public interface IView {
    void displayMessage(String message);
    void init();
    JPanel getPanel();
}
