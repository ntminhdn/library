/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import object.Author;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class AuthorData {

    private ArrayList<Author> authorList = new ArrayList<Author>();
    private DataAccess da = new DataAccess();

    public ArrayList<Author> getList() {
        return authorList;
    }

    public void load(String sql) {
        try {
            ResultSet rs = da.getData(sql);
            Author b;
            while (rs.next()) {
                b = new Author(rs.getString("AuthorID"),
                        rs.getString("AuthorName"));
                authorList.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AuthorData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void add(Author b) {
        authorList.add(b);
        String sql = "insert into author "
                + "(AuthorID,AuthorName)"
                + "values ('"
                + b.getAuthorID() + "',N'"
                + b.getAuthorName() + "') ";
        da.updateDB(sql);
    }

    public void update(int i, Author b) {
        authorList.set(i, b);
        String sql = "update author set "
                + " authorName = N'" + b.getAuthorName()
                + "' where authorID='" + b.getAuthorID() + "'";
//        System.out.println(sql);
        da.updateDB(sql);
    }

    public int delete(String authorID) {
        String sql = "delete from author where authorID ='" + authorID + "'";
        da.updateDB(sql);
        for (Author b : authorList) {
            if (b.getAuthorID().equals(authorID)) {
                int i = authorList.indexOf(b);
                authorList.remove(b);
                return i;
            }
        }
        return -1;
    }

    public Author getAuthor(int i) {
        return authorList.get(i);
    }
}
