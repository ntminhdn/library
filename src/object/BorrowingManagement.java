/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import data.DataAccess;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PhiLong
 */
public class BorrowingManagement {

    private String BorrowID;
    private String RdID;
    private String BookID;
    private Date BorrowDate;
    private Date ReturnDate;
    public DataAccess da = new DataAccess();
    public PreparedStatement ps = null;

    public BorrowingManagement(String BorrowID, String RdID, String BookID, Date BorrowDate, Date ReturnDate) {
        this.BorrowID = BorrowID;
        this.RdID = RdID;
        this.BookID = BookID;
        this.BorrowDate = BorrowDate;
        this.ReturnDate = ReturnDate;
    }

    public String getBorrowID() {
        return BorrowID;
    }

    public void setBorrowID(String BorrowID) {
        this.BorrowID = BorrowID;
    }

    public String getRdID() {
        return RdID;
    }

    public void setRdID(String RdID) {
        this.RdID = RdID;
    }

    public String getBookID() {
        return BookID;
    }

    public void setBookID(String BookID) {
        this.BookID = BookID;
    }

    public Date getBorrowDate() {
        return BorrowDate;
    }

    public void setBorrowDate(Date BorrowDate) {
        this.BorrowDate = BorrowDate;
    }

    public Date getReturnDate() {
        return ReturnDate;
    }

    public void setReturnDate(Date ReturnDate) {
        this.ReturnDate = ReturnDate;
    }

    public String getRdName(String id) throws Exception {
        ResultSet re = da.getData("select RdName from reader,borrowingmanagement where BorrowID = '" + id + "' and reader.RdID = borrowingmanagement.RdID");
        String kq = "";
        while (re.next()) {
            kq = re.getString("RdName");
        }
        return kq;
    }

    public String getRdEmail(String id) {
        ResultSet re = da.getData("select Email from reader,borrowingmanagement where BorrowID = '" + id + "' and reader.RdID = borrowingmanagement.RdID");
        String kq = "";
        try {
            while (re.next()) {
                kq = re.getString("Email");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowingManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }

    public Vector toVector() {
        DateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        Vector v = new Vector();
        v.addElement(this.BorrowID);
        v.addElement(this.RdID);
        v.addElement(this.BookID);
        v.addElement(d.format(this.BorrowDate));
        v.addElement(d.format(this.ReturnDate));
        v.addElement("+");
        return v;
    }
}
