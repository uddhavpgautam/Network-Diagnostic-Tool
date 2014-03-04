
/**
 * ChatApplet class provides applet-based graphical user interface
 *
 */
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;

/*
 <applet code="ServerApplet" width=800 height=600>
 </applet>
 */
public class ServerApplet extends Applet implements ActionListener {

    private boolean isStarted = false;
    public ChatServer server = new ChatServer(this);
    private ServerCapturePlayback sPlayback;
    private TextField textField1 = new TextField();
    private Button startButton = new Button();
    private Button closeButton = new Button();
    private Button sendButton = new Button();
    private Button clearButton = new Button();
    Button voiceButton = new Button();
    private Button chatButton = new Button();
    private Label label1 = new Label();
    private Label label2 = new Label();
    public TextArea textArea1 = new TextArea();
    public TextArea textArea2 = new TextArea();
    private Button captureButton = new Button();
    private Button stopButton = new Button();
    private Button playButton = new Button();

    public void setStarted(boolean aStarted) {
        isStarted = aStarted;
    }

    public boolean getStarted() {
        return isStarted;
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
        sendButton.setBounds(new Rectangle(316, 265, 68, 23));
        sendButton.addActionListener(this);
        this.add(sendButton);

        startButton.setLabel("Start Server");
        startButton.setBounds(new Rectangle(10, 290, 95, 22));
        startButton.addActionListener(this);
        this.add(startButton, null);

        closeButton.addActionListener(this);
        closeButton.setBounds(new Rectangle(120, 290, 108, 22));
        closeButton.setLabel("Close");
        this.add(closeButton, null);

        clearButton.setLabel("Clear");
        clearButton.addActionListener(this);
        clearButton.setBounds(new Rectangle(240, 290, 68, 23));
        this.add(clearButton, null);

        voiceButton.setLabel("Voice");
        voiceButton.setBounds(new Rectangle(316, 220, 68, 23));
        voiceButton.addActionListener(this);
        this.add(voiceButton, null);

        chatButton.setLabel("Chat");
        chatButton.setBounds(new Rectangle(316, 180, 68, 23));
        chatButton.addActionListener(this);
        this.add(chatButton, null);

        captureButton.setLabel("Capture");
        captureButton.setBounds(new Rectangle(450, 190, 68, 23));
        captureButton.addActionListener(this);
        this.add(captureButton, null);

        stopButton.setLabel("Stop");
        stopButton.setBounds(new Rectangle(450, 160, 68, 23));
        stopButton.addActionListener(this);
        this.add(stopButton, null);

        playButton.setLabel("Playback");
        playButton.setBounds(new Rectangle(450, 130, 68, 23));
        playButton.addActionListener(this);
        this.add(playButton, null);

        // -- End buttons section
        // -- Begin edit controls
        textField1.setBounds(new Rectangle(10, 265, 303, 23));
        textField1.addActionListener(this);
        textField1.setBackground(Color.lightGray);
        this.add(textField1, null);

        textArea1.setBounds(new Rectangle(10, 30, 303, 233));
        textArea1.setBackground(Color.lightGray);
        this.add(textArea1, null);

        textArea2.setBounds(new Rectangle(10, 350, 303, 233));
        textArea2.setBackground(Color.lightGray);
        this.add(textArea2, null);
        // -- End edit controls

        label1.setText("Sending Message");
        label1.setBackground(Color.cyan);
        label1.setBounds(new Rectangle(10, 10, 250, 23));
        this.add(label1, null);

        label2.setText("Receiving Message");
        label2.setBackground(Color.cyan);
        label2.setBounds(new Rectangle(10, 330, 250, 23));
        this.add(label2, null);
        // -- Labels 

        this.setBackground(Color.cyan);
    }

    /**
     * Method sends aMessage to server through ChatClient.
     *
     * @param aMessage
     */
    public void sendMessage(String aMessage) {
        server.getOutput().println(aMessage);
        server.getOutput().flush();

    }

    /**
     * Method handles ActionEvent event, registered by "this" Action listener.
     *
     * @param ae - ActionEvent which we used to indicate "sender".
     */
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource().equals(textField1)) {
            // catch ActionEvents coming from textField1
            sendButtonPressed();
        } else if (ae.getSource().equals(startButton)) {
            // catch ActionEvents coming connect button from textField1
            if (!isStarted) {
                addSystemMessage("Starting.....");
                isStarted = server.startServer();
                server.Listen();
                /*if(server.isServerStarted)
                 {
                 voiceButton.setEnabled(true);
                 }*/
            } else {
                addSystemMessage("Already started.");
            }

        } else if (ae.getSource().equals(sendButton)) {
            // catch ActionEvents coming send button from textField1
            sendButtonPressed();

        } else if (ae.getSource().equals(clearButton)) {
            // catch ActionEvents comming from clear button
            textArea1.setText("");
        } else if (ae.getSource().equals(closeButton)) {
            // catch ActionEvents comming from disconnect button
            server.closeServer();
            //  server.isServerStarted = false;
            voiceButton.setEnabled(false);
        } else if (ae.getSource().equals(voiceButton)) {
            // catch ActionEvents comming from voiceButton
            server.isServerChatMesg = false;
            sPlayback = new ServerCapturePlayback(server);
            //	sPlayback.captureAudio();
            //	sPlayback.playAudio();
        } else if (ae.getSource().equals(captureButton)) {
            sPlayback.captureAudio();
        } else if (ae.getSource().equals(playButton)) {
            sPlayback.playAudio();
        } else if (ae.getSource().equals(stopButton)) {
            sPlayback.stopCapture = true;
            sPlayback.sendVoiceOutput();

        } else if (ae.getSource().equals(chatButton)) {
            server.isServerChatMesg = true;
        }
    }

    private void sendButtonPressed() {
        if (!isStarted) {
            textArea1.append("Please start server first.\n");
            return;
        }
        String text = textField1.getText();
        if (text.equals("")) {
            return;
        }

        sendMessage(text);
        textArea1.append(text + "\n");
        textField1.setText("");
    }
}
