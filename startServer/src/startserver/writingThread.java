package startserver;

import javax.swing.JTextArea;

class writingThread extends Thread {

    private final String clientSentence;
    private final JTextArea textAreaServer;

    writingThread(String clientSentence, JTextArea textAreaServer) {
        this.clientSentence = clientSentence;
        this.textAreaServer = textAreaServer;
    }

    @Override
    public void run() {
        textAreaServer.setText("Received: " + clientSentence);
        System.out.println("Received: " + clientSentence);
    }
}
