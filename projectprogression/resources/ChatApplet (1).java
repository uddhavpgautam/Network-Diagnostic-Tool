
/**
 * ChatApplet class provides applet-based graphical user interface
 */
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.Vector;
import java.util.Enumeration;
import javax.sound.sampled.*;
import java.io.*;
import java.security.*;

/*
 <applet code="ChatApplet" width=800 height=600>
 </applet>
 */
public class ChatApplet extends Applet implements ActionListener {

    public ChatClient client = new ChatClient(this);
    private CapturePlayback cPlayback;
    private PopupMenu popupMenu1 = new PopupMenu();
    private MenuItem menuItem1 = new MenuItem();
    private MenuItem menuItem2 = new MenuItem();
    private Vector allUsers = new Vector();
    private Vector deniedUsers = new Vector();
    private boolean isConnected = false;
    private TextField textField1 = new TextField();
    TextField textField2 = new TextField();
    TextField textField3 = new TextField();
    private Button connectButton = new Button();
    private Button disconnectButton = new Button();
    private Button sendButton = new Button();
    private Button clearButton = new Button();
    private Button voiceButton = new Button();
    private Button captureButton = new Button();
    private Button stopButton = new Button();
    private Button playButton = new Button();
    private Button chatButton = new Button();
    private Label label1 = new Label();
    private Label label2 = new Label();
    private Label label3 = new Label();
    private Label label4 = new Label();
    private Label label5 = new Label();
    public TextArea textArea1 = new TextArea();
    public TextArea textArea2 = new TextArea();
    //public TextArea textArea3      = new TextArea();
    public List list1 = new List(50);

    public void setConnected(boolean aConnected) {
        isConnected = aConnected;
    }

    public boolean getConnected() {
        return isConnected;
    }

    /**
     * Method is called from ChatClient to get reference to
     * ChatApplet.textArea1.
     *
     * @return java.awt.TextArea
     */
    public TextArea getTextArea() {
        return textArea1;
    }

    /**
     * Method is called from ChatClient to append given message to the
     * ChatApplet's TextArea. It also checks whether anUser is in our Ignore
     * list and ignores the message in this case.
     *
     * @param anUser - user that have sent the message
     * @param aText - message to be appened to the applet's TextArea
     */
    public synchronized void addText(String aText) {
        textArea1.append(aText + "\n");

    }

    public synchronized void addRecText(String rText) {
        textArea2.append(rText + "\n");

    }

    public synchronized void addSystemMessage(String aText) {
        textArea1.append(aText + "\n");
    }

    /**
     * Applet life cycle initiliazation.
     */
    public void init() {
        try {
            captureButton.setEnabled(false);
            stopButton.setEnabled(false);
            playButton.setEnabled(false);
            voiceButton.setEnabled(false);
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Component initialization.
     *
     * @throws Exception
     */
    private void jbInit() throws Exception {
        this.setLayout(null);

        // -- Begin buttons section
        sendButton.setLabel("Send");
        sendButton.setBounds(new Rectangle(316, 260, 68, 23));
        sendButton.addActionListener(this);
        this.add(sendButton);

        connectButton.setLabel("Connect");
        connectButton.setBounds(new Rectangle(550, 210, 95, 22));
        connectButton.addActionListener(this);
        this.add(connectButton, null);

        disconnectButton.addActionListener(this);
        disconnectButton.setBounds(new Rectangle(690, 210, 108, 22));
        disconnectButton.setLabel("Disconnect");
        this.add(disconnectButton, null);

        clearButton.setLabel("Clear");
        clearButton.addActionListener(this);
        clearButton.setBounds(new Rectangle(240, 290, 68, 23));
        this.add(clearButton, null);

        voiceButton.setLabel("Voice");
        voiceButton.setBounds(new Rectangle(466, 220, 68, 23));
        voiceButton.addActionListener(this);
        this.add(voiceButton, null);

        captureButton.setLabel("Capture");
        captureButton.setBounds(new Rectangle(466, 190, 68, 23));
        captureButton.addActionListener(this);
        this.add(captureButton, null);

        stopButton.setLabel("Stop");
        stopButton.setBounds(new Rectangle(466, 160, 68, 23));
        stopButton.addActionListener(this);
        this.add(stopButton, null);

        playButton.setLabel("Playback");
        playButton.setBounds(new Rectangle(466, 130, 68, 23));
        playButton.addActionListener(this);
        this.add(playButton, null);

        chatButton.setLabel("Chat");
        chatButton.setBounds(new Rectangle(400, 260, 68, 23));
        chatButton.addActionListener(this);
        this.add(chatButton, null);
        // -- End buttons section

        // -- Begin edit controls
        textField1.setBounds(new Rectangle(10, 260, 303, 23));
        textField1.addActionListener(this);
        textField1.setBackground(Color.lightGray);
        this.add(textField1, null);

        textField2.setBounds(new Rectangle(630, 120, 120, 23));
        textField2.addActionListener(this);
        textField2.setBackground(Color.lightGray);
        this.add(textField2, null);

        textField3.setBounds(new Rectangle(630, 150, 120, 23));
        textField3.addActionListener(this);
        textField3.setBackground(Color.lightGray);
        this.add(textField3, null);

        textArea1.setBounds(new Rectangle(10, 30, 303, 233));
        textArea1.setBackground(Color.lightGray);
        this.add(textArea1, null);

        textArea2.setBounds(new Rectangle(10, 350, 303, 233));
        textArea2.setBackground(Color.lightGray);
        this.add(textArea2, null);

        /* textArea3.setBounds(new Rectangle(316, 30, 140, 230));
         textArea3.setBackground(Color.lightGray);
         this.add(textArea3, null);*/
        list1.setBounds(new Rectangle(316, 30, 140, 230));
        list1.setBackground(Color.lightGray);
        this.add(list1, null);

        // -- End edit controls
        label1.setText("Sending Message");
        label1.setBackground(Color.cyan);
        label1.setBounds(new Rectangle(10, 10, 250, 23));
        this.add(label1, null);

        label2.setText("Receiving Message");
        label2.setBackground(Color.cyan);
        label2.setBounds(new Rectangle(10, 330, 250, 23));
        this.add(label2, null);

        label5.setText("Users");
        label5.setBackground(Color.cyan);
        label5.setBounds(new Rectangle(316, 10, 150, 23));
        this.add(label5, null);

        label3.setText("User Name");
        label3.setBackground(Color.cyan);
        label3.setBounds(new Rectangle(550, 120, 120, 23));
        this.add(label3, null);

        label4.setText("Server Name");
        label4.setBackground(Color.cyan);
        label4.setBounds(new Rectangle(550, 150, 120, 23));
        this.add(label4, null);
        // -- Labels 

        this.setBackground(Color.cyan);

    }

    /**
     * Method sends aMessage to server through ChatClient.
     *
     * @param aMessage
     */
    public void sendMessage(String aMessage) {
        client.getOutput().println(aMessage);
        client.getOutput().flush();
    }

    /* public void sendUserName(String userName)
     {
     client.getOutput().println(socket);
     client.getOutput().flush();
     }*/
    /**
     * Method handles ActionEvent event, registered by "this" Action listener.
     *
     * @param ae - ActionEvent which we used to indicate "sender".
     */
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource().equals(connectButton)) {
            // catch ActionEvents coming connect button from textField1
            if (!isConnected) {
                addSystemMessage("Connecting...");
                isConnected = client.connect();
                //if(isConnected)
                {
                    String mesg = textField2.getText();
                    sendMessage(mesg);
                    //sendSocket(client.m_Socket);
                    voiceButton.setEnabled(true);

                }
            } else {
                addSystemMessage("Already connected.");
            }
        } else if (ae.getSource().equals(textField1)) {
            // catch ActionEvents coming from textField1
            if (list1.getSelectedItem() != null) {
                sendMessage(list1.getSelectedItem());
                sendButtonPressed();
            } else {
                textArea1.append("Please select user from users list \n");
            }
        } else if (ae.getSource().equals(sendButton)) {
            // catch ActionEvents coming send button from textField1
            if (list1.getSelectedItem() != null) {
                sendMessage(list1.getSelectedItem());
                sendButtonPressed();
            } else {
                textArea1.append("Please select user from users list \n");
            }
        } else if (ae.getSource().equals(clearButton)) {
            // catch ActionEvents comming from clear button
            textArea1.setText("");
        } else if (ae.getSource().equals(disconnectButton)) {
            // catch ActionEvents comming from disconnect button
            client.disconnect();
            voiceButton.setEnabled(false);
            captureButton.setEnabled(false);
            stopButton.setEnabled(false);
            playButton.setEnabled(false);
        } else if (ae.getSource().equals(voiceButton)) {
            client.isChatMesg = false;
            client.voiceBtnClicked = true;
            captureButton.setEnabled(true);
            playButton.setEnabled(true);
            cPlayback = new CapturePlayback(client);
            //cPlayback.captureAudio();
            //cPlayback.playAudio();
        } else if (ae.getSource().equals(captureButton)) {
            //captureButton.setEnabled(false);
            stopButton.setEnabled(true);
            cPlayback.captureAudio();

        } else if (ae.getSource().equals(stopButton)) {
            captureButton.setEnabled(true);
            //	stopButton.setEnabled(false);
            playButton.setEnabled(true);
            cPlayback.stopCapture = true;
            cPlayback.sendVoiceOutput();

        } else if (ae.getSource().equals(playButton)) {
            //	playButton.setEnabled(false);
            cPlayback.playAudio();
        } else if (ae.getSource().equals(chatButton)) {
            client.isChatMesg = true;
        }
    }

    private void sendButtonPressed() {
        if (!isConnected) {
            textArea1.append("Please connect first.\n");
            return;
        }
        String text = textField1.getText();
        if (text.equals("")) {
            return;
        }
        textArea1.append(text + "\n");
        sendMessage(textField2.getText() + ":" + text);
        textField1.setText("");
    }
}
