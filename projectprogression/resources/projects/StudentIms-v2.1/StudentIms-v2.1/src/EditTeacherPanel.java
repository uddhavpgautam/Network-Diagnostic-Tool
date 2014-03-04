
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

public class EditTeacherPanel extends JFrame {

    boolean DEBUG;
    public JTable jt;
    public static int Tid = 0;
    boolean selected = false;
    private JButton btnAdd, btnReset, btnDash, btnExit, btnLogout;
    private JTextField txtName, txtLname, txtHiredate;
    @SuppressWarnings("rawtypes")
    private JComboBox jcbTehLevel, jcbSex;
    private JLabel lblName, lblLname, lblHiredate, lblLevel, lblSex;
    public JTable tblListStdn;
    private static final long serialVersionUID = 1L;

    public EditTeacherPanel() {
        super("Teacher's Index");
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

        btnReset = new JButton("Back to list");
        btnReset.addActionListener(new ActionListener() {
            @SuppressWarnings("unused")
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                dispose();
                TeacherPanel pnl = new TeacherPanel(1);
            }
        });
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

        tabStudent.addTab("Edit Teacher", new ImageIcon("img/add.png"), pnlAdd);
        //tabStudent.addTab("List Teachers", new ImageIcon("img/list.png"),pnlList);

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
        String[] rows = {"teh_name", "teh_lname", "teh_enroll", "teh_level", "teh_sex"};
        Object[] data = SqlLibrary.selectOneRow("*", "teacher", "teh_id=" + EditTeacherPanel.Tid, rows);

        JPanel pnlname = new JPanel();
        pnlname.setLayout(new BorderLayout());
        lblName = new JLabel("Name of Teacher:");
        txtName = new JTextField((String) data[0], 30);
        txtName.setPreferredSize(new Dimension(150, 30));
        pnlname.add(lblName, BorderLayout.NORTH);
        pnlname.add(txtName, BorderLayout.CENTER);
        left.add(pnlname);

        String[] temp = this.listSex();
        JPanel pnlAddress = new JPanel();
        pnlAddress.setLayout(new BorderLayout());
        lblSex = new JLabel("Sex :");
        jcbSex = new JComboBox(temp);
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].equals(data[4])) {
                jcbSex.setSelectedIndex(i);
                break;
            }
        }

        pnlAddress.add(lblSex, BorderLayout.NORTH);
        pnlAddress.add(jcbSex, BorderLayout.CENTER);
        left.add(pnlAddress);

        JPanel pnlLastname = new JPanel();
        pnlLastname.setLayout(new BorderLayout());
        lblLname = new JLabel("Lastname:");
        txtLname = new JTextField((String) data[1], 30);
        //txtLname.setPreferredSize(new Dimension(100,30));
        pnlLastname.add(lblLname, BorderLayout.NORTH);
        pnlLastname.add(txtLname, BorderLayout.CENTER);
        right.add(pnlLastname);

        JPanel pnlParentname = new JPanel();
        pnlParentname.setLayout(new BorderLayout());
        lblHiredate = new JLabel("Hiredate:");
        txtHiredate = new JTextField((String) data[2], 20);
        pnlParentname.add(lblHiredate, BorderLayout.NORTH);
        pnlParentname.add(txtHiredate, BorderLayout.CENTER);
        right.add(pnlParentname);

        String[] level = this.listLevel();
        JPanel pnlTeacherLevel = new JPanel();
        pnlTeacherLevel.setLayout(new BorderLayout());
        lblLevel = new JLabel("Level:");
        jcbTehLevel = new JComboBox<>(level);
        for (int i = 0; i < level.length; i++) {
            if (data[3].equals(level[i])) {
                jcbTehLevel.setSelectedIndex(i);
                break;
            }
        }
        pnlTeacherLevel.add(lblLevel, BorderLayout.NORTH);
        pnlTeacherLevel.add(jcbTehLevel, BorderLayout.CENTER);
        left.add(pnlTeacherLevel);

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

    public String[] listSex() {
        String[] cls = {"       ------|  Select Sex  |------", "Male", "Female"};
        return cls;
    }

    public String[] listLevel() {
        String[] cls = {"       ------|  Select Level  |------", "Primary", "Lower Secondary", "Secondary"};
        return cls;
    }

    public static String[] listTeacher() {
        String[] lst = {"class_id", "class", "class_sec"};
        ArrayList<ArrayList<Object>> list = SqlLibrary.select("Class", "*", "1=1", lst);
        int rows = list.size();
        String[] cls = new String[rows + 1];
        cls[0] = "       ------|  Select Class  |------";
        System.out.println(rows);
        if (rows > 0) {
            for (int i = 0; i < rows; i++) {
                cls[i + 1] = (String) ((ArrayList<?>) list.get(i)).get(2);
            }
        }
        return cls;
    }

    public class editViewHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int row = jt.convertRowIndexToModel(jt.getSelectedRow());
                int col = jt.convertColumnIndexToModel(jt.getSelectedColumn());
                if (jt.isCellSelected(row, col)) {
                    int data = Integer.parseInt("" + jt.getValueAt(row, 1));
                    EditStudentPanel.roll = data;
                    dispose();
                    @SuppressWarnings("unused")
                    EditStudentPanel pnl = new EditStudentPanel();
                }
            } catch (Exception exp) {
                JOptionPane.showMessageDialog(null, "You Must Select a row to edit the record.", "Warning!", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public class addHadler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
//			if(txtName.equals("")){
//				System.out.println("Ok fine.");
//			}
            if (txtName.getText().equals("") || txtLname.getText().equals("") || txtHiredate.getText().equals("")
                    || jcbSex.getSelectedIndex() == 0 || jcbTehLevel.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "All Fileds Are Required", "Error !", JOptionPane.ERROR_MESSAGE);
            } else {
                if (SqlLibrary.update("teacher", "teh_name='" + txtName.getText() + "',teh_lname='" + txtLname.getText() + "'"
                        + ",teh_enroll='" + txtHiredate.getText() + "',teh_level='" + jcbTehLevel.getSelectedItem() + "',teh_sex='" + jcbSex.getSelectedItem() + "'",
                        " teh_id=" + EditTeacherPanel.Tid));
                {
                    JOptionPane.showMessageDialog(null, "The record is updated.", "Success !", JOptionPane.INFORMATION_MESSAGE);
                }
            }
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
        EditTeacherPanel me = new EditTeacherPanel();

    }
}
