package startserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

class connectionHandler {

    private BufferedReader inFromClient;
    private String clientSentence;
    public static Socket clientSock;

    connectionHandler(Socket clienSocket, JTextArea textAreaServer) {
        this.clientSock = clienSocket;
        try {
            inFromClient = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(startServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true) {
            try {
                clientSentence = inFromClient.readLine();
            } catch (IOException ex) {
                Logger.getLogger(startServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            textAreaServer.setText("Received: " + clientSentence);
            System.out.println("Received: " + clientSentence);
        }
    }

}
