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
public class Author {
    private String authorID;
    private String authorName;

    public Author(String authorID, String authorName) {
        this.authorID = authorID;
        this.authorName = authorName;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public Vector toVector(){
        Vector v = new Vector();
        v.addElement(this.authorID);
        v.addElement(this.authorName);
        return v;
    }
}
