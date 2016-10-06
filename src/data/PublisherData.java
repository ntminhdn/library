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
import object.Author;
import object.Publisher;

/**
 *
 * @author PhiLong
 */
public class PublisherData {
    private ArrayList<Publisher> publisherList = new ArrayList<Publisher>();
    private DataAccess da = new DataAccess();

    public ArrayList<Publisher> getPublisherList() {
        return publisherList;
    }

    public void load(String sql) {
        try {
            ResultSet rs = da.getData(sql);
            Publisher b;
            while (rs.next()) {
                b = new Publisher(rs.getString("PublisherID"),
                        rs.getString("PublisherName"));
                publisherList.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PublisherData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void add(Publisher b) {
        publisherList.add(b);
        String sql = "insert into publisher "
                + "(PublisherID,PublisherName)"
                + "values ('"
                + b.getPublisherID()+ "',N'"
                + b.getPublisherName()+ "') ";
        da.updateDB(sql);
    }

    public void update(int i, Publisher b) {
        publisherList.set(i, b);
        String sql = "update publisher set "
                + " PublisherName = N'" + b.getPublisherName()
                + "' where PublisherID='" + b.getPublisherID()+ "'";
        da.updateDB(sql);
    }

    public int delete(String publisherID) {
        String sql = "delete from publisher where PublisherID ='" + publisherID + "'";
        da.updateDB(sql);
        for (Publisher b : publisherList) {
            if (b.getPublisherID().equals(publisherID)) {
                int i = publisherList.indexOf(b);
                publisherList.remove(b);
                return i;
            }
        }
        return -1;
    }

    public Publisher getPublisher(int i) {
        return publisherList.get(i);
    }
}
