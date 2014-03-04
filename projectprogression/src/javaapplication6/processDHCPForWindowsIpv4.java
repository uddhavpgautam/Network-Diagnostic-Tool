package javaapplication6;

import java.io.IOException;
import static javaapplication6.IPV4.uddhavcombobox;

public class processDHCPForWindowsIpv4 {

    private String nameinterface;
    private String command;
    private String ipValue;
    private String netmaskValue;
    private String gatewayValue;

    processDHCPForWindowsIpv4(String ipValue, String netmaskValue, String gatewayValue) {
        System.out.println("This is Windows");
        // change ip configuration using netsh command
        if (uddhavcombobox.getSelectedItem().equals("wlan0")) {
            nameinterface = "Wireless Network Connection";
            command = "netsh interface ip set address \"" + nameinterface + "\" static " + ipValue + " " + netmaskValue + " " + gatewayValue;
            try {
                Runtime r = java.lang.Runtime.getRuntime();
                Process p = r.exec(command);
                System.out.println(command);
            } catch (IOException e1) {
                System.out.println(e1.getMessage());
            }
        }
        if (uddhavcombobox.getSelectedItem().equals("eth0")) {
            nameinterface = "Local Area Connection";
            command = "netsh interface ip set address \"" + nameinterface + "\" static " + ipValue + " " + netmaskValue + " " + gatewayValue;

            try {
                Runtime r = java.lang.Runtime.getRuntime();
                Process p = r.exec(command);
                System.out.println(command);
            } catch (IOException e1) {
                System.out.println(e1.getMessage());
            }
        }
    }
}
