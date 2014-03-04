
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("unused")
public class EditMarksPanel extends JFrame {

    boolean DEBUG;
    public JTable jt;
    public static String stdClass;
    public static int stdnId;
    boolean selected = false;
    public static boolean staus = false;
    private JButton btnAdd, btnReset, btnDash, btnExit, btnLogout, btnEdit,
            btnDelete;
    private JTextField txtName, txtLname, txtRoll, txtParentName, txtDob,
            txtParentPhone, txtHomePhone, txtAddress;
    @SuppressWarnings("rawtypes")
    private JComboBox jcbClass, jcbSex, jcbStdn;
    private JLabel lblName, lblLastName, lblRoll, lblParentName, lblDob,
            lblParentPhone, lblHomePhome, lblAddress, lblClass, lblSex, lblStdn;
    private JTextField[] flds;
    private JLabel[] lbls;
    private static final long serialVersionUID = 1L;

    public EditMarksPanel(int index) {
        super("Student Index");
        final JTabbedPane tabStudent = new JTabbedPane();
        JPanel pnlAdd = new JPanel();
        JPanel pnlList = new JPanel();

        Font font = new Font("Comic Sans MS", Font.PLAIN, 13);

        JPanel addRest = new JPanel();
        addRest.setLayout(new GridLayout(1, 2, 10, 0));
        addRest.setBorder(BorderFactory.createEmptyBorder(0, 100, 10, 420));
        btnAdd = new JButton("Update");
        btnAdd.setPreferredSize(new Dimension(90, 35));
        ActionListener addhdl = new addHadler();
        btnAdd.addActionListener(addhdl);

        resetHandler resetHdl = new resetHandler();
        btnReset = new JButton("Reset");
        btnReset.addActionListener(resetHdl);
        addRest.add(btnAdd);
        addRest.add(btnReset);

        JPanel exitPanel = new JPanel();
        exitPanel.setLayout(new GridLayout(1, 3, 10, 0));
        exitPanel.setBorder(BorderFactory.createEmptyBorder(10, 400, 10, 100));

        btnDash = new JButton("Dashboard");
        btnDash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                dispose();
                MainPanel mainPanel = new MainPanel();
            }
        });

        btnLogout = new JButton("Logout");
        btnLogout.addActionListener(new ActionListener() {
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

        JPanel listEdit = new JPanel();
        listEdit.setLayout(new BorderLayout());

        tabStudent.addTab("Update the marks of the student.", new ImageIcon("img/add.png"), pnlAdd);

        tabStudent.setSelectedIndex(0);
        tabStudent.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tabStudent.setForeground(Color.BLUE);
        tabStudent.setFont(font);
        //tabStudent.setSize(750, 500);

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
                "Fill the following fields to Insert Marks.");
        lblInfo.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 0));
        lblInfo.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        lblInfo.setForeground(Color.BLUE);

        JPanel left = new JPanel();
        left.setLayout(new GridLayout(4, 1, 20, 16));

        JPanel right = new JPanel();
        right.setLayout(new GridLayout(5, 1, 5, 5));

        int length = InsertMarks.listSub().length;
        String[] subs = InsertMarks.listSub();
        lbls = new JLabel[length];
        flds = new JTextField[length];

        String hold[] = EditMarksPanel.listStdn();
        JPanel pnlname = new JPanel();
        pnlname.setLayout(new BorderLayout());
        lblStdn = new JLabel("Select Student:");
        lblStdn.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        jcbStdn = new JComboBox(EditMarksPanel.listStdn());
        for (int i = 0; i < hold.length; i++) {
            String[] test = hold[i].split("-");
            System.out.println(test[0]);
            if (test[0].equals("" + stdnId)) {
                jcbStdn.setSelectedIndex(i);
            }
        }

        pnlname.add(lblStdn, BorderLayout.NORTH);
        pnlname.add(jcbStdn, BorderLayout.CENTER);
        left.add(pnlname);
        String[] marks = EditMarksPanel.listSubMark();

        int i = 0;
        for (JLabel lbl : lbls) {
            System.out.println(subs[i]);
            JPanel temp = new JPanel();
            temp.setLayout(new BorderLayout());
            lbl = new JLabel(subs[i].toUpperCase());
            lbl.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
            flds[i] = new JTextField(marks[i], 10);
            flds[i].setPreferredSize(new Dimension(60, 30));
            temp.add(lbl, BorderLayout.NORTH);
            temp.add(flds[i], BorderLayout.CENTER);
            left.add(temp);
            i++;
        }

        left.setBorder(BorderFactory.createEmptyBorder(10, 100, 20, 10));
        //right.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 100));

        listStdn.setLayout(new BorderLayout());
        listStdn.add(lblInfo, BorderLayout.NORTH);
        listStdn.add(left, BorderLayout.WEST);
        listStdn.add(right, BorderLayout.EAST);

        return listStdn;
    }

    public static String[] listClass() {
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

    public static String[] listStdn() {
        String[] lst = {"std_roll", "std_name", "std_lname"};
        ArrayList<ArrayList<Object>> list = SqlLibrary.select("student", "*", "class_id='" + EditMarksPanel.stdClass + "'", lst);
        int rows = list.size();
        String[] cls = new String[rows + 1];
        cls[0] = "       ------|  Select Student  |------";
        System.out.println(rows);
        if (rows > 0) {
            for (int i = 0; i < rows; i++) {
                cls[i + 1] = (String) ((ArrayList<?>) list.get(i)).get(1) + "--" + (String) ((ArrayList<?>) list.get(i)).get(2) + ""
                        + " " + (String) ((ArrayList<?>) list.get(i)).get(3);;
            }
        }

        return cls;
    }

    public static String[] listSub() {
        String[] lst = {"sub_id", "sub_name"};
        ArrayList<ArrayList<Object>> list = SqlLibrary.select("subject", "*", "class_id='" + EditMarksPanel.stdClass + "'", lst);
        int rows = list.size();
        String[] cls = new String[rows];
        System.out.println(rows);
        if (rows > 0) {
            for (int i = 0; i < rows; i++) {
                cls[i] = (String) ((ArrayList<?>) list.get(i)).get(1) + "-" + (String) ((ArrayList<?>) list.get(i)).get(2);
            }
        }

        return cls;
    }

    public static String[] listSubMark() {
        String[] lst = {"std_id", "mrk_obt"};
        ArrayList<ArrayList<Object>> list = SqlLibrary.select("marks", "*", "std_id=" + EditMarksPanel.stdnId + "", lst);
        int rows = list.size();
        String[] cls = new String[rows];
        System.out.println(rows);
        if (rows > 0) {
            for (int i = 0; i < rows; i++) {
                cls[i] = (String) ((ArrayList<?>) list.get(i)).get(2);
            }
        }

        return cls;
    }

    public String GetId(String str) {
        String[] hold = str.split("-");
        return hold[0];
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
            boolean status = false;
            int i = 0;
            for (JTextField txt : flds) {
                if (txt.getText().equals("")) {
                    status = true;
                    break;
                }
            }
            if (jcbStdn.getSelectedIndex() != 0) {

                if (status) {
                    int yes = JOptionPane
                            .showConfirmDialog(
                                    null,
                                    "Some fields are missing value default 40 will be used.",
                                    "Make Sure", 2,
                                    JOptionPane.OK_CANCEL_OPTION);

                    if (yes == JOptionPane.OK_OPTION) {
                        int stdnId = Integer.parseInt(GetId((String) jcbStdn
                                .getSelectedItem()));
                        String subId[] = InsertMarks.listSub();
                        try {
                            for (JTextField txt : flds) {
                                int sub_id = Integer.parseInt(GetId(subId[i]));
                                System.out.println(sub_id);
                                if (txt.getText().equals("")) {
                                    SqlLibrary
                                            .update("marks", "mrk_obt='40'",
                                                    " sub_id=" + sub_id + " and std_id=" + stdnId);
                                } else {
                                    int marks = Integer.parseInt(txt.getText());
                                    SqlLibrary.update("marks", "mrk_obt='" + marks + "'",
                                            " sub_id=" + sub_id + " and std_id=" + stdnId);
                                }
                                i++;
                            }
                            JOptionPane.showMessageDialog(null, "The Result is successfully Updated.", "Success", JOptionPane.INFORMATION_MESSAGE);

                        } catch (Exception exp) {
                            SqlLibrary.delete("marks", " std_id=" + stdnId);
                            JOptionPane.showMessageDialog(null, "Sorry you input strings in marks field. The result is collapsed.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {

                    int stdnId = Integer.parseInt(GetId((String) jcbStdn
                            .getSelectedItem()));
                    String subId[] = InsertMarks.listSub();
                    try {
                        for (JTextField txt : flds) {
                            int sub_id = Integer.parseInt(GetId(subId[i]));
                            System.out.println(sub_id);
                            if (txt.getText().equals("")) {
                                SqlLibrary
                                        .update("marks", "mrk_obt='40'",
                                                " sub_id=" + sub_id + " and std_id=" + stdnId);
                            } else {
                                int marks = Integer.parseInt(txt.getText());
                                SqlLibrary.update("marks", "mrk_obt='" + marks + "'",
                                        " sub_id=" + sub_id + " and std_id=" + stdnId);
                            }
                            i++;
                        }
                        JOptionPane.showMessageDialog(null, "The Result is successfully Updated.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception exp) {
                        SqlLibrary.delete("marks", " std_id=" + stdnId);
                        JOptionPane.showMessageDialog(null, "Sorry you input strings in marks field.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }

            } else {
                JOptionPane.showMessageDialog(null,
                        "You must select a student !", "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public class resetHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            int yes = JOptionPane.showConfirmDialog(null, "Do you wnat to reset the panel.", "Reset the fields", 2, JOptionPane.OK_CANCEL_OPTION);
            if (yes == JOptionPane.OK_OPTION) {
                jcbStdn.setSelectedIndex(0);
                for (JTextField txt : flds) {
                    txt.setText("");
                }
            } else {
            }
        }
    }

    public class tabHandler implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception unused) {
            ; // Ignore exception because we can't do anything. Will use
            // default.
        }
        EditMarksPanel me = new EditMarksPanel(0);
    }
}
