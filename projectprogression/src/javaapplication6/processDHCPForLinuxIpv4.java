package javaapplication6;

import java.io.IOException;
import static javaapplication6.IPV4.uddhavcombobox;

public class processDHCPForLinuxIpv4 {

    private final String nameinterface;
    private final String command;
    private String ipValue;
    private String netmaskValue;
    private String gatewayValue;

    processDHCPForLinuxIpv4(String ipValue, String netmaskValue, String gatewayValue) {
        System.out.println("This is Unix or Linux");
        // change ip configuration using netsh command
//            ifconfig  $interface_name $IP netmask $subnetmask  broadcast $Broadcast_address pointopoint $gateway up
        nameinterface = (String) uddhavcombobox.getSelectedItem();
        command = "ifconfig " + nameinterface + " " + ipValue + " netmask " + netmaskValue + " " + "pointtopoint " + gatewayValue + " up";

        try {
            Runtime r = java.lang.Runtime.getRuntime();
            Process p = r.exec(command);
            System.out.println(command);
        } catch (IOException e1) {
            System.out.println(e1.getMessage());
        }
    }
}
