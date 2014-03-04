
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JButton btnLogin, btnCancel;
    private JTextField txtUname;
    private JPasswordField txtPass;
    private JLabel lblName, lblPass;

    public Login() {
        Font fnMono = new Font("Comic Sans MS", Font.PLAIN, 13);

        btnLogin = new JButton("Login");
        btnLogin.setPreferredSize(new Dimension(100, 30));
        btnLogin.setFont(fnMono);
        btnLogin.setForeground(Color.BLUE);
        LoginHandler lgn = new LoginHandler();
        btnLogin.addActionListener(lgn);

        btnCancel = new JButton("Reset");
        btnCancel.setPreferredSize(new Dimension(100, 30));
        btnCancel.setFont(fnMono);
        btnCancel.setForeground(Color.BLUE);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtUname.setText("");
                txtPass.setText("");
            }
        });

        lblName = new JLabel("Username :-");
        lblName.setForeground(Color.DARK_GRAY);
        lblName.setFont(fnMono);

        lblPass = new JLabel("Password  :-");
        lblPass.setForeground(Color.DARK_GRAY);
        lblPass.setFont(fnMono);

        txtUname = new JTextField(25);
        txtUname.setPreferredSize(new Dimension(100, 25));
        txtUname.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        txtUname.setHorizontalAlignment(SwingConstants.CENTER);

        txtPass = new JPasswordField(25);
        txtPass.setPreferredSize(new Dimension(100, 25));
        txtPass.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        txtPass.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblImg = new JLabel();
        lblImg.setIcon(new ImageIcon("img/Student.png"));;

        JPanel img = new JPanel();
        img.setLayout(new GridLayout(1, 1));
        img.add(lblImg);

        JPanel uname = new JPanel();
        uname.setLayout(new FlowLayout());
        uname.add(lblName);
        uname.add(txtUname);

        JPanel pass = new JPanel();
        pass.setLayout(new FlowLayout());
        pass.add(lblPass);
        pass.add(txtPass);

        JPanel butt = new JPanel();
        butt.setLayout(new FlowLayout());
        butt.add(btnLogin);
        butt.add(btnCancel);

        JPanel mids = new JPanel();
        JPanel sec = new JPanel();
        //sec.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true),"--| Member Login |--", TitledBorder.CENTER,new Font ("Comic Sans MS",Font.PLAIN, 20),Color.l));
        sec.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true), "--| Member Login |--"));
        sec.setLayout(new GridLayout(3, 1));
        sec.add(uname);
        sec.add(pass);
        sec.add(butt);
        mids.add(sec);
        mids.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

        JPanel last = new JPanel();
        last.setLayout(new BorderLayout());
        //last.add(Suman,BorderLayout.NORTH);
        last.add(img, BorderLayout.NORTH);
        last.add(mids, BorderLayout.CENTER);
        last.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        this.pack();
        this.setContentPane(last);
        this.setTitle("Student Information System and Result processing(v.1.1).");

        this.setSize(820, 510);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception unused) {
            ; // Ignore exception because we can't do anything. Will use
            // default.
        }
        @SuppressWarnings("unused")
        Login me = new Login();

    }

    public class LoginHandler implements ActionListener {

        @SuppressWarnings("deprecation")
        @Override
        public void actionPerformed(ActionEvent e) {
            if (txtUname.getText().equals("") || new String(txtPass.getText()).equals("")) {
                JOptionPane.showMessageDialog(null, "All fields are required", "Error !", JOptionPane.ERROR_MESSAGE);
            } else {
                //System.out.println(txtUname.getText()+" "+new String(txtPass.getText()));

                if (SqlLibrary.ValidUser(txtUname.getText(), new String(txtPass.getText()))) {

                    dispose();
                    @SuppressWarnings("unused")
                    MainPanel pnl = new MainPanel();
                } else {
                    JOptionPane.showMessageDialog(null, "Sorry! Username and password mismatch.", "Error !", JOptionPane.ERROR_MESSAGE);
                }
            }

        }
    }
}
