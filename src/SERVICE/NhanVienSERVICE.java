/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SERVICE;

import DAO.NhanVienDAO;
import DTO.NhanVien;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class NhanVienSERVICE {
    
    private NhanVienDAO nhanVienDAO;
    
    public NhanVienSERVICE() {
        nhanVienDAO = new NhanVienDAO();
    }
    
    public List<NhanVien> getAllNhanVien(){
            return nhanVienDAO.getAllNhanVien();
    }
    
    public NhanVien getNhanVienbyMaNV(int maNV) throws SQLException{
            return nhanVienDAO.getNhanVienbyMaNV(maNV);
    }
    
    public void themNhanVien(NhanVien nhanVien) {
        nhanVienDAO.themNhanVien(nhanVien);
    }
    
    public void suaNhanVien(NhanVien nhanVien) {
        nhanVienDAO.suaNhanVien(nhanVien);
    }
    
    public void suaNhanVien2(NhanVien nhanVien) {
        nhanVienDAO.suaNhanVien2(nhanVien);
    }
    
    public void xoaNhanVien(String maNV) {
        nhanVienDAO.xoaNhanVien(maNV);
    }
    
}
