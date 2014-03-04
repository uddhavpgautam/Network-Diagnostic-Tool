package startserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class startServer extends javax.swing.JFrame {

    UtilitiesFunctions utl = new UtilitiesFunctions();
    private String portval;
    private int portint;
    private ServerSocket serverSock;
    private BufferedReader inFromClient;
    private DataOutputStream outToClient;
    private String clientSentence;
    private String sentence;
    private Thread t1;
    private Runnable r1;
    private Socket clienSocket;

    public startServer() {
        initComponents();
        startButtonstartServer.setEnabled(true);
        setTitle("Server");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        startButtonstartServer = new javax.swing.JButton();
        portTextFieldstartServer = new javax.swing.JTextField();
        serverStartNotificationstartServer = new javax.swing.JLabel();
        scrollPaneServer = new javax.swing.JScrollPane();
        textAreaServer = new javax.swing.JTextArea();
        sendButtonServer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        startButtonstartServer.setText("Start");
        startButtonstartServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonstartServerActionPerformed(evt);
            }
        });

        portTextFieldstartServer.setText("12345");
        portTextFieldstartServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                portTextFieldstartServerActionPerformed(evt);
            }
        });
        portTextFieldstartServer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                portTextFieldstartServerMouseClicked(evt);
            }
        });

        serverStartNotificationstartServer.setText("Server start Notification");

        textAreaServer.setColumns(20);
        textAreaServer.setRows(5);
        textAreaServer.setText("Hello Clients?\n");
        scrollPaneServer.setViewportView(textAreaServer);

        sendButtonServer.setText("Send");
        sendButtonServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonServerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(serverStartNotificationstartServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(portTextFieldstartServer, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(startButtonstartServer)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(scrollPaneServer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(sendButtonServer)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startButtonstartServer)
                    .addComponent(portTextFieldstartServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(serverStartNotificationstartServer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollPaneServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sendButtonServer)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonstartServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonstartServerActionPerformed
        portval = portTextFieldstartServer.getText();
        if (utl.isInteger(portval)) {
            if (utl.isOutOfRange(portval)) {
                try {
                    portint = Integer.parseInt(portval);
                    serverSock = new ServerSocket(portint);
                } catch (IOException ex) {
                    Logger.getLogger(startServer.class.getName()).log(Level.SEVERE, null, ex);
                }
//                listener for clients
                SocketAcceptandListener sat = new SocketAcceptandListener(serverSock, inFromClient, clientSentence, textAreaServer);
                sat.start();
            }
        }
    }//GEN-LAST:event_startButtonstartServerActionPerformed

    private void portTextFieldstartServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_portTextFieldstartServerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_portTextFieldstartServerActionPerformed

    private void portTextFieldstartServerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_portTextFieldstartServerMouseClicked
        portTextFieldstartServer.setText(null);
    }//GEN-LAST:event_portTextFieldstartServerMouseClicked

    private void sendButtonServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonServerActionPerformed
        try {
            outToClient = new DataOutputStream(SocketAcceptandListener.clienSocket.getOutputStream());

        } catch (IOException ex) {
            Logger.getLogger(startServer.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        sentence = textAreaServer.getText();
        try {
            outToClient.writeBytes(sentence + '\n');

        } catch (IOException ex) {
            Logger.getLogger(startServer.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
//                    after send button clicked clear the text
        textAreaServer.setText(null);
    }//GEN-LAST:event_sendButtonServerActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(startServer.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new startServer().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField portTextFieldstartServer;
    private javax.swing.JScrollPane scrollPaneServer;
    private javax.swing.JButton sendButtonServer;
    private javax.swing.JLabel serverStartNotificationstartServer;
    private javax.swing.JButton startButtonstartServer;
    public static javax.swing.JTextArea textAreaServer;
    // End of variables declaration//GEN-END:variables
}
