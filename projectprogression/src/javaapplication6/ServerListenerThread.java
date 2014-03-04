package javaapplication6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JTextArea;

class ServerListenerThread implements Runnable {

    public static Socket socket;
    public static BufferedReader in = null;
    private final JTextArea textArea1;
    private final JTextArea textArea2;
    private String line;

    public ServerListenerThread(Socket soc, JTextArea jTextArea1, JTextArea jTextArea2) {
        this.socket = soc;
        this.textArea1 = jTextArea1;
        this.textArea2 = jTextArea2;
    }

    @Override
    public void run() {

        while (true) {
            try {
                try {
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                } catch (IOException e) {
                    System.out.println("in or out failed");
                    System.exit(-1);
                }
                //listen from own socket
                line = in.readLine();
                textArea1.append("Client ===>   " + line + "\n");
            } catch (IOException ex) {
                System.out.println("Client socket listening error in.readline");
                System.exit(-1);
            }

        }
    }
}
