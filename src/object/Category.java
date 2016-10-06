/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;


import java.util.Vector;

/**
 *
 * @author Admin
 */
public class Category {
    private String CategoryID;
    private String CategoryName;
    public Category(String CategoryID,String CategoryName)
            {
                this.CategoryID= CategoryID;
                this.CategoryName=CategoryName;
            }

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }
     public Vector toVector() {
       
        Vector v = new Vector();
        v.addElement(this.CategoryID);
        v.addElement(this.CategoryName);
        return v;
    }
}
