/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import data.DataAccess;
import object.Search;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class SearchList {

    private ArrayList<Search> searchList = new ArrayList<Search>();
    private DataAccess da = new DataAccess();

    public ArrayList<Search> getList() {
        return searchList;
    }
    
    public void load(String sql){
        try {
            ResultSet rs = da.getData(sql);
            Search t;
            while(rs.next()){
                t = new Search(rs.getString("BookID"),
                        rs.getString("BookName"),
                        rs.getString("AuthorName"),
                        rs.getString("SupplierName"),
                        rs.getString("CategoryName"),
                        rs.getInt("Price"),
                        rs.getInt("Quantity"));
                searchList.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SearchList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
