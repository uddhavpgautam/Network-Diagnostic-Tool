
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

public class SubjectPanel extends JFrame {

    boolean DEBUG;
    public JTable jt;
    public static String stdClass = "";
    boolean selected = false;
    private JButton btnAdd, btnReset, btnDash, btnExit, btnLogout, btnEdit,
            btnDelete;
    private JTextField txtSubName, txtPublication, txtEdition, txtAutours, txtFm, txtPm;
    @SuppressWarnings("rawtypes")
    private JComboBox jcbClass, jcbTeacher;
    private JLabel lblSubName, lblPublication, lblEidition, lblAuthors, lblTeacher, lblClass, lblPm, lblFm;
    public JTable tblListStdn;
    private Mymodel model = new Mymodel();
    private static final long serialVersionUID = 1L;

    public SubjectPanel(int index) {
        super("Subject Index");
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

        tabStudent.addTab("Add Subject", new ImageIcon("img/add.png"), pnlAdd);
        tabStudent.addTab("List Subject", new ImageIcon("img/list.png"),
                pnlList);
        tabStudent.addChangeListener(new ChangeListener() {
            @SuppressWarnings("unused")
            @Override
            public void stateChanged(ChangeEvent e) {
                if (tabStudent.getSelectedIndex() == 1) {
                    if (StudentPanel.stdClass.equals("")) {
                        try {
                            dispose();
                            AllClass cls = new AllClass(2);
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
        for (int i = 0; i < 7; i++) {
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
        pnlEditDel.setBorder(BorderFactory.createEmptyBorder(0, 5, 10, 550));

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
                            SqlLibrary.delete("subject", " sub_id =" + temp);
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

    @SuppressWarnings({"unchecked", "rawtypes"})
    public JPanel addStduentPanel() {
        JPanel listStdn = new JPanel();

        JLabel lblInfo = new JLabel(
                "Fill the following fields to Insert a Subject. The Full Mark and Pass Mark are 100 and 40 by default.");
        lblInfo.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 0));
        lblInfo.setForeground(new Color(50, 100, 250));
        lblInfo.setFont(new Font("Comic sans MS", Font.ITALIC, 12));

        JPanel me = new JPanel();
        me.setLayout(new GridLayout(5, 2, 5, 5));

        JPanel left = new JPanel();
        left.setLayout(new GridLayout(5, 1, 5, 5));

        JPanel right = new JPanel();
        right.setLayout(new GridLayout(5, 1, 5, 5));

        JPanel pnlname = new JPanel();
        pnlname.setLayout(new BorderLayout());
        lblSubName = new JLabel("Name of subject:");
        txtSubName = new JTextField("", 20);
        txtSubName.setPreferredSize(new Dimension(100, 30));
        pnlname.add(lblSubName, BorderLayout.NORTH);
        pnlname.add(txtSubName, BorderLayout.CENTER);
        left.add(pnlname);

        JPanel pnlcls = new JPanel();
        pnlcls.setLayout(new BorderLayout());
        lblClass = new JLabel("Class:");
        jcbClass = new JComboBox(StudentPanel.listClass());
        pnlcls.add(lblClass, BorderLayout.NORTH);
        pnlcls.add(jcbClass, BorderLayout.CENTER);
        left.add(pnlcls);

        JPanel pnlParentname = new JPanel();
        pnlParentname.setLayout(new BorderLayout());
        lblPublication = new JLabel("Publications:");
        txtPublication = new JTextField("", 20);
        pnlParentname.add(lblPublication, BorderLayout.NORTH);
        pnlParentname.add(txtPublication, BorderLayout.CENTER);
        left.add(pnlParentname);

        JPanel pnlParentPh = new JPanel();
        pnlParentPh.setLayout(new BorderLayout());
        lblEidition = new JLabel("Edition:");
        txtEdition = new JTextField("", 30);
        pnlParentPh.add(lblEidition, BorderLayout.NORTH);
        pnlParentPh.add(txtEdition, BorderLayout.CENTER);
        left.add(pnlParentPh);

        //String[] temp = {"Select teacher"};
        JPanel pnlLastname = new JPanel();
        pnlLastname.setLayout(new BorderLayout());
        lblTeacher = new JLabel("Subject Teacher:");
        jcbTeacher = new JComboBox(SubjectPanel.listTeacher());
        jcbTeacher.setPreferredSize(new Dimension(100, 30));
        pnlLastname.add(lblTeacher, BorderLayout.NORTH);
        pnlLastname.add(jcbTeacher, BorderLayout.CENTER);
        left.add(pnlLastname);

        JPanel pnlAddress = new JPanel();
        pnlAddress.setLayout(new BorderLayout());
        lblAuthors = new JLabel("Authors :");
        txtAutours = new JTextField("", 30);
        pnlAddress.add(lblAuthors, BorderLayout.NORTH);
        pnlAddress.add(txtAutours, BorderLayout.CENTER);
        right.add(pnlAddress);

        JPanel pnlfm = new JPanel();
        pnlfm.setLayout(new BorderLayout());
        lblFm = new JLabel("Full Marks :");
        txtFm = new JTextField("100", 30);
        pnlfm.add(lblFm, BorderLayout.NORTH);
        pnlfm.add(txtFm, BorderLayout.CENTER);
        right.add(pnlfm);

        JPanel pnlpm = new JPanel();
        pnlpm.setLayout(new BorderLayout());
        lblPm = new JLabel("Pass Marks :");
        txtPm = new JTextField("40", 30);
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
        System.out.println(rows);
        if (rows > 0) {
            for (int i = 0; i < rows; i++) {
                cls[i + 1] = (String) ((ArrayList<?>) list.get(i)).get(2) + " " + (String) ((ArrayList<?>) list.get(i)).get(1);
            }
        }
        return cls;
    }

    @SuppressWarnings("serial")
    class Mymodel extends AbstractTableModel {

        String columns[];
        String[] lst = {"sub_id", "sub_name", "class_id", "sub_teach", "sub_publication", "sub_authors", "sub_edition"};
        ArrayList<ArrayList<Object>> data = null;

        public Mymodel() {
            prepareCol();
            prepareData();
        }

        private void prepareData() {

            data = SqlLibrary.select("subject", "*", "class_id ='" + StudentPanel.stdClass + "'", lst);
        }

        private void prepareCol() {
            columns = new String[]{"S.N", "Subject ID", "Subject Name", "Subject Class", "Subject Teacher", "Subject Publication", "Author"};
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
                    EditSubjectPanel.sid = data;
                    dispose();
                    @SuppressWarnings("unused")
                    EditSubjectPanel pnl = new EditSubjectPanel(1);
                }
            } catch (Exception exp) {
                JOptionPane.showMessageDialog(null, "You Must Select a row to edit the record.", "Warning!", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public class addHadler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (txtSubName.getText().equals("") || txtPublication.getText().equals("") || txtAutours.getText().equals("")
                    || jcbClass.getSelectedIndex() == 0 || jcbTeacher.getSelectedIndex() == 0 || txtEdition.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "All Fileds Are Required", "Error !", JOptionPane.ERROR_MESSAGE);
            } else {
                if (SqlLibrary.Insert("subject", "sub_name,sub_publication,class_id,sub_authors,sub_edition,sub_teach,sub_fm,sub_pm",
                        "'" + txtSubName.getText() + "','" + txtPublication.getText() + "','" + jcbClass.getSelectedItem() + "',"
                        + "'" + txtAutours.getText() + "','" + txtEdition.getText() + "','" + jcbTeacher.getSelectedItem() + "','" + Integer.parseInt(txtFm.getText()) + "','" + Integer.parseInt(txtPm.getText()) + "'"));
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
                txtAutours.setText("");
                txtEdition.setText("");
                txtSubName.setText("");
                txtPublication.setText("");
                jcbClass.setSelectedIndex(0);
                jcbTeacher.setSelectedIndex(0);
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
        SubjectPanel me = new SubjectPanel(0);

    }
}
