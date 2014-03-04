
/**
 * ChatClient class handles the communication with the chat server.
 */
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.Hashtable;

public class ChatClient {

    public static final int SERVER_PORT = 1501;
    public Socket m_Socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private ChatApplet m_Applet;
    boolean voiceBtnClicked = false;
    volatile boolean isChatMesg = true;

    /**
     * Constructor initialize ChatClient and sets ChatApplet refferences.
     */
    public ChatClient(ChatApplet anApplet) {
        this.m_Applet = anApplet;
    }

    public ChatClient() {
    }

    /**
     * Method is called from ChatApplet.
     *
     * @return PrintWriter reference to server connection.
     */
    public PrintWriter getOutput() {
        return out;
    }

    /**
     * Method is called from ChatApplet.
     *
     * @return BufferedReader reference to server connection.
     */
    public BufferedReader getInput() {
        return in;
    }

    /**
     * Method is called from ChatApplet to establish connection to Server.
     */
    public boolean connect() {
        boolean successfull = true;
        String serverHost = m_Applet.textField3.getText();
        if (serverHost.length() == 0) {
            m_Applet.addSystemMessage("Please enter Server name");
            return false;
        }
        try {
            m_Socket = new Socket(serverHost, SERVER_PORT);
            in = new BufferedReader(new InputStreamReader(m_Socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(m_Socket.getOutputStream()));
            m_Applet.addSystemMessage("Connected to server " + serverHost + ":" + SERVER_PORT);
            //  String allUsers = in.readLine();
            //  m_Applet.addSystemMessage(allUsers);
        } catch (SecurityException se) {
            m_Applet.addSystemMessage("Security policy does not allow " + "connection to " + serverHost + ":" + SERVER_PORT);
            successfull = false;
        } catch (IOException e) {
            m_Applet.addSystemMessage("Can not establish connection to " + serverHost + ":" + SERVER_PORT);
            successfull = false;
        }

        // Create and start Listener thread
        Listener listener = new Listener(m_Applet, in);
        listener.setDaemon(true);
        listener.start();

        return successfull;
    }

    public void disconnect() {
        if (!m_Applet.getConnected()) {
            m_Applet.addSystemMessage("Can not disconnect. Not connected.");
            return;
        }
        m_Applet.setConnected(false);
        try {
            m_Socket.close();
        } catch (IOException ioe) {
        }
        m_Applet.addSystemMessage("Disconnected.");
    }

    /**
     * Listener class - thread is used for receiving data that comes from the
     * server and then "forward" it to ChatApplet.
     */
    class Listener extends Thread {

        private BufferedReader mIn;
        private ChatApplet mCA;

        /**
         * Constructor initiliaze InputStream, and ChatApplet reference
         *
         * @param aCA - ChatApplet reference
         * @param aIn - InputStream from server connection
         */
        public Listener(ChatApplet aCA, BufferedReader aIn) {
            mCA = aCA;
            mIn = aIn;
        }

        public void run() {
            try {
                while (isChatMesg) //!isInterrupted()
                {
                    String message = mIn.readLine();
                    if (message != null) {  // mCA.addRecText(message);
                        if (message.indexOf("-") != -1) {
                            //mCA.addRecText(message.substring(0,5));
                            //mCA.addRecText(message.substring(0,5).equals("Users"));
                            if (message.substring(0, 5).equals("Users")) {	//mCA.addRecText("in users");
                                String users = message.substring(6);
                                //mCA.addRecText (users);
                                if (users != null) {
                                    addUsers(users);
                                }

                            }
                        } else {
                            if (message.indexOf(":") != -1) {
                                boolean flag = true;
                                int k = 0;
                                String userName = message.substring(0, message.indexOf(":"));
                                //mCA.addRecText(userName);
                                k = mCA.list1.getItemCount();
                                if (k > 0) {
                                    mCA.list1.removeAll();
                                }
                                //mCA.addRecText(userName);
                                mCA.list1.add(userName);
                                mCA.list1.select(0);
                                /*
                                 for (int i=0; i<k ; i++)
                                 {  mCA.addRecText("for" + i + mCA.list1.getItem(i));
                                 if (mCA.list1.getItem(i) != userName)
                                 {
                                 flag = false;
                                 }
                                 }
                                 if (!flag)
                                 {
                                 mCA.addRecText(userName);
                                 mCA.list1.add(userName);
                                 mCA.list1.select(k+1);
                                 }*/

                            }
                            //String userName = mesg.substring(0,mesg.indexOf(":"));
                            if (!message.equals(mCA.textField2.getText())) {
                                mCA.addRecText(message);
                            }
                        }
                    }
                }
            } catch (Exception ioe) {
                if (m_Applet.getConnected()) {
                    m_Applet.addSystemMessage("Communication error.");
                }
            }
            m_Applet.setConnected(false);
        }

        private void addUsers(String users) {
            if (users.indexOf(";") != -1) {
                mCA.list1.add(users.substring(0, users.indexOf(";")));
                addUsers(users.substring(users.indexOf(";") + 1));
            } else {
                mCA.list1.add(users);
            }
        }
    }
}
