
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class EditStudentPanel extends JFrame {

    boolean DEBUG;
    public JTable jt;
    public static int roll;
    public static String clsTemp;
    private JButton btnAdd, btnReset, btnDash, btnExit, btnLogout;
    private JTextField txtName, txtLname, txtRoll, txtParentName, txtDob,
            txtParentPhone, txtHomePhone, txtAddress;
    @SuppressWarnings("rawtypes")
    private JComboBox jcbClass, jcbSex;
    private JLabel lblName, lblLastName, lblRoll, lblParentName, lblDob,
            lblParentPhone, lblHomePhome, lblAddress, lblClass, lblSex;
    JTable tblListStdn;
    private static final long serialVersionUID = 1L;

    public EditStudentPanel() {
        super("Student Edit/Update Index");
        JTabbedPane tabStudent = new JTabbedPane();
        JPanel pnlAdd = new JPanel();
        Font font = new Font("Comic Sans MS", Font.PLAIN, 13);

        JPanel addRest = new JPanel();
        addRest.setLayout(new GridLayout(1, 2, 10, 0));
        addRest.setBorder(BorderFactory.createEmptyBorder(0, 100, 10, 420));
        btnAdd = new JButton("Update");
        btnAdd.setPreferredSize(new Dimension(90, 35));
        updateHandle upHdl = new updateHandle();
        btnAdd.addActionListener(upHdl);

        btnReset = new JButton("Back to list");
        btnReset.addActionListener(new ActionListener() {
            @SuppressWarnings("unused")
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                StudentPanel.stdClass = EditStudentPanel.clsTemp;
                StudentPanel pnl = new StudentPanel(1);
            }
        });
        addRest.add(btnAdd);
        addRest.add(btnReset);

        JPanel exitPanel = new JPanel();
        exitPanel.setLayout(new GridLayout(1, 3, 10, 0));
        exitPanel.setBorder(BorderFactory.createEmptyBorder(10, 500, 10, 10));

        btnDash = new JButton("Dashboard");
        btnDash.addActionListener(new ActionListener() {
            @SuppressWarnings("unused")
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                dispose();
                MainPanel mainPanel = new MainPanel();
            }
        });

        btnLogout = new JButton("Logout");
        btnLogout.addActionListener(new ActionListener() {
            @SuppressWarnings("unused")
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                dispose();
                Login lgn = new Login();
            }
        });

        btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                System.exit(0);
            }
        });
        exitPanel.add(btnDash);
        exitPanel.add(btnLogout);
        exitPanel.add(btnExit);

        // String tabStudentTitle = "Add Student";
        JPanel stdn = new JPanel();
        stdn.setLayout(new BorderLayout());
        stdn.add(addRest, BorderLayout.CENTER);
        stdn.add(this.addStduentPanel(), BorderLayout.NORTH);

        pnlAdd = stdn;

        tabStudent.addTab("Edit Student", new ImageIcon("img/add.png"), pnlAdd);

        tabStudent.setSelectedIndex(0);
        tabStudent.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tabStudent.setForeground(Color.BLUE);
        tabStudent.setFont(font);

        JPanel AllGui = new JPanel();
        AllGui.setLayout(new BorderLayout());
        AllGui.add(tabStudent, BorderLayout.NORTH);
        AllGui.add(exitPanel, BorderLayout.CENTER);

        this.pack();
        this.setContentPane(AllGui);
        this.setSize(800, 500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public class updateHandle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (txtName.getText().equals("") || txtLname.getText().equals("") || txtRoll.getText().equals("")
                    || txtParentName.getText().equals("") || txtDob.getText().equals("")
                    || txtParentPhone.getText().equals("") || txtHomePhone.getText().equals("") || txtAddress.getText().equals("")
                    || jcbClass.getSelectedIndex() == 0 || jcbSex.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "All Fileds Are Required", "Error !", JOptionPane.ERROR_MESSAGE);
            } else {
                if (SqlLibrary.update("student", "std_roll='" + txtRoll.getText() + "',std_name='" + txtName.getText() + "'"
                        + ",std_lname='" + txtLname.getText() + "',class_id='" + jcbClass.getSelectedItem() + "',std_parentName='" + txtParentName.getText() + "'"
                        + ",std_parentPhone='" + txtParentPhone.getText() + "',std_Address='" + txtAddress.getText() + "',std_Dob='" + txtDob.getText() + "'"
                        + ",std_homePh='" + txtHomePhone.getText() + "',std_Sex='" + jcbSex.getSelectedItem() + "'", " std_roll=" + EditStudentPanel.roll));
                {
                    JOptionPane.showMessageDialog(null, "The student's record is updated.", "Success !", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public JPanel addStduentPanel() {
        JPanel listStdn = new JPanel();

        JLabel lblInfo = new JLabel(
                "Fill the following fields to Insert a student. * Indicates Required Field.");
        lblInfo.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 0));

        JPanel me = new JPanel();
        me.setLayout(new GridLayout(5, 2, 5, 5));

        JPanel left = new JPanel();
        left.setLayout(new GridLayout(5, 1, 5, 5));

        JPanel right = new JPanel();
        right.setLayout(new GridLayout(5, 1, 5, 5));

        String rows[] = {"std_roll", "std_name", "std_lname", "class_id", "std_parentName", "std_parentPhone",
            "std_Address", "std_Dob", "std_homePh", "std_Sex"};
        Object[] dat = SqlLibrary.selectOneRow("*", "student", "std_roll=" + EditStudentPanel.roll, rows);

        JPanel pnlname = new JPanel();
        pnlname.setLayout(new BorderLayout());
        lblName = new JLabel("First Name:");
        txtName = new JTextField((String) dat[1], 20);
        txtName.setPreferredSize(new Dimension(100, 30));
        pnlname.add(lblName, BorderLayout.NORTH);
        pnlname.add(txtName, BorderLayout.CENTER);
        left.add(pnlname);

        JPanel pnlcls = new JPanel();
        String[] combo = StudentPanel.listClass();
        pnlcls.setLayout(new BorderLayout());
        lblClass = new JLabel("Class:");
        jcbClass = new JComboBox(combo);
        EditStudentPanel.clsTemp = (String) dat[3];

        for (int i = 0; i < combo.length; i++) {
            if (combo[i].equals((String) dat[3])) {
                jcbClass.setSelectedIndex(i);
                break;
            }
        }
        pnlcls.add(lblClass, BorderLayout.NORTH);
        pnlcls.add(jcbClass, BorderLayout.CENTER);
        left.add(pnlcls);

        JPanel pnlParentname = new JPanel();
        pnlParentname.setLayout(new BorderLayout());
        lblParentName = new JLabel("Parent Name:");
        txtParentName = new JTextField((String) dat[4], 20);
        pnlParentname.add(lblParentName, BorderLayout.NORTH);
        pnlParentname.add(txtParentName, BorderLayout.CENTER);
        left.add(pnlParentname);

        JPanel pnlParentPh = new JPanel();
        pnlParentPh.setLayout(new BorderLayout());
        lblParentPhone = new JLabel("Parent Phone");
        txtParentPhone = new JTextField((String) dat[5], 30);
        pnlParentPh.add(lblParentPhone, BorderLayout.NORTH);
        pnlParentPh.add(txtParentPhone, BorderLayout.CENTER);
        left.add(pnlParentPh);

        JPanel pnlAddress = new JPanel();
        pnlAddress.setLayout(new BorderLayout());
        lblAddress = new JLabel("Address :");
        txtAddress = new JTextField((String) dat[6], 30);
        pnlAddress.add(lblAddress, BorderLayout.NORTH);
        pnlAddress.add(txtAddress, BorderLayout.CENTER);
        left.add(pnlAddress);

        JPanel pnlLastname = new JPanel();
        pnlLastname.setLayout(new BorderLayout());
        lblLastName = new JLabel("Lastname:");
        txtLname = new JTextField((String) dat[2], 30);
        pnlLastname.add(lblLastName, BorderLayout.NORTH);
        pnlLastname.add(txtLname, BorderLayout.CENTER);
        right.add(pnlLastname);

        JPanel pnlRoll = new JPanel();
        pnlRoll.setLayout(new BorderLayout());
        lblRoll = new JLabel("Roll NO:");
        txtRoll = new JTextField((String) dat[0], 20);
        pnlRoll.add(lblRoll, BorderLayout.NORTH);
        pnlRoll.add(txtRoll, BorderLayout.CENTER);
        right.add(pnlRoll);

        JPanel pnlDob = new JPanel();
        pnlDob.setLayout(new BorderLayout());
        lblDob = new JLabel("D.O.B:");
        txtDob = new JTextField((String) dat[7], 20);
        pnlDob.add(lblDob, BorderLayout.NORTH);
        pnlDob.add(txtDob, BorderLayout.CENTER);
        right.add(pnlDob);

        JPanel pnlHomPhone = new JPanel();
        pnlHomPhone.setLayout(new BorderLayout());
        lblHomePhome = new JLabel("Home Phone NO:");
        txtHomePhone = new JTextField((String) dat[8], 20);
        pnlHomPhone.add(lblHomePhome, BorderLayout.NORTH);
        pnlHomPhone.add(txtHomePhone, BorderLayout.CENTER);
        right.add(pnlHomPhone);

        JPanel pnlSex = new JPanel();
        String[] sexi = this.listSex();
        pnlSex.setLayout(new BorderLayout());
        lblSex = new JLabel("Sex:");
        jcbSex = new JComboBox(sexi);
        for (int i = 0; i < sexi.length; i++) {
            if (sexi[i].equals((String) dat[9])) {
                jcbSex.setSelectedIndex(i);
                break;
            }
        }

        pnlSex.add(lblSex, BorderLayout.NORTH);
        pnlSex.add(jcbSex, BorderLayout.CENTER);
        right.add(pnlSex);

        left.setBorder(BorderFactory.createEmptyBorder(10, 100, 20, 10));
        right.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 100));

        listStdn.setLayout(new BorderLayout());
        listStdn.add(lblInfo, BorderLayout.NORTH);
        listStdn.add(left, BorderLayout.WEST);
        listStdn.add(right, BorderLayout.EAST);
        // listStdn.setSize(780, 380);

        return listStdn;
    }

    public JTextField setSize(JTextField txt) {
        txt = new JTextField(20);
        txt.setPreferredSize(new Dimension(100, 30));
        return txt;
    }

    public String[] listClass() {
        return StudentPanel.listClass();
    }

    public String[] listSex() {
        String[] cls = {"------Select Sex-----------", "Male", "Female"};
        return cls;
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception unused) {
            ; // Ignore exception because we can't do anything. Will use
            // default.
        }
        EditStudentPanel me = new EditStudentPanel();

    }
}
