package server;

import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.servidor.Servidor;
import model.factorys.GameMatchFactory;
import model.factorys.IGameMatchFactory;
import model.logic.GameModel;
import model.interfaces.IGameModel;
import javax.swing.*;
import java.rmi.RemoteException;

public class AppServer {
    private static final IGameMatchFactory gameMatchFactory = new GameMatchFactory();
    private static final int PORT = 8888;
    private static final String IP = "127.0.0.1";
    public static void main(String[] args) throws RemoteException {
//        ArrayList<String> ips = Util.getIpDisponibles();
//        String ip = (String) JOptionPane.showInputDialog(
//                null,
//                "Seleccione la IP en la que escuchará peticiones el servidor", "IP del servidor",
//                JOptionPane.QUESTION_MESSAGE,
//                null,
//                ips.toArray(),
//                null
//        );
//        String port = (String) JOptionPane.showInputDialog(
//                null,
//                "Seleccione el puerto en el que escuchará peticiones el servidor", "Puerto del servidor",
//                JOptionPane.QUESTION_MESSAGE,
//                null,
//                null,
//                8888
//        );
        //String nombre =  JOptionPane.showInputDialog(null, "Ingrese el nombre de la partida a recuperar (deje vacio para una nueva)", "Nombre usuario", JOptionPane.QUESTION_MESSAGE);

        IGameModel gameModel = GameModel.getInstance(gameMatchFactory);
        Servidor servidor = new Servidor(AppServer.IP, AppServer.PORT);
//        if (gameModel == null) {
//            gameModel = GameModel.getInstance(gameMatchFactory);
//            //SwingUtilities.invokeLater(()->JOptionPane.showMessageDialog(null, "No se encontro partida guardada, comenzara una nueva.", "Nueva partida", JOptionPane.INFORMATION_MESSAGE));
//            SwingUtilities.invokeLater(()->JOptionPane.showMessageDialog(null, "Comenzara una nueva Partida.", "Nueva partida", JOptionPane.INFORMATION_MESSAGE));
//        } else
//            SwingUtilities.invokeLater(()->JOptionPane.showMessageDialog(null, "Se encontro una partida guardada, se retomara desde ese punto", "Cargar partida", JOptionPane.INFORMATION_MESSAGE));

        try {
            servidor.iniciar(gameModel);
            SwingUtilities.invokeLater(()->JOptionPane.showMessageDialog(null, "Servidor levantado.", "Status", JOptionPane.INFORMATION_MESSAGE));
        } catch (RemoteException e) {
            SwingUtilities.invokeLater(()->JOptionPane.showMessageDialog(null, "Ha ocurrido un error de red !!!",
                    "Error Red", JOptionPane.ERROR_MESSAGE));
            new javax.swing.Timer(7000, evt -> System.exit(1)).start();
            e.printStackTrace();
        } catch (RMIMVCException e) {
            SwingUtilities.invokeLater(()->JOptionPane.showMessageDialog(null, "Ha ocurrido un error !!!",
                    "Error", JOptionPane.ERROR_MESSAGE));
            new javax.swing.Timer(7000, evt -> System.exit(1)).start();
            e.printStackTrace();
        }
    }
}
