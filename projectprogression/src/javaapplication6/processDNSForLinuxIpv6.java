package javaapplication6;

import java.io.IOException;
import java.net.SocketException;

public class processDNSForLinuxIpv6 {

    private String command1;
    private String command2;
    private String dnsone;
    private String dnstwo;
    private String nameinterface;

    public processDNSForLinuxIpv6(String one, String two) throws SocketException, IOException {
        System.out.println("This is Unix or Linux");
        IPV6 ipv6 = new IPV6();
        nameinterface = (String) ipv6.uddhavcombobox.getSelectedItem();
//                            netsh interface ip set dns "Local Area Connection" static 192.168.0.200
//                            netsh dnsclient add dnsserver "Local Area Connection" 192.168.137.202 1
//                            netsh dnsclient add dnsserver "Local Area Connection" 192.168.137.200 2
//                echo "nameserver $dns_primary" >> /etc/resolv.conf
//                echo "nameserver $dns_secondary" >> /etc/resolv.conf
        command1 = "echo \"nameserver" + dnsone + "\" >> /etc/resolv.conf";
        command2 = "echo \"nameserver" + dnstwo + "\" >> /etc/resolv.conf";

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
