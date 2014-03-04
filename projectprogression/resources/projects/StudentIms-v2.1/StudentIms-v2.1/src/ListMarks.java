
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
public class ListMarks extends JFrame {

    boolean DEBUG;
    public JTable jt;
    public static String stdClass = "";
    boolean selected = false;
    private JButton btnDash, btnExit, btnLogout, btnEdit, btnDelete;
    private JTextField txtSubName, txtPublication, txtEdition, txtAutours,
            txtFm, txtPm;
    @SuppressWarnings("rawtypes")
    private JComboBox jcbClass, jcbTeacher;
    public JTable tblListStdn;
    private Mymodel model = new Mymodel();
    private static final long serialVersionUID = 1L;

    public ListMarks(int index) {
        super("Subject Index");
        final JTabbedPane tabStudent = new JTabbedPane();
        JPanel pnlList = new JPanel();

        Font font = new Font("Comic Sans MS", Font.PLAIN, 13);

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

        JPanel listEdit = new JPanel();
        listEdit.setLayout(new BorderLayout());
        listEdit.add(this.listStudentPanel(), BorderLayout.NORTH);
        listEdit.add(this.editDelete(), BorderLayout.CENTER);

        pnlList.setLayout(new FlowLayout());
        pnlList.add(listEdit);

        tabStudent.addTab(
                "Select Row to view the result of the respective Student.",
                new ImageIcon("img/list.png"), pnlList);

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
        for (int i = 0; i < 6; i++) {
            jt.getColumnModel().getColumn(i).setCellRenderer(rdr);
        }

        jt.setPreferredScrollableViewportSize(new Dimension(730, 265));
        // jt.setFillsViewportHeight(true);

        // enable selection to only
        jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jt.getTableHeader().setBackground(Color.BLUE);
        jt.getTableHeader().setForeground(Color.BLUE);
        // jt.setShowGrid(false);
        // jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        jt.setAutoCreateRowSorter(true);
        jt.setRowHeight(25);

        JScrollPane jps = new JScrollPane(jt,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        stdnAdd.add(jps);

        return stdnAdd;
    }

    public JPanel editDelete() {

        editViewHandler edit = new editViewHandler();
        JPanel pnlEditDel = new JPanel();
        pnlEditDel.setLayout(new GridLayout(1, 2, 10, 0));
        pnlEditDel.setBorder(BorderFactory.createEmptyBorder(0, 4, 10, 510));

        btnEdit = new JButton("View Marksheet");
        btnEdit.addActionListener(edit);
        btnEdit.setPreferredSize(new Dimension(110, 35));

        btnDelete = new JButton("Delete");

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int row = jt.convertRowIndexToModel(jt.getSelectedRow());
                    int col = jt.convertColumnIndexToModel(jt
                            .getSelectedColumn());
                    if (jt.isCellSelected(row, col)) {
                        int temp = Integer.parseInt("" + jt.getValueAt(row, 1));
                        int i = JOptionPane.showConfirmDialog(null,
                                "Do you want to Perform Delete", "Delete",
                                JOptionPane.OK_CANCEL_OPTION);
                        if (i == JOptionPane.OK_OPTION) {
                            SqlLibrary.delete("marks", " std_id =" + temp);
                            model.deleteRow(row);
                        }
                    }
                } catch (Exception exp) {
                    JOptionPane.showMessageDialog(null,
                            "You Must Select a row to edit the record.",
                            "Warning!", JOptionPane.WARNING_MESSAGE);
                }

            }
        });

        pnlEditDel.add(btnEdit);
        pnlEditDel.add(btnDelete);
        return pnlEditDel;

    }

    public static String[] listSub() {
        String[] lst = {"sub_id", "sub_name"};
        ArrayList<ArrayList<Object>> list = SqlLibrary.select("subject", "*",
                "class_id='nursery'", lst);
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

            data = SqlLibrary.selResult("class_id = '" + ListMarks.stdClass + "'");
        }

        private void prepareCol() {
            String[] temp = {"S.N", "Student Roll", "Student Name", "Status", "Total Marks",
                "Percentage"};
            // ArrayList<String> lst = new ArrayList<String>();
            // int length = temp.length;
            // String[] hold = listSub();
            // int len = hold.length;
            // int j=0;
            //
            // String[] allInOne = new String[len+length];
            // for(String cp : temp){
            // lst.add(cp);
            // }
            //
            // for(String cp : hold){
            // lst.add(cp);
            // }
            //
            // for(int i = 0; i< lst.size(); i++){
            // //System.out.println(lst.get(i));
            // allInOne[i] = lst.get(i);
            // }
            //
            columns = temp;
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
                    int data = Integer.parseInt("" + jt.getValueAt(row, 1));
                    MarkSheet.name = "" + jt.getValueAt(row, 2);
                    MarkSheet.totalNo = "" + jt.getValueAt(row, 4);
                    MarkSheet.percent = "" + jt.getValueAt(row, 5);
                    MarkSheet.stat = "" + jt.getValueAt(row, 3);
                    MarkSheet.classId = ListMarks.stdClass;
                    //System.out.println(Float.parseFloat(MarkSheet.percent));
                    MarkSheet.holdId = data;
                    dispose();
                    MarkSheet pnl = new MarkSheet();
                }
            } catch (Exception exp) {
                // exp.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "You Must Select Class to Insert Marks for result.",
                        "Warning!", JOptionPane.WARNING_MESSAGE);
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
        ListMarks me = new ListMarks(0);

    }
}
