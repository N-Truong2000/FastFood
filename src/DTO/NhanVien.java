/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author ADMIN
 */
public class NhanVien {
    
    public NhanVien(){
        
    }
    
    private int maNV;
    private String tenNV;
    private String ngaySinh;
    private String gioiTinh;
    private String soDT;
    private String diaChi;
    private String chucVu;
    private String matKhau;
    private String tenDangNhap;
    private String imageNV;

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public void setImageNV(String imageNV) {
        this.imageNV = imageNV;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public int getMaNV() {
        return maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public String getSoDT() {
        return soDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getChucVu() {
        return chucVu;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public String getImageNV() {
        return imageNV;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public NhanVien(int maNV, String tenNV, String ngaySinh, String gioiTinh, String soDT, String diaChi, String chucVu,
             String matKhau, String imageNV, String tenDangNhap) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.soDT = soDT;
        this.diaChi = diaChi;
        this.chucVu = chucVu;
        this.matKhau = matKhau;
        this.imageNV = imageNV;
        this.tenDangNhap = tenDangNhap;
    }
}
