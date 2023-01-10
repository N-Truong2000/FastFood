/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SERVICE;

import DAO.DanhMucDAO;
import DTO.DanhMuc;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class DanhMucSERVICE {
    
    private DanhMucDAO danhMucDAO;
    
    public DanhMucSERVICE() {
        danhMucDAO = new DanhMucDAO();
    }
    
    public List<DanhMuc> getAllDanhMuc(){
            return danhMucDAO.getAllDanhMuc();
    }
    
    public void themDanhMuc(DanhMuc danhMuc) {
        danhMucDAO.themDanhMuc(danhMuc);
    }
    
    public void suaDanhMuc(DanhMuc danhMuc) {
        danhMucDAO.suaDanhMuc(danhMuc);
    }
    
    public void xoaDanhMuc(String maDM) {
        danhMucDAO.xoaDanhMuc(maDM);
    }
    
}
