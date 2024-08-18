/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Dung Dinh
 */
public class Author {
    private int AuthorID;
    private String AuthorName;

    public Author() {
    }

    public Author(int AuthorID, String AuthorName) {
        this.AuthorID = AuthorID;
        this.AuthorName = AuthorName;
    }

    public int getAuthorID() {
        return AuthorID;
    }

    public void setAuthorID(int AuthorID) {
        this.AuthorID = AuthorID;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public void setAuthorName(String AuthorName) {
        this.AuthorName = AuthorName;
    }

    @Override
    public String toString() {
        return "Author{" + "AuthorID=" + AuthorID + ", AuthorName=" + AuthorName + '}';
    }
    
}
