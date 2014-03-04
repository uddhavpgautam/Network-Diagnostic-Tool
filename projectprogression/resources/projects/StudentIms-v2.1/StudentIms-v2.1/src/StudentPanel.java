
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

public class StudentPanel extends JFrame {

    boolean DEBUG;
    public JTable jt;
    public static String stdClass = "";
    boolean selected = false;
    public static boolean staus = false;
    private JButton btnAdd, btnReset, btnDash, btnExit, btnLogout, btnEdit,
            btnDelete;
    private JTextField txtName, txtLname, txtRoll, txtParentName, txtDob,
            txtParentPhone, txtHomePhone, txtAddress;
    @SuppressWarnings("rawtypes")
    private JComboBox jcbClass, jcbSex;
    private JLabel lblName, lblLastName, lblRoll, lblParentName, lblDob,
            lblParentPhone, lblHomePhome, lblAddress, lblClass, lblSex;
    public JTable tblListStdn;
    private Mymodel model = new Mymodel();
    private static final long serialVersionUID = 1L;

    public StudentPanel(int index) {
        super("Student Index");
        final JTabbedPane tabStudent = new JTabbedPane();
        JPanel pnlAdd = new JPanel();
        JPanel pnlList = new JPanel();

        Font font = new Font("Comic Sans MS", Font.PLAIN, 13);

        JPanel addRest = new JPanel();
        addRest.setLayout(new GridLayout(1, 2, 10, 0));
        addRest.setBorder(BorderFactory.createEmptyBorder(0, 100, 10, 420));
        btnAdd = new JButton("Add");
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

        JPanel listEdit = new JPanel();
        listEdit.setLayout(new BorderLayout());
        listEdit.add(this.listStudentPanel(), BorderLayout.NORTH);
        listEdit.add(this.editDelete(), BorderLayout.CENTER);

        pnlList.setLayout(new FlowLayout());
        pnlList.add(listEdit);

        tabStudent.addTab("Add Student", new ImageIcon("img/add.png"), pnlAdd);
        tabStudent.addTab("List Student", new ImageIcon("img/list.png"),
                pnlList);
        tabStudent.addChangeListener(new ChangeListener() {
            @SuppressWarnings("unused")
            @Override
            public void stateChanged(ChangeEvent e) {
                if (tabStudent.getSelectedIndex() == 1) {
                    if (StudentPanel.stdClass.equals("")) {
                        try {
                            dispose();
                            AllClass pnl = new AllClass(1);
                        } catch (Exception exp) {
                            JOptionPane.showMessageDialog(null, "You must input class as Integer Values.", "Error", JOptionPane.ERROR_MESSAGE);
                            MainPanel mp = new MainPanel();
                        }
                    }
                }
            }
        });

        tabStudent.setSelectedIndex(index);
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

    @SuppressWarnings("serial")
    public JPanel listStudentPanel() {
        JPanel stdnAdd = new JPanel();

        jt = new JTable(model) {
            public Component prepareRenderer(TableCellRenderer r, int rows,
                    int columns) {
                Component c = super.prepareRenderer(r, rows, columns);

                if (rows % 2 == 0) {
                    c.setBackground(new Color(208, 208, 208));
                } else {
                    c.setBackground(new Color(192, 192, 192));
                }

                if (isCellSelected(rows, columns)) {
                    c.setBackground(new Color(150, 150, 150));
                }

                return c;
            }
        };

        DefaultTableCellRenderer rdr = new DefaultTableCellRenderer();
        rdr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < 8; i++) {
            jt.getColumnModel().getColumn(i).setCellRenderer(rdr);
        }

        jt.setPreferredScrollableViewportSize(new Dimension(730, 260));
        jt.setFillsViewportHeight(true);
        // enable selection to only
        jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jt.getTableHeader().setBackground(Color.BLUE);
        jt.getTableHeader().setForeground(Color.BLUE);

        jt.setAutoCreateRowSorter(true);
        jt.setRowHeight(25);

        JScrollPane jps = new JScrollPane(jt);
        stdnAdd.add(jps);

        return stdnAdd;
    }

    public JPanel editDelete() {

        editViewHandler edit = new editViewHandler();
        JPanel pnlEditDel = new JPanel();
        pnlEditDel.setLayout(new GridLayout(1, 2, 10, 0));
        pnlEditDel.setBorder(BorderFactory.createEmptyBorder(0, 4, 10, 520));

        btnEdit = new JButton("Edit / View");
        btnEdit.addActionListener(edit);
        btnEdit.setPreferredSize(new Dimension(90, 35));
        btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int row = jt.convertRowIndexToModel(jt.getSelectedRow());
                    int col = jt.convertColumnIndexToModel(jt.getSelectedColumn());
                    if (jt.isCellSelected(row, col)) {
                        int temp = Integer.parseInt("" + jt.getValueAt(row, 1));
                        int i = JOptionPane.showConfirmDialog(null, "Do you want to Perform Delete", "Delete", JOptionPane.OK_CANCEL_OPTION);
                        if (i == JOptionPane.OK_OPTION) {
                            SqlLibrary.delete("student", " std_roll =" + temp);
                            model.deleteRow(row);
                        }
                    }
                } catch (Exception exp) {
                    JOptionPane.showMessageDialog(null, "You Must Select a row to edit the record.", "Warning!", JOptionPane.WARNING_MESSAGE);
                }

            }
        });

        pnlEditDel.add(btnEdit);
        pnlEditDel.add(btnDelete);
        return pnlEditDel;

    }

    @SuppressWarnings({"unchecked", "rawtypes", "static-access"})
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

        JPanel pnlname = new JPanel();
        pnlname.setLayout(new BorderLayout());
        lblName = new JLabel("First Name:");
        txtName = new JTextField("", 20);
        txtName.setPreferredSize(new Dimension(100, 30));
        pnlname.add(lblName, BorderLayout.NORTH);
        pnlname.add(txtName, BorderLayout.CENTER);
        left.add(pnlname);

        JPanel pnlcls = new JPanel();
        pnlcls.setLayout(new BorderLayout());
        lblClass = new JLabel("Class:");
        jcbClass = new JComboBox(this.listClass());
        pnlcls.add(lblClass, BorderLayout.NORTH);
        pnlcls.add(jcbClass, BorderLayout.CENTER);
        left.add(pnlcls);

        JPanel pnlParentname = new JPanel();
        pnlParentname.setLayout(new BorderLayout());
        lblParentName = new JLabel("Parent Name:");
        txtParentName = new JTextField("", 20);
        pnlParentname.add(lblParentName, BorderLayout.NORTH);
        pnlParentname.add(txtParentName, BorderLayout.CENTER);
        left.add(pnlParentname);

        JPanel pnlParentPh = new JPanel();
        pnlParentPh.setLayout(new BorderLayout());
        lblParentPhone = new JLabel("Parent Phone");
        txtParentPhone = new JTextField("", 30);
        pnlParentPh.add(lblParentPhone, BorderLayout.NORTH);
        pnlParentPh.add(txtParentPhone, BorderLayout.CENTER);
        left.add(pnlParentPh);

        JPanel pnlAddress = new JPanel();
        pnlAddress.setLayout(new BorderLayout());
        lblAddress = new JLabel("Address :");
        txtAddress = new JTextField("", 30);
        pnlAddress.add(lblAddress, BorderLayout.NORTH);
        pnlAddress.add(txtAddress, BorderLayout.CENTER);
        left.add(pnlAddress);

        JPanel pnlLastname = new JPanel();
        pnlLastname.setLayout(new BorderLayout());
        lblLastName = new JLabel("Lastname:");
        txtLname = new JTextField("", 30);
        pnlLastname.add(lblLastName, BorderLayout.NORTH);
        pnlLastname.add(txtLname, BorderLayout.CENTER);
        right.add(pnlLastname);

        JPanel pnlRoll = new JPanel();
        pnlRoll.setLayout(new BorderLayout());
        lblRoll = new JLabel("Roll NO:");
        txtRoll = new JTextField("", 20);
        pnlRoll.add(lblRoll, BorderLayout.NORTH);
        pnlRoll.add(txtRoll, BorderLayout.CENTER);
        right.add(pnlRoll);

        JPanel pnlDob = new JPanel();
        pnlDob.setLayout(new BorderLayout());
        lblDob = new JLabel("D.O.B:");
        txtDob = new JTextField("", 20);
        pnlDob.add(lblDob, BorderLayout.NORTH);
        pnlDob.add(txtDob, BorderLayout.CENTER);
        right.add(pnlDob);

        JPanel pnlHomPhone = new JPanel();
        pnlHomPhone.setLayout(new BorderLayout());
        lblHomePhome = new JLabel("Home Phone NO:");
        txtHomePhone = new JTextField("", 20);
        pnlHomPhone.add(lblHomePhome, BorderLayout.NORTH);
        pnlHomPhone.add(txtHomePhone, BorderLayout.CENTER);
        right.add(pnlHomPhone);

        JPanel pnlSex = new JPanel();
        pnlSex.setLayout(new BorderLayout());
        lblSex = new JLabel("Sex:");
        jcbSex = new JComboBox(this.listSex());
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

    public String[] listSex() {
        String[] cls = {"       ------|  Select Sex  |------", "Male", "Female"};
        return cls;
    }

    @SuppressWarnings("serial")
    class Mymodel extends AbstractTableModel {

        String columns[];
        String[] lst = {"std_roll", "std_name", "std_lname", "std_parentName", "std_parentPhone", "std_Address", "std_Sex"};
        ArrayList<ArrayList<Object>> data = null;

        public Mymodel() {
            prepareCol();
            prepareData();
        }

        private void prepareData() {

            data = SqlLibrary.select("student", "std_roll,std_name,std_lname,std_parentName,std_parentPhone"
                    + ",std_Address,std_Sex", "class_id ='" + StudentPanel.stdClass + "'", lst);
        }

        private void prepareCol() {
            columns = new String[]{"S.N", "Roll No", "First Name", "Last Name", "Parent", "Parent Ph.", "Address", "Sex"};
        }

        @Override
        public String getColumnName(int columnIndex) {
            return columns[columnIndex];
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data.get(row).get(col);
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int colIndex) {
            data.get(rowIndex).set(colIndex, (String) aValue);
            fireTableCellUpdated(rowIndex, colIndex);
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return false;
        }

        @SuppressWarnings("unused")
        public void deleteRow(int row) {
            ArrayList<Object> temp = data.get(row);
            data.remove(row);
            fireTableRowsDeleted(row, row);

        }
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
            if (txtName.getText().equals("") || txtLname.getText().equals("") || txtRoll.getText().equals("")
                    || txtParentName.getText().equals("") || txtDob.getText().equals("")
                    || txtParentPhone.getText().equals("") || txtHomePhone.getText().equals("") || txtAddress.getText().equals("")
                    || jcbClass.getSelectedIndex() == 0 || jcbSex.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "All Fileds Are Required", "Error !", JOptionPane.ERROR_MESSAGE);
            } else {
                if (SqlLibrary.Insert("student", "std_roll,std_name,std_lname,class_id,std_parentName,std_parentPhone"
                        + ",std_Address,std_Dob,std_homePh,std_Sex", "'" + txtRoll.getText() + "','" + txtName.getText() + "','"
                        + txtLname.getText() + "','" + jcbClass.getSelectedItem() + "','" + txtParentName.getText() + "','"
                        + txtParentPhone.getText() + "','" + txtAddress.getText() + "','" + txtDob.getText() + "','"
                        + txtHomePhone.getText() + "','" + jcbSex.getSelectedItem() + "'"));
                {
                    JOptionPane.showMessageDialog(null, "One row Inserted", "Success !", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    public class resetHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            int yes = JOptionPane.showConfirmDialog(null, "Do you wnat to reset the panel.", "Reset the fields", 2, JOptionPane.OK_CANCEL_OPTION);
            if (yes == JOptionPane.OK_OPTION) {
                txtName.setText("");
                txtLname.setText("");
                txtRoll.setText("");
                txtParentName.setText("");
                txtDob.setText("");
                txtParentPhone.setText("");
                txtHomePhone.setText("");
                txtAddress.setText("");
                jcbClass.setSelectedIndex(0);
                jcbSex.setSelectedIndex(0);
            } else {
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
        StudentPanel me = new StudentPanel(0);

    }
}
