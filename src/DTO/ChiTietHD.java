/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author ADMIN
 */
public class ChiTietHD {

    public ChiTietHD(int maHoaDon, int maMonAn, String tenMonAn, float giaBan, int soLuong, float thanhTien) {
        this.maHoaDon = maHoaDon;
        this.maMonAn = maMonAn;
        this.tenMonAn = tenMonAn;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
    }

    public ChiTietHD() {
    }

    public float getThanhTien() {
        return thanhTien;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public void setMaMonAn(int maMonAn) {
        this.maMonAn = maMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public void setGiaBan(float giaBan) {
        this.giaBan = giaBan;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public int getMaMonAn() {
        return maMonAn;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public float getGiaBan() {
        return giaBan;
    }

    public int getSoLuong() {
        return soLuong;
    }
   public int maHoaDon;
   public int maMonAn;
   public String tenMonAn;
   public float giaBan;
   public int soLuong;
   public float thanhTien;
}
