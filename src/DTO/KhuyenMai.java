/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author ASUS
 */
public class KhuyenMai {

    public KhuyenMai(int maKhuyenMai, String tenKhuyenMai, float giaKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
        this.tenKhuyenMai = tenKhuyenMai;
        this.giaKhuyenMai = giaKhuyenMai;
    }

    public KhuyenMai() {
        
    }

    public int getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public float getGiaKhuyenMai() {
        return giaKhuyenMai;
    }

    public void setMaKhuyenMai(int maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        this.tenKhuyenMai = tenKhuyenMai;
    }

    public void setGiaKhuyenMai(float giaKhuyenMai) {
        this.giaKhuyenMai = giaKhuyenMai;
    }
    
    public int maKhuyenMai;
    public String tenKhuyenMai;
    public float giaKhuyenMai;

}
