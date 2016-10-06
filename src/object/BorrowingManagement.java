/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

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
    
    public Vector toVector() {
        DateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        Vector v = new Vector();
        v.addElement(this.BorrowID);
        v.addElement(this.RdID);
        v.addElement(this.BookID);
        v.addElement(d.format(this.BorrowDate));
        v.addElement(d.format(this.ReturnDate));
        return v;
    }
}
