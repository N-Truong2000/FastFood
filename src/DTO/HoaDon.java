/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class HoaDon {
    
    public HoaDon(){
        
    }

    public int maHD;
    public int maBanAn;
    public int maNhanVien;
    public Date ngayXuatHoaDon;
    public float tongTien;

    public HoaDon(int maHD, int maBanAn, int maNhanVien, Date ngayXuatHoaDon, float tongTien) {
        this.maHD = maHD;
        this.maBanAn = maBanAn;
        this.maNhanVien = maNhanVien;
        this.ngayXuatHoaDon = ngayXuatHoaDon;
        this.tongTien = tongTien;
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
    
}
