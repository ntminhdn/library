/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.Admin;
import object.Author;

/**
 *
 * @author PhiLong
 */
public class AdminData {

    private ArrayList<Admin> adminList = new ArrayList<Admin>();
    private DataAccess da = new DataAccess();

    public ArrayList<Admin> getList() {
        return adminList;
    }

    public void load(String sql) {
        try {
            ResultSet rs = da.getData(sql);
            Admin b;
            while (rs.next()) {
                b = new Admin(rs.getString("AdminID"),
                        rs.getString("AdminName"),
                        rs.getString("UserName"),
                        rs.getString("Pass"),
                        rs.getString("Sex"),
                        rs.getString("Role"),
                        rs.getString("email")
                );
                adminList.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AuthorData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void add(Admin b) {
        adminList.add(b);
        String sql = "insert into admin "
                + "values ('"
                + b.getAdID() + "','"
                + b.getAdName() + "','"
                + b.getAdUser() + "','"
                + b.getAdPass() + "','"
                + b.getSex() + "','"
                + b.getRole() + "','"
                + b.getEmail() + "') ";
        System.out.println(sql);
        da.updateDB(sql);
    }

    public void update(int i, Admin b) {
        adminList.set(i, b);
        String sql = "update admin set "
                + " AdminName = '" + b.getAdName()
                + "', UserName = '" + b.getAdUser()
                + "', Pass = '" + b.getAdPass()
                + "', Sex = '" + b.getSex()
                + "', Role = '" + b.getRole()
                + "', email = '" + b.getEmail()
                + "' where AdminID='" + b.getAdID()+ "'";
        System.out.println(sql);
        da.updateDB(sql);
    }

    public int delete(String authorID) {
        String sql = "delete from admin where AdminID ='" + authorID + "'";
        da.updateDB(sql);
        for (Admin b : adminList) {
            if (b.getAdID().equals(authorID)) {
                int i = adminList.indexOf(b);
                adminList.remove(b);
                return i;
            }
        }
        return -1;
    }

    public Admin getAdmin(int i) {
        return adminList.get(i);
    }
}
