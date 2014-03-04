package javaapplication6;

import java.awt.Color;
import javax.swing.BorderFactory;

public class Container extends javax.swing.JFrame {

    public Container() {
        Ping = new javaapplication6.Ping();
        initComponents();
        jTabbedPane1.addTab("Ping", Ping);
        jTabbedPane1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "                                                                                                        Network Diagnostic Tool-Box"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        iPConfiguration1 = new javaapplication6.IPConfiguration();
        portScan1 = new javaapplication6.PortScan();
        NetworkDiscovery = new javaapplication6.NetworkDiscovery();
        Chat = new javaapplication6.Chat();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NetworkDiagnosticDashBoard");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setIconImage(getIconImage());
        setMaximizedBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.addTab("IP Configuration", iPConfiguration1);
        jTabbedPane1.addTab("Port Scan", portScan1);
        jTabbedPane1.addTab("Network Discovery", NetworkDiscovery);
        jTabbedPane1.addTab("Chat", Chat);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 809, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Container.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Container().setVisible(true);

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javaapplication6.Chat Chat;
    private javaapplication6.NetworkDiscovery NetworkDiscovery;
    private javaapplication6.IPConfiguration iPConfiguration1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javaapplication6.PortScan portScan1;
    // End of variables declaration//GEN-END:variables
    private javaapplication6.Ping Ping;
}
