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
import javax.swing.JOptionPane;
import object.Supplier;

/**
 *
 * @supplier Administrator
 */
public class SupplierData {

    private ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
    private DataAccess da = new DataAccess();

    public ArrayList<Supplier> getSupplierList() {
        return supplierList;
    }

    public void load(String sql) {
        try {
            ResultSet rs = da.getData(sql);
            Supplier b;
            while (rs.next()) {
                b = new Supplier(rs.getString("SupplierID"),
                        rs.getString("SupplierName"),
                        rs.getString("S_Address"),
                        rs.getString("S_Phone"));
                supplierList.add(b);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void add(Supplier b) {
        supplierList.add(b);
        String sql = "insert into supplier "
                + "(SupplierID,SupplierName,S_Address,S_Phone)"
                + "values ('"
                + b.getSupplierID() + "',N'"
                + b.getSupplierName() + "','"
                + b.getS_Address() + "','"
                + b.getS_Phone() + "') ";
        da.updateDB(sql);
    }

    public void update(int i, Supplier b) {
        supplierList.set(i, b);
        String sql = "update supplier set "
                + " supplierName = N'" + b.getSupplierName()
                + "', S_Address ='" + b.getS_Address() + "',"
                + " S_Phone ='" + b.getS_Phone() + "' "
                + " where supplierID='" + b.getSupplierID() + "'";
        da.updateDB(sql);
    }

    public int delete(String supplierID) {
        String sql = "delete from supplier where supplierID ='" + supplierID + "'";
        da.updateDB(sql);
        for (Supplier b : supplierList) {
            if (b.getSupplierID().equals(supplierID)) {
                int i = supplierList.indexOf(b);
                supplierList.remove(b);
                return i;
            }
        }
        return -1;
    }

    public Supplier getSupplier(int i) {
        return supplierList.get(i);
    }

}
