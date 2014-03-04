package javaapplication6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientSocketWithListener extends Thread {

    private BufferedReader in;
    private PrintWriter out;
    private String msg;

    ClientSocketWithListener(Socket clientsock) {
        try {
            in = new BufferedReader(new InputStreamReader(clientsock.getInputStream()));
            out = new PrintWriter(clientsock.getOutputStream(), true);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                while ((msg = in.readLine()) != null) {
                    Chat.msgshowingAreabodyPanellChat.append("From Server :: " + msg);
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

}
