package startserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

class SocketAcceptandListener extends Thread {

    public static Socket clienSocket;
    private ServerSocket sc;
    private BufferedReader br;
    private String clientSentence;
    private JTextArea textAreaServer;
    private boolean flag = true;

    SocketAcceptandListener(ServerSocket serverSock, BufferedReader inFromClient, String clientSentence, JTextArea textAreaServer) {
        this.sc = serverSock;
        this.br = inFromClient;
        this.clientSentence = clientSentence;
        this.textAreaServer = textAreaServer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                clienSocket = sc.accept();
            } catch (IOException ex) {
                Logger.getLogger(startServer.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                br = new BufferedReader(new InputStreamReader(clienSocket.getInputStream()));
            } catch (IOException ex) {
                Logger.getLogger(startServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            while (flag) {
                try {
                    clientSentence = br.readLine();
                    if(clientSentence == null) {
                        flag = false;
                    }
                } catch (IOException ex) {
                    Logger.getLogger(startServer.class.getName()).log(Level.SEVERE, null, ex);
                }
                textAreaServer.append("Received From Client: " + clientSentence + "\n");
//                System.out.println("Received: " + clientSentence);
            }
        }
    }

}
