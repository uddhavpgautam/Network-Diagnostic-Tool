
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
public class AllClass extends JFrame {

    boolean DEBUG;
    public JTable jt;
    public static String stdClass = "";
    boolean selected = false;
    public static int holdCLs;
    private JButton btnDash, btnExit, btnLogout, btnEdit,
            btnDelete;
    private JTextField txtSubName, txtPublication, txtEdition, txtAutours, txtFm, txtPm;
    @SuppressWarnings("rawtypes")
    private JComboBox jcbClass, jcbTeacher;
    public JTable tblListStdn;
    private Mymodel model = new Mymodel();
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("static-access")
    public AllClass(int index) {
        super("Subject Index");
        this.holdCLs = index;

        final JTabbedPane tabStudent = new JTabbedPane();
        JPanel pnlList = new JPanel();

        Font font = new Font("Comic Sans MS", Font.PLAIN, 13);

        JPanel exitPanel = new JPanel();
        exitPanel.setLayout(new GridLayout(1, 3, 10, 0));
        exitPanel.setBorder(BorderFactory.createEmptyBorder(3, 500, 10, 10));

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

        JPanel listEdit = new JPanel();
        listEdit.setLayout(new BorderLayout());
        listEdit.add(this.listStudentPanel(), BorderLayout.NORTH);
        listEdit.add(this.editDelete(), BorderLayout.CENTER);

        pnlList.setLayout(new FlowLayout());
        pnlList.add(listEdit);

        tabStudent.addTab("Select Class To view Result", new ImageIcon("img/list.png"),
                pnlList);

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
        for (int i = 0; i < 3; i++) {
            jt.getColumnModel().getColumn(i).setCellRenderer(rdr);
        }

        jt.setPreferredScrollableViewportSize(new Dimension(730, 270));
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
        pnlEditDel.setBorder(BorderFactory.createEmptyBorder(0, 4, 10, 620));

        btnEdit = new JButton("Proceed Next");
        btnEdit.addActionListener(edit);
        btnEdit.setPreferredSize(new Dimension(90, 35));

        pnlEditDel.add(btnEdit);
        return pnlEditDel;

    }

    @SuppressWarnings("serial")
    class Mymodel extends AbstractTableModel {

        String columns[];
        String[] lst = {"class_id", "class"};
        ArrayList<ArrayList<Object>> data = null;

        public Mymodel() {
            prepareCol();
            prepareData();
        }

        private void prepareData() {

            data = SqlLibrary.select("class", "*", "1=1", lst);
        }

        private void prepareCol() {
            columns = new String[]{"S.N", "Class ID", "Class Name"};
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
                    String data = "" + jt.getValueAt(row, 2);
                    InsertMarks.stdClass = data;
                    if (InsertMarks.listSub().length > 0) {

                        dispose();
                        if (AllClass.holdCLs == 0) {
                            ListMarks.stdClass = data;
                            ListMarks pnl = new ListMarks(0);
                        } else if (AllClass.holdCLs == 1) {
                            StudentPanel.stdClass = data;
                            StudentPanel pnl = new StudentPanel(1);
                        } else if (AllClass.holdCLs == 2) {
                            StudentPanel.stdClass = data;
                            SubjectPanel pnl = new SubjectPanel(1);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "No subjects  were Found for Class " + InsertMarks.stdClass, "Warning!", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } catch (Exception exp) {
                //exp.printStackTrace();
                JOptionPane.showMessageDialog(null, "You Must Select Class to Insert Marks for result.", "Warning!", JOptionPane.WARNING_MESSAGE);
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
        AllClass me = new AllClass(0);

    }
}
