package javaapplication6;

import java.io.IOException;
import java.net.SocketException;
import static javaapplication6.IPV4.uddhavcombobox;

public class processDNSForWindowsIpv6 {

    private String command1;
    private String command2;
    private String dnsone;
    private String dnstwo;
    private String nameinterface;

    processDNSForWindowsIpv6(String dnsone, String dnstwo) throws SocketException, IOException {
        System.out.println("This is Windows");
        // change ip configuration using netsh command
        if (uddhavcombobox.getSelectedItem().equals("wlan0")) {
            nameinterface = "Wireless Network Connection";
//                            netsh interface ip set dns "Local Area Connection" static 192.168.0.200
//                            netsh dnsclient add dnsserver "Local Area Connection" 192.168.137.202 1
//                            netsh dnsclient add dnsserver "Local Area Connection" 192.168.137.200 2
//                            netsh interface ipv6 add dnsserver "Local Area Connection" 2001:db8::99:4acd::8
//                            netsh interface ipv6 add dns "Ethernet" 2401:ddc0:60::1 
//                            2620:0:ccc::2
//                            2620:0:ccd::2
//                            google-public-dns-a.google.com has IPv6 address 2001:4860:4860::8888
            command1 = "netsh interface ipv6 add dns  \"" + nameinterface + "\" " + dnsone + " index=1";
            command2 = "netsh interface ipv6 add dns  \"" + nameinterface + "\" " + dnstwo + " index=2";

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
            command1 = "netsh interface ipv6 add dns  \"" + nameinterface + "\" " + dnsone + " index=1";
            command2 = "netsh interface ipv6 add dns  \"" + nameinterface + "\" " + dnstwo + " index=2";

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
