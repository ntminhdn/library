/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.util.Vector;

/**
 *
 * @author PhiLong
 */
public class Admin {
    private String AdID;
    private String AdName;
    private String AdUser;
    private String AdPass;
    private String Sex;
    private String Role;
    private String Email;

    public Admin(String AdID, String AdName, String AdUser, String AdPass, String Sex, String Role, String Email) {
        this.AdID = AdID;
        this.AdName = AdName;
        this.AdUser = AdUser;
        this.AdPass = AdPass;
        this.Sex = Sex;
        this.Role = Role;
        this.Email = Email;
    }

    public String getAdID() {
        return AdID;
    }

    public void setAdID(String AdID) {
        this.AdID = AdID;
    }

    public String getAdName() {
        return AdName;
    }

    public void setAdName(String AdName) {
        this.AdName = AdName;
    }

    public String getAdUser() {
        return AdUser;
    }

    public void setAdUser(String AdUser) {
        this.AdUser = AdUser;
    }

    public String getAdPass() {
        return AdPass;
    }

    public void setAdPass(String AdPass) {
        this.AdPass = AdPass;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    public Vector toVector(){
        Vector v = new Vector();
        v.addElement(this.AdID);
        v.addElement(this.AdName);
        v.addElement(this.AdUser);
        v.addElement(this.AdPass);
        v.addElement(this.Sex);
        v.addElement(this.Role);
        v.addElement(this.Email);
        return v;
    }
    
}
