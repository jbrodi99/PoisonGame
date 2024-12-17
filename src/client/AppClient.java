package client;

import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.Util;
import ar.edu.unlu.rmimvc.cliente.Cliente;
import controller.GameController;
import utils.EventMap;
import view.interfaces.IView;
import view.main.MainView;
import view.main.PopupView;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class AppClient {
    private static final String IP = "127.0.0.1";
    private static final int PORT = 8888;

    public static void main(String[] args) throws RemoteException {
        ArrayList<String> ips = Util.getIpDisponibles();
//        String ip = (String) JOptionPane.showInputDialog(
//                null,
//                "Seleccione la IP en la que escuchará peticiones el cliente", "IP del cliente",
//                JOptionPane.QUESTION_MESSAGE,
//                null,
//                ips.toArray(),
//                null
//        );
        String port = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione el puerto en el que escuchará peticiones el cliente", "Puerto del cliente",
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                9999
        );
//        String ipServidor = (String) JOptionPane.showInputDialog(
//                null,
//                "Seleccione la IP en la corre el servidor", "IP del servidor",
//                JOptionPane.QUESTION_MESSAGE,
//                null,
//                null,
//                null
//        );
//        String portServidor = (String) JOptionPane.showInputDialog(
//                null,
//                "Seleccione el puerto en el que corre el servidor", "Puerto del servidor",
//                JOptionPane.QUESTION_MESSAGE,
//                null,
//                null,
//                8888
//        );
        GameController controller = new GameController(new EventMap(), new PopupView());
//        IView view = new MainView(controller);
        Cliente c = null;
        try {
            c = new Cliente(AppClient.IP, Integer.parseInt(port), AppClient.IP, AppClient.PORT);
        } catch (NumberFormatException ex) {
            SwingUtilities.invokeLater(()->JOptionPane.showMessageDialog(null, "Ha ocurrido un error de red, revise la configuracion.!!!",
                    "Error Red", JOptionPane.ERROR_MESSAGE));
//            System.exit(1);
            ex.printStackTrace();
        }
        try {
            c.iniciar(controller);
            IView view = new MainView(controller);
            view.init();
        } catch (RemoteException e) {
            SwingUtilities.invokeLater(()->JOptionPane.showMessageDialog(null, "Ha ocurrido un error de red, revise la configuracion.!!!",
                    "Error Red", JOptionPane.ERROR_MESSAGE));
//            System.exit(1);
            e.printStackTrace();

        } catch (RMIMVCException e) {
            SwingUtilities.invokeLater(()->JOptionPane.showMessageDialog(null, "Ha ocurrido un error !!!",
                    "Error", JOptionPane.ERROR_MESSAGE));
//            System.exit(1);
            e.printStackTrace();
        }
    }
}
