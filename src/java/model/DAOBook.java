/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.Book;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Logger;
import java.util.logging.Level;
/**
 *
 * @author Dung Dinh
 */
public class DAOBook extends DBConnect {
    private static final Logger LOG = Logger.getLogger(DAOBook.class.getName());
    public Vector<Book> get(Book bk, String sql) {
        Vector<Book> vector = new Vector<Book>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                
            }
        }catch(SQLException ex){
            Logger.getLogger(DAOBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
}
