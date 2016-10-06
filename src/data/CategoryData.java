/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;


import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.WebRowSet;
import object.Category;

/**
 *
 * @author Admin
 */
public class CategoryData {

    private ArrayList<Category> categoryList = new ArrayList<Category>();
    private ConnectDatabase conn = new ConnectDatabase();

    public ArrayList<Category> getCategoryList() {
        return categoryList;
    }
    public Category getCategory(int i) {
        return categoryList.get(i);
    }
    public void loadCategory(){
        try {
            String sql = "Select * from category";
            ResultSet rs = conn.getData(sql);
            while (rs.next()){
                String categoryid = rs.getString("CategoryID");
                String category = rs.getString("CategoryName");
                Category c = new Category(categoryid, category);
                this.categoryList.add(c);
            }
        } catch (SQLException ex) {
//            System.out.println("Err: "+ex.getMessage());;
        }
    }

    public void load(String sql) {
        try {
            ResultSet rs = conn.getData(sql);
            Category c;
            while (rs.next()) {
                c = new Category(rs.getString("CategoryID"),
                        rs.getString("CategoryName"));
                categoryList.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void them(Category c) {
        categoryList.add(c);
        String sql = "insert into category "
                + "(CategoryID,CategoryName)"
                + "values ('"
                + c.getCategoryID() + "','"
                + c.getCategoryName() + "') ";
//        System.out.println(sql);
        try {
            conn.addData(sql);
        } catch (Exception ex) {
            Logger.getLogger(CategoryData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sua(int i, Category c) {
        //sua arraylist
        categoryList.set(i, c);
        //sua CSDL          
        String sql = "update category set "
                + " CategoryID='" + c.getCategoryID() + "',"
                + " CategoryName ='" + c.getCategoryName() + "'"
                + " where CategoryID='" + c.getCategoryID() + "'";
//        System.out.println(sql);
        try {
            conn.updateData(sql);
        } catch (Exception ex) {
            Logger.getLogger(CategoryData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int xoa(String CategoryId) {
        String sql = "delete from category where CategoryID ='" + CategoryId + "'";
//        System.out.println(sql);
        try {
            conn.updateData(sql);
        } catch (Exception ex) {
            Logger.getLogger(CategoryData.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Category c : categoryList) {
            if (c.getCategoryID().equalsIgnoreCase(CategoryId)) {
                int i = categoryList.indexOf(c);
                categoryList.remove(c);
                return i;
            }
        }
        return -1;
    }
}
