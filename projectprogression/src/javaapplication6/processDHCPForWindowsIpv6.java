package javaapplication6;

import java.io.IOException;
import static javaapplication6.IPV4.uddhavcombobox;

public class processDHCPForWindowsIpv6 {

    private String nameinterface;
    private String command;
    private String command1;
    private String command2;
    private String ipValue;
    private String netmaskValue;
    private String gatewayValue;

    processDHCPForWindowsIpv6(String ipValue, String netmaskValue, String gatewayValue) {
        System.out.println("This is Windows");
        System.out.println("This is WindowsThis is WindowsThis is WindowsThis is WindowsThis is Windows");
        // change ip configuration using netsh command
        if (uddhavcombobox.getSelectedItem().equals("wlan0")) {
            System.out.println("Inside wireless connection");
            nameinterface = "Wireless Network Connection";
//                            netsh interface ipv6 add address "Local Area Connection" 2001:db8:290c:1291::1
//                            netsh interface ipv6 add route ::/0 "Local Area Connection" fe80::2aa:ff:fe9a:21b8
//                            netsh interface ipv6 add address "Wireless Network Conection" fe80::2/45
//                            netsh interface ipv6 set/add address "Wireless Network Connection" fe80::22/45
//                            netsh interface ipv6 set/add route fe80::4/34 "Wireless Network Connection"
//                netsh interface ipv6 add route ::/0 interface="Ethernet" 2001:610:168:dcad::1 store=persistent
            command1 = "netsh interface ipv6 set address " + "\"" + nameinterface + "\" " + ipValue+"/"+netmaskValue;
            command2 = "netsh interface ipv6 add route ::/0 interface=" + "\"" + nameinterface + "\" " + gatewayValue;
            try {
                Runtime r = java.lang.Runtime.getRuntime();
                Process p1 = r.exec(command1);
                Process p2 = r.exec(command2);
                System.out.println(command1);
                System.out.println(command2);
            } catch (IOException e1) {
                System.out.println(e1.getMessage());
            }
        }
        if (uddhavcombobox.getSelectedItem().equals("eth0")) {
            System.out.println("Inside etho");
            nameinterface = "Local Area Connection";
//                            netsh interface ipv6 add address "Wireless Network Conection" fe80::2/45
//                            netsh interface ipv6 set/add address "Wireless Network Connection" fe80::22/45
//                            netsh interface ipv6 set/add route fe80::4/34 "Wireless Network Connection"
            command1 = "netsh interface ipv6 set address " + "\"" + nameinterface + "\" " + ipValue+"/"+netmaskValue;
            command2 = "netsh interface ipv6 add route ::/0 interface=" + "\"" + nameinterface + "\" " + gatewayValue;
            try {
                Runtime r = java.lang.Runtime.getRuntime();
                Process p1 = r.exec(command1);
                Process p2 = r.exec(command2);
                System.out.println(command1);
                System.out.println(command2);
            } catch (IOException e1) {
                System.out.println(e1.getMessage());
            }
        }
    }
}
