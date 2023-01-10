/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class Menu {

    public Menu(){
        
    }
    
   public String maHoaDon;
   public String maMonAn;
   public String tenMonAn;
   public float giaBan;
   public int soLuong;
   public float thanhTien;
    public int maHD;
    public int maBanAn;
    public int maNhanVien;
    public Date ngayXuatHoaDon;
    public float tongTien;

    public Menu(int maHD, int maBanAn, int maNhanVien, Date ngayXuatHoaDon, float tongTien, String maHoaDon, String maMonAn, String tenMonAn, float giaBan, int soLuong, float thanhTien) {
        this.maHD = maHD;
        this.maBanAn = maBanAn;
        this.maNhanVien = maNhanVien;
        this.ngayXuatHoaDon = ngayXuatHoaDon;
        this.tongTien = tongTien;
        this.maHoaDon = maHoaDon;
        this.maMonAn = maMonAn;
        this.tenMonAn = tenMonAn;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
    }

    public int getMaHD() {
        return maHD;
    }

    public int getMaBanAn() {
        return maBanAn;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public Date getNgayXuatHoaDon() {
        return ngayXuatHoaDon;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public void setMaBanAn(int maBanAn) {
        this.maBanAn = maBanAn;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public void setNgayXuatHoaDon(Date ngayXuatHoaDon) {
        this.ngayXuatHoaDon = ngayXuatHoaDon;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }
    

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public void setMaMonAn(String maMonAn) {
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

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public String getMaMonAn() {
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

    public float getThanhTien() {
        return thanhTien;
    }
}
