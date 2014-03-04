
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
import javax.swing.table.TableColumn;

@SuppressWarnings("unused")
public class MarkSheet extends JFrame {

    private static final long serialVersionUID = 1L;
    public static int holdId;
    public static String name, totalNo, percent, stat, classId;
    private JLabel lblName, lblDob, lblRoll, lblClass;
    private JButton btnEdit, btnBack, btnDash, btnLogout, btnExit;
    private JTextField txtName, txtDob, txtRoll, txtClass;
    private JTable tblMrk;
    private Mymodel model = new Mymodel();
    private JTable tblMark;

    public MarkSheet() {
        super("Mark Sheet");

        JPanel firstThree = new JPanel();
        firstThree.setLayout(new BorderLayout());
        firstThree.add(descRip(), BorderLayout.NORTH);
        firstThree.add(showMarks(), BorderLayout.CENTER);
        firstThree.add(Summary(), BorderLayout.SOUTH);

        JPanel secondTwo = new JPanel();
        secondTwo.setLayout(new BorderLayout());
        secondTwo.add(firstThree, BorderLayout.NORTH);
        secondTwo.add(editDelete(), BorderLayout.CENTER);
        secondTwo.add(exitL(), BorderLayout.SOUTH);
        secondTwo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true), "-| Marksheet Ledger |-"));

        JPanel third = new JPanel();
        third.setLayout(new GridLayout(1, 1));
        third.add(secondTwo);
        third.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.pack();
        this.setContentPane(third);
        this.setSize(620, 630);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception unused) {
            ; // Ignore exception because we can't do anything. Will use
            // default.
        }
        // TODO Auto-generated method stub
        MarkSheet sht = new MarkSheet();
    }

    public JPanel descRip() {
        JPanel icon = new JPanel();
        icon.setLayout(new BorderLayout());
        JLabel ico = new JLabel(new ImageIcon("img/male.png"));
        ico.setBorder(BorderFactory.createEmptyBorder(10, 50, 0, 50));

        //Stirng[] rows = SqlLibrary.selectOneRow("std_roll", table, cond, flds)
        JPanel desc = new JPanel();
        lblName = new JLabel("Full Name :-");
        lblDob = new JLabel("Date Of Birth :-");
        lblRoll = new JLabel("Roll No. :-");
        lblClass = new JLabel("Class :-");

        txtName = new JTextField(MarkSheet.name, 20);
        txtName.setEditable(false);
        txtName.setPreferredSize(new Dimension(90, 20));
        txtDob = new JTextField("2048-12-30", 20);
        txtDob.setEditable(false);
        txtDob.setPreferredSize(new Dimension(90, 20));
        txtRoll = new JTextField("" + holdId, 20);
        txtRoll.setEditable(false);
        txtRoll.setPreferredSize(new Dimension(90, 20));
        txtClass = new JTextField(classId, 20);
        txtClass.setEditable(false);
        txtClass.setPreferredSize(new Dimension(90, 20));

        desc.setLayout(new GridLayout(4, 2, 3, 3));
        desc.setBorder(BorderFactory.createEmptyBorder(10, 70, 5, 10));
        desc.add(lblName);
        desc.add(txtName);
        desc.add(lblDob);
        desc.add(txtDob);
        desc.add(lblRoll);
        desc.add(txtRoll);
        desc.add(lblClass);
        desc.add(txtClass);

        icon.add(ico, BorderLayout.WEST);
        icon.add(desc, BorderLayout.CENTER);

        return icon;
    }

    public JPanel showMarks() {
        JPanel allSubs = new JPanel();
        tblMark = new JTable(model) {
            private static final long serialVersionUID = 1L;

            public Component prepareRenderer(TableCellRenderer r, int rows, int cols) {
                Component c = super.prepareRenderer(r, rows, cols);

                if (rows % 2 == 0) {
                    c.setBackground(new Color(208, 208, 208));
                } else {
                    c.setBackground(new Color(192, 192, 192));
                }

                if (isCellSelected(rows, cols)) {
                    c.setBackground(new Color(150, 150, 150));
                }

                return c;
            }
        };

        //define renderer to palce the contents of the cell in the 
        //center of each cell in the jtable
        DefaultTableCellRenderer rdr = new DefaultTableCellRenderer();
        rdr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < 6; i++) {
            tblMark.getColumnModel().getColumn(i).setCellRenderer(rdr);
        }

        tblMark.getColumnModel().getColumn(1).setPreferredWidth(120);
        tblMark.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblMark.getColumnModel().getColumn(2).setPreferredWidth(50);
        tblMark.getColumnModel().getColumn(3).setPreferredWidth(50);
        //column.setPreferredWidth(100);

        tblMark.getTableHeader().setBackground(Color.BLUE);
        tblMark.getTableHeader().setForeground(Color.BLUE);

        tblMark.setAutoCreateRowSorter(true);
        tblMark.setRowHeight(30);

        tblMark.setPreferredScrollableViewportSize(new Dimension(570, 240));
        tblMark.setFillsViewportHeight(true);

        //allow to select only one row at a time
        tblMark.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane jps = new JScrollPane(tblMark);
        allSubs.add(jps);

        return allSubs;
    }

    public JPanel Summary() {
        JPanel allgui = new JPanel();
        allgui.setLayout(new GridLayout(3, 1, 5, 5));

        JPanel totalMrk = new JPanel();
        totalMrk.setLayout(new GridLayout(1, 2));
        JLabel lblTol = new JLabel("Total Marks Obtained");
        JLabel lblMark = new JLabel(MarkSheet.totalNo);
        totalMrk.add(lblTol);
        totalMrk.add(lblMark);

        JPanel Percent = new JPanel();
        Percent.setLayout(new GridLayout(1, 2));
        JLabel lblPer = new JLabel("Percentage");
        JLabel lblper = new JLabel(MarkSheet.percent);
        Percent.add(lblPer);
        Percent.add(lblper);

        JPanel status = new JPanel();
        status.setLayout(new GridLayout(1, 2));
        JLabel lblStat = new JLabel("Division");
        JLabel lblsta = new JLabel(this.division(MarkSheet.percent, MarkSheet.stat));
        status.add(lblStat);
        status.add(lblsta);

        allgui.add(totalMrk);
        allgui.add(Percent);
        allgui.add(status);
        allgui.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return allgui;
    }

    public JPanel editDelete() {

        editHandler edit = new editHandler();
        JPanel pnlEditDel = new JPanel();
        pnlEditDel.setLayout(new GridLayout(1, 2, 10, 0));
        pnlEditDel.setBorder(BorderFactory.createEmptyBorder(10, 10, 3, 350));

        btnEdit = new JButton("Edit Result");
        btnEdit.addActionListener(edit);
        btnEdit.setPreferredSize(new Dimension(90, 35));
        btnBack = new JButton("Back to Result");
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ListMarks.stdClass = classId;
                    dispose();
                    ListMarks mrk = new ListMarks(0);
                } catch (Exception exp) {
                    JOptionPane.showMessageDialog(null, "You Must Select a row to edit the record.", "Warning!", JOptionPane.WARNING_MESSAGE);
                }

            }
        });

        pnlEditDel.add(btnEdit);
        pnlEditDel.add(btnBack);
        return pnlEditDel;

    }

    public JPanel exitL() {
        JPanel exitPanel = new JPanel();
        exitPanel.setLayout(new GridLayout(1, 3, 10, 0));
        exitPanel.setBorder(BorderFactory.createEmptyBorder(0, 300, 10, 10));

        btnDash = new JButton("Dashboard");
        btnDash.setPreferredSize(new Dimension(80, 30));
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

        return exitPanel;
    }

    @SuppressWarnings("serial")
    class Mymodel extends AbstractTableModel {

        String columns[];
        String[] lst = {"sub_name", "sub_fm", "sub_pm", "mrk_obt"};
        ArrayList<ArrayList<Object>> data = null;

        public Mymodel() {
            prepareCol();
            prepareData();
        }

        private void prepareData() {
            data = SqlLibrary.selectMarkSheet("subject natural join marks", "sub_name,sub_fm,sub_pm,mrk_obt", " std_id = " + MarkSheet.holdId + "", lst);
        }

        private void prepareCol() {
            columns = new String[]{"S.N", "Subject ", "Full Marks", "Pass Marks", "Obtained Marks", "Remarks "};
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

    public String division(String perc, String str) {
        String[] list = perc.split("%");
        String status = "";
        //System.out.println(list[0]);
        float per = Float.parseFloat(list[0]);
        //System.out.println(per);
        //System.out.println(str);
        if (str.equalsIgnoreCase("fail")) {
            status = "Fail";
        } else {
            //System.out.println(per);	
            if (per >= (float) 80.0) {
                status = str + " First Divison with Distinction";
                //System.out.println(status);
            } else if (per >= (float) 60.0) {
                status = "First Divison";
                //System.out.println(status);
            } else if (per >= (float) 50.0) {
                status = "Second Division";
                //System.out.println(status);
            } else if (per >= (float) 40.0) {
                status = "Third Division";
                //System.out.println(status);
            } else {
                status = "Fail";
                //System.out.println(status);
            }
        }
        return status;
    }

    public class editHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            EditMarksPanel.stdClass = classId;
            EditMarksPanel.stdnId = holdId;
            dispose();
            EditMarksPanel pnl = new EditMarksPanel(0);
        }
    }
}
