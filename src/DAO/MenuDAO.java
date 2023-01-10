/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.HoaDon;
import DTO.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class MenuDAO {

    private static MenuDAO instance;

    public MenuDAO() {
    }
    
    
    public int getOneDocgia(String tenBan) {
        String sql = "SELECT tenMonAn, giaBan, soLuong,thanhTien FROM ChiTietHD ct, HoaDonTT hd, BanAn ba WHERE maBanAn = maBan AND maHoaDon = maHD AND hd.trangThaiTT = N'Chưa thanh toán' AND tenBan = ?";
        try {
            PreparedStatement stm = ConnectSQL.getConnection().prepareStatement(sql);
            ResultSet oneDocgia = null;
            stm.setString(1, tenBan);
            oneDocgia = stm.executeQuery();
            
            while(oneDocgia.next()) {
                return oneDocgia.getInt(1);
            }
          //  return listAlldg;
        } catch (SQLException ex) {
        }
        return 0;
    }

    public List<Menu> getBill(String maPhieuMuon) {
        List<Menu> ctpmList = new ArrayList<Menu>();
        
        Connection connection = ConnectSQL.getConnection();
        
        String sql = "SELECT tenMonAn, giaBan, soLuong,thanhTien FROM ChiTietHD ct, HoaDonTT hd, BanAn ba WHERE maBanAn = maBan AND maHoaDon = maHD AND hd.trangThaiTT = N'Chưa thanh toán' AND tenBan = ?";
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Menu ctpm = new Menu();

                ctpm.setTenMonAn(rs.getString("tenMonAn"));
                ctpm.setGiaBan(rs.getFloat("giaBan"));
                ctpm.setSoLuong(rs.getInt("soLuong"));
                ctpm.setThanhTien(rs.getFloat("thanhTien"));
                
                ctpmList.add(ctpm);
            }
        } catch (SQLException e) {
        }

        return ctpmList;
    }
    
    public int GetMaxMaHoaDon() {
        Connection con = ConnectSQL.getConnection();
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT MAX(maHoaDon) FROM HoaDon");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 1;
    }
    
}
