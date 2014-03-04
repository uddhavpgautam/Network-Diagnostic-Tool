
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

@SuppressWarnings({"serial", "unused"})
public class SqlLibrary {

    public static Connection getConnection() {
        Connection con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.getLocalizedMessage();
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolims", "root", "");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return con;
    }

    public static boolean ValidUser(String uname, String pass) {
        boolean status = false;
        int count = 0;
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        String query = "SELECT * FROM user WHERE uname='" + uname + "' and upass='" + pass + "'";
        //System.out.println(query);
        try {
            stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                count = count + 1;
            }

            if (count > 0) {
                status = true;
            } else {
                status = false;
            }

        } catch (SQLException e) {
            status = false;
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error !", JOptionPane.ERROR_MESSAGE);
            // TODO Auto-generated catch block
            //e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.getMessage();
                }
            }
        }

        return status;
    }

    public static boolean Insert(String table, String field, String data) {
        boolean stat = false;
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO " + table + " (" + field + ") VALUES (" + data + ")";
        //System.out.println(sql);
        try {
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            stat = true;
        } catch (SQLException e) {
            stat = false;
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return stat;
    }

    public static ArrayList<ArrayList<Object>> select(String table, String sel, String cond, String[] flds) {
        ArrayList<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();
        int len = flds.length + 1;
        final String[] temp = new String[len];
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        String query = "SELECT " + sel + " FROM " + table + " WHERE " + cond;
        try {
            stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            rs.last();
            int i = 0, j = 1;
            int count = rs.getRow();
            rs = stmt.executeQuery();
            //System.out.println(query);
            while (rs.next()) {
                temp[0] = "" + j;
                i++;
                for (String fld : flds) {
                    temp[i] = rs.getString(fld);
                    //System.out.println(temp[i]);
                    i++;
                }
                j++;
                i = 0;
                //System.out.println("ggggggg");
                list.add(new ArrayList<Object>() {
                    {
                        for (String txt : temp) {
                            add(txt);
                        }
                    }
                });
            }
            if (count > 0) {
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;
    }

    public static Object[] selectOneRow(String sel, String table, String cond, String[] flds) {
        Object[] one = null;
        int len = flds.length;
        one = new Object[len];
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        String query = "SELECT " + sel + " FROM " + table + " WHERE " + cond;
        try {
            stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            rs = stmt.executeQuery();
            //System.out.println(query);
            int i = 0;
            while (rs.next()) {
                for (String fld : flds) {
                    one[i] = rs.getString(fld);
                    i++;
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return one;
    }

//	public static Object[][] selectInObject(String sel,String table, String cond,String[] flds){
//		Object[] one = null;
//		int len = flds.length;
//		one = new Object[len];
//		Connection conn = getConnection();
//		PreparedStatement stmt = null;
//		String query = "SELECT "+sel+" FROM "+table+" WHERE "+cond;
//		try {
//			stmt = conn.prepareStatement(query);
//			ResultSet rs = stmt.executeQuery();
//			rs = stmt.executeQuery();
//			System.out.println(query);
//			int i = 0;
//			while(rs.next()){
//				for(String fld : flds){
//					one[i] = rs.getString(fld);
//					i++;
//				}
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return one;
//	}
//	
    public static boolean update(String table, String mode, String con) {
        Connection conn = getConnection();
        boolean status = false;
        PreparedStatement stmt = null;
        String query = "UPDATE " + table + " SET " + mode + " WHERE" + con;
        //System.out.println(query);
        try {
            stmt = conn.prepareStatement(query);
            int count = stmt.executeUpdate();
            if (count > 0) {
                status = true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            status = false;
        }
        return status;
    }

    public static boolean delete(String table, String con) {
        boolean status = false;
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        String query = "DELETE FROM " + table + " WHERE " + con;

        try {
            stmt = conn.prepareStatement(query);
            int row = stmt.executeUpdate();
            if (row > 0) {
                status = true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return status;
    }

    public static ArrayList<ArrayList<Object>> selResult(String cond) {
        ArrayList<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();
        int len = 6;
        final String[] temp = new String[len];
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        String query = "SELECT * FROM student WHERE " + cond;
        try {
            stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            rs.last();
            int i = 0, j = 1;
            int count = rs.getRow();
            rs = stmt.executeQuery();
            //System.out.println(query);
            while (rs.next()) {
                temp[0] = "" + j;
                i++;
                temp[1] = "" + rs.getInt("std_roll");
                temp[2] = rs.getString("std_name") + " " + rs.getString("std_lname");
                temp[3] = SqlLibrary.chkStatus(rs.getInt("std_roll"));
                temp[4] = "" + SqlLibrary.calTotal(rs.getInt("std_roll"));
                temp[5] = "" + SqlLibrary.calPercent(rs.getInt("std_roll")) + "%";

                j++;
                i = 0;
                list.add(new ArrayList<Object>() {
                    {
                        for (String txt : temp) {
                            add(txt);
                        }
                    }
                });
            }
            if (count > 0) {
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;
    }

    public static int calTotal(int sid) {
        int total = 0;
        String[] lst = {"mrk_id", "mrk_obt"};
        ArrayList<ArrayList<Object>> list = SqlLibrary.select("marks", "*", "std_id=" + sid, lst);
        int rows = list.size();
        if (rows > 0) {
            for (int i = 0; i < rows; i++) {
                total += Integer.parseInt((String) ((ArrayList<?>) list.get(i)).get(2));
            }
        }
        return total;
    }

    public static float calPercent(int sid) {
        float per = (float) 0.00;
        int total = 0;
        String[] lst = {"mrk_id", "mrk_obt"};
        ArrayList<ArrayList<Object>> list = SqlLibrary.select("marks", "*", "std_id=" + sid, lst);
        int rows = list.size();
        if (rows > 0) {
            for (int i = 0; i < rows; i++) {
                total += Integer.parseInt((String) ((ArrayList<?>) list.get(i)).get(2));
            }
            per = total / rows;
        }

        return per;
    }

    public static String chkStatus(int sid) {
        String st = "No result";
        String[] lst = {"mrk_id", "mrk_obt"};
        ArrayList<ArrayList<Object>> list = SqlLibrary.select("marks", "*", "std_id=" + sid, lst);
        int rows = list.size();
        if (rows > 0) {
            for (int i = 0; i < rows; i++) {
                if (Integer.parseInt((String) ((ArrayList<?>) list.get(i)).get(2)) < 40) {
                    st = "Fail";
                    break;
                } else {
                    st = "Pass";
                }

            }
        }

        return st;
    }

    public static ArrayList<ArrayList<Object>> selMarkSheet(String cond) {
        ArrayList<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();
        int len = 6;
        final String[] temp = new String[len];
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        String query = "SELECT * FROM student WHERE " + cond;
        try {
            stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            rs.last();
            int i = 0, j = 1;
            int count = rs.getRow();
            rs = stmt.executeQuery();
            //System.out.println(query);
            while (rs.next()) {
                temp[0] = "" + j;
                i++;
                temp[1] = "" + rs.getInt("std_roll");
                temp[2] = rs.getString("std_name") + " " + rs.getString("std_lname");
                temp[3] = SqlLibrary.chkStatus(rs.getInt("std_roll"));
                temp[4] = "" + SqlLibrary.calTotal(rs.getInt("std_roll"));
                temp[5] = "" + SqlLibrary.calPercent(rs.getInt("std_roll")) + "%";

                j++;
                i = 0;
                list.add(new ArrayList<Object>() {
                    {
                        for (String txt : temp) {
                            add(txt);
                        }
                    }
                });
            }
            if (count > 0) {
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;
    }

    public static ArrayList<ArrayList<Object>> selectMarkSheet(String table, String sel, String cond, String[] flds) {
        ArrayList<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();
        int len = flds.length + 2;
        final String[] temp = new String[len];
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        String query = "SELECT " + sel + " FROM " + table + " WHERE " + cond;
        try {
            stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            rs.last();
            int i = 0, j = 1;
            int count = rs.getRow();
            rs = stmt.executeQuery();
            //System.out.println(query);
            while (rs.next()) {
                temp[0] = "" + j;
                i++;
                for (String fld : flds) {
                    temp[i] = rs.getString(fld);
                    //System.out.println(temp[i]);
                    i++;
                }
                if (Integer.parseInt(temp[4]) < 40) {
                    temp[5] = "*";
                } else {
                    temp[5] = "";
                }

                j++;
                i = 0;
                //System.out.println("ggggggg");
                list.add(new ArrayList<Object>() {
                    {
                        for (String txt : temp) {
                            add(txt);
                        }
                    }
                });
            }
            if (count > 0) {
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;
    }

    //query for the event list for the database
    public static ArrayList<ArrayList<Object>> selectEvents(String table, String sel, String cond) {
        ArrayList<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();
        int len = 1;
        final String[] temp = new String[len];
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        String query = "SELECT " + sel + " FROM " + table + " WHERE " + cond + " order by evt_date asc";
        try {
            stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            rs.last();
            int i = 0, j = 1;
            int count = rs.getRow();
            rs = stmt.executeQuery();
            //System.out.println(query);
            while (rs.next()) {
                //temp[0] = ""+j;
                long tempDate = Long.parseLong(rs.getString("evt_date"));
                DateFormat ft = new SimpleDateFormat("d");
                Date date = new Date(tempDate);
                String tmpDate = ft.format(date);
                String title = rs.getString("evt_name");
                temp[0] = tmpDate + " - " + title;
                j++;
                list.add(new ArrayList<Object>() {
                    {
                        for (String txt : temp) {
                            add(txt);
                        }
                    }
                });
            }
            if (count > 0) {
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;
    }

    public static int noOfRows(String table, String conditon) {
        int noRows = 0;
        Connection con = getConnection();
        PreparedStatement stmt = null;
        String query = "SELECT * FROM " + table + " WHERE " + conditon;
        try {
            stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            rs.last();
            noRows = rs.getRow();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return noRows;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
    }
}
