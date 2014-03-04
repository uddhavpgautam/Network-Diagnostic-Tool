
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EditSubjectPanel extends JFrame {

    boolean DEBUG;
    public JTable jt;
    public static String stdClass = "";
    boolean selected = false;
    public static String tempClass = "";
    public static int sid;
    private JButton btnAdd, btnReset, btnDash, btnExit, btnLogout;
    private JTextField txtSubName, txtPublication, txtEdition, txtAutours, txtFm, txtPm;
    @SuppressWarnings("rawtypes")
    private JComboBox jcbClass, jcbTeacher;
    private JLabel lblSubName, lblPublication, lblEidition, lblAuthors, lblTeacher, lblClass, lblPm, lblFm;
    ;

	private static final long serialVersionUID = 1L;

    public EditSubjectPanel(int index) {
        super("Subject Index");

        final JTabbedPane tabStudent = new JTabbedPane();
        JPanel pnlAdd = new JPanel();
        Font font = new Font("Comic Sans MS", Font.PLAIN, 13);

        JPanel addRest = new JPanel();
        addRest.setLayout(new GridLayout(1, 2, 10, 0));
        addRest.setBorder(BorderFactory.createEmptyBorder(0, 100, 10, 420));
        btnAdd = new JButton("Update");
        btnAdd.setPreferredSize(new Dimension(90, 35));
        ActionListener addhdl = new addHadler();
        btnAdd.addActionListener(addhdl);

        resetHandler resetHdl = new resetHandler();
        btnReset = new JButton("Back to list");
        btnReset.addActionListener(resetHdl);
        addRest.add(btnAdd);
        addRest.add(btnReset);

        JPanel exitPanel = new JPanel();
        exitPanel.setLayout(new GridLayout(1, 3, 10, 0));
        exitPanel.setBorder(BorderFactory.createEmptyBorder(10, 500, 10, 10));

        btnDash = new JButton("Dashboard");
        btnDash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                dispose();
                @SuppressWarnings("unused")
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

        tabStudent.addTab("Edit Subject", new ImageIcon("img/add.png"), pnlAdd);

        tabStudent.setSelectedIndex(0);
        tabStudent.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tabStudent.setForeground(Color.BLUE);
        tabStudent.setFont(font);

        JPanel AllGui = new JPanel();
        AllGui.setLayout(new BorderLayout());
        AllGui.add(tabStudent, BorderLayout.NORTH);
        AllGui.add(exitPanel, BorderLayout.CENTER);

        StudentPanel.stdClass = "";

        this.pack();
        this.setContentPane(AllGui);
        this.setSize(800, 500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public JPanel addStduentPanel() {
        JPanel listStdn = new JPanel();

        JLabel lblInfo = new JLabel(
                "Fill the following fields to Insert a Book. * Indicates Required Field.");
        lblInfo.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 0));

        JPanel me = new JPanel();
        me.setLayout(new GridLayout(5, 2, 5, 5));

        JPanel left = new JPanel();
        left.setLayout(new GridLayout(5, 1, 5, 5));

        JPanel right = new JPanel();
        right.setLayout(new GridLayout(5, 1, 5, 5));

        String[] rows = {"sub_name", "sub_publication", "class_id", "sub_authors", "sub_edition", "sub_teach", "sub_fm", "sub_pm"};
        Object[] data = SqlLibrary.selectOneRow("*", "subject", "sub_id=" + EditSubjectPanel.sid, rows);
        JPanel pnlname = new JPanel();
        pnlname.setLayout(new BorderLayout());
        lblSubName = new JLabel("Name of subject:");
        txtSubName = new JTextField((String) data[0], 20);
        txtSubName.setPreferredSize(new Dimension(100, 30));
        pnlname.add(lblSubName, BorderLayout.NORTH);
        pnlname.add(txtSubName, BorderLayout.CENTER);
        left.add(pnlname);

        String[] test = StudentPanel.listClass();
        JPanel pnlcls = new JPanel();
        pnlcls.setLayout(new BorderLayout());
        lblClass = new JLabel("Class:");
        jcbClass = new JComboBox(StudentPanel.listClass());
        for (int i = 0; i < test.length; i++) {
            if (test[i].equals(data[2])) {
                jcbClass.setSelectedIndex(i);
                break;
            }
        }
        pnlcls.add(lblClass, BorderLayout.NORTH);
        pnlcls.add(jcbClass, BorderLayout.CENTER);
        left.add(pnlcls);
        EditSubjectPanel.tempClass = (String) data[2];

        JPanel pnlParentname = new JPanel();
        pnlParentname.setLayout(new BorderLayout());
        lblPublication = new JLabel("Publications:");
        txtPublication = new JTextField((String) data[1], 20);
        pnlParentname.add(lblPublication, BorderLayout.NORTH);
        pnlParentname.add(txtPublication, BorderLayout.CENTER);
        left.add(pnlParentname);

        JPanel pnlParentPh = new JPanel();
        pnlParentPh.setLayout(new BorderLayout());
        lblEidition = new JLabel("Edition:");
        txtEdition = new JTextField((String) data[4], 30);
        pnlParentPh.add(lblEidition, BorderLayout.NORTH);
        pnlParentPh.add(txtEdition, BorderLayout.CENTER);
        left.add(pnlParentPh);

        JPanel pnlAddress = new JPanel();
        pnlAddress.setLayout(new BorderLayout());
        lblAuthors = new JLabel("Authors :");
        txtAutours = new JTextField((String) data[3], 30);
        pnlAddress.add(lblAuthors, BorderLayout.NORTH);
        pnlAddress.add(txtAutours, BorderLayout.CENTER);
        right.add(pnlAddress);

        System.out.println(data[5]);
        String[] temp = SubjectPanel.listTeacher();
        JPanel pnlLastname = new JPanel();
        pnlLastname.setLayout(new BorderLayout());
        lblTeacher = new JLabel("Subject Teacher:");
        jcbTeacher = new JComboBox(SubjectPanel.listTeacher());
        for (int i = 0; i < temp.length; i++) {
            System.out.println(temp[i]);
            if (temp[i].equals(data[5])) {

                jcbTeacher.setSelectedIndex(i);
                break;
            }
        }
        jcbTeacher.setPreferredSize(new Dimension(100, 30));
        pnlLastname.add(lblTeacher, BorderLayout.NORTH);
        pnlLastname.add(jcbTeacher, BorderLayout.CENTER);
        left.add(pnlLastname);

        JPanel pnlfm = new JPanel();
        pnlfm.setLayout(new BorderLayout());
        lblFm = new JLabel("Full Marks :");
        txtFm = new JTextField((String) data[6], 30);
        pnlfm.add(lblFm, BorderLayout.NORTH);
        pnlfm.add(txtFm, BorderLayout.CENTER);
        right.add(pnlfm);

        JPanel pnlpm = new JPanel();
        pnlpm.setLayout(new BorderLayout());
        lblPm = new JLabel("Pass Marks :");
        txtPm = new JTextField((String) data[7], 30);
        pnlpm.add(lblPm, BorderLayout.NORTH);
        pnlpm.add(txtPm, BorderLayout.CENTER);
        right.add(pnlpm);

        left.setBorder(BorderFactory.createEmptyBorder(10, 100, 20, 10));
        right.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 100));

        listStdn.setLayout(new BorderLayout());
        listStdn.add(lblInfo, BorderLayout.NORTH);
        listStdn.add(left, BorderLayout.WEST);
        listStdn.add(right, BorderLayout.EAST);

        return listStdn;
    }

    public JTextField setSize(JTextField txt) {
        txt = new JTextField(20);
        txt.setPreferredSize(new Dimension(100, 30));
        return txt;
    }

    public static String[] listTeacher() {
        String[] lst = {"teh_lname", "teh_name"};
        ArrayList<ArrayList<Object>> list = SqlLibrary.select("teacher", "*", "1=1", lst);
        int rows = list.size();
        String[] cls = new String[rows + 1];
        cls[0] = "       ------|  Select Subject Teacher  |------";
        //System.out.println(rows);
        if (rows > 0) {
            for (int i = 0; i < rows; i++) {
                cls[i + 1] = (String) ((ArrayList<?>) list.get(i)).get(2) + " " + (String) ((ArrayList<?>) list.get(i)).get(1);
            }
        }
        return cls;
    }

    public class addHadler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (txtSubName.getText().equals("") || txtPublication.getText().equals("") || txtAutours.getText().equals("")
                    || jcbClass.getSelectedIndex() == 0 || jcbTeacher.getSelectedIndex() == 0 || txtEdition.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "All Fileds Are Required", "Error !", JOptionPane.ERROR_MESSAGE);
            } else {
                if (SqlLibrary.update("subject", "sub_name='" + txtSubName.getText() + "',sub_publication='" + txtPublication.getText() + "',"
                        + "class_id='" + jcbClass.getSelectedItem() + "',sub_authors='" + txtAutours.getText() + "'"
                        + ",sub_edition='" + txtEdition.getText() + "',sub_teach='" + jcbTeacher.getSelectedItem() + "',"
                        + "sub_fm='" + txtFm.getText() + "',sub_pm='" + txtPm.getText() + "'",
                        " sub_id=" + EditSubjectPanel.sid));
                {
                    JOptionPane.showMessageDialog(null, "One row Inserted", "Success !", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    public class resetHandler implements ActionListener {

        @SuppressWarnings("unused")
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            StudentPanel.stdClass = EditSubjectPanel.tempClass;
            SubjectPanel pnl = new SubjectPanel(1);
        }
    }

    public class tabHandler implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
        }
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception unused) {
            ; // Ignore exception because we can't do anything. Will use
            // default.
        }
        EditSubjectPanel me = new EditSubjectPanel(0);

    }
}
