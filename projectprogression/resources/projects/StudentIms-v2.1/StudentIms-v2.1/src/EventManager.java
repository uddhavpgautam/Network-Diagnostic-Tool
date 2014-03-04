
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings({"unused", "serial"})
public class EventManager extends JFrame {

    public JButton btnExit, btnLout, btnDash;
    public JTable jt, jh;
    public Mymodel model = new Mymodel();
    public HoliModel mod = new HoliModel();
    public static String date = "";

    public EventManager() {
        super("Event Manger");

        this.pack();
        this.setSize(907, 600);
        this.setContentPane(CreateAndShow());
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }

    //whole calender will be in this panel
    public JPanel Calender() {
        JPanel demo = new JPanel();
        String tempDate;

        if (EventManager.date.equals("")) {
            tempDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();
            //System.out.println("ggggggggggggggggggggggggggg"+tempDate);
        } else {
            tempDate = EventManager.date;
        }

        EventManager.date = tempDate;

        //System.out.println(tempDate);
        //Separate the month day and year of given date.
        String[] temp = tempDate.split("-");

        /*
         * Make a new gregorainCalendr with passing the date as constructor. 
         */
        GregorianCalendar gc = new GregorianCalendar(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]) - 1, Integer.parseInt(temp[2]));

        /*
         * Calculate the no of days in a month and first day of month 
         */
        int noOfDaysInMonth = gc.getActualMaximum(Calendar.DAY_OF_MONTH);
        int firstDayOfMonth = this.getStartDay(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]) - 1);
        int j = 1;

        /*
         * Assign the date and change the color to red if it is saturday otherwise not. 
         */
        demo.setLayout(new GridLayout(6, 7, 3, 3));
        for (int i = 0; i < 42; i++) {
            if (i < firstDayOfMonth - 1) {
                demo.add(new JPanel());
            } else if (j <= noOfDaysInMonth) {
                if (i % 7 == 6) {
                    JPanel it = this.btnDate(j);
                    Object[] dod = it.getComponents();
                    ((JLabel) dod[0]).setForeground(Color.RED);
                    demo.add(it);
                } else {
                    demo.add(this.btnDate(j));
                }
                j++;
            } else {
                demo.add(new JPanel());
            }

        }
        demo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return demo;
    }

    /*
     *Function takes the argument as year and month and returns the 
     * Day which the first date of the months falls like if the first day
     * of the month 1 falls in Sunday returns 1
     */
    public int getStartDay(int year, int mon) {
        GregorianCalendar temp = new GregorianCalendar(year, mon, 1);
        int day = temp.get(Calendar.DAY_OF_WEEK);
        return day;
    }

    public JPanel day_next() {
        JPanel days = new JPanel();
        String[] day = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thusday", "Friday", "Saturday"};
        String[] months = {"JANUARY -", "FEBRUARY -", "MARCH -", "APRIL -", "MAY -", "JUNE -", "JULY -", "AUGEST -", "SEPTEMBER -", "OCTOBER -", "NOVEMBER -", "DECEMBER -"};
        days.setLayout(new GridLayout(1, 7, 2, 2));
        for (String da : day) {
            JButton btn = new JButton(da);
            btn.setForeground(new Color(0, 71, 120));
            if (da.equals("Saturday")) {
                btn.setForeground(new Color(255, 0, 0));
            }
            days.add(btn);
        }
        days.setBorder(BorderFactory.createEmptyBorder(2, 5, 5, 5));

        JPanel nextPrev = new JPanel();
        nextPrev.setLayout(new BorderLayout());
        nextPrev.setBorder(BorderFactory.createEmptyBorder(2, 5, 5, 5));

        JButton nxt = new JButton("Next");
        nextHandler nxtHl = new nextHandler();
        nxt.addActionListener(nxtHl);
        nxt.setForeground(new Color(0, 82, 141));
        nxt.setPreferredSize(new Dimension(90, 25));

        JButton prev = new JButton("Previous");
        prevHandler prevHdl = new prevHandler();
        prev.addActionListener(prevHdl);
        prev.setPreferredSize(new Dimension(90, 25));
        prev.setForeground(new Color(0, 82, 141));

        String time = "";

        if (EventManager.date.equals("")) {
            time += months[Integer.parseInt(new SimpleDateFormat("MM").format(new Date()).toString()) - 1];
            time += " " + (new SimpleDateFormat("YYYY").format(new Date()).toString());
        } else {
            String[] sTemp = EventManager.date.split("-");
            if (Integer.parseInt(sTemp[1]) < 1) {
                time += months[0];
            } else {
                time += months[Integer.parseInt(sTemp[1]) - 1];
            }
            time += " " + sTemp[0];
        }
        JLabel monthDay = new JLabel(time);

        monthDay.setForeground(new Color(99, 0, 165));
        monthDay.setHorizontalAlignment(SwingConstants.CENTER);

        nextPrev.add(nxt, BorderLayout.EAST);
        nextPrev.add(monthDay, BorderLayout.CENTER);
        nextPrev.add(prev, BorderLayout.WEST);

        JPanel detail = new JPanel();
        detail.setLayout(new BorderLayout());
        detail.add(days, BorderLayout.CENTER);
        detail.add(nextPrev, BorderLayout.NORTH);
        detail.add(this.Calender(), BorderLayout.SOUTH);

        JPanel mid = new JPanel();
        mid.setLayout(new GridLayout(1, 1, 0, 0));
        detail.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red, 1, true), "Event Manger", 2, TitledBorder.CENTER, new Font("Comic Sans Ms", Font.ITALIC, 12)));

        return detail;
    }

    //each entry of the calender is calculated by this panel
    public JPanel btnDate(int day) {
        JPanel demo = new JPanel();
        demo.setLayout(new BorderLayout());

        String[] time = EventManager.date.split("-");
        long timeStmp = dateTimestamp(time[0] + "-" + time[1] + "-" + day);
        int events = SqlLibrary.noOfRows("event", "evt_date =" + timeStmp + " and evt_type = 0");
        JLabel event;
        if (events > 0) {
            event = new JLabel(events + " - Event");
        } else {
            event = new JLabel(" ");
        }

        JLabel dat = new JLabel("" + day);
        if (events > 0) {
            dat.setForeground(new Color(50, 130, 200));
        } else {
            dat.setForeground(new Color(30, 30, 30));
        }
        if (SqlLibrary.noOfRows("event", "evt_date =" + timeStmp + " and evt_type = 1") > 0) {
            dat.setForeground(new Color(255, 0, 0));
        }

        dat.setFont(new Font("Comic Sans MS", Font.ITALIC + Font.BOLD, 15));
        dat.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        dat.setHorizontalAlignment(SwingConstants.CENTER);
        demo.add(dat, BorderLayout.NORTH);

        event.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        event.setHorizontalAlignment(SwingConstants.CENTER);
        event.setFont(new Font("Comic Sans MS", Font.ITALIC, 10));
        event.setForeground(new Color(50, 150, 50));
        EventHandler hdl = new EventHandler();

        demo.addMouseListener(hdl);
        demo.add(event, BorderLayout.CENTER);
        demo.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1, true));

        return demo;
    }

    public JPanel listEvent() {
        JPanel demo = new JPanel();
        demo.setLayout(new BorderLayout());

        JPanel shlEvent = new JPanel();
        JPanel holEvent = new JPanel();

        shlEvent.setLayout(new BorderLayout());
        JLabel lblEvent = new JLabel();
        lblEvent.setText("<html><u><b>School Events</b></u></html>");
        lblEvent.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 145));
        shlEvent.add(lblEvent, BorderLayout.NORTH);

        JPanel eventList = new JPanel();
        JPanel stdnAdd = new JPanel();

        jt = new JTable(model) {
            public Component prepareRenderer(TableCellRenderer r, int rows,
                    int columns) {
                Component c = super.prepareRenderer(r, rows, columns);

                if (rows % 2 == 0) {
                    c.setBackground(new Color(208, 208, 208));
                } else {
                    //c.setBackground(new Color(255,255,255));
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
        //rdr.setVerticalTextPosition(SwingConstants.CENTER);
        //rdr.setHorizontalTextPosition(SwingConstants.LEFT);
        for (int i = 0; i < 1; i++) {
            jt.getColumnModel().getColumn(i).setCellRenderer(rdr);
        }
        //jt.getColumnModel().getColumn(0).setPreferredWidth(80);
        //jt.getColumnModel().getColumn(1).setPreferredWidth(200);
        jt.setPreferredScrollableViewportSize(new Dimension(200, 200));
        jt.setFillsViewportHeight(true);
        // enable selection to only
        jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jt.getTableHeader().setBackground(Color.BLUE);
        jt.getTableHeader().setForeground(Color.BLUE);

        jt.setAutoCreateRowSorter(true);
        jt.setRowHeight(28);
        //jt.setAutoResizeMode(jt.AUTO_RESIZE_OFF);

        JScrollPane jps = new JScrollPane(jt);
        stdnAdd.setLayout(new BorderLayout());
        stdnAdd.add(jps, BorderLayout.NORTH);

        JButton btnEdit = new JButton("Edit Event");
        EditHander edl = new EditHander(jt);
        btnEdit.addActionListener(edl);

        JButton btnDelete = new JButton("Delete Event");
        DeleteHandler del = new DeleteHandler(jt);
        btnDelete.addActionListener(del);

        JPanel edit = new JPanel();
        edit.setLayout(new GridLayout(1, 2, 5, 5));
        edit.add(btnEdit);
        edit.add(btnDelete);
        btnEdit.setPreferredSize(new Dimension(80, 25));
        edit.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        stdnAdd.add(edit, BorderLayout.CENTER);

        shlEvent.add(stdnAdd);

        demo.add(shlEvent, BorderLayout.NORTH);
        demo.add(this.hoiDay(), BorderLayout.CENTER);

        demo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red, 1, true), "Events", 2, TitledBorder.CENTER, new Font("Comic Sans Ms", Font.ITALIC, 12)));

        return demo;
    }

    public JPanel CreateAndShow() {
        JPanel hold = new JPanel();
        hold.setLayout(new BorderLayout());
        hold.add(this.day_next(), BorderLayout.WEST);
        hold.add(this.listEvent(), BorderLayout.EAST);
        hold.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        hold.add(this.exitLogout(), BorderLayout.SOUTH);
        return hold;
    }

    public JPanel hoiDay() {
        JPanel holi = new JPanel();
        holi.setLayout(new BorderLayout());
        JLabel lblEvent = new JLabel();
        lblEvent.setText("<html><u><b>Holidays</b></u></html>");
        lblEvent.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 145));
        holi.add(lblEvent, BorderLayout.NORTH);

        jh = new JTable(mod) {
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
        for (int i = 0; i < 1; i++) {
            jh.getColumnModel().getColumn(i).setCellRenderer(rdr);
        }

        jh.setPreferredScrollableViewportSize(new Dimension(200, 100));
        jh.setFillsViewportHeight(true);
        // enable selection to only
        jh.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jh.getTableHeader().setBackground(Color.BLUE);
        jh.getTableHeader().setForeground(Color.RED);

        jh.setAutoCreateRowSorter(true);
        jh.setRowHeight(28);

        JButton btnEdit = new JButton("Edit Holiday");
        EditHander editHdl = new EditHander(jh);
        btnEdit.addActionListener(editHdl);

        JButton btnDelete = new JButton("Delete Holiday");
        DeleteHandler delHdl = new DeleteHandler(jh);
        btnDelete.addActionListener(delHdl);

        JPanel edit = new JPanel();
        edit.setLayout(new GridLayout(1, 2, 5, 5));
        edit.add(btnEdit);
        edit.add(btnDelete);
        btnEdit.setPreferredSize(new Dimension(80, 25));
        edit.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        holi.add(edit, BorderLayout.SOUTH);

        JScrollPane jpt = new JScrollPane(jh);
        holi.add(jpt, BorderLayout.CENTER);

        return holi;
    }

    public class DeleteHandler implements ActionListener {

        JTable temp;

        public DeleteHandler(JTable table) {
            temp = table;
        }

        public void actionPerformed(ActionEvent e) {

            try {
                int row = temp.convertRowIndexToModel(temp.getSelectedRow());

                if (temp.isRowSelected(row)) {

                    String event = (String) temp.getValueAt(row, 0);
                    //System.out.println(event);
                    final String[] explodeEvent = event.split("-");
                    String[] dates = EventManager.date.split("-");

                    //System.out.println(event);
                    String clickDate;

                    clickDate = dates[0] + "-" + dates[1] + "-" + (explodeEvent[0].trim());

                    DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = null;
                    try {
                        date = fmt.parse(clickDate);
                    } catch (ParseException e1) {
                        System.out.println("Date and time error. \n" + e1.getMessage());
                    }
                    final long time = date.getTime();
                    if (SqlLibrary.delete("event", "evt_name = '" + explodeEvent[1].trim() + "'"
                            + " and evt_date = '" + time + "'")) {
                        JOptionPane.showMessageDialog(null, "The event has been deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();

                        EventManager evt = new EventManager();
                    }
                }
            } catch (Exception evt) {
                JOptionPane.showMessageDialog(null, "Sorry You must select row to delete.", "Error", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    public class EditHander implements ActionListener {

        JTable temp;

        public EditHander(JTable table) {
            temp = table;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int row = temp.convertRowIndexToModel(temp.getSelectedRow());

                if (temp.isRowSelected(row)) {

                    String event = (String) temp.getValueAt(row, 0);
                    //System.out.println(event);
                    final String[] explodeEvent = event.split("-");
                    String[] dates = EventManager.date.split("-");

                    //System.out.println(event);
                    String clickDate;

                    clickDate = dates[0] + "-" + dates[1] + "-" + (explodeEvent[0].trim());

                    final DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = null;
                    try {
                        date = fmt.parse(clickDate);
                    } catch (ParseException e1) {
                        System.out.println("Date and time error. \n" + e1.getMessage());
                    }
                    final long time = date.getTime();

                    String[] flds = {"evt_type", "evt_detail"};
                    Object[] eventDetails = SqlLibrary.selectOneRow("*", "event", "evt_name = '" + explodeEvent[1].trim() + "'"
                            + " and evt_date = '" + time + "'", flds);

                    for (Object evt : eventDetails) {
                        System.out.println("" + evt);
                    }

                    final JDialog diag;
                    diag = new JOptionPane().createDialog(rootPane, "Edit Event");
                    JPanel sho = new JPanel();
                    sho.setLayout(new GridLayout(1, 2));
                    sho.add(new JLabel("Date For the event :-")).setForeground(new Color(22, 22, 22));
                    final JTextField eventDate = new JTextField(clickDate);

                    sho.add(eventDate).setForeground(Color.BLUE);
                    sho.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

                    JPanel hold = new JPanel();
                    hold.setLayout(new BorderLayout());
                    JLabel lblEvent = new JLabel("Name Of the event");
                    lblEvent.setForeground(new Color(22, 22, 22));
                    final JTextField txtName = new JTextField(explodeEvent[1].trim(), 20);
                    txtName.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
                    txtName.setPreferredSize(new Dimension(200, 25));

                    hold.add(lblEvent, BorderLayout.NORTH);
                    hold.add(txtName, BorderLayout.CENTER);
                    hold.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

                    JPanel evtType = new JPanel();
                    evtType.setLayout(new BorderLayout());

                    final JRadioButton shlEvt = new JRadioButton("School Event", Integer.parseInt("" + eventDetails[0]) == 0 ? true : false);
                    final JRadioButton holiEvt = new JRadioButton("Holiday", Integer.parseInt("" + eventDetails[0]) == 1 ? true : false);
                    ButtonGroup radio = new ButtonGroup();
                    radio.add(shlEvt);
                    radio.add(holiEvt);
                    JPanel radioBtn = new JPanel();
                    radioBtn.setLayout(new GridLayout(1, 2));
                    radioBtn.add(shlEvt);
                    radioBtn.add(holiEvt);
                    JLabel lblEvtType = new JLabel("Event Type");
                    evtType.add(lblEvtType, BorderLayout.NORTH);
                    evtType.add(radioBtn, BorderLayout.CENTER);
                    evtType.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

                    JPanel detail = new JPanel();
                    detail.setLayout(new BorderLayout());
                    JLabel lblDetail = new JLabel("Description Of the event.");
                    lblDetail.setForeground(new Color(22, 22, 22));
                    final JTextArea txtDetail = new JTextArea(4, 10);
                    txtDetail.setText("" + eventDetails[1]);
                    txtDetail.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
                    detail.add(lblDetail, BorderLayout.NORTH);
                    detail.add(txtDetail, BorderLayout.CENTER);
                    detail.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

                    JPanel all = new JPanel();
                    all.setLayout(new BorderLayout());
                    all.add(sho, BorderLayout.NORTH);
                    all.add(hold, BorderLayout.CENTER);
                    all.add(evtType, BorderLayout.SOUTH);
                    all.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

                    JPanel lastAll = new JPanel();
                    lastAll.setLayout(new BorderLayout());
                    lastAll.add(all, BorderLayout.NORTH);
                    lastAll.add(detail, BorderLayout.CENTER);
                    lastAll.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                    JPanel btns = new JPanel();
                    btns.setLayout(new GridLayout(1, 2));
                    JButton btnAdd = new JButton("Update Event");
                    JButton btnCancel = new JButton("Cancel");
                    btnCancel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // TODO Auto-generated method stub
                            diag.dispose();
                        }
                    });
                    btns.add(btnAdd);
                    btns.add(btnCancel);
                    btns.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));

                    btnAdd.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int evttype;
                            if (shlEvt.isSelected()) {
                                evttype = 0;
                            } else {
                                evttype = 1;
                            }
                            if (txtName.getText().equals("") || eventDate.getText().equals("")) {
                                JOptionPane.showMessageDialog(null, "You must fill the date and name of the Event.", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                Date tdate = null;
                                try {
                                    tdate = fmt.parse(eventDate.getText());
                                } catch (ParseException e1) {
                                    // TODO Auto-generated catch block
                                    JOptionPane.showMessageDialog(null, "Your date format didn't match use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                                Long ttime = tdate.getTime();
                                //System.out.println(ttime);
                                if (SqlLibrary.update("event", "evt_name ='" + txtName.getText() + "', evt_detail ='" + txtDetail.getText() + "'"
                                        + ",evt_date='" + ttime + "',evt_type='" + evttype + "'", " evt_name = '" + explodeEvent[1].trim() + "'"
                                        + " and evt_date = '" + time + "'")) {
                                    JOptionPane.showMessageDialog(null, "The Changes has been Saved", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    diag.dispose();
                                    dispose();
                                    EventManager evt = new EventManager();
                                }
                            }
                        }
                    });

                    JPanel finalAll = new JPanel();
                    finalAll.setLayout(new BorderLayout());
                    finalAll.add(lastAll, BorderLayout.NORTH);
                    finalAll.add(btns, BorderLayout.CENTER);

                    finalAll.setBorder(BorderFactory.createTitledBorder(
                            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
                            "---| Update Event |---", 1, TitledBorder.CENTER,
                            new Font("Comic Sans MS", Font.PLAIN, 12), new Color(123, 123, 124)));

                    JPanel finalAllPopup = new JPanel();
                    finalAllPopup.setLayout(new GridLayout(1, 1));
                    finalAllPopup.add(finalAll);
                    finalAllPopup.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                    diag.setSize(new Dimension(320, 325));

                    diag.setContentPane(finalAllPopup);
                    diag.setLocationRelativeTo(rootPane);
                    diag.setVisible(true);
                    //temps.getSize();
                }
            } catch (Exception tbl) {
                tbl.printStackTrace();
                JOptionPane.showMessageDialog(null, "You must select a row to edit Holiday.", "Warning!", JOptionPane.WARNING_MESSAGE);

            }
        }
    }

    @SuppressWarnings("deprecation")
    public class EventHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            /*
             *Get the components of the parent component to find the clicked date
             */
            JPanel temp = (JPanel) e.getSource();
            Object[] get = temp.getComponents();
            JLabel temps = (JLabel) get[0];

            //JOptionPane.showMessageDialog(rootPane, temps.getText(), temps.getText(), JOptionPane.INFORMATION_MESSAGE);

            /*
             *Process the date to get the timestamp of the date 
             *and to store in the database. 
             */
            String clickDate = temps.getText();
            String[] dates = EventManager.date.split("-");
            clickDate = dates[0] + "-" + dates[1] + "-" + clickDate;
            DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = fmt.parse(clickDate);
            } catch (ParseException e1) {
                System.out.println("Date and time error. \n" + e1.getMessage());
            }
            final long time = date.getTime();

            /*
             *Testing bugs
             */
//			System.out.println("The new date in timestamp is :"+time);
//			System.out.println("The current date is :"+clickDate);
//			System.out.println("The current date is :"+System.currentTimeMillis());

            /*
             *Creating a jpanel for displaying the event to be registered 
             */
            final JDialog diag;
            diag = new JOptionPane().createDialog(rootPane, "Event for Date : " + clickDate);
            JPanel sho = new JPanel();
            sho.setLayout(new GridLayout(1, 2));
            sho.add(new JLabel("Date For the event :-")).setForeground(new Color(22, 22, 22));
            sho.add(new JLabel(clickDate)).setForeground(Color.BLUE);
            sho.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

            JPanel hold = new JPanel();
            hold.setLayout(new BorderLayout());
            JLabel lblEvent = new JLabel("Name Of the event");
            lblEvent.setForeground(new Color(22, 22, 22));
            final JTextField txtName = new JTextField(20);
            txtName.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            txtName.setPreferredSize(new Dimension(200, 25));

            hold.add(lblEvent, BorderLayout.NORTH);
            hold.add(txtName, BorderLayout.CENTER);
            //hold.add(radioBtn,BorderLayout.SOUTH);
            hold.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

            JPanel evtType = new JPanel();
            evtType.setLayout(new BorderLayout());
            final JRadioButton shlEvt = new JRadioButton("School Event", true);
            final JRadioButton holiEvt = new JRadioButton("Holiday", false);
            ButtonGroup radio = new ButtonGroup();
            radio.add(shlEvt);
            radio.add(holiEvt);
            JPanel radioBtn = new JPanel();
            radioBtn.setLayout(new GridLayout(1, 2));
            radioBtn.add(shlEvt);
            radioBtn.add(holiEvt);
            JLabel lblEvtType = new JLabel("Event Type");
            evtType.add(lblEvtType, BorderLayout.NORTH);
            evtType.add(radioBtn, BorderLayout.CENTER);
            evtType.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

            JPanel detail = new JPanel();
            detail.setLayout(new BorderLayout());
            JLabel lblDetail = new JLabel("Description Of the event.");
            lblDetail.setForeground(new Color(22, 22, 22));
            final JTextArea txtDetail = new JTextArea(4, 15);
            txtDetail.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            detail.add(lblDetail, BorderLayout.NORTH);
            detail.add(txtDetail, BorderLayout.CENTER);
            detail.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

            JPanel all = new JPanel();
            all.setLayout(new BorderLayout());
            all.add(sho, BorderLayout.NORTH);
            all.add(hold, BorderLayout.CENTER);
            all.add(evtType, BorderLayout.SOUTH);
            all.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

            JPanel lastAll = new JPanel();
            lastAll.setLayout(new BorderLayout());
            lastAll.add(all, BorderLayout.NORTH);
            lastAll.add(detail, BorderLayout.CENTER);
            lastAll.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            JPanel btns = new JPanel();
            btns.setLayout(new GridLayout(1, 2));
            JButton btnAdd = new JButton("Add Event");
            JButton btnCancel = new JButton("Cancel");
            btnCancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    diag.dispose();
                }
            });
            btns.add(btnAdd);
            btns.add(btnCancel);
            btns.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));

            btnAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int evttype;
                    if (shlEvt.isSelected()) {
                        evttype = 0;
                    } else {
                        evttype = 1;
                    }
                    if (txtName.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "You must fill the name of the Event.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (SqlLibrary.Insert("event", "evt_name, evt_detail,evt_date,evt_type", "'" + txtName.getText() + "',"
                                + "'" + txtDetail.getText() + "','" + time + "','" + evttype + "'")) {
                            JOptionPane.showMessageDialog(null, "The event has been Saved", "Success", JOptionPane.INFORMATION_MESSAGE);
                            diag.dispose();
                        }
                    }
                }
            });

            JPanel finalAll = new JPanel();
            finalAll.setLayout(new BorderLayout());
            finalAll.add(lastAll, BorderLayout.NORTH);
            finalAll.add(btns, BorderLayout.CENTER);

            finalAll.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
                    "---| Register Event |---", 1, TitledBorder.CENTER,
                    new Font("Comic Sans MS", Font.PLAIN, 12), new Color(123, 123, 124)));

            JPanel finalAllPopup = new JPanel();
            finalAllPopup.setLayout(new GridLayout(1, 1));
            finalAllPopup.add(finalAll);
            finalAllPopup.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            diag.setSize(new Dimension(320, 325));

            diag.setContentPane(finalAllPopup);
            diag.setLocationRelativeTo(rootPane);
            diag.setVisible(true);
            temps.getSize();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            JPanel temp = (JPanel) e.getSource();
            temp.setBorder(BorderFactory.createLineBorder(new Color(255, 102, 0), 1, true));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            JPanel temp = (JPanel) e.getSource();
            temp.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1, true));

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JPanel temp = (JPanel) e.getSource();
            temp.setCursor(new Cursor(HAND_CURSOR));
            temp.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70), 1, true));
            Object[] get = temp.getComponents();
            JLabel temps = (JLabel) get[1];
            temps.setForeground(new Color(18, 60, 5));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JPanel temp = (JPanel) e.getSource();
            temp.setCursor(new Cursor(DEFAULT_CURSOR));
            temp.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1, true));
            Object[] get = temp.getComponents();
            JLabel temps = (JLabel) get[1];
            temps.setForeground(new Color(50, 150, 50));

        }
    }

    /*
     * Function to get the timestamp of the of supplied date
     */
    public long dateTimestamp(String time) {
        Date dat = getDate(time);
        long date = dat.getTime();
        return date;
    }

    public Date getDate(String date) {
        Date dat = null;
        DateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dat = ft.parse(date);
        } catch (ParseException e) {
            System.out.println("Error in date format");
        }
        return dat;
    }

    /*
     *Function to get the last date of the month 
     */
    public long[] timestamp() {
        long[] stmp = new long[2];
        String[] temp = EventManager.date.split("-");
        //System.out.println("Test date"+EventManager.date);
        GregorianCalendar gc = new GregorianCalendar(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]) - 1, Integer.parseInt(temp[2]));
        int noOfDaysInMonth = gc.getActualMaximum(Calendar.DAY_OF_MONTH);
        int firstDayOfMonth = this.getStartDay(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]) - 1);

        stmp[0] = dateTimestamp(temp[0] + "-" + temp[1] + "-" + firstDayOfMonth);
        stmp[1] = dateTimestamp(temp[0] + "-" + temp[1] + "-" + noOfDaysInMonth);
        return stmp;
    }

    public class Mymodel extends AbstractTableModel {

        String columns[];
        ArrayList<ArrayList<Object>> data = null;

        public Mymodel() {
            prepareCol();
            prepareData();
        }

        private void prepareData() {
            if (EventManager.date == "") {
                EventManager.date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();
            }
            long[] time = timestamp();
            data = SqlLibrary.selectEvents("event ", "*", "evt_date between '" + time[0] + "' and '" + time[1] + "' and evt_type = 0");
        }

        private void prepareCol() {
            columns = new String[]{"Event for This Month."};
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

    public JPanel exitLogout() {
        JPanel demo = new JPanel();
        btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnDash = new JButton("Dashboard");
        btnDash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventManager.date = "";
                dispose();
                MainPanel pnl = new MainPanel();
            }
        });
        btnDash.setPreferredSize(new Dimension(100, 35));
        btnLout = new JButton("Logout");
        btnLout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventManager.date = "";
                dispose();
                Login lgn = new Login();
            }
        });

        demo.setLayout(new GridLayout(1, 3, 5, 5));
        demo.add(btnDash);
        demo.add(btnLout);
        demo.add(btnExit);
        demo.setBorder(BorderFactory.createEmptyBorder(5, 550, 0, 0));

        return demo;
    }

    public class HoliModel extends AbstractTableModel {

        String columns[];
        ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();

        public HoliModel() {
            prepareCol();
            prepareData();
        }

        private void prepareData() {
            long[] time = timestamp();
            data = SqlLibrary.selectEvents("event ", "*", "evt_date between '" + time[0] + "' and '" + time[1] + "' and evt_type = 1");
        }

        private void prepareCol() {
            columns = new String[]{"Holidays for This Month."};
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

    public class nextHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            /*
             * Separate the date of the eventManger
             */
            String[] temp = EventManager.date.split("-");
            int year = Integer.parseInt(temp[0]);
            int month = Integer.parseInt(temp[1]);
            int day = Integer.parseInt(temp[2]);

            month = month + 1;
            day = 1;
            if (month > 12) {
                year = year + 1;
                month = 1;
                day = 1;
            }

            EventManager.date = "" + year + "-" + month + "-" + day;
            dispose();
            EventManager evt = new EventManager();
            System.out.println(EventManager.date);

        }
    }

    public class prevHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            /*
             * Separate the date of the eventManger
             */
            String[] temp = EventManager.date.split("-");
            int year = Integer.parseInt(temp[0]);
            int month = Integer.parseInt(temp[1]);
            int day = Integer.parseInt(temp[2]);

            month = month - 1;
            day = 1;
            if (month < 1) {
                month = 12;
                year = year - 1;
            }

            EventManager.date = "" + year + "-" + month + "-" + day;
            dispose();
            EventManager evt = new EventManager();
            System.out.println(EventManager.date);
        }
    }

//	public class message extends Popup implements WindowFocusListener{
//
//		private JDialog diag;
//		
//		public message(Frame base ,String mess){
//			diag = new JOptionPane().createDialog(base, "Add Event");
//			diag.setModal(false);
//			JPanel event = new JPanel();
//			event.setLayout(new BorderLayout());
//			JLabel lblEventName = new JLabel("Enter the name of the event.");
//			JTextArea txtEvent = new JTextArea(3,10);
//			JButton btn = new JButton("Add event");
//			event.add(lblEventName,BorderLayout.NORTH);
//			event.add(txtEvent,BorderLayout.CENTER);
//			event.add(btn,BorderLayout.SOUTH);
//			diag.setContentPane(event);
//		}
//		@Override
//		public void windowGainedFocus(WindowEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void windowLostFocus(WindowEvent e) {
//			hide();
//		}
//		
//	}
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception unused) {
            ; // Ignore exception because we can't do anything. Will use
            // default.
        }
        EventManager evtM = new EventManager();
    }
}
