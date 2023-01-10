/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SERVICE;

import DAO.ChiTietHDDAO;
import DAO.HoaDonDAO;
import DTO.ChiTietHD;
import DTO.HoaDon;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class HoaDonSERVICE {
    
    private HoaDonDAO hoaDonDAO;
    private ChiTietHDDAO chiTietHDDAO;
    
    
    public HoaDonSERVICE() {
        hoaDonDAO = new HoaDonDAO();
        chiTietHDDAO = new ChiTietHDDAO();
    }
    
    
    public void themCTHoaDon(ChiTietHD chiTietHD) {
        chiTietHDDAO.themCTHoaDon(chiTietHD);
    }
    
    public void themHoaDon(HoaDon hoaDon) {
        hoaDonDAO.themHoaDon(hoaDon);
    }
    
    public List<HoaDon> getHoaDonByMaBan(String maBanAn){
            return hoaDonDAO.getHoaDonByMaBan(maBanAn);
    }
    
    public List<ChiTietHD> getAllCTHD(){
            return chiTietHDDAO.getAllCTHD();
    }
    
    public List<HoaDon> getAllLSHD(){
            return hoaDonDAO.getAllLSHD();
    }
}
