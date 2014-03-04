
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class EditClass extends JFrame {

    boolean DEBUG;
    public JTable jt;
    private JButton btnAdd, btnReset, btnDash, btnExit, btnLogout, btnEdit;
    private JTextField txtClass, txtSection;
    private JLabel lblClass, lblSection;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private int id;
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    public EditClass(int row) {
        super("Student Edit/Update Index");
        this.setId(row);

        //txtClass.setText((String) dat[1]);
        //txtSection.setText((String) dat[2]);
        JTabbedPane tabStudent = new JTabbedPane();
        JPanel pnlAdd = new JPanel();
        JPanel pnlList = new JPanel();

        Font font = new Font("Comic Sans MS", Font.PLAIN, 13);

        JPanel addRest = new JPanel();
        addRest.setLayout(new GridLayout(1, 2, 10, 0));
        addRest.setBorder(BorderFactory.createEmptyBorder(0, 100, 10, 420));
        updateHandler edit = new updateHandler();
        btnAdd = new JButton("Update");
        btnAdd.addActionListener(edit);
        btnAdd.setPreferredSize(new Dimension(90, 35));

        btnReset = new JButton("Back To List");
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ClassPanel pnl = new ClassPanel(1);
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

        tabStudent.addTab("Edit Class", new ImageIcon("img/add.png"), pnlAdd);

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

    public JPanel editDelete() {

        updateHandler edit = new updateHandler();
        JPanel pnlEditDel = new JPanel();
        pnlEditDel.setLayout(new GridLayout(1, 2, 10, 0));
        pnlEditDel.setBorder(BorderFactory.createEmptyBorder(0, 100, 10, 420));

        btnEdit = new JButton("Edit / View");
        btnEdit.addActionListener(edit);
        btnEdit.setPreferredSize(new Dimension(90, 35));
        //btnDelete = new JButton("Delete");

        pnlEditDel.add(btnEdit);
        //pnlEditDel.add(btnDelete);
        return pnlEditDel;

    }

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

        String[] rows = {"class_id", "class", "class_sec"};
        Object[] dat = SqlLibrary.selectOneRow("*", "class", "class_id =" + this.getId(), rows);

        JPanel pnlname = new JPanel();
        pnlname.setLayout(new BorderLayout());
        lblClass = new JLabel("Class :");
        txtClass = new JTextField((String) dat[1], 20);
        txtClass.setPreferredSize(new Dimension(100, 30));
        pnlname.add(lblClass, BorderLayout.NORTH);
        pnlname.add(txtClass, BorderLayout.CENTER);
        left.add(pnlname);

        JPanel pnlcls = new JPanel();
        pnlcls.setLayout(new BorderLayout());
        lblSection = new JLabel("No of Sections:");
        txtSection = new JTextField((String) dat[2], 20);
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

    public class updateHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (SqlLibrary.update("class", "class='" + txtClass.getText() + "', class_sec='" + txtSection.getText() + "'", " class_id=" + getId())) {
                JOptionPane.showMessageDialog(null, "Success record Updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public class deleteHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            DefaultTableModel model = (DefaultTableModel) jt.getModel();
            int row = jt.convertRowIndexToModel(jt.getSelectedRow());
            model.removeRow(row);
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
        EditClass me = new EditClass(0);

    }
}
