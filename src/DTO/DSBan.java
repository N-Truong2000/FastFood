/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author ADMIN
 */
public class DSBan {

    public int maBan;
    public String tenBan;
    public String trangThai;
    
    public void DSBan(int maBan, String tenBan, String trangThai){
        this.maBan = maBan;
        this.tenBan = tenBan;
        this.trangThai = trangThai;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getMaBan() {
        return maBan;
    }

    public String getTenBan() {
        return tenBan;
    }

    public String getTrangThai() {
        return trangThai;
    }
    
}
