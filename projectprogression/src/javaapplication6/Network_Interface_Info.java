package javaapplication6;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.net.*;
import java.util.*;

public class Network_Interface_Info {

    public NetworkInterface NETINIT;
    Enumeration<NetworkInterface> ALL_INTERFACE;
    Enumeration<InetAddress> ALL_ADDRESSES;
    public int InterfaceCount = 0;
    public int TInterfaceNumber = 12;
    //GUI Globals
    private final JFrame MainWindow = new JFrame("Network Diagnosis Tool");
    private final JLabel Title = new JLabel("Network Interface Information");
    private final JLabel TotalInterface = new JLabel("Total # Interface on this Host:");
    private final JLabel TotalInterfaces_Box = new JLabel();
    private final JLabel InterfaceNumber = new JLabel("Interface #:");
    private final JLabel InterfaceNumber_Box = new JLabel();
    private final JLabel InterfaceName = new JLabel("Tnterface Name:");
    private final JLabel InterfaceName_Box = new JLabel();
    private final JLabel InterfaceID = new JLabel("Interface ID:");
    private final JLabel InterfaceID_Box = new JLabel();
    private final JLabel MAC = new JLabel("MAC Address:");
    private final JLabel MAC_BOX = new JLabel();
    private final JLabel IP = new JLabel("IP Address:");
    private final JLabel IP_BOX = new JLabel();
    private final JLabel HostName = new JLabel("Host Name");
    private final JLabel HostName_Box = new JLabel();
    private final JLabel MTU = new JLabel("MTU:");
    private final JLabel MTU_Box = new JLabel();
    private final JLabel Status = new JLabel("Status:");
    private final JLabel Status_Box = new JLabel();
    private final JLabel PointToPoint = new JLabel("Point to Point:");
    private final JLabel PointToPoint_Box = new JLabel();
    private final JLabel Multicast = new JLabel("Multicast:");
    private final JLabel Multicast_Box = new JLabel();
    private final JLabel Loopback = new JLabel("Loopback:");
    private final JLabel Loopback_Box = new JLabel();
    private final JLabel Virtual = new JLabel("Virtual:");
    private final JLabel Virtual_Box = new JLabel();
    private final JButton NEXT = new JButton("NEXT");
    private final JButton PREVIOUS = new JButton("PREV");
    private final JButton QUIT = new JButton("QUIT");

    public static void main(String[] args) {
        new Network_Interface_Info();
    }

    public Network_Interface_Info() {
        BuildGUI();
        GetInterfaces();
        DisplayInterfaceInfo(TInterfaceNumber);

    }
    //-------------------------------------------------------------------

    public void GetInterfaces() {
        try {
            ALL_INTERFACE = NetworkInterface.getNetworkInterfaces();
            InterfaceCount = Collections.list(ALL_INTERFACE).size();
        } catch (SocketException x) {
            x.printStackTrace();
        }
    }
    //----------------------------------------------------------------

    public void DisplayInterfaceInfo(int INT_NUM) {
        try {
            ALL_INTERFACE = NetworkInterface.getNetworkInterfaces();
            NETINIT = Collections.list(ALL_INTERFACE).get(INT_NUM);
            InterfaceNumber_Box.setText(Integer.toString(INT_NUM + 1));
            InterfaceName_Box.setText(NETINIT.getDisplayName());
            InterfaceID_Box.setText(NETINIT.getName());
            MAC_BOX.setText(Arrays.toString(NETINIT.getHardwareAddress()));
            ALL_ADDRESSES = NETINIT.getInetAddresses();
            for (InetAddress X : Collections.list(ALL_ADDRESSES)) {
                IP_BOX.setText(X.getHostAddress());
                HostName_Box.setText(X.getHostName());
            }
            MTU_Box.setText(Integer.toString(NETINIT.getMTU()));
            String STATUS;
            if (NETINIT.isUp()) {
                STATUS = "Up!";
            } else {
                STATUS = "Down";
            }
            Status_Box.setText(STATUS);

            String POINTTOPOINT;
            if (NETINIT.isPointToPoint()) {
                POINTTOPOINT = "Yes!";
            } else {
                POINTTOPOINT = "No!";
            }
            PointToPoint_Box.setText(POINTTOPOINT);

            String MULTICAST;
            if (NETINIT.supportsMulticast()) {
                MULTICAST = "Yes!";
            } else {
                MULTICAST = "No!";
            }
            Multicast_Box.setText(MULTICAST);
            String LOOPBACK;
            if (NETINIT.isLoopback()) {
                LOOPBACK = "Yes!";
            } else {
                LOOPBACK = "No!";

            }
            Loopback_Box.setText(LOOPBACK);
            String ISVIRTUAL;
            if (NETINIT.isVirtual()) {
                ISVIRTUAL = "Yes!";

            } else {
                ISVIRTUAL = "No!";

            }
            Virtual_Box.setText(ISVIRTUAL);
        } catch (SocketException x) {
        }
    }
    //---------------------------------------------------------------------

    public void BuildGUI() {
//        MainWindow.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        MainWindow.setSize(450, 400);
        MainWindow.setLayout(null);
        MainWindow.add(Title);
        Title.setBounds(112, 0, 172, 16);
        //---------------------------------------------
        MainWindow.add(TotalInterface);
        TotalInterface.setBounds(12, 23, 181, 16);
        TotalInterfaces_Box.setForeground(new java.awt.Color(255, 0, 0));
        TotalInterfaces_Box.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TotalInterfaces_Box.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        MainWindow.add(TotalInterfaces_Box);
        TotalInterfaces_Box.setBounds(224, 23, 136, 16);
        //---------------------------------------------------------
        MainWindow.add(InterfaceNumber);
        InterfaceNumber.setBounds(12, 46, 69, 16);
        InterfaceNumber_Box.setForeground(new java.awt.Color(255, 0, 0));
        InterfaceNumber_Box.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        InterfaceNumber_Box.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        MainWindow.add(InterfaceNumber_Box);
        InterfaceNumber_Box.setBounds(112, 46, 248, 16);
        //----------------------------------------------------------------
        MainWindow.add(InterfaceName);
        InterfaceName.setBounds(12, 69, 93, 16);
        InterfaceName_Box.setForeground(new java.awt.Color(255, 0, 0));
        InterfaceName_Box.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        InterfaceName_Box.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        MainWindow.add(InterfaceName_Box);
        InterfaceName_Box.setBounds(112, 66, 248, 16);
        //--------------------------------------------------
        MainWindow.add(InterfaceID);
        InterfaceID.setBounds(12, 92, 72, 16);
        InterfaceID_Box.setForeground(new java.awt.Color(255, 0, 0));
        InterfaceID_Box.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        InterfaceID_Box.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        MainWindow.add(InterfaceID_Box);
        InterfaceID_Box.setBounds(112, 92, 248, 16);
        //------------------------------------------------
        MainWindow.add(MAC);
        MAC.setBounds(12, 112, 80, 16);
        MAC_BOX.setForeground(new java.awt.Color(255, 0, 0));
        MAC_BOX.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MAC_BOX.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        MainWindow.add(MAC_BOX);
        MAC_BOX.setBounds(112, 112, 248, 16);
        //---------------------------------------------------
        MainWindow.add(IP);
        IP.setBounds(12, 138, 80, 16);
        IP_BOX.setForeground(new java.awt.Color(255, 0, 0));
        IP_BOX.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        IP_BOX.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        MainWindow.add(IP_BOX);
        IP_BOX.setBounds(112, 138, 248, 16);
        //-----------------------------------------------------
        MainWindow.add(HostName);
        HostName.setBounds(12, 161, 80, 16);
        HostName_Box.setForeground(new java.awt.Color(255, 0, 0));
        HostName_Box.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        HostName_Box.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        MainWindow.add(HostName_Box);
        HostName_Box.setBounds(112, 161, 248, 16);
        //--------------------------------------------------------
        MainWindow.add(MTU);
        MTU.setBounds(12, 184, 31, 16);
        MTU_Box.setForeground(new java.awt.Color(255, 0, 0));
        MTU_Box.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MTU_Box.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        MainWindow.add(MTU_Box);
        MTU_Box.setBounds(112, 184, 248, 16);
        //---------------------------------------------------------
        MainWindow.add(Status);
        Status.setBounds(12, 207, 41, 16);
        Status_Box.setForeground(new java.awt.Color(255, 0, 0));
        Status_Box.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Status_Box.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        MainWindow.add(Status_Box);
        Status_Box.setBounds(112, 207, 248, 16);
        //-------------------------------------------------------------
        MainWindow.add(PointToPoint);
        PointToPoint.setBounds(12, 230, 100, 16);
        PointToPoint_Box.setForeground(new java.awt.Color(255, 0, 0));
        PointToPoint_Box.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PointToPoint_Box.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        MainWindow.add(PointToPoint_Box);
        PointToPoint_Box.setBounds(112, 230, 248, 16);
        //-----------------------------------------------------------
        MainWindow.add(Multicast);
        Multicast.setBounds(12, 253, 100, 16);
        Multicast_Box.setForeground(new java.awt.Color(255, 0, 0));
        Multicast_Box.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Multicast_Box.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        MainWindow.add(Multicast_Box);
        Multicast_Box.setBounds(112, 253, 248, 16);
        //-------------------------------------------------------------
        MainWindow.add(Loopback);
        Loopback.setBounds(12, 276, 100, 16);
        Loopback_Box.setForeground(new java.awt.Color(255, 0, 0));
        Loopback_Box.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Loopback_Box.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        MainWindow.add(Loopback_Box);
        Loopback_Box.setBounds(112, 276, 248, 16);
        //--------------------------------------------------------------
        MainWindow.add(Virtual);
        Virtual.setBounds(12, 299, 100, 16);
        Virtual_Box.setForeground(new java.awt.Color(255, 0, 0));
        Virtual_Box.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Virtual_Box.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        MainWindow.add(Virtual_Box);
        Virtual_Box.setBounds(112, 299, 248, 16);
        //-----------------------------------------------------------
        PREVIOUS.setBackground(Color.black);
        PREVIOUS.setForeground(Color.white);
        PREVIOUS.setText("PREV");
        PREVIOUS.setToolTipText("Previous");
        MainWindow.add(PREVIOUS);
        PREVIOUS.setBounds(112, 322, 65, 25);
        //--------------------------------------------------------------
        NEXT.setBackground(Color.black);
        NEXT.setForeground(Color.white);
        NEXT.setText("NEXT");
        NEXT.setToolTipText("next");
        MainWindow.add(NEXT);
        NEXT.setBounds(184, 322, 65, 25);
        //---------------------------------------------------------------
        QUIT.setBackground(Color.black);
        QUIT.setForeground(Color.white);
        QUIT.setText("QUIT");
        QUIT.setToolTipText("Quit");
        MainWindow.add(QUIT);
        QUIT.setBounds(297, 322, 65, 25);
        //-------------------------------------------------------------
        Add_Listeners();
        MainWindow.setVisible(true);

    }
    //------------------------------------------------------------

    public void Add_Listeners() {
        PREVIOUS.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ACTION_PREVIOUS();
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        NEXT.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ACTION_NEXT();
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        QUIT.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ACTION_QUIT();
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }
    //------------------------------------------------------------

    public void ACTION_PREVIOUS() {
        if (TInterfaceNumber > 0) {
            TInterfaceNumber--;
            DisplayInterfaceInfo(TInterfaceNumber);
        }
    }
//-----------------------------------------------------------------

    public void ACTION_NEXT() {
        if (TInterfaceNumber < (InterfaceCount - 1)) {
            TInterfaceNumber++;
            DisplayInterfaceInfo(TInterfaceNumber);
        }
    }

    public void ACTION_QUIT() {
        MainWindow.setVisible(false);
        MainWindow.dispose();
    }
}
