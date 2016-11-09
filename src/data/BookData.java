/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import object.Book;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Administrator
 */
public class BookData {

    private ArrayList<Book> bookList = new ArrayList<Book>();
    private DataAccess da = new DataAccess();

    public ArrayList<Book> getList() {
        return bookList;
    }

    public void load(String sql) {
        try {
            ResultSet rs = da.getData(sql);
            Book b;
            while (rs.next()) {
                b = new Book(rs.getString("BookID"),
                        rs.getString("BookName"),
                        rs.getString("AuthorID"),
                        rs.getString("PublisherID"),
                        rs.getString("SupplierID"),
                        rs.getString("CategoryID"),
                        rs.getInt("Price"),
                        rs.getInt("Quantity"),
                        rs.getString("Shelf"),
                        rs.getInt("RowNum"),
                        rs.getInt("ColNum"),
                        rs.getString("Image"));
                bookList.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void add(Book b) {
        bookList.add(b);
        String sql = "insert into book "
                + "(BookID,BookName,AuthorID, PublisherID,SupplierID,CategoryID,Price,Quantity,Shelf,RowNum,ColNum,Image)"
                + "values ('"
                + b.getBookID() + "',N'"
                + b.getBookName() + "','"
                + b.getAuthorID() + "','"
                + b.getPublisherID()+ "','"
                + b.getSupplierID() + "','"
                + b.getCategoryID() + "','"
                + b.getPrice() + "', '"
                + b.getQuantity() + "', '"
                + b.getShelf() + "', '"
                + b.getRowNum() + "', '"
                + b.getColNum() + "', '"
                + b.getImage() + "') ";
        da.updateDB(sql);
    }

    public void update(int i, Book b) {
        bookList.set(i, b);
        String sql = "update book set "
                + " bookName =N'" + b.getBookName() + "',"
                + " authorID ='" + b.getAuthorID() + "',"
                + " PublisherID ='" + b.getPublisherID()+ "',"
                + " supplierID ='" + b.getSupplierID() + "',"
                + " categoryID ='" + b.getCategoryID() + "',"
                + " price ='" + b.getPrice() + "',"
                + " quantity ='" + b.getQuantity() + "',"
                + " shelf ='" + b.getShelf() + "',"
                + " rowNum ='" + b.getRowNum() + "',"
                + " colNum ='" + b.getColNum() + "',"
                + " image='" + b.getImage() + "'"
                + " where bookID='" + b.getBookID() + "'";
        da.updateDB(sql);
    }

    public int delete(String bookID) {
        String sql = "delete from book where bookID ='" + bookID + "'";
        da.updateDB(sql);
        for (Book b : bookList) {
            if (b.getBookID().equals(bookID)) {
                int i = bookList.indexOf(b);
                bookList.remove(b);
                return i;
            }
        }
        return -1;
    }
    public Book getBook(int i) {
        return bookList.get(i);
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

    public void updateQuantity(int sl, String id) {
//        bookList.set(i, b);
        String sql = "update book set "
                + " quantity ='" + sl + "' "
                + " where bookID='" + id + "'";
//        System.out.println(sql);
        da.updateDB(sql);
    }
}
