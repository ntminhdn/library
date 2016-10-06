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
public class Publisher {
    private String PublisherID;
    private String PublisherName;

    public Publisher(String PublisherID, String PublisherName) {
        this.PublisherID = PublisherID;
        this.PublisherName = PublisherName;
    }

    public String getPublisherID() {
        return PublisherID;
    }

    public void setPublisherID(String PublisherID) {
        this.PublisherID = PublisherID;
    }

    public String getPublisherName() {
        return PublisherName;
    }

    public void setPublisherName(String PublisherName) {
        this.PublisherName = PublisherName;
    }
    public Vector toVector(){
        Vector v = new Vector();
        v.addElement(this.PublisherID);
        v.addElement(this.PublisherName);
        return v;
    }
}
