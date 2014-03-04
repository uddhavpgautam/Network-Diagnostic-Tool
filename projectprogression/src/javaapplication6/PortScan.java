package javaapplication6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PortScan extends javax.swing.JPanel {

    private Process process;

    public PortScan() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PingPanel = new javax.swing.JPanel();
        portTextbox = new javax.swing.JTextField();
        Label = new javax.swing.JLabel();
        ResultContainerPane = new javax.swing.JScrollPane();
        Result = new javax.swing.JTextArea();
        Start = new javax.swing.JButton();
        textbox1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        textbox2 = new javax.swing.JTextField();

        PingPanel.setBackground(new java.awt.Color(0, 0, 0));

        portTextbox.setText("Enter IP or Hostname Here");
        portTextbox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                portTextboxMouseClicked(evt);
            }
        });
        portTextbox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                portTextboxFocusGained(evt);
            }
        });
        portTextbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                portTextboxActionPerformed(evt);
            }
        });

        Label.setForeground(new java.awt.Color(255, 255, 255));
        Label.setText("IP with port-range");

        Result.setColumns(20);
        Result.setRows(5);
        ResultContainerPane.setViewportView(Result);

        Start.setText("Start");
        Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartActionPerformed(evt);
            }
        });

        textbox1.setText("80");
        textbox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textbox1MouseClicked(evt);
            }
        });
        textbox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textbox1ActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("to");

        textbox2.setText("84");
        textbox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textbox2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PingPanelLayout = new javax.swing.GroupLayout(PingPanel);
        PingPanel.setLayout(PingPanelLayout);
        PingPanelLayout.setHorizontalGroup(
            PingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Label, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(portTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textbox1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textbox2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Start)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(ResultContainerPane, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
        );
        PingPanelLayout.setVerticalGroup(
            PingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PingPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(PingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(portTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label)
                    .addComponent(Start)
                    .addComponent(textbox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(textbox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ResultContainerPane, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void portTextboxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_portTextboxFocusGained
//        portTextbox.setText("");
    }//GEN-LAST:event_portTextboxFocusGained

    private void portTextboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_portTextboxActionPerformed
    }//GEN-LAST:event_portTextboxActionPerformed

    private void StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartActionPerformed

        InetAddress ia = null;
        String host = null;
        try {
            host = portTextbox.getText();
            if (!host.isEmpty()) {
                ia = InetAddress.getByName(host);
                try {
                    Runtime runTime = Runtime.getRuntime();
                    process = runTime.exec("ping " + host);
                    BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    br.readLine();
                    br.readLine();
                    String output = br.readLine();

                    if ((!output.contains(" Destination host unreachable.")) && (!output.contains("Request timed out."))) {
                        scan(ia);
                    }
                     else {
                        Result.setText("Host not reachable");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(PortScan.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Result.setText("Null Host");
            }
        } catch (UnknownHostException ae) {
            ae.getMessage();
        }
        System.out.println("Bye from NFS");
        revalidate();
        repaint();
        //System.exit(0);

    }//GEN-LAST:event_StartActionPerformed

    private void textbox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textbox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textbox1ActionPerformed

    private void portTextboxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_portTextboxMouseClicked
        portTextbox.setText(null);
    }//GEN-LAST:event_portTextboxMouseClicked

    private void textbox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textbox1MouseClicked
        textbox1.setText(null);
    }//GEN-LAST:event_textbox1MouseClicked

    private void textbox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textbox2MouseClicked
        textbox2.setText(null);
    }//GEN-LAST:event_textbox2MouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Label;
    private javax.swing.JPanel PingPanel;
    private javax.swing.JTextArea Result;
    private javax.swing.JScrollPane ResultContainerPane;
    private javax.swing.JButton Start;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField portTextbox;
    private javax.swing.JTextField textbox1;
    private javax.swing.JTextField textbox2;
    // End of variables declaration//GEN-END:variables

    private void scan(InetAddress remote) {
        int port = 0;
        String hostname = remote.getHostName();
        String resulta = "";
        String resultaaa = "";
        for (port = Integer.parseInt(textbox1.getText()); port < Integer.parseInt(textbox2.getText()) + 1; port++) {
            try {
                try (Socket s = new Socket(remote, port)) {
                    resulta = "Server is listening on port " + port + " of " + hostname;
                    resultaaa += resulta + "\n";
                    Result.setText(resultaaa);
                }
            } catch (IOException ex) {
                // The remote host is not listening on this port
                resulta = "Server is not listening on port " + port + " of " + hostname;
                resultaaa += resulta + "\n";
                Result.setText(resultaaa);
            }
        }//for ends
    }
}
