package javaapplication6;

import java.io.IOException;
import static javaapplication6.IPV4.uddhavcombobox;

public class processDNSForWindowsIpv4 {

    private String command1;
    private String command2;
    private String dnsone;
    private String dnstwo;
    private String nameinterface;

    processDNSForWindowsIpv4(String dnsone, String dnstwo) {
        System.out.println("This is Windows inside processDNSForWindowsIpv4");
//            nameinterface = (String) ipv4.uddhavcombobox.getSelectedItem();
        // change ip configuration using netsh command
        if (uddhavcombobox.getSelectedItem().equals("wlan0")) {
            nameinterface = "Wireless Network Connection";
//                            netsh interface ip set dns "Local Area Connection" static 192.168.0.200
//                            netsh dnsclient add dnsserver "Local Area Connection" 192.168.137.202 1
//                            netsh dnsclient add dnsserver "Wireless Network connection" 8.8.8.8 2
            command1 = "netsh dnsclient add dnsserver \"" + nameinterface + "\" " + dnsone + " 1";
            command2 = "netsh dnsclient add dnsserver \"" + nameinterface + "\" " + dnstwo + " 2";

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
            nameinterface = "Local Area Connection";
//                            netsh interface ip set dns "Local Area Connection" static 192.168.0.200
//                            netsh dnsclient add dnsserver "Local Area Connection" 192.168.137.202 1
//                            netsh dnsclient add dnsserver "Local Area Connection" 192.168.137.200 2
            command1 = "netsh dnsclient add dnsserver \"" + nameinterface + "\" " + dnsone + " 1";
            command2 = "netsh dnsclient add dnsserver \"" + nameinterface + "\" " + dnstwo + " 2";

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
