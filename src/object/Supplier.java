/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.util.Vector;

/**
 *
 * @supplier Administrator
 */
public class Supplier {

    private String supplierID;
    private String supplierName;
    private String s_Address;
    private String s_Phone;

    public Supplier(String supplierID, String supplierName, String s_Address, String s_Phone) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.s_Address = s_Address;
        this.s_Phone = s_Phone;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getS_Address() {
        return s_Address;
    }

    public void setS_Address(String s_Address) {
        this.s_Address = s_Address;
    }

    public String getS_Phone() {
        return s_Phone;
    }

    public void setS_Phone(String s_Phone) {
        this.s_Phone = s_Phone;
    }

    public Vector toVector() {
        Vector v = new Vector();
        v.addElement(this.supplierID);
        v.addElement(this.supplierName);
        v.addElement(this.s_Address);
        v.addElement(this.s_Phone);
        return v;
    }
}
