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
    private String AuthorID, AuthorName;

    public Author() {
    }

    public Author(String AuthorID, String AuthorName) {
        this.AuthorID = AuthorID;
        this.AuthorName = AuthorName;
    }

    public String getAuthorID() {
        return AuthorID;
    }

    public void setAuthorID(String AuthorID) {
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
