/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

public class Slider {
    private int silderID;
    private String silderImg, tile;
    private boolean status;
    private String note;

    public Slider() {
    }
    

    public Slider(int silderID, String silderImg, String tile, boolean status) {
        this.silderID = silderID;
        this.silderImg = silderImg;
        this.tile = tile;
        this.status = status;
    }

    public Slider(int silderID, String silderImg, String tile, boolean status, String note) {
        this.silderID = silderID;
        this.silderImg = silderImg;
        this.tile = tile;
        this.status = status;
        this.note = note;
    }

    public int getSilderID() {
        return silderID;
    }

    public void setSilderID(int silderID) {
        this.silderID = silderID;
    }

    public String getSilderImg() {
        return silderImg;
    }

    public void setSilderImg(String silderImg) {
        this.silderImg = silderImg;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Slider{" + "silderID=" + silderID + ", silderImg=" + silderImg + ", tile=" + tile + ", status=" + status + ", note=" + note + '}';
    }
    
}

