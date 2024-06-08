/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Dung Dinh
 */
public class Managers {
    private int ManagerID;
    private String ManagerName, Email, Password, RoleID;

    public Managers() {
    }

    public Managers(int ManagerID, String ManagerName, String Email, String Password, String RoleID) {
        this.ManagerID = ManagerID;
        this.ManagerName = ManagerName;
        this.Email = Email;
        this.Password = Password;
        this.RoleID = RoleID;
    }

    public int getManagerID() {
        return ManagerID;
    }

    public void setManagerID(int ManagerID) {
        this.ManagerID = ManagerID;
    }

    public String getManagerName() {
        return ManagerName;
    }

    public void setManagerName(String ManagerName) {
        this.ManagerName = ManagerName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getRoleID() {
        return RoleID;
    }

    public void setRoleID(String RoleID) {
        this.RoleID = RoleID;
    }

    @Override
    public String toString() {
        return "Managers{" + "ManagerID=" + ManagerID + ", ManagerName=" + ManagerName + ", Email=" + Email + ", Password=" + Password + ", RoleID=" + RoleID + '}';
    }
    
}
