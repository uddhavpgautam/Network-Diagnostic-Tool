package javaapplication6;

import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class UtilitiesFunctions {

    private JButton buttontoshow;
    private JTextArea messageshowingplace;
    private JTextArea messagewritingplace;
    private final PrintWriter out = null;
    private byte[] addr;
    DefaultTableModel model;
    private Thread t[];
    private String valueforsubnet;
    private String[] subnetvalue;
    private String host;
    private String subnetvalue1;
    private int k;
    private int noofthreads;
    private int timeout;
    private String writtenText;
    private String line;
    private Runnable r1;
    private Thread t1;

    public void settextnullonmouseclick(MouseEvent evt) {
        javax.swing.JTextField obj = (javax.swing.JTextField) evt.getSource();
        obj.setText("");
    }

    public boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            System.out.println("This is integer checked");
            return true;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Not integer value");
            return false;
        }
    }

    public boolean isOutOfRange(String value) {
        int val = Integer.parseInt(value);
        System.out.println(val);
        if ((val < 0) || (val > 65536)) {
            JOptionPane.showMessageDialog(null, "Input Integer must be in between 0 to 65536");
            return false;
        } else {
            System.out.println("This is not out of range");
            return true;
        }
    }

    void repaintandvalidate(JButton jButton1) {
        java.awt.Container objj = jButton1.getParent();
        objj.revalidate();//this should revalidate and repaint the container of jButton1 button
        objj.repaint();
    }

    void showButton(JButton jButton1) {
        buttontoshow = jButton1;
        buttontoshow.show();
    }

    void enableButtons(JButton jButton3, JButton jButton4) {
        jButton3.setEnabled(true);
        jButton4.setEnabled(true);
    }

    void stopFunction(Socket soc) {
        try {
//            closes corresponding socket 
//            if it is called from serverprogram then it closes serversocket
//            if it is called from clientprogram then it closes socket
            soc.close();

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Corresponding socket is not being closed!");
        }
    }

    void hideButton(JButton jButton5) {
        jButton5.hide();
    }

    void disableButton(JButton jButton3) {
        jButton3.setEnabled(false);
    }

//          start client listening on socket
    void SendforClient(JTextArea jTextArea1, JTextArea jTextArea2) {
    }

    void SendforServer(JTextArea jTextArea1, JTextArea jTextArea2) {
    }

    void listenClientSocket(Socket clientsock, String host, JTextArea ClientmsgShowingPlace, JTextArea ClientmsgWritingPlace) {

    }

    void listenServerSocket(JTextArea jTextArea1, JTextArea jTextArea2) {
    }

    void showNetworkDeviceList() throws UnknownHostException, SocketException, IOException, InterruptedException {
        javaapplication6.NetworkDiscovery.startButtonNetworkWatcher.setEnabled(false);
        javaapplication6.NetworkDiscovery.stopButtonNetworkWatcher.setEnabled(true);
        model = (DefaultTableModel) javaapplication6.NetworkDiscovery.tableNetworkWatcher.getModel();
        valueforsubnet = JOptionPane.showInputDialog(null, "Input IP of your Network Adapter");
        subnetvalue = valueforsubnet.split("\\.");
        subnetvalue1 = subnetvalue[0] + "." + subnetvalue[1] + "." + subnetvalue[2];
        noofthreads = (254 / 5) + 1;
        Runnable r = null;//[] = new Runnable[noofthreads+1];
        timeout = 1000;
        t = new Thread[noofthreads + 1];
        int k = 1;
        for (int i = 1; i <= noofthreads; i += 5) {
            threadObject tObj = new threadObject(model, subnetvalue1, i, i + 5, timeout);
            tObj.start();
        }
    }

    void clearNetworkDeviceList() {
        model = (DefaultTableModel) javaapplication6.NetworkDiscovery.tableNetworkWatcher.getModel();
        model.getDataVector().removeAllElements();

//        another technique to remove all rows
//        DefaultTableModel dm = (DefaultTableModel) getModel();
//        int rowCount = dm.getRowCount();
//        for (int i = 0; i < rowCount; i++) {
//            dm.removeRow(i);
//        }
        javaapplication6.NetworkDiscovery.tableNetworkWatcher.repaint();
        javaapplication6.NetworkDiscovery.tableNetworkWatcher.revalidate();
        javaapplication6.NetworkDiscovery.startButtonNetworkWatcher.setEnabled(true);
        javaapplication6.NetworkDiscovery.stopButtonNetworkWatcher.setEnabled(false);
    }

    void enableButton(JButton sendButtonbodyPanellChat) {
        sendButtonbodyPanellChat.setEnabled(true);
        repaintandvalidate(sendButtonbodyPanellChat);
    }

    void listenFromClient(Socket clientsock, String host, final JTextArea ClientmsgShowingPlace, JTextArea ClientmsgWritingPlace) throws IOException {
        writtenText = ClientmsgWritingPlace.getText();
        final DataInputStream in = new DataInputStream(clientsock.getInputStream());
        PrintStream out = new PrintStream(clientsock.getOutputStream());
        r1 = new Runnable() {

            @Override
            public void run() {
                try {
                    while ((line = in.readLine()) != null) {
                        //        write writtenText to socket
                        ClientmsgShowingPlace.append("From Server :: " + line);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(UtilitiesFunctions.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        t1 = new Thread(r1);
        t1.start();

    }

    void sendFromClient() throws IOException {
//        sendFromClient means write msg to socket 
        Socket clientsock;
        String host;
        JTextArea ClientmsgShowingPlace;
        JTextArea ClientmsgWritingPlace;
        clientsock = javaapplication6.clientStarter.clientsock;
        host = javaapplication6.Chat.host;
        ClientmsgShowingPlace = javaapplication6.Chat.msgshowingAreabodyPanellChat;
        ClientmsgWritingPlace = javaapplication6.Chat.msgwritingAreabodyPanellChat;
        writtenText = ClientmsgWritingPlace.getText();
        DataInputStream in = new DataInputStream(clientsock.getInputStream());
        PrintStream out = new PrintStream(clientsock.getOutputStream());
//         write writtenText to socket 
//        while ((line = in.readLine()) != null) {
//            out.println(line);
//        }
//        also write msg to own msgshowing text area
        ClientmsgShowingPlace.setText("Own PC :: " + writtenText + "\n");
    }
}
