
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Popup;

@SuppressWarnings({"serial", "unused"})
public class PopupTester extends JFrame {

    private static class MessagePopup extends Popup
            implements WindowFocusListener {

        private final JDialog dialog;

        public MessagePopup(Frame base, String message) {
            super();
            dialog = new JOptionPane().createDialog(base, "Message");
            dialog.setModal(false);
            JPanel sho = new JPanel();
            sho.setLayout(new GridLayout(2, 1));
            sho.add(new JButton(message));
            sho.add(new JLabel("POP uP tesint"));
            dialog.setContentPane(sho);
        }

        @Override
        public void show() {
            dialog.addWindowFocusListener(this);
            dialog.setVisible(true);
        }

        @Override
        public void hide() {
            dialog.setVisible(false);
            dialog.removeWindowFocusListener(this);
        }

        public void windowGainedFocus(WindowEvent e) {
            // NO-OP
        }

        public void windowLostFocus(WindowEvent e) {
            hide();
        }
    }

    public static void main(String[] args) {
        final PopupTester popupTester = new PopupTester();
        popupTester.setLayout(new FlowLayout());
        popupTester.setSize(300, 100);
        popupTester.add(new JButton("Click Me") {
            @Override
            protected void fireActionPerformed(ActionEvent event) {
                Point location = getLocationOnScreen();
                MessagePopup popup = new MessagePopup(popupTester, "Howdy");
                popup.show();
            }
        });
        popupTester.add(new JButton("No Click Me"));
        popupTester.setVisible(true);
        popupTester.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        popupTester.setLocationRelativeTo(null);
    }
}
