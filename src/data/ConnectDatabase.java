package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectDatabase {

    private static Connection con;
    PreparedStatement pst = null;
    public static final String DRIVER = "org.gjt.mm.mysql.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/library";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "";

    public ResultSet getData(String sql) {
        ResultSet rs = null;
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public void updateData(String sql) throws Exception {
        openConnect();
        pst = con.prepareStatement(sql);
        pst.executeUpdate();
        closeConnect();
    }

    public void addData(String sql) throws Exception {
        openConnect();
        pst = con.prepareStatement(sql);
        pst.executeUpdate();
        closeConnect();
    }

    public void openConnect() throws Exception {

        Class.forName(DRIVER);
        con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);

    }

    public void closeConnect() throws Exception {
        if (con != null && !con.isClosed()) {
            con.isClosed();
        }
    }

    public ArrayList<String> loadBookName(String sql, String name) {
        ResultSet rs = getData(sql);
        ArrayList<String> t = new ArrayList<>();
        try {
            while (rs.next()) {
                t.add(rs.getString(name));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SearchList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
}
