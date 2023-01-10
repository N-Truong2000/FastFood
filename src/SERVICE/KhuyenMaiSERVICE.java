/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SERVICE;

import DAO.KhuyenMaiDAO;
import DTO.KhuyenMai;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class KhuyenMaiSERVICE {
    
    private KhuyenMaiDAO khuyenMaiDAO;
    
    public KhuyenMaiSERVICE() {
        khuyenMaiDAO = new KhuyenMaiDAO();
    }
    
    public List<KhuyenMai> getAllDanhMuc(){
            return khuyenMaiDAO.getAllKM();
    }
    
    public void themKhuyenMai(KhuyenMai khuyenMai) {
        khuyenMaiDAO.themKhuyenMai(khuyenMai);
    }
    
    public void suaKhuyenMai(KhuyenMai khuyenMai) {
        khuyenMaiDAO.suaKhuyenMai(khuyenMai);
    }
    
    public void xoaKhuyenMai(String maKM) {
        khuyenMaiDAO.xoaKhuyenMai(maKM);
    }
}
