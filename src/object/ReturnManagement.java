/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PhiLong
 */
public class ReturnManagement {

    private String BorrowID;
    private String BookID;
    private String ReaderName;
    private String BookName;
    private String AuthorName;
    private String PublisherName;
    private int Price;
    private Date BorrowDate;
    private Date ReturnDate;
    private long OverdueDays;
    private long Penalty;
    static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    static Date today = new Date();

    public ReturnManagement(String BorrowID, String BookID, String ReaderName,String BookName, String AuthorName, String PublisherName, int Price, Date BorrowDate, Date ReturnDate) {
        this.BorrowID = BorrowID;
        this.BookID = BookID;
        this.ReaderName = ReaderName;
        this.BookName = BookName;
        this.AuthorName = AuthorName;
        this.PublisherName = PublisherName;
        this.Price = Price;
        this.BorrowDate = BorrowDate;
        this.ReturnDate = ReturnDate;
        try {
            today = df.parse(df.format(today));
        } catch (ParseException ex) {
            Logger.getLogger(ReturnManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        OverdueDays = truNgayThang(this.ReturnDate);
        if(OverdueDays <= 30){
            Penalty = 1000*OverdueDays;
        } else {
            int m = (int) (OverdueDays/30);
            Penalty = (m * 20000) + (OverdueDays * 1000);
        }
        
    }

    private static long truNgayThang(Date a) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        Date today = new Date();

        try {
            c1.setTime(df.parse(df.format(a)));
            c2.setTime(df.parse(df.format(today)));
        } catch (ParseException ex) {
            Logger.getLogger(ReturnManagement.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Công thức tính số ngày giữa 2 mốc thời gian:
        long noDay = (c2.getTime().getTime() - c1.getTime().getTime())
                / (24 * 3600 * 1000);
        if (noDay > 0) {
            return noDay;
        } else {
            return 0;
        }
    }

    public String getBorrowID() {
        return BorrowID;
    }

    public void setBorrowID(String BorrowID) {
        this.BorrowID = BorrowID;
    }

    public String getBookID() {
        return BookID;
    }

    public void setBookID(String BookID) {
        this.BookID = BookID;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String BookName) {
        this.BookName = BookName;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public void setAuthorName(String AuthorName) {
        this.AuthorName = AuthorName;
    }

    public String getPublisherName() {
        return PublisherName;
    }

    public void setPublisherName(String PublisherName) {
        this.PublisherName = PublisherName;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
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

    public long getOverdueDays() {
        return OverdueDays;
    }

    public void setOverdueDays(long OverdueDays) {
        this.OverdueDays = OverdueDays;
    }

    public long getPenalty() {
        return Penalty;
    }

    public void setPenalty(long Penalty) {
        this.Penalty = Penalty;
    }

    public String getReaderName() {
        return ReaderName;
    }

    public void setReaderName(String ReaderName) {
        this.ReaderName = ReaderName;
    }
    
    
    public Vector toVector() {
        DateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        Vector v = new Vector();
        v.addElement(this.BorrowID);
        v.addElement(this.BookID);
        v.addElement(this.ReaderName);
        v.addElement(this.BookName);
        v.addElement(this.AuthorName);
        v.addElement(this.PublisherName);
        v.addElement(this.Price);
        v.addElement(d.format(this.BorrowDate));
        v.addElement(d.format(this.ReturnDate));
        v.addElement(this.OverdueDays);
        v.addElement(this.Penalty);
        return v;
    }
    
}
