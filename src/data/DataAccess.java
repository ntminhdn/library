package data;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

public class DataAccess {

    private static Connection conn;
    public static final String DRIVER = "org.gjt.mm.mysql.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/library";
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
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
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
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Thông báo lỗi", 1);
        }
    }
}
