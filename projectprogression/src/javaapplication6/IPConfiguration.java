package javaapplication6;

import java.awt.Color;

public class IPConfiguration extends javax.swing.JPanel {

    public IPConfiguration() {
        initComponents();
        IPV4.setBackground(Color.green);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        IPV4 = new javax.swing.JButton();
        IPV6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        try {
            iPV4 = new javaapplication6.IPV4();
        } catch (java.net.SocketException e1) {
            e1.printStackTrace();
        } catch (java.io.IOException e2) {
            e2.printStackTrace();
        }
        try {
            iPV6 = new javaapplication6.IPV6();
        } catch (java.net.SocketException e1) {
            e1.printStackTrace();
        } catch (java.io.IOException e2) {
            e2.printStackTrace();
        }

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        IPV4.setText("IPV4");
        IPV4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IPV4ActionPerformed(evt);
            }
        });

        IPV6.setText("IPV6");
        IPV6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IPV6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(IPV4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(IPV6)
                .addGap(93, 93, 93))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IPV4)
                    .addComponent(IPV6))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel2.setLayout(new java.awt.CardLayout());
        jPanel2.add(iPV4, "card2");
        jPanel2.add(iPV6, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void IPV6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IPV6ActionPerformed
        jPanel2.add(iPV4);
        IPV6.setBackground(Color.green);
//        Color col = "#E3E5E9";
        IPV4.setBackground(Color.gray);
    }//GEN-LAST:event_IPV6ActionPerformed

    private void IPV4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IPV4ActionPerformed
        jPanel2.add(iPV6);
        IPV4.setBackground(Color.green);
        IPV6.setBackground(Color.gray);
    }//GEN-LAST:event_IPV4ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton IPV4;
    private javax.swing.JButton IPV6;
    private javaapplication6.IPV4 iPV4;
    private javaapplication6.IPV6 iPV6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
