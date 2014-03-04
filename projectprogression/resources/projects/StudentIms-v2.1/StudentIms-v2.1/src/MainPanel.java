
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

@SuppressWarnings("unused")
public class MainPanel extends JFrame {

    private static final long serialVersionUID = 1L;
    public JButton btnAddStdn, btnListStdn, btnAddCls, btnListCls, btnAddTeacher, btnListTeacher,
            btnAddSubject, btnListSubject, btnAddMarks, btnListMarks, btnEvent, btnAccount,
            btnAddSchedule, btnListSchedule;
    public JButton[] buttons = {btnAddStdn, btnListStdn, btnAddCls, btnListCls, btnAddTeacher, btnListTeacher,
        btnAddSubject, btnListSubject, btnAddMarks, btnListMarks, btnEvent,
        btnAddSchedule, btnListSchedule, btnAccount};
    public JLabel[] labels = new JLabel[12];
    public String[] txtLabels = {"Add Student", "List Student", "Add Class", "List Class",
        "Add Teacher", "List Teacher", "Add Subject", "List Subject",
        "Add Marks", "List Results", "Event Manger", "Add Schedule",
        "View Schedule", "Payroll"};
    public String[] imgs = {"add-student.png", "list-student.png", "add-class.png", "list-class.png",
        "add-teacher.png", "list-teacher.png", "add-subject.png", "list-subject.png",
        "add-marks.png", "list-marks.png", "calendar.png", "add-schedule.png",
        "list-schedule.png", "account.png"};
    public JButton btnLogout;
    public JButton btnExit;

    public MainPanel() {
        super("Student Information System - Dashboard ");

        Font fnt = new Font("Comic Sans MS", Font.BOLD, 12);

        JPanel dashboard = new JPanel();
        dashboard.setLayout(new GridLayout(3, 4, 30, 20));
        int i = 0;

        JPanel exitPanel = new JPanel();
        btnLogout = new JButton("Logout");
        btnExit = new JButton("Exit");
        btnLogout.setForeground(Color.BLUE);
        btnLogout.setPreferredSize(new Dimension(100, 35));
        btnExit.setForeground(Color.RED);
        btnLogout.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        btnExit.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));

        LogoutHandler loutHandler = new LogoutHandler();
        btnLogout.addActionListener(loutHandler);

        /*
         * The action event to exit from thePanel
         */
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                System.exit(0);
            }
        });

        exitPanel.setLayout(new GridLayout(1, 2, 10, 10));
        exitPanel.setBorder(BorderFactory.createEmptyBorder(0, 450, 5, 50));
        exitPanel.add(btnLogout);
        exitPanel.add(btnExit);
        IconBtnsHandler btnListner = new IconBtnsHandler();

        for (int k = 0; k < 14; k++) {
            JPanel temp = new JPanel();
            temp.setLayout(new BorderLayout());
            JLabel test = new JLabel(txtLabels[i]);
            test.setHorizontalAlignment(SwingConstants.CENTER);
            test.setFont(fnt);
            test.setForeground(Color.BLACK);
            buttons[k] = new JButton(new ImageIcon("img/" + imgs[i], txtLabels[i]));
            buttons[k].setPreferredSize(new Dimension(100, 100));
            buttons[k].addActionListener(btnListner);
            temp.add(buttons[k], BorderLayout.NORTH);
            temp.add(test, BorderLayout.CENTER);
            dashboard.add(temp);
            i++;
        }

        JPanel sep = new JPanel();
        sep.setLayout(new FlowLayout());
        sep.add(dashboard);
        sep.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JPanel mid = new JPanel();
        mid.setLayout(new BorderLayout());
        mid.add(sep, BorderLayout.NORTH);
        mid.add(exitPanel, BorderLayout.CENTER);
        //mid.setSize(800, 500);

        mid.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(101, 171, 210), 1, true),
                " DASHBOARD ", TitledBorder.LEFT, 0,
                new Font("Times New Roman", Font.PLAIN, 15), new Color(10, 80, 128)));

        JPanel contentPane = new JPanel();
        contentPane.add(mid);
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 5, 5, 5));

        this.pack();
        this.setContentPane(contentPane);
        this.setSize(800, 650);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    //public JPanel btnAdd(){}
    public class IconBtnsHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btns = (JButton) e.getSource();
            String test = (((ImageIcon) btns.getIcon()).toString());
            if (test.equals("Add Student")) {
                StudentPanel stdn = new StudentPanel(0);
                dispose();
            } else if (test.equals("List Student")) {
                if (StudentPanel.listClass().length > 0) {
                    dispose();
                    AllClass pnl = new AllClass(1);
                } else {
                    JOptionPane.showMessageDialog(null, "At least one Class is needed To add Marks.", "Warning!", JOptionPane.WARNING_MESSAGE);
                }
            } else if (test.equals("Add Class")) {
                ClassPanel stdn = new ClassPanel(0);
                dispose();
            } else if (test.equals("List Class")) {
                ClassPanel stdn = new ClassPanel(1);
                dispose();
            } else if (test.equals("Add Teacher")) {
                TeacherPanel stdn = new TeacherPanel(0);
                dispose();
            } else if (test.equals("List Teacher")) {
                TeacherPanel stdn = new TeacherPanel(1);
                dispose();
            } else if (test.equals("Add Subject")) {
                dispose();
                SubjectPanel stdn = new SubjectPanel(0);
            } else if (test.equals("Event Manger")) {
                dispose();
                EventManager stdn = new EventManager();
            } else if (test.equals("List Subject")) {
                try {
                    dispose();
                    AllClass pnl = new AllClass(2);
                } catch (Exception exp) {
                    JOptionPane.showMessageDialog(null, "You must input class as Integer Values.", "Error", JOptionPane.ERROR_MESSAGE);
                    MainPanel mp = new MainPanel();
                }
            } else if (test.equals("Add Marks")) {
                if (StudentPanel.listClass().length > 0) {
                    dispose();
                    MarksPanel pnl = new MarksPanel(0);
                } else {
                    JOptionPane.showMessageDialog(null, "At least one Class is needed To add Marks.", "Warning!", JOptionPane.WARNING_MESSAGE);
                }
            } else if (test.equals("List Results")) {
                if (StudentPanel.listClass().length > 0) {
                    dispose();
                    AllClass pnl = new AllClass(0);
                } else {
                    JOptionPane.showMessageDialog(null, "At least one Class is needed To add Marks.", "Warning!", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                System.out.println("No register");
            }
        }
    }

    public class LogoutHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            dispose();
            Login me = new Login();
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception unused) {
            ; // Ignore exception because we can't do anything. Will use
            // default.
        }
        MainPanel me = new MainPanel();

    }
}
