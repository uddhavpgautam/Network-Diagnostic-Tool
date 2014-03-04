package javaapplication6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

class SocketAcceptandListener extends Thread {

    private final Socket clientSocket;
    private final JTextArea msgshowingAreabodyPanellChat;
    private BufferedReader br;
    private String clientSentence;
    private boolean  flag = true;

    SocketAcceptandListener(Socket clientSocket, JTextArea msgshowingAreabodyPanellChat) {
        this.clientSocket = clientSocket;
        this.msgshowingAreabodyPanellChat = msgshowingAreabodyPanellChat;
    }

    @Override
    public void run() {
        while (true) {

            try {
                br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException ex) {
                Logger.getLogger(SocketAcceptandListener.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            while (flag) {
                try {
                    clientSentence = br.readLine();
                    if(clientSentence == null) {
                        flag = false;
                    }
                } catch (IOException ex) {
                    Logger.getLogger(SocketAcceptandListener.class.getName()).log(Level.SEVERE, null, ex);
                }
                msgshowingAreabodyPanellChat.append("Received From Client: " + clientSentence + "\n");
                
            }
            
        }
        

    }

}
