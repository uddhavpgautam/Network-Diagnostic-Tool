package javaapplication6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ping extends javax.swing.JPanel {

    Ping() {
        initComponents();
        checkOS checkos = new checkOS();
        Stop.hide();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PingPanel = new javax.swing.JPanel();
        pingTextBox = new javax.swing.JTextField();
        Label = new javax.swing.JLabel();
        Start = new javax.swing.JButton();
        Stop = new javax.swing.JButton();
        ResultContainerPane = new javax.swing.JScrollPane();
        Result = new javax.swing.JTextArea();

        PingPanel.setBackground(new java.awt.Color(0, 0, 0));

        pingTextBox.setText("Enter Ping command Here");
        pingTextBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pingTextBoxMouseClicked(evt);
            }
        });
        pingTextBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pingTextBoxFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                pingTextBoxFocusLost(evt);
            }
        });
        pingTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pingTextBoxActionPerformed(evt);
            }
        });

        Label.setForeground(new java.awt.Color(255, 255, 255));
        Label.setText("Ping Command");

        Start.setText("Start");
        Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartActionPerformed(evt);
            }
        });

        Stop.setText("Stop");

        Result.setColumns(20);
        Result.setRows(5);
        ResultContainerPane.setViewportView(Result);

        javax.swing.GroupLayout PingPanelLayout = new javax.swing.GroupLayout(PingPanel);
        PingPanel.setLayout(PingPanelLayout);
        PingPanelLayout.setHorizontalGroup(
            PingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PingPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(Label, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pingTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Start)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Stop)
                .addGap(0, 91, Short.MAX_VALUE))
            .addComponent(ResultContainerPane)
        );
        PingPanelLayout.setVerticalGroup(
            PingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PingPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(PingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pingTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Start)
                    .addComponent(Label)
                    .addComponent(Stop))
                .addGap(18, 18, 18)
                .addComponent(ResultContainerPane, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))
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

    private void pingTextBoxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pingTextBoxFocusGained
//        pingTextBox.setText("");
    }//GEN-LAST:event_pingTextBoxFocusGained

    private void pingTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pingTextBoxActionPerformed
    }//GEN-LAST:event_pingTextBoxActionPerformed

    private void StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartActionPerformed
        Result.setText("");
        if (checkOS.isUnix()) {
            try {
//                System.out.println("Ping function started");
                pingFunctionforLinux();
//                System.out.println("Ping function started");
            } catch (IOException ex) {
                Logger.getLogger(Ping.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (checkOS.isWindows()) {
            try {
//                System.out.println("Ping function started");
                pingFunctionforWindows();
//                System.out.println("Ping function started");
            } catch (IOException ex) {
                Logger.getLogger(Ping.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Other OS");
        }

    }//GEN-LAST:event_StartActionPerformed

    private void pingTextBoxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pingTextBoxFocusLost
//       TextBox.setText("Enter Ping command Here");
    }//GEN-LAST:event_pingTextBoxFocusLost

    private void pingTextBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pingTextBoxMouseClicked
        pingTextBox.setText(null);
    }//GEN-LAST:event_pingTextBoxMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Label;
    private javax.swing.JPanel PingPanel;
    private javax.swing.JTextArea Result;
    private javax.swing.JScrollPane ResultContainerPane;
    private javax.swing.JButton Start;
    private javax.swing.JButton Stop;
    private javax.swing.JTextField pingTextBox;
    // End of variables declaration//GEN-END:variables
    private long timeStart;
    private long timeEnd;
    private long timeGap;
    private String timetoshow;
    private String inputLine;
    private String pingCmd;
    private Runtime r;
    private Process p;
    private Integer count;

    private void pingFunctionforWindows() throws IOException {
        pingCmd = pingTextBox.getText();
        System.out.println(pingCmd);
        if ((pingCmd.startsWith("ping ")) || (pingCmd.startsWith("PING "))) {
            r = Runtime.getRuntime();
            p = r.exec(pingCmd);
//            Stop.show();
//            repaint();
//            revalidate();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            timeStart = System.currentTimeMillis();
            while ((inputLine = in.readLine()) != null) {
                Result.append(inputLine + "\n");
            }
            timeEnd = System.currentTimeMillis();
            timeGap = timeEnd - timeStart;
            timetoshow = String.valueOf(timeGap);
            Result.append("Total time taken = " + timetoshow + " miliseconds");
            Result.append("\n");

        } else {
            Result.setText("Ping command not matched");
        }
    }

    private void pingFunctionforLinux() throws IOException {
        pingCmd = pingTextBox.getText();
        System.out.println(pingCmd);
        if ((pingCmd.startsWith("ping "))) {
            r = Runtime.getRuntime();
            p = r.exec(pingCmd);
//            Stop.show();
//            repaint();
//            revalidate();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            timeStart = System.currentTimeMillis();
            while (((inputLine = in.readLine()) != null) && count <= 10) {
                Result.append(inputLine + "\n");
                count++;
            }
            timeEnd = System.currentTimeMillis();
            timeGap = timeEnd - timeStart;
            timetoshow = String.valueOf(timeGap);
            Result.append("Total time taken = " + timetoshow + " miliseconds");
            Result.append("\n");
        } else {
            Result.setText("Ping command not matched");
        }
    }
}
