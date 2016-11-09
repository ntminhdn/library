package object;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @authorID Administrator
 */
public class Book {

    private String bookID;
    private String bookName;
    private String authorID;
    private String publisherID;
    private String supplierID;
    private String categoryID;
    private int price;
    private int quantity;
    private String shelf;
    private int rowNum;
    private int colNum;
    private String image;
    

    public Book(String bookID, String bookName, String authorID, String publisherID , String supplierID, String categoryID, int price, int quantity, String shelf, int rowNum, int colNum, String image) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.authorID = authorID;
        this.publisherID = publisherID;
        this.supplierID = supplierID;
        this.categoryID = categoryID;
        this.price = price;
        this.quantity = quantity;
        this.shelf = shelf;
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    public void setColNum(int colNum) {
        this.colNum = colNum;
    }

    public String getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(String publisherID) {
        this.publisherID = publisherID;
    }
    

    public Vector toVector() {
        Vector v = new Vector();
        v.addElement(this.bookID);
        v.addElement(this.bookName);
        v.addElement(this.authorID);
        v.addElement(this.publisherID);
        v.addElement(this.supplierID);
        v.addElement(this.categoryID);
        v.addElement(this.price);
        v.addElement(this.quantity);
        v.addElement(this.shelf);
        v.addElement(this.rowNum);
        v.addElement(this.colNum);
        v.addElement(this.image);
        
        return v;
    }
}
