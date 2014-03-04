package javaapplication6;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JTextArea;

public class clientStarter {

    UtilitiesFunctions utl = new UtilitiesFunctions();
    private InetAddress inetaddress;
    public static Socket clientsock;
    private final int portint;

    clientStarter(String host, String port, JTextArea ClientmsgShowingPlace, JTextArea ClientmsgWritingPlace) throws IOException {
//        try {
//            inetaddress = InetAddress.getByName(host);
//            System.out.println("done inetaddress getbyname");
//        } catch (UnknownHostException ex) {
//            System.out.println("can't done inetaddress getbyname");
//        }
        portint = Integer.parseInt(port);
        try {
            clientsock = new Socket(host, portint);
        } catch (UnknownHostException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        utl.showButton(javaapplication6.Chat.stopButtonheaderPanelChat);
        utl.repaintandvalidate(javaapplication6.Chat.stopButtonheaderPanelChat);
        utl.hideButton(javaapplication6.Chat.startButtonheaderPanelChat);
        utl.repaintandvalidate(javaapplication6.Chat.startButtonheaderPanelChat);
        utl.enableButton(javaapplication6.Chat.sendButtonbodyPanellChat);
//        utl.listenFromClient(clientsock, host, ClientmsgShowingPlace, ClientmsgWritingPlace);

    }
}
