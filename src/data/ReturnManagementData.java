/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.BorrowingManagement;
import object.ReturnManagement;

/**
 *
 * @author PhiLong
 */
public class ReturnManagementData {
    private ArrayList<ReturnManagement> returnManagementList = new ArrayList<ReturnManagement>();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private DataAccess da = new DataAccess();

    public ArrayList<ReturnManagement> getReturnList() {
        return returnManagementList;
    }

    public void load(String sql) {
        try {
            ResultSet rs = da.getData(sql);
            ReturnManagement b;
            while (rs.next()) {
                b = new ReturnManagement(rs.getString("BorrowID"),
                        rs.getString("BookID"),
                        rs.getString("RdName"),
                        rs.getString("BookName"),
                        rs.getString("AuthorName"),
                        rs.getString("PublisherName"),
                        rs.getInt("Price"),
                        df.parse(rs.getString("BorrowDate")),
                        df.parse(rs.getString("ReturnDate")));
                returnManagementList.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowingManagementData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(BorrowingManagementData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ReturnManagement getReturnManagement(int i) {
        return returnManagementList.get(i);
    }

}
