/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.util.Vector;

/**
 *
 * @author Administrator
 */
public class Search {
    private String bookID;
    private String bookName;
    private String authorName;
    private String supplierName;
    private String categoryName;
    private int price;
    private int quantity;

    public Search(String bookID, String bookName, String authorName, String supplierName, String categoryName, int price, int quantity) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.authorName = authorName;
        this.supplierName = supplierName;
        this.categoryName = categoryName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public Vector toVector(){
        Vector v = new Vector();
        v.addElement(this.bookID);
        v.addElement(this.bookName);
        v.addElement(this.authorName);
        v.addElement(this.supplierName);
        v.addElement(this.categoryName);
        v.addElement(this.price);
        v.addElement(this.quantity);
        return v;
    }
}
