
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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings({"serial", "unused"})
public class ClassPanel extends JFrame {

    public JTable jt;
    boolean selected = false;
    private JButton btnAdd, btnReset, btnDash, btnExit, btnLogout, btnEdit,
            btnDelete;
    private JTextField txtClass, txtSection;
    private JLabel lblClass, lblSection;
    public JTable tblListStdn;
    private Mymodel model = new Mymodel();

    public ClassPanel(int index) {
        super("Class Index");
        JTabbedPane tabStudent = new JTabbedPane();
        JPanel pnlAdd = new JPanel();
        JPanel pnlList = new JPanel();

        Font font = new Font("Comic Sans MS", Font.PLAIN, 13);

        JPanel addRest = new JPanel();
        addRest.setLayout(new GridLayout(1, 2, 10, 0));
        addRest.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 420));
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
        exitPanel.setBorder(BorderFactory.createEmptyBorder(2, 500, 10, 10));

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

        // lsit panel
        // JScrollPane pane = new JScrollPane();
        // pane.setSize(700, 300);
        JPanel listEdit = new JPanel();
        listEdit.setLayout(new BorderLayout());
        listEdit.add(this.listStudentPanel(), BorderLayout.NORTH);
        listEdit.add(this.editDelete(), BorderLayout.CENTER);

        pnlList.setLayout(new FlowLayout());
        pnlList.add(listEdit);

        tabStudent.addTab("Add Class", new ImageIcon("img/add.png"), pnlAdd);
        tabStudent.addTab("List Class", new ImageIcon("img/list.png"),
                pnlList);
        tabStudent.setSelectedIndex(index);
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
        //jt.getColumn("Class").setCellRenderer(rdr);
        jt.getColumnModel().getColumn(0).setCellRenderer(rdr);
        jt.getColumnModel().getColumn(1).setCellRenderer(rdr);
        jt.getColumnModel().getColumn(2).setCellRenderer(rdr);
        jt.getColumnModel().getColumn(3).setCellRenderer(rdr);

        //JTableHeader jtHead = jt.getTableHeader();
        //jtHead.setForeground(Color.BLUE);
        //jtHead.setFont(new Font("Sans sarif",Font.PLAIN,15));
        jt.setPreferredScrollableViewportSize(new Dimension(730, 260));
        jt.setFillsViewportHeight(true);
        // enable selection to only
        jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
        pnlEditDel.setBorder(BorderFactory.createEmptyBorder(0, 4, 10, 420));

        btnEdit = new JButton("Edit / View");
        btnEdit.addActionListener(edit);
        btnEdit.setPreferredSize(new Dimension(90, 35));
        btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int row = jt.convertRowIndexToModel(jt.getSelectedRow());
                    int i = JOptionPane.showConfirmDialog(null, "Do you want to Perform Delete", "Delete", JOptionPane.OK_CANCEL_OPTION);
                    if (i == JOptionPane.OK_OPTION) {
                        if (SqlLibrary.delete("class", "class_id=" + row)) {
                            model.deleteRow(row);
                        }
                        JOptionPane.showMessageDialog(null, "The row is successfully deleted", "Success", JOptionPane.INFORMATION_MESSAGE);
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

    @SuppressWarnings({})
    public JPanel addStduentPanel() {
        JPanel listStdn = new JPanel();

        JLabel lblInfo = new JLabel(
                "Fill the following fields to Insert a New Class. * Indicates Required Field.");
        lblInfo.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 0));

        JPanel me = new JPanel();
        me.setLayout(new GridLayout(5, 2, 5, 5));

        JPanel left = new JPanel();
        left.setLayout(new GridLayout(5, 1, 5, 5));

        JPanel right = new JPanel();
        right.setLayout(new GridLayout(5, 1, 5, 5));

        JPanel pnlname = new JPanel();
        pnlname.setLayout(new BorderLayout());
        lblClass = new JLabel("Class :");
        txtClass = new JTextField("", 20);
        txtClass.setPreferredSize(new Dimension(100, 30));
        pnlname.add(lblClass, BorderLayout.NORTH);
        pnlname.add(txtClass, BorderLayout.CENTER);
        left.add(pnlname);

        JPanel pnlcls = new JPanel();
        pnlcls.setLayout(new BorderLayout());
        lblSection = new JLabel("No of Sections:");
        txtSection = new JTextField("", 20);
        pnlcls.add(lblSection, BorderLayout.NORTH);
        pnlcls.add(txtSection, BorderLayout.CENTER);
        left.add(pnlcls);

        left.setBorder(BorderFactory.createEmptyBorder(10, 100, 20, 10));
        right.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 100));

        listStdn.setLayout(new BorderLayout());
        listStdn.add(lblInfo, BorderLayout.NORTH);
        listStdn.add(left, BorderLayout.WEST);
        listStdn.add(right, BorderLayout.EAST);
        // listStdn.setSize(780, 380);

        return listStdn;
    }

    class Mymodel extends AbstractTableModel {

        String columns[];
        String[] lst = {"class_id", "class", "class_sec"};
        ArrayList<ArrayList<Object>> data = null;

        public Mymodel() {
            prepareCol();
            prepareData();
        }

        private void prepareData() {
            data = SqlLibrary.select("class", "*", " 1 = 1", lst);

        }

        private void prepareCol() {
            columns = new String[]{"S.N.", "Class ID", "Class", "No of Sections"};
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

        public void deleteRow(int row) {
            ArrayList<Object> temp = data.get(row);//backup of value in case of IOException while writing to file
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
                    Object data = jt.getValueAt(row, 1);
                    int dat = Integer.parseInt((String) data);
                    System.out.println(dat);
                    dispose();

                    EditClass pnl = new EditClass(dat);
                }
            } catch (Exception exp) {
                JOptionPane.showMessageDialog(null, "You Must Select a row to edit the record.", "Warning!", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public class addHadler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String section = " ";
            if (txtClass.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "All Fileds Are Required", "Error !", JOptionPane.ERROR_MESSAGE);
            } else {
                if (txtSection.getText().equals("")) {
                    section = "''";
                } else {
                    section = "'" + txtSection.getText() + "'";
                }

                if (SqlLibrary.Insert("class", "class,class_sec", "'" + txtClass.getText() + "'," + section)) {
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
                txtClass.setText("");
                txtSection.setText("");
            } else {
            }
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception unused) {
            ; // Ignore exception because we can't do anything. Will use
            // default.
        }

        ClassPanel me = new ClassPanel(0);

    }
}
