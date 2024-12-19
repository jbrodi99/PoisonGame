package view.panels;

import model.interfaces.IPlayerPublic;
import view.components.MyTextArea;
import view.main.MainView;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.List;

public class LobbyConsolePanel extends CustomPanel {

    private JTextPane output;

    public LobbyConsolePanel(JPanel parent, CardLayout panels, MainView context){
        super(parent, panels, context);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        MyTextArea logo = new MyTextArea("LOBBY", 500,130);
        logo.setTexture("src/resources/img/components/txtareaV1.png");

        JPanel center = new JPanel();
        center.setLayout(new BorderLayout());
        center.setBorder(BorderFactory.createEmptyBorder(130,100,130,100));

        center.setOpaque(false);

        output = new JTextPane();
        output.setOpaque(false);
        output.setFont(new Font(Font.SANS_SERIF,Font.BOLD, 20));
        output.setForeground(new Color(237,140,255));
        output.setAlignmentX(CENTER_ALIGNMENT);
        output.setAlignmentY(CENTER_ALIGNMENT);

        StyledDocument doc = output.getStyledDocument();
        SimpleAttributeSet centerTxt = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerTxt, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), centerTxt, false);

        center.add(output, BorderLayout.CENTER);
        add(logo,BorderLayout.NORTH);
        add(center,BorderLayout.CENTER);
    }

    public void updateLobby(List<IPlayerPublic> players){
        output.setText("");
        if (players.isEmpty()) {
            appendText(output,"No hay jugadores esperando.");
            return;
        }
        StringBuilder stickmen = new StringBuilder();
        StringBuilder stickmenHead = new StringBuilder();
        StringBuilder stickmenBody = new StringBuilder();
        StringBuilder stickmenleg = new StringBuilder();

        players.forEach( player ->{
            stickmenHead.append("      O      ");
            stickmenBody.append("     /|\\     ");
            stickmenleg.append("     / \\     ");
        });
        stickmenHead.append("\n");
        stickmenBody.append("\n");
        stickmenleg.append("\n");

        stickmen.append(stickmenHead).append(stickmenBody).append(stickmenleg);
        stickmen.append("\nEsperando a que el(los) ").append("jugador(es) estén listos...\n");

        appendText(output,stickmen.toString());
    }

    private static void appendText(JTextPane textPane, String text) {
        StyledDocument doc = textPane.getStyledDocument();
        try {
            doc.insertString(doc.getLength(), text, null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}
