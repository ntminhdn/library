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
public class NextYear {

    private Date today = new Date();
    private Date nextYearDay;
    static GregorianCalendar calendar = new GregorianCalendar();
    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public NextYear() {
        try {
            this.today = df.parse(df.format(today));
            calendar.setTime(today);
            calendar.add(calendar.YEAR, 1);
            this.nextYearDay = calendar.getTime();
        } catch (ParseException ex) {
            Logger.getLogger(NextYear.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public Date getNextYearDay() {
        return nextYearDay;
    }

    public void setNextYearDay(Date nextYearDay) {
        this.nextYearDay = nextYearDay;
    }
    
    
}
