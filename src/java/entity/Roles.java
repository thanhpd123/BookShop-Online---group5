/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Dung Dinh
 */
public class Roles {
    private String RoleID, RoleName;

    public Roles() {
    }

    public Roles(String RoleID, String RoleName) {
        this.RoleID = RoleID;
        this.RoleName = RoleName;
    }

    public String getRoleID() {
        return RoleID;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleID(String RoleID) {
        this.RoleID = RoleID;
    }

    public void setRoleName(String RoleName) {
        this.RoleName = RoleName;
    }

    @Override
    public String toString() {
        return "Roles{" + "RoleID=" + RoleID + ", RoleName=" + RoleName + '}';
    }
    
    
}
