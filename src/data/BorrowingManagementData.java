/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import object.BorrowingManagement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author PhiLong
 */
public class BorrowingManagementData {

    private ArrayList<BorrowingManagement> borrowingManagementList = new ArrayList<BorrowingManagement>();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private DataAccess da = new DataAccess();

    public ArrayList<BorrowingManagement> getList() {
        return borrowingManagementList;
    }

    public void load(String sql) {
        try {
            ResultSet rs = da.getData(sql);
            BorrowingManagement b;
            while (rs.next()) {
                b = new BorrowingManagement(rs.getString("BorrowID"),
                        rs.getString("RdID"),
                        rs.getString("BookID"),
                        df.parse(rs.getString("BorrowDate")),
                        df.parse(rs.getString("ReturnDate")));
                borrowingManagementList.add(b);
            }
        } catch (SQLException | ParseException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public BorrowingManagement getBorrowingManagement(int i) {
        return borrowingManagementList.get(i);
    }

    public void them(BorrowingManagement t) throws Exception {
        borrowingManagementList.add(t);

        String sql = "insert into borrowingmanagement "
                + "(BorrowID, RdID,BookID,BorrowDate,ReturnDate) "
                + "values('"
                + t.getBorrowID() + "','"
                + t.getRdID() + "','"
                + t.getBookID() + "','"
                + df.format(t.getBorrowDate()) + "','"
                + df.format(t.getReturnDate())
                + "')";
//        System.out.println(sql);
        try {
            da.addData(sql);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void sua(int i, BorrowingManagement t) throws Exception {
        //sua arraylist
        borrowingManagementList.set(i, t);
        //sua CSDL          
        String sql = "update borrowingmanagement set"
                + " RdID = '" + t.getRdID() + "',"
                + " BookID ='" + t.getBookID() + "',"
                + " BorrowDate='" + df.format(t.getBorrowDate()) + "',"
                + " ReturnDate='" + df.format(t.getReturnDate()) + "'"
                + " where BorrowID='" + t.getBorrowID()+ "'";
        da.updateData(sql);
    }

    public void suaReturnDate(int i, BorrowingManagement t) throws Exception {
        //sua arraylist
        borrowingManagementList.set(i, t);
        //sua CSDL          
        String sql = "update borrowingmanagement set"
                + " BorrowDate='" + df.format(t.getBorrowDate()) + "'"
                + " ,ReturnDate='" + df.format(t.getReturnDate()) + "'"
                + " where BorrowID='" + t.getBorrowID() + "'";
        System.out.println(sql);
        da.updateData(sql);
    }
//

    public int xoa(String BorrowID) throws Exception {
        String sql = "delete from borrowingmanagement where BorrowID = '" + BorrowID + "'";
//        System.out.println(sql);
        da.updateData(sql);

        for (BorrowingManagement t : borrowingManagementList) {
            if (t.getBorrowID().equals(BorrowID)) {
                int i = borrowingManagementList.indexOf(t);
                borrowingManagementList.remove(t);
                return i;
            }
        }
        return -1;
    }
}
