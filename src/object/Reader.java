/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

/**
 *
 * @author PhiLong
 */
public class Reader {

    private String RdID;
    private String RdName;
    private String IDCardNumber;
    private String Sex;
    private Date Birthday;
    private String Address;
    private String Phone;
    private String Email;
    private Date ActivationDate;
    private Date ExpiredDate;


    public Reader(String RdID, String RdName, String IDCardNumber, String Sex, Date Birthday, String Address, String Phone, String Email, Date ActivationDate, Date ExpiredDate) {
        this.RdID = RdID;
        this.RdName = RdName;
        this.IDCardNumber = IDCardNumber;
        this.Sex = Sex;
        this.Birthday = Birthday;
        this.Address = Address;
        this.Phone = Phone;
        this.Email = Email;
        this.ActivationDate = ActivationDate;
        this.ExpiredDate = ExpiredDate;
    }

    public String getRdID() {
        return RdID;
    }

    public void setRdID(String RdID) {
        this.RdID = RdID;
    }

    public String getRdName() {
        return RdName;
    }

    public void setRdName(String RdName) {
        this.RdName = RdName;
    }

    public String getIDCardNumber() {
        return IDCardNumber;
    }

    public void setIDCardNumber(String IDCardNumber) {
        this.IDCardNumber = IDCardNumber;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date Birthday) {
        this.Birthday = Birthday;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Date getActivationDate() {
        return ActivationDate;
    }

    public void setActivationDate(Date ActivationDate) {
        this.ActivationDate = ActivationDate;
    }

    public Date getExpiredDate() {
        return ExpiredDate;
    }

    public void setExpiredDate(Date ExpiredDate) {
        this.ExpiredDate = ExpiredDate;
    }

    public Vector toVector() {
        DateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        Vector v = new Vector();
        v.addElement(this.RdID);
        v.addElement(this.RdName);
        v.addElement(this.IDCardNumber);
        v.addElement(this.Sex);
        v.addElement(d.format(this.Birthday));
        v.addElement(this.Address);
        v.addElement(this.Phone);
        v.addElement(this.Email);
        v.addElement(d.format(this.ActivationDate));
        v.addElement(d.format(this.ExpiredDate));
        v.addElement("+");
        return v;
    }
//    @Override
//    public String toString() {
//        DateFormat df = new SimpleDateFormat("yyyy-m-d");
//        return "Library{" + "tourID=" + tourID + ", title=" + title + ", type_tour=" + type + ", departureDate=" + df.format(departureDate)+ ", dateNumber=" + dateNumber + ", categoryID="+categoryID+'}';
//    }
}
