/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import object.Reader;

/**
 *
 * @author PhiLong
 */
public class ReaderData {

    private ArrayList<Reader> readerList = new ArrayList<Reader>();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private DataAccess da = new DataAccess();

    public ArrayList<Reader> getList() {
        return readerList;
    }

    public void load(String sql) {
        try {
            ResultSet rs = da.getData(sql);
            Reader b;
            while (rs.next()) {
                b = new Reader(rs.getString("RdID"),
                        rs.getString("RdName"),
                        rs.getString("IDCardNumber"),
                        rs.getString("Sex"),
                        df.parse(rs.getString("Birthday")),
                        rs.getString("Address"),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        df.parse(rs.getString("ActivationDate")),
                        df.parse(rs.getString("ExpiredDate")));
                readerList.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReaderData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ReaderData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Reader getReader(int i) {
        return readerList.get(i);
    }

    public void them(Reader t) throws Exception {
        readerList.add(t);
        
        String sql = "insert into reader "
                + "(RdID,RdName,IDCardNumber,Sex,Birthday,Address,Phone,Email,ActivationDate,ExpiredDate) "
                + "values('"
                + t.getRdID() + "','"
                + t.getRdName() + "','"
                + t.getIDCardNumber() + "','"
                + t.getSex() + "','"
                + df.format(t.getBirthday()) + "','"
                + t.getAddress() + "','"
                + t.getPhone() + "','"
                + t.getEmail() + "','"
                + df.format(t.getActivationDate())
                + "','" + df.format(t.getExpiredDate())
                + "')";
//        System.out.println(sql);
        try {
            da.addData(sql);
        } catch (Exception ex) {
            Logger.getLogger(ReaderData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sua(int i, Reader t) throws Exception {
        //sua arraylist
        readerList.set(i, t);
        //sua CSDL          
        String sql = "update reader set"
                + " RdName = '" + t.getRdName()+ "',"
                + " IDCardNumber ='" + t.getIDCardNumber()+ "',"
                + " Sex='" + t.getSex() + "',"
                + " Birthday='" + df.format(t.getBirthday())+ "',"
                + " Address='" + t.getAddress()+ "',"
                + " Phone='" + t.getPhone()+ "',"
                + " Email='" + t.getEmail()+ "',"
                + " ActivationDate='" + df.format(t.getActivationDate())+ "',"
                + " ExpiredDate='" + df.format(t.getExpiredDate())+ "'"
                + " where RdID='" + t.getRdID()+ "'";
        da.updateData(sql);
    }
//
    public int xoa(String RdID) throws Exception {
        String sql = "delete from reader where RdID = '" + RdID + "'";
//        System.out.println(sql);
        da.updateData(sql);
        
        for (Reader t : readerList) {
            if (t.getRdID().equals(RdID)) {
                int i = readerList.indexOf(t);
                readerList.remove(t);
                return i;
            }
        }
        return -1;
    }

    public void suaExpiredDate(int i, Reader t) throws Exception {
        readerList.set(i, t);
        //sua CSDL          
        String sql = "update reader set"
                + " ActivationDate='" + df.format(t.getActivationDate()) + "'"
                + " ,ExpiredDate='" + df.format(t.getExpiredDate()) + "'"
                + " where RdID='" + t.getRdID()+ "'";
        System.out.println(sql);
        da.updateData(sql);
    }
    public Icon loadIcon(String linkImage, int k, int m) {/*linkImage là tên icon, k kích thước chiều rộng mình muốn,m chiều dài và hàm này trả về giá trị là 1 icon.*/
        try {
            BufferedImage image = ImageIO.read(new File(linkImage));//đọc ảnh dùng BufferedImage

            int x = k;
            int y = m;
            int ix = image.getWidth();
            int iy = image.getHeight();
            int dx = 0, dy = 0;

            if (x / y > ix / iy) {
                dy = y;
                dx = dy * ix / iy;
            } else {
                dx = x;
                dy = dx * iy / ix;
            }

            return new ImageIcon(image.getScaledInstance(dx, dy,
                    image.SCALE_SMOOTH));

        } catch (IOException e) {
//            System.out.println("Err: " + e.getMessage());
        }
        return null;
    }
}
