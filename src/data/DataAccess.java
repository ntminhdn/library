package data;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

public class DataAccess {

    private static Connection conn;
    public static final String DRIVER = "org.gjt.mm.mysql.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/library?autoReconnect=true";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "";
    public static PreparedStatement ps = null;

    public ResultSet getData(String sql) {
        ResultSet rs = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            Statement st = conn.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex, "Thông báo lỗi", 1);
        }
        return rs;
    }

    public int updateDB(String sql) {
        int n = 0;
        try {
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            Statement st = conn.createStatement();
            n = st.executeUpdate(sql);
            st.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Thông báo lỗi", 1);
        }
        return n;
    }

    public static void LoadData(String sql, JTable tb) {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            tb.setModel(DbUtils.resultSetToTableModel(rs));
            //conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Thông báo lỗi", 1);
        }
    }

    public Connection getConnection() {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return conn;
    }

    public void openConnect() throws Exception {
        Class.forName(DRIVER);
        conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }

    public void closeConnect() throws Exception {
        if (conn != null && !conn.isClosed()) {
            conn.isClosed();
        }
    }

    public void updateData(String sql) throws Exception {
        openConnect();
        ps = conn.prepareStatement(sql);
        ps.executeUpdate();
        closeConnect();
    }

    public void addData(String sql) throws Exception {
        openConnect();
        ps = conn.prepareStatement(sql);
        ps.executeUpdate();
        closeConnect();
    }

    public ArrayList<String> loadBookName(String sql, String name) {
        ResultSet rs = getData(sql);
        ArrayList<String> t = new ArrayList<>();
        try {
            while (rs.next()) {
                t.add(rs.getString(name));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return t;
    }
}
