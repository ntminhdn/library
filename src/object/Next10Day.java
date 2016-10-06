/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PhiLong
 */
public class Next10Day {

    private Date today = new Date();
    private Date next10Day;
    static GregorianCalendar calendar = new GregorianCalendar();
    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public Next10Day() {
        try {
            this.today = df.parse(df.format(today));
            calendar.setTime(today);
            calendar.add(calendar.DATE, 10);
            this.next10Day = calendar.getTime();
        } catch (ParseException ex) {
            Logger.getLogger(Next10Day.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public Date getNext10Day() {
        return next10Day;
    }

    public void setNext10Day(Date next10Day) {
        this.next10Day = next10Day;
    }
    
}
