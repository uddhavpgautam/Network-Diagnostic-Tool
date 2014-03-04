
/**
 * ChatClient class handles the communication with the chat server.
 */
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.Hashtable;

public class ChatServer {

    final int portNumber = 1501;
    private ServerSocket serverSocket = null;
    public Socket theConnection = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private BufferedReader lin = null;
    private PrintWriter lout = null;
    private BufferedReader din = null;
    private PrintWriter dout = null;
    private ServerApplet m_Applet;
    volatile boolean isServerChatMesg = true;
    String strDestSocket = null;
    Socket destSocket = null;
    Hashtable inout = new Hashtable();

    /**
     * Constructor initialize ChatClient and sets ChatApplet refferences.
     */
    public ChatServer(ServerApplet anApplet) {
        this.m_Applet = anApplet;
    }

    public ChatServer() {
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
    public boolean startServer() {
        try {
            serverSocket = new ServerSocket(1501);
            m_Applet.addSystemMessage("Server started");
            m_Applet.voiceButton.setEnabled(true);
        } catch (IOException ioe) {
            m_Applet.addSystemMessage("Couldn't open server socket");
            return false;
        }
        return true;
    }

    public void Listen() {
        //Hashtable inout = new Hashtable();
        String users[] = new String[50];
        String allUsers = null;
        String duname = null;
        boolean flag = false;
        Socket l_socket = null;
        Socket d_socket = null;
        int i = 0;
        while (true) {
            try {
                flag = false;
                theConnection = serverSocket.accept();
                //m_Applet.addSystemMessage("new client comes");
                in = new BufferedReader(new InputStreamReader(theConnection.getInputStream()));
                out = new PrintWriter(new OutputStreamWriter(theConnection.getOutputStream()));
                String userName = in.readLine();
                inout.put(userName, theConnection);
                //String userName = mesg.substring(0,mesg.indexOf(":"));
                //strDestSocket = mesg.substring(mesg.indexOf(":") + 1);
                m_Applet.addSystemMessage(userName);
                //if(users.length>0)
                {
                    for (int k = 0; k < users.length; k++) {
                        if (users[k] != null) {
                            if (k == 0) {
                                allUsers = "Users-" + users[k];
                            } else {
                                allUsers = allUsers + ";" + users[k];
                            }
                        }

                    }
                }
                users[i] = userName;
                if (allUsers != null) {
                    //m_Applet.addSystemMessage("Users" + allUsers);
                    out.println(allUsers);
                    out.flush();
                }
                if (i > 0) {
                    while (!flag) {
                        duname = in.readLine();
                        if (duname != null) {
                            flag = true;
                        }
                    }
                }
                i++;
                //m_Applet.addSystemMessage(duname);
                //for (int j=1; j<=inout.size(); j++)
                {
                    l_socket = (Socket) inout.get(userName);
                }

                //for (int j=1; j<=inout.size(); j++)
                if (duname != null) {
                    d_socket = (Socket) inout.get(duname);
                }

                if (l_socket != null) {
                    lin = new BufferedReader(new InputStreamReader(l_socket.getInputStream()));
                    lout = new PrintWriter(new OutputStreamWriter(l_socket.getOutputStream()));
                }

                if (d_socket != null) {
                    din = new BufferedReader(new InputStreamReader(d_socket.getInputStream()));
                    dout = new PrintWriter(new OutputStreamWriter(d_socket.getOutputStream()));

                    Listener listener = new Listener(dout, lin);
                    listener.setDaemon(true);
                    listener.start();
                    Listener listener1 = new Listener(lout, din);
                    listener1.setDaemon(true);
                    listener1.start();
                }
                //din = new BufferedReader(new InputStreamReader(destSocket.getInputStream()));
                //dout = new PrintWriter(new OutputStreamWriter(destSocket.getOutputStream()));
            } catch (IOException ioe) {
                m_Applet.addSystemMessage("Error in accepting socket connection");
                //	return false;
            }

            /*Listener listener = new Listener(out, in);
             listener.setDaemon(true);
             listener.start();*/
            //	return true;
        }
    }

    public void closeServer() {
        if (!m_Applet.getStarted()) {
            m_Applet.addSystemMessage("Can not close. Not strated.");
            return;
        }
        m_Applet.setStarted(false);
        try {
            serverSocket.close();
            theConnection.close();
        } catch (IOException ioe) {
        }
        m_Applet.addSystemMessage("Server Closed");
    }

    /**
     * Listener class - thread is used for receiving data that comes from the
     * server and then "forward" it to ChatApplet.
     */
    class Listener extends Thread {

        private BufferedReader mIn;
        private PrintWriter mOut;

        /**
         * Constructor initiliaze InputStream, and ChatApplet reference
         *
         * @param aCA - ChatApplet reference
         * @param aIn - InputStream from server connection
         */
        public Listener(PrintWriter aOut, BufferedReader aIn) {
            mOut = aOut;
            mIn = aIn;
        }

        public void run() {
            try {
                while (isServerChatMesg) //!isInterrupted()
                {
                    String message = mIn.readLine();
                    if (message != null) {
                        /*if (message.indexOf(":") != -1)
                         {
                         m_Applet.addSystemMessage(message.substring(0,5));
                         //mCA.addRecText(message.substring(0,5).equals("Users"));
                         if (message.substring(0,5).equals("DUser"))
                         {	//mCA.addRecText("in users");
                         String duser = message.substring(6);
                         m_Applet.addSystemMessage(duser);
                         if (duser != null)
                         {
                         addDestUser(duser);
                         }
									
                         }
                         }
                         else*/
                        {
                            //mCA.addRecText (message);
                            sendMesg(message);
                        }
                    }
                }
            } catch (Exception ioe) {
                if (m_Applet.getStarted()) {
                    m_Applet.addSystemMessage("Communication error.");
                }
            }
            m_Applet.setStarted(false);
        }

        private void sendMesg(String mesg) {
            mOut.println(mesg);
            mOut.flush();
        }

        /*  private void addDestUser(String duname)
         {
         Socket d_socket = null;
         try
         {
         if (duname != null)
         {
         d_socket = (Socket)inout.get(duname);
         }

			
         if (d_socket != null)
         {
         din = new BufferedReader(new InputStreamReader(d_socket.getInputStream()));
         dout = new PrintWriter(new OutputStreamWriter(d_socket.getOutputStream()));	
         }
         m_Applet.addSystemMessage("333");
         Listener listener = new Listener(dout, lin);
         listener.setDaemon(true);
         listener.start();
         m_Applet.addSystemMessage("444");
         Listener listener1 = new Listener(lout, din);
         listener1.setDaemon(true);
         listener1.start();
         } 
         catch (IOException ioe) 
         {
         m_Applet.addSystemMessage("Error in accepting socket connection");
         }
         }*/
    }// end Listener class
}
