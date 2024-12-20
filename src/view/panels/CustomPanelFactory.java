package view.panels;

import utils.ITextureFactory;
import view.main.MainView;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CustomPanelFactory {

    private static final Map<PANELS, CustomPanel> panelInstances = new HashMap<>();
    private static final Map<PANELS, CustomPanelTexture> texturePanelInstances = new HashMap<>();

    public static CustomPanel createCustomPanel(JPanel parent, CardLayout panels, MainView context, PANELS panel){

        if (panelInstances.containsKey(panel)) {

            CustomPanel existingPanel = panelInstances.get(panel);
            if (parent.isAncestorOf(existingPanel)) {
                return existingPanel;
            }

            parent.add(existingPanel, panel.getName());
            return existingPanel;
        }


        CustomPanel newPanel = switch (panel) {
            case LOGIN -> new LoginPanel(parent, panels, context);
            case REGISTER -> new RegisterPanel(parent, panels, context);
            case SELECTION -> new SelectionPanel(parent, panels, context);
            case JOIN_OR_CREATE -> new JoinOrCreatePanel(parent, panels, context);
            case JOIN -> new JoinPanel(parent, panels, context);
            case CREATE -> new CreateGamePanel(parent, panels, context);
            case LOAD -> new LoadGamePanel(parent, panels, context);
            case LOBBY_CONSOLE -> new LobbyConsolePanel(parent, panels, context);
            case LOBBY_GRAPHICS -> new LobbyGrapichsPanel(parent, panels, context);
            case CONSOLE -> new ConsolePanel(parent, panels, context);
            default -> null;
        };

        if (newPanel != null) {
            parent.add(newPanel, panel.getName());
            panelInstances.put(panel, newPanel);
        }
        return newPanel;
    }

    public static CustomPanelTexture createCustomPaneltexture(JPanel parent, CardLayout panels, MainView context, ITextureFactory textureFactory, PANELS panel){

        if (texturePanelInstances.containsKey(panel)) {

            CustomPanelTexture existingPanel = texturePanelInstances.get(panel);
            if (parent.isAncestorOf(existingPanel)) {
                return existingPanel;
            }
            parent.add(existingPanel, panel.getName());
            return existingPanel;
        }

        CustomPanelTexture newPanel = switch (panel) {
            case LOGIN_AND_REGISTER -> new LoginAndRegisterPanel(parent, panels, context, textureFactory);
            case MENU -> new MenuPanel(parent, panels, context, textureFactory);
            case GRAPHICS -> new GraphicPanel(parent, panels, context, textureFactory);
            case RANKING -> new RankingPanel(parent, panels, context, textureFactory);
            case HOW_PLAY -> new HowPlayPanel(parent, panels, context, textureFactory);
            default -> null;
        };

        if (newPanel != null) {
            parent.add(newPanel, panel.getName());
            texturePanelInstances.put(panel, newPanel);
        }
        return newPanel;
    }

    public static void removePanel(PANELS panel){
        if (panelInstances.containsKey(panel)){
            CustomPanel toRemove = panelInstances.remove(panel);
            if (toRemove != null){
                Container parent = toRemove.getParent();
                if (parent != null){
                    parent.remove(toRemove);
                }
            }
        } else if (texturePanelInstances.containsKey(panel)){
            CustomPanelTexture toRemove = texturePanelInstances.remove(panel);
            if (toRemove != null){
                Container parent = toRemove.getParent();
                if (parent != null){
                    parent.remove(toRemove);
                }
            }
        }
    }

}
