/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author ADMIN
 */
public class ThucDon {

    public ThucDon() {
        
    }

    private int maMonAn;
    private String tenMonAn;
    private int maLoaiMon;
    private String donVi;
    private float giaTien;
    private int soLuong;
    private String imageMonAn;

    public ThucDon(int maMonAn, String tenMonAn, int maLoaiMon, String donVi, float giaTien, int soLuong, String imageMonAn) {
        this.maMonAn = maMonAn;
        this.tenMonAn = tenMonAn;
        this.maLoaiMon = maLoaiMon;
        this.donVi = donVi;
        this.giaTien = giaTien;
        this.soLuong = soLuong;
        this.imageMonAn = imageMonAn;
    }

    public int getMaMonAn() {
        return maMonAn;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public int getMaLoaiMon() {
        return maLoaiMon;
    }

    public String getDonVi() {
        return donVi;
    }

    public float getGiaTien() {
        return giaTien;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public String getImageMonAn() {
        return imageMonAn;
    }

    public void setMaMonAn(int maMonAn) {
        this.maMonAn = maMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public void setMaLoaiMon(int maLoaiMon) {
        this.maLoaiMon = maLoaiMon;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public void setGiaTien(float giaTien) {
        this.giaTien = giaTien;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setImageMonAn(String imageMonAn) {
        this.imageMonAn = imageMonAn;
    }

    
}
